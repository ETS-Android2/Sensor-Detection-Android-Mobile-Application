package com.example.sensordata.EntityClass;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.sensordata.DateTypeConverter;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "Temperature")
public class TemperatureEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "Value_0")
    private double value_0;

    @ColumnInfo(name = "date")
    @TypeConverters({DateTypeConverter.class})
    private Date date;

    public TemperatureEntity(double value_0,Date date) {
        this.value_0 = value_0;
        this.date = date;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getValue_0() {
        return value_0;
    }

    public void setValue_0(double value_0) {
        this.value_0 = value_0;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}