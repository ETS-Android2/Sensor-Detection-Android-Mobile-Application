package com.example.sensordata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.util.Log;
import android.widget.TextView;

import com.example.sensordata.EntityClass.AccelerometerEntity;
import com.example.sensordata.EntityClass.GPSEntity;
import com.example.sensordata.EntityClass.LightEntity;
import com.example.sensordata.EntityClass.LinearAccelerationEntity;
import com.example.sensordata.EntityClass.ProximityEntity;
import com.example.sensordata.EntityClass.TemperatureEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity implements SensorEventListener, LocationListener {
    private Switch accelerometer, linear_acc, temp, gps, proximity, light;
    private Button avg_accelerometer, avg_temperature, motion_detect;
    private TextView avg_acc_view, avg_temp_view, motion_view;
    private static final String TAG = "MainActivity";
    private SensorManager sensorManager;
    private LocationManager locationManager;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    Sensor accelerometer_sensor, linearAcc_sensor, light_sensor, proximity_sensor, temp_sensor;
    private int acc_flag = 0, linear_flag = 0, temp_flag = 0, gps_flag = 0, proximity_flag = 0, light_flag = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accelerometer = (Switch) findViewById(R.id.accelerometer);
        linear_acc = (Switch) findViewById(R.id.linearacc);
        temp = (Switch) findViewById(R.id.temperature);
        light = (Switch) findViewById(R.id.light);
        gps = (Switch) findViewById(R.id.gps);
        proximity = (Switch) findViewById(R.id.proximity);
        avg_accelerometer = (Button) findViewById(R.id.avg_acc);
        avg_acc_view = (TextView) findViewById(R.id.avg_acc_show);
        avg_temperature = (Button) findViewById(R.id.avg_temp_btn);
        avg_temp_view = (TextView) findViewById(R.id.avg_temp_view);
        motion_detect = (Button) findViewById(R.id.motion_btn);
        motion_view = (TextView) findViewById(R.id.motion_view);
    }

    @Override
    protected void onResume() {
        super.onResume();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        accelerometer_sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        linearAcc_sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        light_sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        proximity_sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        temp_sensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        sensorManager.registerListener(MainActivity.this, accelerometer_sensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(MainActivity.this, linearAcc_sensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(MainActivity.this, light_sensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(MainActivity.this, proximity_sensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(MainActivity.this, temp_sensor, SensorManager.SENSOR_DELAY_NORMAL);

        accelerometer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    acc_flag = 1;
                } else {
                    acc_flag = 0;
                }
            }
        });

        linear_acc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    linear_flag = 1;
                } else {
                    linear_flag = 0;
                }
            }
        });

        temp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    temp_flag = 1;
                } else {
                    temp_flag = 0;
                }
            }
        });

        gps.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gps_flag = 1;
                    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.INTERNET
                        }, 100);
                        // Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    else{
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, MainActivity.this::onLocationChanged);
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, MainActivity.this::onLocationChanged);
                    }
                }
                else{
                    gps_flag = 0;
                }
            }
        });

        light.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    light_flag = 1;
                }
                else{
                    light_flag = 0;
                }
            }
        });

        proximity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    proximity_flag = 1;
                }
                else{
                    proximity_flag = 0;
                }
            }
        });

        avg_accelerometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date endDate = Calendar.getInstance().getTime();
                calendar = Calendar.getInstance();
                calendar.setTime(endDate);
                calendar.add(Calendar.HOUR, -1);
                Date startDate = calendar.getTime();
                String display = "";
                List<AccelerometerEntity> accelerations = RoomDatabaseActivity.getInstance(getApplicationContext()).accelerometerDao().getPastOneHourAcc(startDate, endDate);
                if (accelerations.size() > 0) {
                    Double x_values = 0.0, y_values = 0.0, z_values = 0.0, avg_x = 0.0, avg_y = 0.0, avg_z = 0.0;
                    for (int i = 0; i < accelerations.size(); i++) {
                        x_values += accelerations.get(i).getX_axis();
                        y_values += accelerations.get(i).getY_axis();
                        z_values += accelerations.get(i).getZ_axis();
                    }
                    avg_x = x_values / accelerations.size();
                    avg_y = y_values / accelerations.size();
                    avg_z = z_values / accelerations.size();
                    display = "Average X: " + avg_x + "\n" + "Average Y: " + avg_y + "\n" + "Average Z: " + avg_z;
                }
                else{
                    display = "There are no values of accelerations in past One hour.";
                }
                avg_acc_view.setText(display);
            }
        });

        avg_temperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date endDate = Calendar.getInstance().getTime();
                calendar = Calendar.getInstance();
                calendar.setTime(endDate);
                calendar.add(Calendar.HOUR, -1);
                Date startDate = calendar.getTime();
                String display = "";
                List<TemperatureEntity> temperatures = RoomDatabaseActivity.getInstance(getApplicationContext()).temperatureDao().getPastOneHourTemp(startDate, endDate);
                if (temperatures.size() > 0) {
                    Double x_values = 0.0, avg_x = 0.0;
                    for (int i = 0; i < temperatures.size(); i++) {
                        x_values += temperatures.get(i).getValue_0();
                    }
                    avg_x = x_values / temperatures.size();

                    display = "Average Temperature: " + "\n" + "   " + avg_x + "\n";
                }
                else{
                    display = "There are no values of Temperature in past One hour.";
                }
                avg_temp_view.setText(display);
            }
        });

        motion_detect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String display = "Switch on Accelerometer";
                List<AccelerometerEntity> accelerations_motion = RoomDatabaseActivity.getInstance(getApplicationContext()).accelerometerDao().getMotionValues();
                if(accelerations_motion.size() <= 30 && accelerations_motion.size() > 0){
                    int count_x = 0;
                    for (int i = 0; i<accelerations_motion.size(); i++){
                        if((int)Math.round(accelerations_motion.get(i).getX_axis()) == 0){
                            count_x += 1;
                        }
                    }
                    int size_data = accelerations_motion.size()/2;
                    if (count_x >= size_data){
                        display = "The Phone is Stationary";
                    }
                    else{
                        display = "The Phone is in Motion";
                    }
                }
                motion_view.setText(display);
            }
        });
    }



    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
        locationManager.removeUpdates(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER && acc_flag == 1) {
            Log.d("RUN", "Accelerometer ON");
            AccelerometerEntity accelerometerEntity = new AccelerometerEntity(event.values[0], event.values[1], event.values[2], Calendar.getInstance().getTime());
            RoomDatabaseActivity.getInstance(getApplicationContext()).accelerometerDao().accelerometer_insert(accelerometerEntity);
            Log.d("RUN", "Accelerometer Data Added");
        }

        if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION && linear_flag == 1){
            Log.d("RUN", "Linear Acceleration ON");
            LinearAccelerationEntity linearAccelerationEntity = new LinearAccelerationEntity(event.values[0], event.values[1], event.values[2]);
            RoomDatabaseActivity.getInstance(getApplicationContext()).linearAccelerationDao().linearAcceleration_insert(linearAccelerationEntity);
            Log.d("RUN", "Linear Acceleration Data Added");
        }

        if (event.sensor.getType() == Sensor.TYPE_LIGHT && light_flag == 1){
            Log.d("RUN", "Light ON");
            LightEntity lightEntity = new LightEntity(event.values[0]);
            RoomDatabaseActivity.getInstance(getApplicationContext()).lightDao().light_insert(lightEntity);
            Log.d("RUN", "Light Data Added");
        }

        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY && proximity_flag == 1){
            Log.d("RUN", "Proximity ON");
            ProximityEntity proximityEntity = new ProximityEntity(event.values[0]);
            RoomDatabaseActivity.getInstance(getApplicationContext()).proximityDao().proximity_insert(proximityEntity);
            Log.d("RUN", "Proximity Data Added");
        }

        if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE && temp_flag == 1){
            Log.d("RUN", "Temperature ON");
            TemperatureEntity temperatureEntity = new TemperatureEntity(event.values[0], Calendar.getInstance().getTime());
            RoomDatabaseActivity.getInstance(getApplicationContext()).temperatureDao().temperature_insert(temperatureEntity);
            Log.d("RUN", "Temperature Data Added");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 100:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, MainActivity.this::onLocationChanged);
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, MainActivity.this);
                }
                return;
        }
    }


    @Override
    public void onLocationChanged(@NonNull Location location) {
        if(gps_flag == 1) {
            Log.d("RUN", "GPS ON");
            GPSEntity gpsEntity = new GPSEntity(location.getLongitude(), location.getLatitude(), location.getAltitude());
            RoomDatabaseActivity.getInstance(getApplicationContext()).gpsDao().gps_insert(gpsEntity);
            Log.d("RUN", "GPS Data Added");
        }
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);

    }
}