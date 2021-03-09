package com.example.sportotosuperlig.dbRoom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TeamDao {

    @Query("SELECT * FROM team")
    List<Team> getAll();

    @Insert
    void insert(Team task);

    @Delete
    void delete(Team task);

    @Update
    void update(Team task);

}
