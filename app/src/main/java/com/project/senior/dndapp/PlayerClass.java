package com.project.senior.dndapp;

import android.util.Log;

import java.util.ArrayList;

/**
 * PlayerClass Object definition
 * Created by Ben on 4/2/2015.
 */
public class PlayerClass {

    private static final String TAG = "DnDApp";
    private String className;
    private String description;
    private int hitDie;
    private String primaryAbility;
    private String savingThrows;
    private String[] proficiencies;
    private Integer[] recommended;
    private ArrayList<Integer> statPerks;
    private ArrayList<String> featPerks;

    public PlayerClass(String name){
        this.className = name;
        this.statPerks = new ArrayList<>();
        this.featPerks = new ArrayList<>();

        switch(this.className){
            case "Barbarian":
                this.description = "A fierce warrior of primitive background who can enter a battle rage.";
                this.hitDie = 12;
                this.primaryAbility = "Strength";
                this.savingThrows = "Strength & Constitution";
                this.proficiencies = new String[]{"Light Armor", "Medium Armor", "Shields", "Simple Weapons", "Martial Weapons"};
                this.recommended = new Integer[]{15, 14, 13, 12, 10, 8};
                break;
            case "Bard":
                this.description = "An inspiring magician whose power echoes the music of creation.";
                this.hitDie = 8;
                this.primaryAbility = "Charisma";
                this.savingThrows = "Dexterity & Constitution";
                this.proficiencies = new String[]{"Light Armor", "Simple Weapons", "Hand Crossbows", "Longswords", "Rapiers", "Shortswords"};
                this.recommended = new Integer[]{12, 14, 13, 10, 8, 15};
                break;
            case "Cleric":
                this.description = "A priestly champion who wields divine magic in service of a higher power.";
                this.hitDie = 8;
                this.primaryAbility = "Wisdom";
                this.savingThrows = "Wisdom & Charisma";
                this.proficiencies = new String[]{"Light Armor", "Medium Armor", "Shields", "Simple Weapons"};
                this.recommended = new Integer[]{14, 12, 13, 10, 15, 8};
                break;
            case "Druid":
                this.description = "A priest of the Old Faith, wielding the powers of nature -- moonlight and plant growth, fire and lightning -- and able to adopt animal forms.";
                this.hitDie = 8;
                this.primaryAbility = "Wisdom";
                this.savingThrows = "Intelligence & Wisdom";
                this.proficiencies = new String[]{"Light Armor (nonmetal)", "Medium Armor (nonmetal)", "Shields (nonmetal)", "Clubs",
                        "Daggers", "Darts", "Javelins", "Maces", "Quarterstaffs", "Scimitars", "Sickles", "Slings", "Spears"};
                this.recommended = new Integer[]{10, 8, 14, 13, 15, 12};
                break;
            case "Fighter":
                this.description = "A master of martial combat, skilled with a variety of weapons and armor.";
                this.hitDie = 10;
                this.primaryAbility = "Strength or Dexterity";
                this.savingThrows = "Strength & Constitution";
                this.proficiencies = new String[]{"All Armor", "Shields", "Simple Weapons", "Martial Weapons"};
                this.recommended = new Integer[]{15, 14, 13, 8, 10, 12};
                break;
            case "Monk":
                this.description = "A master of martial arts, harnessing the power of the body in pursuit of physical and spiritual perfection.";
                this.hitDie = 8;
                this.primaryAbility = "Dexterity & Wisdom";
                this.savingThrows = "Strength & Dexterity";
                this.proficiencies = new String[]{"Simple Weapons", "Shortswords"};
                this.recommended = new Integer[]{12, 15, 10, 13, 14, 8};
                break;
            case "Paladin":
                this.description = "A holy warrior bound to a sacred oath.";
                this.hitDie = 10;
                this.primaryAbility = "Strength & Charisma";
                this.savingThrows = "Wisdom & Charisma";
                this.proficiencies = new String[]{"All Armor", "Shields", "Simple Weapons", "Martial Weapons"};
                this.recommended = new Integer[]{13, 10, 15, 8, 12, 14};
                break;
            case "Ranger":
                this.description = "A warrior who uses martial prowess and nature magic to combat threats on the edges of civilization.";
                this.hitDie = 10;
                this.primaryAbility = "Dexterity & Wisdom";
                this.savingThrows = "Strength & Dexterity";
                this.proficiencies = new String[]{"Light Armor", "Medium Armor", "Shields", "Simple Weapons", "Martial Weapons"};
                this.recommended = new Integer[]{ 12, 15, 10, 13, 14, 8};
                break;
            case "Rogue":
                this.description = "A scoundrel who uses stealth and trickery to overcome obstacles and enemies.";
                this.hitDie = 8;
                this.primaryAbility = "Dexterity";
                this.savingThrows = "Dexterity & Intelligence";
                this.proficiencies = new String[]{"Light Armor", "Simple Weapons", "Hand Crossbows", "Longswords", "Rapiers", "Shortswords"};
                this.recommended = new Integer[]{12, 15, 13, 14, 8, 10};
                break;
            case "Sorcerer":
                this.description = "A spellcaster who draws on inherent magic from a gift or bloodline";
                this.hitDie = 6;
                this.primaryAbility = "Charisma";
                this.savingThrows = "Constitution & Charisma";
                this.proficiencies = new String[]{"Daggers", "Darts", "Slings", "Quarterstaffs", "Light Crossbows"};
                this.recommended = new Integer[]{10, 8, 14, 12, 13, 15};
                break;
            case "Warlock":
                this.description = "A wielder of magic that is derived from a bargain with an extraplanar entity.";
                this.hitDie = 8;
                this.primaryAbility = "Charisma";
                this.savingThrows = "Wisdom & Charisma";
                this.proficiencies = new String[]{"Light Armor", "Simple Weapons"};
                this.recommended = new Integer[]{8, 10, 13, 14, 12, 15};
                break;
            case "Wizard":
                this.description = "A scholarly magic-user capable of manipulating the structures of reality.";
                this.hitDie = 6;
                this.primaryAbility = "Intelligence";
                this.savingThrows = "Intelligence & Wisdom";
                this.proficiencies = new String[]{"Daggers", "Darts", "Slings", "Quarterstaffs", "Light Crossbows"};
                this.recommended = new Integer[]{8, 10, 12, 15, 14, 13};
                break;
            default:
                Log.i(TAG, "No class like that dummy.");
        }
    }

    public int getHitDie() {
        return hitDie;
    }

    public void setHitDie(int hitDie) {
        this.hitDie = hitDie;
    }

    public String getPrimaryAbility() {
        return primaryAbility;
    }

    public void setPrimaryAbility(String primaryAbility) {
        this.primaryAbility = primaryAbility;
    }

    public String getSavingThrows() {
        return savingThrows;
    }

    public void setSavingThrows(String savingThrows) {
        this.savingThrows = savingThrows;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getProficiencies() {
        return proficiencies;
    }

    public void setProficiencies(String[] proficiencies) {
        this.proficiencies = proficiencies;
    }

    public Integer[] getRecommended() {
        return recommended;
    }

    public void setRecommended(Integer[] recommended){
        this.recommended = recommended;
    }
}
