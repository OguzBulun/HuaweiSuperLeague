package com.example.sportotosuperlig.dbRoom;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Team.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TeamDao teamDao();
}
