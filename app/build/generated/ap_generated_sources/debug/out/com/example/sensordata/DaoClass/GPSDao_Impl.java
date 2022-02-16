package com.example.sensordata.DaoClass;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.sensordata.EntityClass.GPSEntity;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class GPSDao_Impl implements GPSDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfGPSEntity;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfGPSEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllData;

  public GPSDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfGPSEntity = new EntityInsertionAdapter<GPSEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `GPS`(`ID`,`Longitude`,`Latitude`,`Altitude`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, GPSEntity value) {
        stmt.bindLong(1, value.getID());
        stmt.bindDouble(2, value.getLongitude());
        stmt.bindDouble(3, value.getLatitude());
        stmt.bindDouble(4, value.getAltitude());
      }
    };
    this.__deletionAdapterOfGPSEntity = new EntityDeletionOrUpdateAdapter<GPSEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `GPS` WHERE `ID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, GPSEntity value) {
        stmt.bindLong(1, value.getID());
      }
    };
    this.__preparedStmtOfDeleteAllData = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM GPS";
        return _query;
      }
    };
  }

  @Override
  public void gps_insert(GPSEntity gpsEntity) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfGPSEntity.insert(gpsEntity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(GPSEntity gpsEntity) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfGPSEntity.handle(gpsEntity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllData() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllData.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllData.release(_stmt);
    }
  }

  @Override
  public List<GPSEntity> getAllValues() {
    final String _sql = "SELECT * FROM GPS";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfID = _cursor.getColumnIndexOrThrow("ID");
      final int _cursorIndexOfLongitude = _cursor.getColumnIndexOrThrow("Longitude");
      final int _cursorIndexOfLatitude = _cursor.getColumnIndexOrThrow("Latitude");
      final int _cursorIndexOfAltitude = _cursor.getColumnIndexOrThrow("Altitude");
      final List<GPSEntity> _result = new ArrayList<GPSEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final GPSEntity _item;
        final double _tmpLongitude;
        _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
        final double _tmpLatitude;
        _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
        final double _tmpAltitude;
        _tmpAltitude = _cursor.getDouble(_cursorIndexOfAltitude);
        _item = new GPSEntity(_tmpLongitude,_tmpLatitude,_tmpAltitude);
        final int _tmpID;
        _tmpID = _cursor.getInt(_cursorIndexOfID);
        _item.setID(_tmpID);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
