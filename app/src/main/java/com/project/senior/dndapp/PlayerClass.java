package com.project.senior.dndapp;

import java.util.ArrayList;

/**
 * PlayerClass Object definition
 * Created by Ben on 4/2/2015.
 */
public class PlayerClass {

    private String className;
    private ArrayList<Integer> statPerks;
    private ArrayList<String> featPerks;

    public PlayerClass(String name){
        this.className = name;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
