package com.roger.cv.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Information.class}, version = 1)
public abstract class InformationDatabase extends RoomDatabase {

    private static InformationDatabase instance;

    public abstract InformationDao informationDao();

    public static InformationDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    InformationDatabase.class,
                    "information-database").build();
        }
        return instance;
    }

}
