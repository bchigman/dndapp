package com.project.senior.dndapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class StatDistributionActivity extends ActionBarActivity {

    private static final String TAG = "DnDApp";
    ArrayList<EditText> editTexts;
    Button[] buttons;
    String[] standardArray;
    Character character;
    Button nextPage;
    DBHandler dbHandler;
    RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_distribution);

        nextPage = (Button) findViewById(R.id.stats_next_page_button);
        dbHandler = new DBHandler(this);
        group = (RadioGroup) findViewById(R.id.stat_radio_grp);
        List<Character> charList = dbHandler.dbToList();

        String charString = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            charString = extras.getString("character");
        }
        character = new Gson().fromJson(charString, Character.class);
        character.set_id(charList.get(charList.size() - 1).get_id());
        TextView displayCharacter = (TextView) findViewById(R.id.char_display_row_stats_page);
        displayCharacter.setText(character.toString() +
                " \n\nPrimary Stat: " + character.getPlayerClass().getPrimaryAbility() +
                " \nSaving Throws: " + character.getPlayerClass().getSavingThrows());

        standardArray = new String[]{"15", "14", "13", "12", "10", "8"};
        buttons = new Button[]{(Button) findViewById(R.id.button1), (Button) findViewById(R.id.button2), (Button) findViewById(R.id.button3),
                (Button) findViewById(R.id.button4), (Button) findViewById(R.id.button5), (Button) findViewById(R.id.button6)};
        editTexts = new ArrayList<>();
        editTexts.add((EditText) findViewById(R.id.strength_val));
        editTexts.add((EditText) findViewById(R.id.dexterity_val));
        editTexts.add((EditText) findViewById(R.id.constitution_val));
        editTexts.add((EditText) findViewById(R.id.intelligence_val));
        editTexts.add((EditText) findViewById(R.id.wisdom_val));
        editTexts.add((EditText) findViewById(R.id.charisma_val));

        CheckBox standardArrayCheck = (CheckBox) findViewById(R.id.standard_array_check);
        standardArrayCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox check = (CheckBox) v;
                if (!check.isChecked()) {
                    for (int i = 0; i < 6; i++) {
                        buttons[i].setText(randomizeStatButton());
                    }
                } else {
                    for (int i = 0; i < 6; i++) {
                        buttons[i].setText(standardArray[i]);
                    }
                }
            }
        });

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                List<Integer> buttonVals = new ArrayList<>();
                for (Button b : buttons) {
                    b.setEnabled(false);
                    buttonVals.add(Integer.parseInt(b.getText().toString()));
                }

                if(group.getCheckedRadioButtonId() == R.id.reccomend_radio) {
                    List<Integer> recommended = Arrays.asList(character.getPlayerClass().getRecommended());
                    for (int i = 0; i < editTexts.size(); i++) {
                        editTexts.get(i).setText(""+recommended.get(i));
                    }

                }
                else if(group.getCheckedRadioButtonId() == R.id.random_radio){
                    Collections.shuffle(buttonVals);
                    for (int i = 0; i < editTexts.size(); i++) {
                        editTexts.get(i).setText("" + buttonVals.get(i));
                    }
                }
                else{
                    for(Button b : buttons){
                        b.setEnabled(true);
                    }
                    for(EditText et : editTexts){
                        et.setText("");
                    }
                    Log.i(TAG, "Operating as normal.");
                 }
            }
        });

        Button clearButton = (Button) findViewById(R.id.clear_stats_button);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (EditText et : editTexts) {
                    et.setText("");
                }
                for (Button button : buttons) {
                    button.setEnabled(true);
                }
            }
        });

        View.OnClickListener clickListener = new View.OnClickListener() {
            public void onClick(View v) {
                Button button = (Button) v;

                for (EditText et : editTexts) {
                    if (et.getText().toString().equals("")) {
                        et.setText(button.getText());
                        button.setEnabled(false);
                        Log.i(TAG, "Should set text");
                        break;
                    }
                }
            }
        };

        for (int i = 0; i < 6; i++) {
            buttons[i].setOnClickListener(clickListener);
        }

        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (EditText et : editTexts) {
                    character.getStatsArray().add(Integer.parseInt(et.getText().toString()));
                }
                Log.i(TAG, character.get_race().toString());
                dbHandler.updateCharacter(character);
                Log.i(TAG, ""+!character.getPlayerClass().getClassName().equals("Barbarian"));
                if(!(character.getPlayerClass().getClassName().equals("Barbarian") || character.getPlayerClass().getClassName().equals("Fighter")
                        || character.getPlayerClass().getClassName().equals("Rogue") || character.getPlayerClass().getClassName().equals("Monk"))) {
                    Intent intent = new Intent(StatDistributionActivity.this, NewCharacterSpells.class);
                    intent.putExtra("character", new Gson().toJson(character));
                    startActivity(intent);
                }
                finish();
            }
        });
    }

    public String randomizeStatButton() {
        int[] rolls = new int[4];
        for (int i = 0; i < 4; i++) {
            rolls[i] = (int) (Math.random() * 6 + 1);
        }
        Arrays.sort(rolls);
        return Integer.toString(rolls[1] + rolls[2] + rolls[3]);
    }

    public void printDatabase() {
        String dbString = dbHandler.dbToList().toString();
        toast(dbString);
    }

    public void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    public int getMax(List<Integer> ints){
        int max = 0;
        for(Integer i : ints){
            if(i > max){
                max = i;
            }
        }
        return max;
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Activity")
                .setMessage("Are you sure you want to cancel character creation?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHandler.deleteCharacter(character.get_id());
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}