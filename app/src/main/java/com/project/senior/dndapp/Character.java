package com.project.senior.dndapp;

import java.util.ArrayList;

/**
 * Player Character class
 * Created by Ben on 4/2/2015.
 */
public class Character {

    private int _id;
    private String _name;
    private String _race;
    private int _level;
    private PlayerClass playerClass;
    private ArrayList<Integer> statsArray;
    private ArrayList<Spell> charaterSpells;

    public Character(String name){
        this._name = name;
        this._level = 1;
        this.statsArray = new ArrayList<>();
        this.charaterSpells = new ArrayList<>();
    }

    public String getName(){
        return this._name;
    }

    public void setName(String name){
        this._name = name;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
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

    public ArrayList<Spell> getCharaterSpells() {
        return charaterSpells;
    }

    public void setCharaterSpells(ArrayList<Spell> charaterSpells) {
        this.charaterSpells = charaterSpells;
    }

    public String get_race() {
        return _race;
    }

    public void set_race(String _race) {
        this._race = _race;
    }

    public int get_level() {
        return _level;
    }

    public void set_level(int _level) {
        this._level = _level;
    }

    public String toString(){
        return this._id + ") " + this._name + ", level " + this._level + " " + this._race + " "+ this.playerClass.getClassName();
    }
}
