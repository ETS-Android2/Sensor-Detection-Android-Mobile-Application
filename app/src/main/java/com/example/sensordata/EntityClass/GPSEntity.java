package com.example.sensordata.EntityClass;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "GPS")
public class GPSEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "Longitude")
    private double longitude;

    @ColumnInfo(name = "Latitude")
    private double latitude;

    @ColumnInfo(name = "Altitude")
    private double altitude;

    public GPSEntity(double longitude, double latitude, double altitude) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }
}
