package com.project.senior.dndapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;


public class SavedCharacterActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_character);

        DBHandler dbHandler = new DBHandler(this);
        List<Character> characters = dbHandler.dbToList();
        ListView listView = (ListView) findViewById(R.id.character_list_view);
        CharacterAdapter charAdapter = new CharacterAdapter(this, characters);
        listView.setAdapter(charAdapter);

    }

}
