package com.example.sensordata;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.example.sensordata.DaoClass.AccelerometerDao;
import com.example.sensordata.DaoClass.AccelerometerDao_Impl;
import com.example.sensordata.DaoClass.GPSDao;
import com.example.sensordata.DaoClass.GPSDao_Impl;
import com.example.sensordata.DaoClass.LightDao;
import com.example.sensordata.DaoClass.LightDao_Impl;
import com.example.sensordata.DaoClass.LinearAccelerationDao;
import com.example.sensordata.DaoClass.LinearAccelerationDao_Impl;
import com.example.sensordata.DaoClass.ProximityDao;
import com.example.sensordata.DaoClass.ProximityDao_Impl;
import com.example.sensordata.DaoClass.TemperatureDao;
import com.example.sensordata.DaoClass.TemperatureDao_Impl;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public final class RoomDatabaseActivity_Impl extends RoomDatabaseActivity {
  private volatile AccelerometerDao _accelerometerDao;

  private volatile LinearAccelerationDao _linearAccelerationDao;

  private volatile GPSDao _gPSDao;

  private volatile LightDao _lightDao;

  private volatile TemperatureDao _temperatureDao;

  private volatile ProximityDao _proximityDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Accelerometer` (`ID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `X_Axis` REAL NOT NULL, `Y_Axis` REAL NOT NULL, `Z_Axis` REAL NOT NULL, `date` INTEGER)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `LinearAcceleration` (`ID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `X_Axis` REAL NOT NULL, `Y_Axis` REAL NOT NULL, `Z_Axis` REAL NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Light` (`ID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `Value_0` REAL NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Proximity` (`ID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `Value_0` REAL NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Temperature` (`ID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `Value_0` REAL NOT NULL, `date` INTEGER)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `GPS` (`ID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `Longitude` REAL NOT NULL, `Latitude` REAL NOT NULL, `Altitude` REAL NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"b45aa547a6caf1374f067c94497d5408\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Accelerometer`");
        _db.execSQL("DROP TABLE IF EXISTS `LinearAcceleration`");
        _db.execSQL("DROP TABLE IF EXISTS `Light`");
        _db.execSQL("DROP TABLE IF EXISTS `Proximity`");
        _db.execSQL("DROP TABLE IF EXISTS `Temperature`");
        _db.execSQL("DROP TABLE IF EXISTS `GPS`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsAccelerometer = new HashMap<String, TableInfo.Column>(5);
        _columnsAccelerometer.put("ID", new TableInfo.Column("ID", "INTEGER", true, 1));
        _columnsAccelerometer.put("X_Axis", new TableInfo.Column("X_Axis", "REAL", true, 0));
        _columnsAccelerometer.put("Y_Axis", new TableInfo.Column("Y_Axis", "REAL", true, 0));
        _columnsAccelerometer.put("Z_Axis", new TableInfo.Column("Z_Axis", "REAL", true, 0));
        _columnsAccelerometer.put("date", new TableInfo.Column("date", "INTEGER", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAccelerometer = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAccelerometer = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAccelerometer = new TableInfo("Accelerometer", _columnsAccelerometer, _foreignKeysAccelerometer, _indicesAccelerometer);
        final TableInfo _existingAccelerometer = TableInfo.read(_db, "Accelerometer");
        if (! _infoAccelerometer.equals(_existingAccelerometer)) {
          throw new IllegalStateException("Migration didn't properly handle Accelerometer(com.example.sensordata.EntityClass.AccelerometerEntity).\n"
                  + " Expected:\n" + _infoAccelerometer + "\n"
                  + " Found:\n" + _existingAccelerometer);
        }
        final HashMap<String, TableInfo.Column> _columnsLinearAcceleration = new HashMap<String, TableInfo.Column>(4);
        _columnsLinearAcceleration.put("ID", new TableInfo.Column("ID", "INTEGER", true, 1));
        _columnsLinearAcceleration.put("X_Axis", new TableInfo.Column("X_Axis", "REAL", true, 0));
        _columnsLinearAcceleration.put("Y_Axis", new TableInfo.Column("Y_Axis", "REAL", true, 0));
        _columnsLinearAcceleration.put("Z_Axis", new TableInfo.Column("Z_Axis", "REAL", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLinearAcceleration = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLinearAcceleration = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLinearAcceleration = new TableInfo("LinearAcceleration", _columnsLinearAcceleration, _foreignKeysLinearAcceleration, _indicesLinearAcceleration);
        final TableInfo _existingLinearAcceleration = TableInfo.read(_db, "LinearAcceleration");
        if (! _infoLinearAcceleration.equals(_existingLinearAcceleration)) {
          throw new IllegalStateException("Migration didn't properly handle LinearAcceleration(com.example.sensordata.EntityClass.LinearAccelerationEntity).\n"
                  + " Expected:\n" + _infoLinearAcceleration + "\n"
                  + " Found:\n" + _existingLinearAcceleration);
        }
        final HashMap<String, TableInfo.Column> _columnsLight = new HashMap<String, TableInfo.Column>(2);
        _columnsLight.put("ID", new TableInfo.Column("ID", "INTEGER", true, 1));
        _columnsLight.put("Value_0", new TableInfo.Column("Value_0", "REAL", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLight = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLight = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLight = new TableInfo("Light", _columnsLight, _foreignKeysLight, _indicesLight);
        final TableInfo _existingLight = TableInfo.read(_db, "Light");
        if (! _infoLight.equals(_existingLight)) {
          throw new IllegalStateException("Migration didn't properly handle Light(com.example.sensordata.EntityClass.LightEntity).\n"
                  + " Expected:\n" + _infoLight + "\n"
                  + " Found:\n" + _existingLight);
        }
        final HashMap<String, TableInfo.Column> _columnsProximity = new HashMap<String, TableInfo.Column>(2);
        _columnsProximity.put("ID", new TableInfo.Column("ID", "INTEGER", true, 1));
        _columnsProximity.put("Value_0", new TableInfo.Column("Value_0", "REAL", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysProximity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesProximity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoProximity = new TableInfo("Proximity", _columnsProximity, _foreignKeysProximity, _indicesProximity);
        final TableInfo _existingProximity = TableInfo.read(_db, "Proximity");
        if (! _infoProximity.equals(_existingProximity)) {
          throw new IllegalStateException("Migration didn't properly handle Proximity(com.example.sensordata.EntityClass.ProximityEntity).\n"
                  + " Expected:\n" + _infoProximity + "\n"
                  + " Found:\n" + _existingProximity);
        }
        final HashMap<String, TableInfo.Column> _columnsTemperature = new HashMap<String, TableInfo.Column>(3);
        _columnsTemperature.put("ID", new TableInfo.Column("ID", "INTEGER", true, 1));
        _columnsTemperature.put("Value_0", new TableInfo.Column("Value_0", "REAL", true, 0));
        _columnsTemperature.put("date", new TableInfo.Column("date", "INTEGER", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTemperature = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTemperature = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTemperature = new TableInfo("Temperature", _columnsTemperature, _foreignKeysTemperature, _indicesTemperature);
        final TableInfo _existingTemperature = TableInfo.read(_db, "Temperature");
        if (! _infoTemperature.equals(_existingTemperature)) {
          throw new IllegalStateException("Migration didn't properly handle Temperature(com.example.sensordata.EntityClass.TemperatureEntity).\n"
                  + " Expected:\n" + _infoTemperature + "\n"
                  + " Found:\n" + _existingTemperature);
        }
        final HashMap<String, TableInfo.Column> _columnsGPS = new HashMap<String, TableInfo.Column>(4);
        _columnsGPS.put("ID", new TableInfo.Column("ID", "INTEGER", true, 1));
        _columnsGPS.put("Longitude", new TableInfo.Column("Longitude", "REAL", true, 0));
        _columnsGPS.put("Latitude", new TableInfo.Column("Latitude", "REAL", true, 0));
        _columnsGPS.put("Altitude", new TableInfo.Column("Altitude", "REAL", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysGPS = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesGPS = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoGPS = new TableInfo("GPS", _columnsGPS, _foreignKeysGPS, _indicesGPS);
        final TableInfo _existingGPS = TableInfo.read(_db, "GPS");
        if (! _infoGPS.equals(_existingGPS)) {
          throw new IllegalStateException("Migration didn't properly handle GPS(com.example.sensordata.EntityClass.GPSEntity).\n"
                  + " Expected:\n" + _infoGPS + "\n"
                  + " Found:\n" + _existingGPS);
        }
      }
    }, "b45aa547a6caf1374f067c94497d5408", "1a3dfa63aba814f319966ae215bf276d");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "Accelerometer","LinearAcceleration","Light","Proximity","Temperature","GPS");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Accelerometer`");
      _db.execSQL("DELETE FROM `LinearAcceleration`");
      _db.execSQL("DELETE FROM `Light`");
      _db.execSQL("DELETE FROM `Proximity`");
      _db.execSQL("DELETE FROM `Temperature`");
      _db.execSQL("DELETE FROM `GPS`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public AccelerometerDao accelerometerDao() {
    if (_accelerometerDao != null) {
      return _accelerometerDao;
    } else {
      synchronized(this) {
        if(_accelerometerDao == null) {
          _accelerometerDao = new AccelerometerDao_Impl(this);
        }
        return _accelerometerDao;
      }
    }
  }

  @Override
  public LinearAccelerationDao linearAccelerationDao() {
    if (_linearAccelerationDao != null) {
      return _linearAccelerationDao;
    } else {
      synchronized(this) {
        if(_linearAccelerationDao == null) {
          _linearAccelerationDao = new LinearAccelerationDao_Impl(this);
        }
        return _linearAccelerationDao;
      }
    }
  }

  @Override
  public GPSDao gpsDao() {
    if (_gPSDao != null) {
      return _gPSDao;
    } else {
      synchronized(this) {
        if(_gPSDao == null) {
          _gPSDao = new GPSDao_Impl(this);
        }
        return _gPSDao;
      }
    }
  }

  @Override
  public LightDao lightDao() {
    if (_lightDao != null) {
      return _lightDao;
    } else {
      synchronized(this) {
        if(_lightDao == null) {
          _lightDao = new LightDao_Impl(this);
        }
        return _lightDao;
      }
    }
  }

  @Override
  public TemperatureDao temperatureDao() {
    if (_temperatureDao != null) {
      return _temperatureDao;
    } else {
      synchronized(this) {
        if(_temperatureDao == null) {
          _temperatureDao = new TemperatureDao_Impl(this);
        }
        return _temperatureDao;
      }
    }
  }

  @Override
  public ProximityDao proximityDao() {
    if (_proximityDao != null) {
      return _proximityDao;
    } else {
      synchronized(this) {
        if(_proximityDao == null) {
          _proximityDao = new ProximityDao_Impl(this);
        }
        return _proximityDao;
      }
    }
  }
}
