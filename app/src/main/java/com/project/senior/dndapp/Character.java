package com.project.senior.dndapp;

import java.util.ArrayList;

/**
 * Player Character class
 * Created by Ben on 4/2/2015.
 */
public class Character {

    private String name;
    private String race;
    private PlayerClass playerClass;
    private ArrayList<Integer> statsArray;
    private ArrayList<Spell> charaterSpells;

    public Character(String name){
        this.name = name;
        this.race = "";
        this.playerClass = new PlayerClass();
        this.statsArray = new ArrayList<>();
        this.charaterSpells = new ArrayList<Spell>();
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getRace(){
        return this.race;
    }

    public void setRace(String race){
        this.race = race;
    }

    public PlayerClass getPlayerClass(){
        return this.playerClass;
    }

    public void setPlayerClass(PlayerClass playerClass){
        this.playerClass = playerClass;
    }

    public ArrayList<Integer> getStatsArray(){
        return this.statsArray;
    }

    public void setStatsArray(int[] stats){
        for(int i : stats){
            this.statsArray.add(i);
        }
    }

    public void setStatsArray(ArrayList<Integer> stats){
        this.statsArray = new ArrayList<>();
        for( Integer currentInt : stats){
            this.statsArray.add(currentInt);
        }
    }
}
