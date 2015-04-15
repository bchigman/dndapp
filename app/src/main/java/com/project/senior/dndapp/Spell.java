package com.project.senior.dndapp;

import java.util.ArrayList;

/**
 * Created by Ben on 4/15/2015.
 */
public class Spell {
    private String name;
    private int level;
    private String school;
    private ArrayList<Source> sources;

    public Spell(String name, int level, String school, ArrayList<Source> sources){
        this.name = name;
        this.level = level;
        this.school = school;
        this.sources = sources;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public ArrayList<Source> getSources() {
        return sources;
    }

    public void setSources(ArrayList<Source> sources) {
        this.sources = sources;
    }

    @Override
    public String toString() {
        return this.name + " - level: " + this.level + "\n";
    }
}
