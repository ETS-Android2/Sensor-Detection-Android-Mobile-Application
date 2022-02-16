package com.example.sensordata.DaoClass;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;

import com.example.sensordata.DateTypeConverter;
import com.example.sensordata.EntityClass.AccelerometerEntity;
import com.example.sensordata.EntityClass.TemperatureEntity;

import java.util.Date;
import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface TemperatureDao {

    //Inserting Query
    @Insert(onConflict = REPLACE)
    public void temperature_insert(TemperatureEntity temperatureEntity);

    @Delete
    void delete(TemperatureEntity temperatureEntity);

    @TypeConverters(DateTypeConverter.class)
    @Query("SELECT * FROM Temperature where date >= :startDate and date <= :endDate")
    List<TemperatureEntity> getPastOneHourTemp(Date startDate, Date endDate);

    @Query("DELETE FROM Temperature")
    void deleteAllData();

    @Query("SELECT * FROM Temperature")
    List<TemperatureEntity> getAllValues();
}
