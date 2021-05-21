package com.example.animalapp.Database;


import android.content.Context;
import android.nfc.Tag;

import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Animal.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AnimalDatabase extends RoomDatabase {

    private static AnimalDatabase INSTANCE;

    public synchronized static AnimalDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = buildDatabase(context);
        }
        return INSTANCE;
    }

    private static AnimalDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                AnimalDatabase.class,
                "animal_list")
                .allowMainThreadQueries()
                .build();
        }

    public abstract AnimalDAO animalDAO();

}
