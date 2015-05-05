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
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;


public class StatDistributionActivity extends ActionBarActivity {

    private static final String TAG = "DnDApp";
    ArrayList<EditText> editTexts;
    Button[] buttons;
    String[] standardArray;
    Character character;
    Button nextPage;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_distribution);

        nextPage = (Button) findViewById(R.id.stats_next_page_button);
        dbHandler = new DBHandler(this);

        String charString = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            charString = extras.getString("character");
        }
        character = new Gson().fromJson(charString, Character.class);
        TextView displayCharacter = (TextView) findViewById(R.id.char_display_row_stats_page);
        displayCharacter.setText(character.toString());

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

        //for(EditText et: editTexts){
        //    stats.add(Integer.parseInt(et.getText().toString()));
        //}

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
                Toast.makeText(v.getContext(), character.getName() + " : " + character.get_race(), Toast.LENGTH_LONG).show();
                for (EditText et : editTexts) {
                    character.getStatsArray().add(Integer.parseInt(et.getText().toString()));
                }
                Log.i(TAG, character.get_race().toString());
                dbHandler.updateCharacter(character);
                //printDatabase();
                Intent intent = new Intent(StatDistributionActivity.this, NewCharacterSpells.class);
                intent.putExtra("character", new Gson().toJson(character));
                startActivity(intent);
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
        toast("Printing");
        String dbString = dbHandler.dbToList().toString();
        toast(dbString);
    }

    public void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
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
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}