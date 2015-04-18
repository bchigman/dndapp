package com.project.senior.dndapp;

import java.util.ArrayList;

/**
 * Created by Ben on 4/15/2015.
 */
public class Spell {
    private String name;
    private String castingTime;
    private String components;
    private String description;
    private String duration;
    private int level;
    private String range;
    private String school;


    public Spell(String name, String castingTime, String components, String description, String duration, int level, String range, String school){
        this.name = name;
        this.castingTime = castingTime;
        this.components = components;
        this.description = description;
        this.duration = duration;
        this.level = level;
        this.range = range;
        this.school = school;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCastingTime() {
        return castingTime;
    }

    public void setCastingTime(String castingTime) {
        this.castingTime = castingTime;
    }

    public String getComponents() {
        return components;
    }

    public void setComponents(String components) {
        this.components = components;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return this.name + " - level: " + this.level + "\n";
    }

}
