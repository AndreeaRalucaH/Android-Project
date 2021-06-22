package com.example.proiecthorhociagurita;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//Not used anymore, cannot be deleted yet, ma mistake

@Database(entities = {Cheltuiala.class, User.class}, version = 2, exportSchema = false)
public abstract class CheltuieliDB extends RoomDatabase {
    public static final String DB_NAME = "aplicatie.db";
    private static CheltuieliDB instanta;

    public static CheltuieliDB getInstanta(Context context){
        if(instanta == null){
            instanta = Room.databaseBuilder(context, CheltuieliDB.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instanta;
    }

    public abstract CheltuieliDao getCheltuialaDao();

    public abstract UserDao getUserDao();
}

