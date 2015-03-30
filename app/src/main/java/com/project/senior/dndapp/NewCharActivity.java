package com.project.senior.dndapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;


public class NewCharActivity extends ActionBarActivity {

    private static final String TAG = "DnDApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_char);

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

        Spinner classSpinner = (Spinner) findViewById(R.id.class_spinner);
        ArrayAdapter<CharSequence> classAdapter = ArrayAdapter.createFromResource(this, R.array.class_list, android.R.layout.simple_spinner_item);
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classSpinner.setAdapter(classAdapter);

        Spinner raceSpinner = (Spinner) findViewById(R.id.race_spinner);
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
                for(int i=0;i<BUTTONS.length;i++){
                    BUTTONS[i].setEnabled(true);
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

    }

    public String randomizeStatButton(){
        int[] rolls = new int[4];
        for(int i=0;i<4;i++){
            rolls[i]= (int)(Math.random()*6+1);
        }
        Arrays.sort(rolls);
        return Integer.toString(rolls[1]+rolls[2]+rolls[3]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_char, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
