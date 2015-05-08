package com.project.senior.dndapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Adapter for the creature class to listview
 * Created by Ben on 5/8/2015.
 */
public class CreatureAdapter extends ArrayAdapter<Creature> {

    public CreatureAdapter(Context context, List<Creature> creatures) {
        super(context, R.layout.creature_row, creatures);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());

        View view = inflater.inflate(R.layout.creature_row, parent, false);
        Creature singleCreature = getItem(position);
        TextView textView = (TextView) view.findViewById(R.id.creature_row_title);
        textView.setText(singleCreature.get_id() + ") " + singleCreature.toString());

        TextView desc = (TextView) view.findViewById(R.id.creature_row_description);
        desc.setText("Size - " + singleCreature.getSize() + "\n" +
                "Attack Roll - " + singleCreature.getAttackDice() + "\n" +
                "Abilities: \n" + singleCreature.getAbilities());

        return view;
    }

    public void clearAdapter() {
        this.clear();
        notifyDataSetChanged();
    }

    public String toString(){
        return "Creature Adapter";
    }
}
