package com.project.senior.dndapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;


public class SavedCreatureActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_creature);

        DBHandler dbHandler = new DBHandler(this);
        List<Creature> creatures = dbHandler.dbToCreatureList();
        ListView listView = (ListView) findViewById(R.id.creature_list_view);
        CreatureAdapter creatureAdapter = new CreatureAdapter(this, creatures);
        listView.setAdapter(creatureAdapter);
    }

}
