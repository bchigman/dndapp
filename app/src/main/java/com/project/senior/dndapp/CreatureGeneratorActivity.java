package com.project.senior.dndapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class CreatureGeneratorActivity extends ActionBarActivity {

    EditText name;
    EditText size;
    EditText cr;
    EditText attackDice;
    EditText abilities;
    Creature creature;
    DBHandler dbHandler;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creature_generator);

        dbHandler = new DBHandler(this);
        creature = new Creature();
        submit = (Button) findViewById(R.id.creature_submit_button);
        name = (EditText) findViewById(R.id.creature_name);
        size = (EditText) findViewById(R.id.creature_size);
        cr = (EditText) findViewById(R.id.creature_cr);
        attackDice = (EditText) findViewById(R.id.creature_dice);
        abilities = (EditText) findViewById(R.id.creature_abilities);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creature.setName(name.getText().toString());
                creature.setSize(size.getText().toString());
                creature.setCr(Double.parseDouble(cr.getText().toString()));
                creature.setAttackDice(attackDice.getText().toString());
                creature.setAbilities(abilities.getText().toString());
                dbHandler.addCreature(creature);
                finish();
            }
        });
    }

}
