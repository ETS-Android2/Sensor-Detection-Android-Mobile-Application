package com.example.sensordata.EntityClass;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Light")
public class LightEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "Value_0")
    private double value_0;

    public LightEntity(double value_0){
        this.value_0 = value_0;
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
}
