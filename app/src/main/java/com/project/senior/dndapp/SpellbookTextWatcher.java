package com.project.senior.dndapp;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Listener for the search bar
 * Created by Ben on 4/23/2015.
 */
public class SpellbookTextWatcher implements TextWatcher {

    private Context context;
    private SpellAdapter listAdapter;
    private List<Spell> resetList;
    private List<Spell> newList;

    public SpellbookTextWatcher(Context context, SpellAdapter la, List<Spell> lv){
        this.context = context;
        this.listAdapter = la;
        this.resetList = lv;
        this.newList = new ArrayList<>();
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
    public void onTextChanged(CharSequence s, int start, int before, int count){}

    @Override
    public void afterTextChanged(Editable s) {
        String text = s.toString();

        this.newList.clear();
        for(int i=0; i<this.resetList.size(); i++){
            if((this.resetList.get(i).getName().contains(text))){
                this.newList.add(this.resetList.get(i));
            }
        }
        this.listAdapter.clearAdapter();
        this.listAdapter.addAll(this.newList);
        Toast.makeText(this.context, ""+this.resetList, Toast.LENGTH_LONG).show();
        if(text.equals("")){
            this.listAdapter.clearAdapter();
            this.listAdapter.addAll(this.resetList);
        }
    }

    public List<Spell> getResetList() {
        return resetList;
    }

    public void setResetList(List<Spell> resetList) {
        this.resetList = resetList;
    }

    public SpellAdapter getListAdapter() {
        return listAdapter;
    }

    public void setListAdapter(SpellAdapter listAdapter) {
        this.listAdapter = listAdapter;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
