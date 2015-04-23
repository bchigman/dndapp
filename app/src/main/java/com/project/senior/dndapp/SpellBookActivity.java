package com.project.senior.dndapp;

import android.content.res.AssetManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
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
            List<Spell> spells = gson.fromJson(reader, new TypeToken<List<Spell>>(){}.getType());
            ListAdapter listAdapter = new spellAdapter(this, spells);
            ListView listView = (ListView) findViewById(R.id.spellbook_listView);
            listView.setAdapter(listAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Spell spell = (Spell) parent.getAdapter().getItem(position);

                    //Toast.makeText(SpellBookActivity.this, spell.getDescription(), Toast.LENGTH_LONG).show();
                    TextView tview = (TextView) view.findViewById(R.id.spell_row_description);
                    if (tview.getText().equals(""))
                        tview.setText(spell.getDescription());
                    else
                        tview.setText("");
                }
            });
            //TextView spellbookText = (TextView) findViewById(R.id.spellbook_text);
            //spellbookText.setText(spells.toString() + spells.size());
        }catch (Exception e){
            e.printStackTrace(System.err);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_spell_book, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
