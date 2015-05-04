package com.project.senior.dndapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;


public class NewCharActivity extends ActionBarActivity {

    private static final String TAG = "DnDApp";
    DBHandler dbHandler;
    EditText charName;
    Spinner classSpinner;
    Spinner raceSpinner;
    Button nextPage;
    LinearLayout subraceLayout;
    Spinner subSpinner;
    ArrayAdapter<CharSequence> subRaceAdapter;
    Character character;
    TextView descriptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_char);

        dbHandler = new DBHandler(this);
        charName = (EditText) findViewById(R.id.char_name);
        nextPage = (Button) findViewById(R.id.race_next_page_button);
        character = new Character();

        subraceLayout = (LinearLayout) findViewById(R.id.subrace_row);
        subraceLayout.setVisibility(View.INVISIBLE);

        classSpinner = (Spinner) findViewById(R.id.class_spinner);
        ArrayAdapter<CharSequence> classAdapter = ArrayAdapter.createFromResource(this, R.array.class_list, android.R.layout.simple_spinner_item);
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classSpinner.setAdapter(classAdapter);

        raceSpinner = (Spinner) findViewById(R.id.race_spinner);
        ArrayAdapter<CharSequence> raceAdapter = ArrayAdapter.createFromResource(this, R.array.race_list, android.R.layout.simple_spinner_item);
        raceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        raceSpinner.setAdapter(raceAdapter);
        raceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView spinner = (TextView) view;
                character.set_race(new PlayerRace(raceSpinner.getSelectedItem().toString()));
                subSpinner = (Spinner) findViewById(R.id.subrace_spinner);
                descriptor = (TextView) findViewById(R.id.subrace_descriptor);
                switch (spinner.getText().toString()) {
                    case "Dwarf":
                        subRaceAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.dwarf_race_list, android.R.layout.simple_spinner_item);
                        subSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(subSpinner.getSelectedItem().toString().equals("Hill Dwarf"))
                                    descriptor.setText(character.get_race().getBenefits()[0]);
                                else
                                    descriptor.setText(character.get_race().getBenefits()[1]);
                            }

                            public void onNothingSelected(AdapterView<?> parent) {}
                        });
                        break;
                    case "Elf":
                        subRaceAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.elf_race_list, android.R.layout.simple_spinner_item);
                        break;
                    default:
                        Log.i(TAG, "WTF did you do?");
                }
                subSpinner.setAdapter(subRaceAdapter);
                subraceLayout.setVisibility(View.VISIBLE);
            }

            public void onNothingSelected(AdapterView<?> parent) {}
        });



        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), charName.getText().toString() + " : " + classSpinner.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
                character = new Character(charName.getText().toString());
                character.set_race(new PlayerRace(raceSpinner.getSelectedItem().toString()));
                character.get_race().setSubrace(subSpinner.getSelectedItem().toString());
                character.setPlayerClass(new PlayerClass(classSpinner.getSelectedItem().toString()));
                dbHandler.addCharacter(character);
                printDatabase();
                Intent intent = new Intent(NewCharActivity.this, StatDistributionActivity.class);
                intent.putExtra("character", new Gson().toJson(character));
                startActivity(intent);
                finish();
            }
        });
    }

    public void printDatabase() {
        toast("Printing");
        String dbString = dbHandler.dbToList().toString();
        toast(dbString);
    }

    public void toast(String text){
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Activity")
                .setMessage("Are you sure you want to cancel character creation?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}
