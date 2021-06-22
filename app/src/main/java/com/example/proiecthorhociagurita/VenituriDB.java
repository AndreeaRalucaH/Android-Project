package com.example.proiecthorhociagurita;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//Not used anymore, cannot be deleted yet, ma mistake

@Database(entities = {Venit.class, User.class}, version = 2, exportSchema = false)
public abstract class VenituriDB extends RoomDatabase {
    public static final String DB_NAME = "aplicatie.db";
    private static VenituriDB instanta;

    public static VenituriDB getInstanta(Context context){
        if(instanta == null){
            instanta = Room.databaseBuilder(context, VenituriDB.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instanta;
    }

    public abstract VenituriDao getVenitDao();

    public abstract UserDao getUserDao();
}
