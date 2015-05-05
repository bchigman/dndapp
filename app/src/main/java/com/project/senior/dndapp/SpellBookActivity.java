package com.project.senior.dndapp;

import android.content.res.AssetManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;


public class SpellBookActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_book);

        Spinner classSpinner = (Spinner) findViewById(R.id.spell_sort_spinner);
        ArrayAdapter<CharSequence> classAdapter = ArrayAdapter.createFromResource(this, R.array.class_list, android.R.layout.simple_spinner_item);
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classSpinner.setAdapter(classAdapter);

        try{
            AssetManager assets = this.getAssets();
            BufferedReader reader = new BufferedReader(new InputStreamReader(assets.open("spellbook.json")));
            Gson gson = new Gson();
            final List<Spell> spells = gson.fromJson(reader, new TypeToken<List<Spell>>(){}.getType());
            SpellAdapter listAdapter = new SpellAdapter(this, spells);
            ListView listView = (ListView) findViewById(R.id.spellbook_listView);
            listView.setAdapter(listAdapter);
            EditText searchText = (EditText) findViewById(R.id.spellbook_searchbar);
            searchText.addTextChangedListener(new SpellbookTextWatcher(this, listAdapter, spells));
            classSpinner.setOnItemSelectedListener(new ClassSpinnerListener(this, listAdapter, spells));

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Spell spell = (Spell) parent.getAdapter().getItem(position);

                    TextView tview = (TextView) view.findViewById(R.id.spell_row_description);
                    if (tview.getText().equals("")) {
                        tview.setText(spell.getDescription());
                    } else {
                        tview.setText("");
                    }
                }
            });

        }catch (Exception e){
            e.printStackTrace(System.err);
        }

    }

}
