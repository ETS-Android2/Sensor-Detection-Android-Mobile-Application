package com.example.sensordata.DaoClass;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.sensordata.EntityClass.LinearAccelerationEntity;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface LinearAccelerationDao {

    //Inserting Query
    @Insert(onConflict = REPLACE)
    public void linearAcceleration_insert(LinearAccelerationEntity linearAccelerationEntity);


    @Delete
    void delete(LinearAccelerationEntity linearAccelerationEntity);


    @Query("DELETE FROM LinearAcceleration")
    void deleteAllData();

    @Query("SELECT * FROM LinearAcceleration")
    List<LinearAccelerationEntity> getAllValues();
}
