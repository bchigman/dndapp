package com.project.senior.dndapp;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Handler for the DB file of characters
 * Created by Ben on 5/1/2015.
 */

public class DBHandler extends SQLiteOpenHelper{

    private static final String TAG = "DnDApp";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "characters.db";
    public static final String TABLE_CHARACTERS = "characters";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LEVEL = "level";
    public static final String COLUMN_RACE = "race";
    public static final String COLUMN_SUBRACE = "subrace";
    public static final String COLUMN_CLASS = "class";
    public static final String COLUMN_STATS = "stats";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_CHARACTERS + "("+
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_LEVEL + " INTEGER, " +
                COLUMN_RACE + " TEXT, " +
                COLUMN_SUBRACE + " TEXT, " +
                COLUMN_CLASS + " TEXT, " +
                COLUMN_STATS + " TEXT " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.delete(TABLE_CHARACTERS, "WHERE 1", null);
        onCreate(db);
    }

    //Add new row to db
    public void addCharacter(Character character){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, character.getName());
        cv.put(COLUMN_LEVEL, character.get_level());
        cv.put(COLUMN_RACE, character.get_race().getName());
        cv.put(COLUMN_SUBRACE, character.get_race().getSubrace());
        cv.put(COLUMN_CLASS, character.getPlayerClass().getClassName());
        cv.put(COLUMN_STATS, character.getStatsArray().toString());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_CHARACTERS, null, cv);
        db.close();
    }

    public void deleteCharacter(int charId){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_CHARACTERS + " WHERE " + COLUMN_ID + "=\"" + charId + "\";");
    }

    public void updateCharacter(Character character){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, character.getName());
        cv.put(COLUMN_LEVEL, character.get_level());
        cv.put(COLUMN_RACE, character.get_race().getName());
        cv.put(COLUMN_SUBRACE, character.get_race().getSubrace());
        cv.put(COLUMN_CLASS, character.getPlayerClass().getClassName());
        cv.put(COLUMN_STATS, character.getStatsArray().toString());
        db.update(TABLE_CHARACTERS, cv, String.format("%s = ?", COLUMN_ID), new String[]{""+character.get_id()});
    }

    public void dropTable(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHARACTERS + ";");
        onCreate(db);
    }

    public List<Character> dbToList(){
        String dbString = "";
        List<Character> chars = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_CHARACTERS + " WHERE 1;";

        //cursor stuff
        Cursor c = db.rawQuery(query, null);
        //Move to row
        c.moveToFirst();
        while(!c.isAfterLast()){
            Character character = cursorToCharacter(c);
            chars.add(character);
            c.moveToNext();
        }
        c.close();
        db.close();

        return chars;
    }

    public Character cursorToCharacter(Cursor c){
        int id = (int) c.getLong(0);
        Character character = new Character(c.getString(1));
        character.set_level((int) c.getLong(2));
        character.set_race(new PlayerRace(c.getString(3)));
        character.get_race().setSubrace(c.getString(4));
        character.setPlayerClass(new PlayerClass(c.getString(5)));
        character.set_id(id);
        String stats = c.getString(6);
        Log.i(TAG, stats);
        if(!stats.equals("[]")) {
            stats = stats.substring(1, stats.length() - 1);
            String[] statsarr = stats.trim().split(", ");
            Log.i(TAG, "" + statsarr[0] + " " + statsarr[1]);
            for (String num : statsarr) {
                character.getStatsArray().add(Integer.parseInt(num));
            }
        }
        return character;
    }
}
