package com.example.sensordata.DaoClass;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.sensordata.EntityClass.LightEntity;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface LightDao {

    //Inserting Query
    @Insert(onConflict = REPLACE)
    public void light_insert(LightEntity lightEntity);

    @Delete
    void delete(LightEntity lightEntity);

    @Query("DELETE FROM Light")
    void deleteAllData();

    @Query("SELECT * FROM Light")
    List<LightEntity> getAllValues();


}
