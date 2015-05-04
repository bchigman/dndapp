package com.project.senior.dndapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * For connecting db to character list
 * Created by Ben on 5/3/2015.
 */
public class CharacterAdapter extends ArrayAdapter<Character>{

    public CharacterAdapter(Context context, List<Character> characters) {
        super(context, R.layout.character_row, characters);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());

        View view = inflater.inflate(R.layout.character_row, parent, false);
        Character singleCharacter = getItem(position);
        TextView textView = (TextView) view.findViewById(R.id.character_row_title);
        textView.setText(singleCharacter.get_id() + ") " + singleCharacter.toString());

        TextView desc = (TextView) view.findViewById(R.id.character_row_description);
        desc.setText(singleCharacter.getStatsArray().toString());

        return view;
    }

    public void clearAdapter() {
        this.clear();
        notifyDataSetChanged();
    }

    public String toString(){
        return "CharacterAdapter";
    }

}
