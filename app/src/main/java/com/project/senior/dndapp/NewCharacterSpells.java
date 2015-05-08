package com.project.senior.dndapp;

import android.content.res.AssetManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewCharacterSpells extends ActionBarActivity {

    private static final String TAG = "DnDApp";
    AssetManager assets;
    BufferedReader reader;
    Gson gson;
    List<Spell> spells;
    List<String> classSpells;
    TextView numSpells;
    ListView cantripView;
    ListView spellView;
    Character character;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_character_spells);

        numSpells = (TextView) findViewById(R.id.num_spells);
        cantripView = (ListView) findViewById(R.id.available_cantrips);
        spellView = (ListView) findViewById(R.id.available_spells);

        String charString = "";
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            charString = extras.getString("character");
        }
        character = new Gson().fromJson(charString, Character.class);

        numSpells.setText("You may choose " + character.getPlayerClass().getKnownSpells()[1] + " cantrips, and\n"
                + character.getPlayerClass().getRecommended()[0] + " spells.");
        TextView test = (TextView) findViewById(R.id.test_char_xfer);
        test.setText(character.toString());

        assets = this.getAssets();
        try {
            reader = new BufferedReader(new InputStreamReader(assets.open("spellbook.json")));
        }catch(Exception e){
            e.printStackTrace(System.err);
        }
        gson = new Gson();
        spells = gson.fromJson(reader, new TypeToken<List<Spell>>() {}.getType());
        switch (character.getPlayerClass().getClassName()) {
            case "Bard":
                classSpells = Arrays.asList(this.getResources().getStringArray(R.array.bardspells));
                break;
            case "Cleric":
                classSpells = Arrays.asList(this.getResources().getStringArray(R.array.clericspells));
                break;
            case "Druid":
                classSpells = Arrays.asList(this.getResources().getStringArray(R.array.druidspells));
                break;
            case "Paladin":
                classSpells = Arrays.asList(this.getResources().getStringArray(R.array.paladinspells));
                break;
            case "Ranger":
                classSpells = Arrays.asList(this.getResources().getStringArray(R.array.rangerspells));
                break;
            case "Sorcerer":
                classSpells = Arrays.asList(this.getResources().getStringArray(R.array.sorcererspells));
                break;
            case "Warlock":
                classSpells = Arrays.asList(this.getResources().getStringArray(R.array.warlockspells));
                break;
            case "Wizard":
                classSpells = Arrays.asList(this.getResources().getStringArray(R.array.wizardspells));
                break;
            default:
                classSpells = new ArrayList<>();
        }
        List<Spell> cantrips = new ArrayList<>();
        List<Spell> oneSpells = new ArrayList<>();

        for(Spell spell : spells){
            if(spell.getLevel() == 0 && classSpells.contains(spell.getName())){
                cantrips.add(spell);
            }
            else if(spell.getLevel() == 1 && classSpells.contains(spell.getName())){
                oneSpells.add(spell);
            }
        }

        SpellConfirmAdapter cantripAdapter = new SpellConfirmAdapter(this, cantrips);
        cantripView.setAdapter(cantripAdapter);
        cantripView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Spell spell = (Spell) parent.getAdapter().getItem(position);

                TextView tview = (TextView) view.findViewById(R.id.new_char_spell_row_description);
                if (tview.getText().equals("")) {
                    tview.setText(spell.getDescription());
                } else {
                    tview.setText("");
                }
            }
        });

        SpellConfirmAdapter oneSpellAdapter = new SpellConfirmAdapter(this, oneSpells);
        spellView.setAdapter(oneSpellAdapter);
        spellView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "Clicky: " + parent.getAdapter().getItem(position));
                Spell spell = (Spell) parent.getAdapter().getItem(position);

                TextView tview = (TextView) view.findViewById(R.id.new_char_spell_row_description);
                if (tview.getText().equals("")) {
                    tview.setText(spell.getDescription());
                } else {
                    tview.setText("");
                }
            }
        });
    }
}
