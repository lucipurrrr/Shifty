package com.shifty.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.shifty.model.Shift;

@Database(entities = {Shift.class}, version = 1, exportSchema = false)
public abstract class ShiftDatabase extends RoomDatabase {

    private static ShiftDatabase instance;

    public abstract ShiftDao shiftDao();

    public static synchronized ShiftDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ShiftDatabase.class, "shifty_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
