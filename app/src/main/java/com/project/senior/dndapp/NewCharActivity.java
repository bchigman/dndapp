package com.project.senior.dndapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class NewCharActivity extends ActionBarActivity {

    private static final String TAG = "DnDApp";
    DBHandler dbHandler;
    EditText charName;
    Spinner classSpinner;
    Spinner raceSpinner;
    Button nextPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_char);
        dbHandler = new DBHandler(this);
        charName = (EditText) findViewById(R.id.char_name);
        nextPage = (Button) findViewById(R.id.next_page_button);

        final String[] STANDARD_ARRAY = {"15", "14", "13", "12", "10", "8"};
        final Button[] BUTTONS = {(Button)findViewById(R.id.button1), (Button) findViewById(R.id.button2), (Button) findViewById(R.id.button3),
                (Button) findViewById(R.id.button4), (Button) findViewById(R.id.button5), (Button) findViewById(R.id.button6)};
        final ArrayList<EditText> EDITTEXTS = new ArrayList<>();
        EDITTEXTS.add((EditText)findViewById(R.id.strength_val));
        EDITTEXTS.add((EditText)findViewById(R.id.dexterity_val));
        EDITTEXTS.add((EditText)findViewById(R.id.constitution_val));
        EDITTEXTS.add((EditText)findViewById(R.id.intelligence_val));
        EDITTEXTS.add((EditText)findViewById(R.id.wisdom_val));
        EDITTEXTS.add((EditText)findViewById(R.id.charisma_val));

        classSpinner = (Spinner) findViewById(R.id.class_spinner);
        ArrayAdapter<CharSequence> classAdapter = ArrayAdapter.createFromResource(this, R.array.class_list, android.R.layout.simple_spinner_item);
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classSpinner.setAdapter(classAdapter);

        raceSpinner = (Spinner) findViewById(R.id.race_spinner);
        ArrayAdapter<CharSequence> raceAdapter = ArrayAdapter.createFromResource(this, R.array.race_list, android.R.layout.simple_spinner_item);
        raceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        raceSpinner.setAdapter(raceAdapter);

        CheckBox standardArrayCheck = (CheckBox) findViewById(R.id.standard_array_check);
        standardArrayCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox check = (CheckBox) v;
                if(!check.isChecked()){
                    for(int i=0;i<6;i++){
                        BUTTONS[i].setText(randomizeStatButton());
                    }
                }
                else{
                    for(int i=0;i<6;i++){
                        BUTTONS[i].setText(STANDARD_ARRAY[i]);
                    }
                }
            }
        });

        Button clearButton = (Button) findViewById(R.id.clear_stats_button);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(EditText et : EDITTEXTS){
                    et.setText("");
                }
                for(Button button : BUTTONS){
                    button.setEnabled(true);
                }
            }
        });

        View.OnClickListener clickListener = new View.OnClickListener(){
            public void onClick(View v){
                Button button = (Button) v;

                for(EditText et: EDITTEXTS){
                    if(et.getText().toString().equals("")){
                        et.setText(button.getText());
                        button.setEnabled(false);
                        Log.i(TAG, "Should set text");
                        break;
                    }
                }
            }
        };

        for(int i=0;i<6;i++){
            BUTTONS[i].setOnClickListener(clickListener);
        }

        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), charName.getText().toString() + " : " + classSpinner.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
                Character character = new Character(charName.getText().toString());
                character.set_race(raceSpinner.getSelectedItem().toString());
                character.setPlayerClass(new PlayerClass(classSpinner.getSelectedItem().toString()));
                ArrayList<Integer> stats = new ArrayList<>();
                for(EditText et: EDITTEXTS){
                    stats.add(Integer.parseInt(et.getText().toString()));
                }
                character.setStatsArray(stats);
                dbHandler.addCharacter(character);
                printDatabase();
            }
        });
    }

    public String randomizeStatButton(){
        int[] rolls = new int[4];
        for(int i=0;i<4;i++){
            rolls[i]= (int)(Math.random()*6+1);
        }
        Arrays.sort(rolls);
        return Integer.toString(rolls[1]+rolls[2]+rolls[3]);
    }

    public void printDatabase (){
        Toast.makeText(this, "Printing", Toast.LENGTH_LONG).show();
        String dbString = dbHandler.dbToList().toString();
        Toast.makeText(this, dbString, Toast.LENGTH_LONG).show();
    }
}
