package com.example.sensordata.DaoClass;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.sensordata.EntityClass.GPSEntity;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface GPSDao {

    //Inserting Query
    @Insert(onConflict = REPLACE)
    public void gps_insert(GPSEntity gpsEntity);

    @Delete
    void delete(GPSEntity gpsEntity);

    @Query("DELETE FROM GPS")
    void deleteAllData();

    @Query("SELECT * FROM GPS")
    List<GPSEntity> getAllValues();
}
