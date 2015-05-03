package com.project.senior.dndapp;

import android.content.Context;
import android.content.res.AssetManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * For the lulz
 * Created by Ben on 4/30/2015.
 */
public class ClassSpinnerListener implements AdapterView.OnItemSelectedListener {

    private Context context;
    private SpellAdapter spellAdapter;
    private List<Spell> resetList;
    private List<Spell> newSpells;
    private List<Spell> currentSpells;

    public ClassSpinnerListener(Context context, SpellAdapter sa, List<Spell> spells){
        this.spellAdapter = sa;
        this.context = context;
        this.newSpells = new ArrayList<>();
        this.currentSpells = spells;
        try {
            AssetManager assets = this.context.getAssets();
            BufferedReader reader = new BufferedReader(new InputStreamReader(assets.open("spellbook.json")));
            Gson gson = new Gson();
            this.resetList = gson.fromJson(reader, new TypeToken<List<Spell>>() {}.getType());
        }catch(Exception e){
            e.printStackTrace(System.err);
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView spinner = (TextView) view;
        List<String> classSpells;

        switch (spinner.getText().toString()) {
            case "Bard":
                classSpells = Arrays.asList(parent.getContext().getResources().getStringArray(R.array.bardspells));
                break;
            case "Cleric":
                classSpells = Arrays.asList(parent.getContext().getResources().getStringArray(R.array.clericspells));
                break;
            case "Druid":
                classSpells = Arrays.asList(parent.getContext().getResources().getStringArray(R.array.druidspells));
                break;
            case "Paladin":
                classSpells = Arrays.asList(parent.getContext().getResources().getStringArray(R.array.paladinspells));
                break;
            case "Ranger":
                classSpells = Arrays.asList(parent.getContext().getResources().getStringArray(R.array.rangerspells));
                break;
            case "Sorcerer":
                classSpells = Arrays.asList(parent.getContext().getResources().getStringArray(R.array.sorcererspells));
                break;
            case "Warlock":
                classSpells = Arrays.asList(parent.getContext().getResources().getStringArray(R.array.warlockspells));
                break;
            case "Wizard":
                classSpells = Arrays.asList(parent.getContext().getResources().getStringArray(R.array.wizardspells));
                break;
            default:
                classSpells = new ArrayList<>();
                this.spellAdapter.clearAdapter();
                this.spellAdapter.addAll(this.resetList);
                //Toast.makeText(parent.getContext(), "nada", Toast.LENGTH_LONG).show();
        }

        if(classSpells.size() > 0) {
            for (int i = 0; i < this.resetList.size(); i++) {
                for (String name : classSpells) {
                    if (name.equals(this.resetList.get(i).getName())) {
                        this.newSpells.add(this.resetList.get(i));
                    }
                }
            }

            spellAdapter.clearAdapter();
            spellAdapter.addAll(newSpells);
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        this.spellAdapter.clearAdapter();
        this.spellAdapter.addAll(this.resetList);
        Toast.makeText(parent.getContext(), "nada", Toast.LENGTH_LONG).show();
    }

    public SpellAdapter getSpellAdapter() {
        return spellAdapter;
    }

    public void setSpellAdapter(SpellAdapter spellAdapter) {
        this.spellAdapter = spellAdapter;
    }

}
