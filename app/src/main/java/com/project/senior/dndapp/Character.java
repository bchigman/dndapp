package com.project.senior.dndapp;

import java.util.ArrayList;

/**
 * Player Character class
 * Created by Ben on 4/2/2015.
 */
public class Character {

    private int _id;
    private String _name;
    private PlayerRace _race;
    private int _level;
    private int _hitpointMax;
    private int _currentHitpoints;
    private PlayerClass playerClass;
    private ArrayList<Integer> _statsArray;
    private ArrayList<Spell> charaterSpells;

    public Character(){
        this._name = "";
        this._level = 1;
        this._hitpointMax = 10;
        this._currentHitpoints = 10;
        this._statsArray = new ArrayList<>();
        this.charaterSpells = new ArrayList<>();
        this._race = new PlayerRace("");
    }

    public Character(String name){
        this._name = name;
        this._level = 1;
        this._hitpointMax = 10;
        this._currentHitpoints = _hitpointMax;
        this._statsArray = new ArrayList<>();
        this.charaterSpells = new ArrayList<>();
        this._race = new PlayerRace("");
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
        return this._statsArray;
    }

    public void setStatsArray(int[] stats){
        for(int i : stats){
            this._statsArray.add(i);
        }
    }

    public void setStatsArray(ArrayList<Integer> stats){
        this._statsArray = new ArrayList<>();
        for( Integer currentInt : stats){
            this._statsArray.add(currentInt);
        }
    }

    public ArrayList<Spell> getCharaterSpells() {
        return charaterSpells;
    }

    public void setCharaterSpells(ArrayList<Spell> charaterSpells) {
        this.charaterSpells = charaterSpells;
    }

    public PlayerRace get_race() {
        return _race;
    }

    public void set_race(PlayerRace _race) {
        this._race = _race;
    }

    public int get_level() {
        return _level;
    }

    public void set_level(int _level) {
        this._level = _level;
    }

    public int get_hitpointMax() {
        return _hitpointMax;
    }

    public void set_hitpointMax(int _hitpointMax) {
        this._hitpointMax = _hitpointMax;
    }

    public int get_currentHitpoints() {
        return _currentHitpoints;
    }

    public void set_currentHitpoints(int _currentHitpoints) {
        this._currentHitpoints = _currentHitpoints;
    }

    //To String
    public String toString(){
        return this._name + ", level " + this._level + " " + this._race + " "+ this.playerClass.getClassName();
    }

}
