package com.example.proiecthorhociagurita;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CheltuieliDao {

    //-------------Inserari
    @Insert
    public void insert(Cheltuiala ch);

    @Insert
    public void insert(List<Cheltuiala> cheltuieli);

    //-------------Selectii
    @Query("select * from cheltuieli")
    public List<Cheltuiala> getAll();

    @Query("select * from cheltuieli where userId = :usId")
    public List<Cheltuiala> getWhereUserId(long usId);

    @Query("select * from cheltuieli where tipCheltuiala = :tip and userId = :usId")
    public List<Cheltuiala> getWhereTip(long usId, String tip);

    @Query("select sum(suma) from cheltuieli where tipCheltuiala = :tip and userId = :usId")
    public long getSumaCheltuieliTip(long usId, String tip);

    //-------------Stergeri
    @Query("delete from cheltuieli where userId = :id1")
    public void deleteAllWhere(long id1);

    @Query("delete from cheltuieli where id = :id1")
    public void deleteWhere(long id1);
}
