package com.project.senior.dndapp;

import java.util.ArrayList;

/**
 * Created by Ben on 4/2/2015.
 */
public class PlayerClass {

    private ArrayList<Integer> statPerks;
    private ArrayList<String> featPerks;

    public PlayerClass(){
        this.statPerks = new ArrayList<>();
        this.featPerks = new ArrayList<>();
    }

    public ArrayList<Integer> getStatPerks(){
        return this.statPerks;
    }

    public void setStatPerks(ArrayList<Integer> statPerks){
        this.statPerks = new ArrayList<>();
        for(Integer item : statPerks){
            this.statPerks.add(item);
        }
    }

    public ArrayList<String> getFeatPerks(){
        return this.featPerks;
    }

    public void setFeatPerks(ArrayList<String> featPerks){
        this.featPerks = new ArrayList<>();
        for(String item : featPerks){
            this.featPerks.add(item);
        }
    }
}
