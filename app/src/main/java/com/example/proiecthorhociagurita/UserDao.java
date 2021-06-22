package com.example.proiecthorhociagurita;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Query("select * from users where mail= :mail and parola= :password")
    User getUser(String mail, String password);

    @Query("select * from users")
    List<User> getAll();

    @Insert
    long insert(User user);

    @Update
    int update(User user);

    @Delete
    int deleteU(User user);

    @Query("delete from users")
    void deleteAll();

    @Query("delete from users where id = :id1")
    public void deleteWhere(long id1);

}
