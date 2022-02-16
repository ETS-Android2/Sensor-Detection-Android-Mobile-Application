package com.example.sensordata.DaoClass;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.sensordata.EntityClass.ProximityEntity;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ProximityDao {

    //Inserting Query
    @Insert(onConflict = REPLACE)
    public void proximity_insert(ProximityEntity proximityEntity);

    @Delete
    void delete(ProximityEntity proximityEntity);

    @Query("DELETE FROM Proximity")
    void deleteAllData();

    @Query("SELECT * FROM Proximity")
    List<ProximityEntity> getAllValues();
}
