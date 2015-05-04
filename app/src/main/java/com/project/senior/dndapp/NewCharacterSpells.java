package com.project.senior.dndapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

public class NewCharacterSpells extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_character_spells);

        String charString = "";
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            charString = extras.getString("character");
        }
        Character character = new Gson().fromJson(charString, Character.class);

        TextView test = (TextView) findViewById(R.id.test_char_xfer);
        test.setText(character.toString() + "\n" + character.getStatsArray().toString());
    }


}
