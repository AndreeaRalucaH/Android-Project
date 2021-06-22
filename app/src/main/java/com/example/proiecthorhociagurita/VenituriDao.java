package com.example.proiecthorhociagurita;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface VenituriDao {

    //-------------Inserari
    @Insert
    public void insert(Venit v);

    @Insert
    public void insert(List<Venit> venituri);

    //-------------Selectii
    @Query("select * from venituri")
    public List<Venit> getAll();

    @Query("select * from venituri where userId = :usId")
    public List<Venit> getWhereUserId(long usId);

    @Query("select sum(suma) from venituri where tipVenit = :tip and userId = :usId")
    public long getSumaVenituriTip(long usId, String tip);

    @Query("delete from venituri")
    public void deleteAll();

    //-------------Stergeri
    @Delete
    public void deleteV(Venit v);

    @Query("delete from venituri where id = :id1")
    public void deleteWhere(long id1);
}
