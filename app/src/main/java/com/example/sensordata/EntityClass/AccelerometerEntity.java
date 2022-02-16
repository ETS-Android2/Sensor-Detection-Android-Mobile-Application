package com.example.sensordata.EntityClass;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.sensordata.DateTypeConverter;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "Accelerometer")
public class AccelerometerEntity implements Serializable{

    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "X_Axis")
    private double x_axis;

    @ColumnInfo(name = "Y_Axis")
    private double y_axis;

    @ColumnInfo(name = "Z_Axis")
    private double z_axis;

    @ColumnInfo(name = "date")
    @TypeConverters({DateTypeConverter.class})
    private Date date;

    public AccelerometerEntity(double x_axis, double y_axis, double z_axis, Date date){
        this.x_axis = x_axis;
        this.y_axis = y_axis;
        this.z_axis = z_axis;
        this.date = date;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getX_axis() {
        return x_axis;
    }

    public void setX_axis(double x_axis) {
        this.x_axis = x_axis;
    }

    public double getY_axis() {
        return y_axis;
    }

    public void setY_axis(double y_axis) {
        this.y_axis = y_axis;
    }

    public double getZ_axis() {
        return z_axis;
    }

    public void setZ_axis(double z_axis) {
        this.z_axis = z_axis;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
