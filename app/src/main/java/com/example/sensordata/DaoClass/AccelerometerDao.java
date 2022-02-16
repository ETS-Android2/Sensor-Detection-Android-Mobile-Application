package com.example.sensordata.DaoClass;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;


import com.example.sensordata.DateTypeConverter;
import com.example.sensordata.EntityClass.AccelerometerEntity;

import java.util.Date;
import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface AccelerometerDao {

    //Inserting Query
    @Insert (onConflict = REPLACE)
    public void accelerometer_insert(AccelerometerEntity accelerometerEntity);

    //void insertAllData(AccelerometerEntity accelerometerEntity);

    @Delete
    void delete(AccelerometerEntity accelerometerEntity);

    @TypeConverters(DateTypeConverter.class)
    @Query("SELECT * FROM Accelerometer where date >= :startDate and date <= :endDate")
    List<AccelerometerEntity> getPastOneHourAcc(Date startDate, Date endDate);

    @Query("DELETE FROM Accelerometer")
    void deleteAllData();

    @Query("SELECT * FROM Accelerometer ORDER BY id DESC LIMIT 30")
    List<AccelerometerEntity> getMotionValues();

}
