package com.project.senior.dndapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Character character = (Character) parent.getAdapter().getItem(position);

                TextView tview = (TextView) view.findViewById(R.id.character_row_description);
                if (tview.getText().equals("")) {
                    tview.setText("STR: " + character.getStatsArray().get(0) + "\n" +
                            "DEX: " + character.getStatsArray().get(1) + "\n" +
                            "CON: " + character.getStatsArray().get(2) + "\n" +
                            "INT: " + character.getStatsArray().get(3) + "\n" +
                            "WIS: " + character.getStatsArray().get(4) + "\n" +
                            "CHA: " + character.getStatsArray().get(5) + "\n");
                } else {
                    tview.setText("");
                }
            }
        });

    }

}
