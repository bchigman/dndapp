package com.project.senior.dndapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class DieRollerActivity extends ActionBarActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_die_roller);

        Button rollDie = (Button) findViewById(R.id.die_roller_submit_button);
        Button rolld4 = (Button) findViewById(R.id.d4_button);
        Button rolld6 = (Button) findViewById(R.id.d6_button);
        Button rolld8 = (Button) findViewById(R.id.d8_button);
        Button rolld10 = (Button) findViewById(R.id.d10_button);
        Button rolld12 = (Button) findViewById(R.id.d12_button);
        Button rolld20 = (Button) findViewById(R.id.d20_button);
        Button rolld100 = (Button) findViewById(R.id.d100_button);

        rolld4.setOnClickListener(this);
        rolld6.setOnClickListener(this);
        rolld8.setOnClickListener(this);
        rolld10.setOnClickListener(this);
        rolld12.setOnClickListener(this);
        rolld20.setOnClickListener(this);
        rolld100.setOnClickListener(this);
        rollDie.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_die_roller, menu);
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

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.die_roller_submit_button){
            TextView result = (TextView) findViewById(R.id.show_die_roll_textview);
            EditText rollText = (EditText) findViewById(R.id.die_roller_edittext);
            result.setText(R.string.die_roller_result_string);
            try{
                String[] rollParts = rollText.getText().toString().split("d");
                int sum = 0;
                for (int i = Integer.parseInt(rollParts[0]); i > 0; i--) {
                    sum += ((int) (Math.random() * Integer.parseInt(rollParts[1])) + 1);
                }
                result.append("\n" + rollText.getText().toString() + " : " + Integer.toString(sum));
            }catch(Exception e){
                result.append("Invalid Roll");
            }
        }
        else {
            Button button = (Button) v;
            TextView result = (TextView) findViewById(R.id.show_die_roll_textview);
            result.setText(R.string.die_roller_result_string);
            try {
                String[] rollParts = button.getText().toString().split("d");
                int sum = 0;
                for (int i = Integer.parseInt(rollParts[0]); i > 0; i--) {
                    sum += ((int) (Math.random() * Integer.parseInt(rollParts[1])) + 1);
                }
                result.append("\n" + button.getText().toString() + " : " + Integer.toString(sum));
            } catch (Exception e) {
                result.append("Invalid Roll");
            }
        }
    }
}