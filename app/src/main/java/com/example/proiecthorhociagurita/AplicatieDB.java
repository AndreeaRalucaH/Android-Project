package com.example.proiecthorhociagurita;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Venit.class, User.class, Cheltuiala.class}, version = 3, exportSchema = false)
public abstract class AplicatieDB extends RoomDatabase {
    public static final String DB_NAME = "aplicatie.db";
    private static AplicatieDB instanta;

    public static AplicatieDB getInstanta(Context context){
        if(instanta == null){
            instanta = Room.databaseBuilder(context, AplicatieDB.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instanta;
    }

    public abstract VenituriDao getVenitDao();

    public abstract UserDao getUserDao();

    public abstract  CheltuieliDao getCheltuialaDao();
}
