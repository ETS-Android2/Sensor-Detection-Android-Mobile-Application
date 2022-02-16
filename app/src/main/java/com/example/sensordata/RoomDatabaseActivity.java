package com.example.sensordata;


import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.sensordata.DaoClass.AccelerometerDao;
import com.example.sensordata.DaoClass.GPSDao;
import com.example.sensordata.DaoClass.LightDao;
import com.example.sensordata.DaoClass.LinearAccelerationDao;
import com.example.sensordata.DaoClass.ProximityDao;
import com.example.sensordata.DaoClass.TemperatureDao;
import com.example.sensordata.EntityClass.AccelerometerEntity;
import com.example.sensordata.EntityClass.GPSEntity;
import com.example.sensordata.EntityClass.LightEntity;
import com.example.sensordata.EntityClass.LinearAccelerationEntity;
import com.example.sensordata.EntityClass.ProximityEntity;
import com.example.sensordata.EntityClass.TemperatureEntity;

@Database(entities = {AccelerometerEntity.class, LinearAccelerationEntity.class, LightEntity.class, ProximityEntity.class, TemperatureEntity.class, GPSEntity.class}, version = 2, exportSchema = false)
public abstract class RoomDatabaseActivity extends RoomDatabase {

    //Create instance of Database
    private static RoomDatabaseActivity database;

    //Define the name of Database
    private static String DATABASE_NAME = "Sensors";

    public synchronized static RoomDatabaseActivity getInstance(Context context){

        //Check condition if Database created or not
        if (database == null){
            database = Room.databaseBuilder(context.getApplicationContext(),
                    RoomDatabaseActivity.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return database;
    }

    //Create DAO
    public abstract AccelerometerDao accelerometerDao();

    public abstract LinearAccelerationDao linearAccelerationDao();

    public abstract GPSDao gpsDao();

    public abstract LightDao lightDao();

    public abstract TemperatureDao temperatureDao();

    public abstract ProximityDao proximityDao();

}
