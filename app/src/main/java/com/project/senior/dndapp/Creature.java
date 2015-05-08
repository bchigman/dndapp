package com.project.senior.dndapp;

/**
 * Creature class
 * Created by Ben on 5/8/2015.
 */
public class Creature {
    private int _id;
    private String name;
    private String attackDice;
    private String size;
    private String abilities;
    private double cr;

    public Creature(){
        this.name = "";
        this.attackDice = "";
        this.size = "";
        this.abilities = "";
        this.cr = 0.0;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttackDice() {
        return attackDice;
    }

    public void setAttackDice(String attackDice) {
        this.attackDice = attackDice;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getAbilities() {
        return abilities;
    }

    public void setAbilities(String abilities) {
        this.abilities = abilities;
    }

    public double getCr() {
        return cr;
    }

    public void setCr(double cr) {
        this.cr = cr;
    }

    public String toString(){
        return this.name + " CR: " + this.cr;
    }

}
