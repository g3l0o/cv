package com.roger.cv.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Job.class}, version = 1)
public abstract class JobDatabase extends RoomDatabase {

    private static JobDatabase instance;

    public abstract JobDao jobDao();

    public static JobDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(
                        context.getApplicationContext(),
                        JobDatabase.class,
                        "jobdatabase").build();
        }
        return instance;
    }

}
