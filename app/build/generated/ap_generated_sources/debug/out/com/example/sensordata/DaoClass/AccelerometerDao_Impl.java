package com.example.sensordata.DaoClass;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.sensordata.DateTypeConverter;
import com.example.sensordata.EntityClass.AccelerometerEntity;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("unchecked")
public final class AccelerometerDao_Impl implements AccelerometerDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfAccelerometerEntity;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfAccelerometerEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllData;

  public AccelerometerDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAccelerometerEntity = new EntityInsertionAdapter<AccelerometerEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Accelerometer`(`ID`,`X_Axis`,`Y_Axis`,`Z_Axis`,`date`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, AccelerometerEntity value) {
        stmt.bindLong(1, value.getID());
        stmt.bindDouble(2, value.getX_axis());
        stmt.bindDouble(3, value.getY_axis());
        stmt.bindDouble(4, value.getZ_axis());
        final Long _tmp;
        _tmp = DateTypeConverter.fromDate(value.getDate());
        if (_tmp == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindLong(5, _tmp);
        }
      }
    };
    this.__deletionAdapterOfAccelerometerEntity = new EntityDeletionOrUpdateAdapter<AccelerometerEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Accelerometer` WHERE `ID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, AccelerometerEntity value) {
        stmt.bindLong(1, value.getID());
      }
    };
    this.__preparedStmtOfDeleteAllData = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Accelerometer";
        return _query;
      }
    };
  }

  @Override
  public void accelerometer_insert(AccelerometerEntity accelerometerEntity) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfAccelerometerEntity.insert(accelerometerEntity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(AccelerometerEntity accelerometerEntity) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfAccelerometerEntity.handle(accelerometerEntity);
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
  public List<AccelerometerEntity> getPastOneHourAcc(Date startDate, Date endDate) {
    final String _sql = "SELECT * FROM Accelerometer where date >= ? and date <= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    final Long _tmp;
    _tmp = DateTypeConverter.fromDate(startDate);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp);
    }
    _argIndex = 2;
    final Long _tmp_1;
    _tmp_1 = DateTypeConverter.fromDate(endDate);
    if (_tmp_1 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp_1);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfID = _cursor.getColumnIndexOrThrow("ID");
      final int _cursorIndexOfXAxis = _cursor.getColumnIndexOrThrow("X_Axis");
      final int _cursorIndexOfYAxis = _cursor.getColumnIndexOrThrow("Y_Axis");
      final int _cursorIndexOfZAxis = _cursor.getColumnIndexOrThrow("Z_Axis");
      final int _cursorIndexOfDate = _cursor.getColumnIndexOrThrow("date");
      final List<AccelerometerEntity> _result = new ArrayList<AccelerometerEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final AccelerometerEntity _item;
        final double _tmpX_axis;
        _tmpX_axis = _cursor.getDouble(_cursorIndexOfXAxis);
        final double _tmpY_axis;
        _tmpY_axis = _cursor.getDouble(_cursorIndexOfYAxis);
        final double _tmpZ_axis;
        _tmpZ_axis = _cursor.getDouble(_cursorIndexOfZAxis);
        final Date _tmpDate;
        final Long _tmp_2;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmp_2 = null;
        } else {
          _tmp_2 = _cursor.getLong(_cursorIndexOfDate);
        }
        _tmpDate = DateTypeConverter.toDate(_tmp_2);
        _item = new AccelerometerEntity(_tmpX_axis,_tmpY_axis,_tmpZ_axis,_tmpDate);
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

  @Override
  public List<AccelerometerEntity> getMotionValues() {
    final String _sql = "SELECT * FROM Accelerometer ORDER BY id DESC LIMIT 30";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfID = _cursor.getColumnIndexOrThrow("ID");
      final int _cursorIndexOfXAxis = _cursor.getColumnIndexOrThrow("X_Axis");
      final int _cursorIndexOfYAxis = _cursor.getColumnIndexOrThrow("Y_Axis");
      final int _cursorIndexOfZAxis = _cursor.getColumnIndexOrThrow("Z_Axis");
      final int _cursorIndexOfDate = _cursor.getColumnIndexOrThrow("date");
      final List<AccelerometerEntity> _result = new ArrayList<AccelerometerEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final AccelerometerEntity _item;
        final double _tmpX_axis;
        _tmpX_axis = _cursor.getDouble(_cursorIndexOfXAxis);
        final double _tmpY_axis;
        _tmpY_axis = _cursor.getDouble(_cursorIndexOfYAxis);
        final double _tmpZ_axis;
        _tmpZ_axis = _cursor.getDouble(_cursorIndexOfZAxis);
        final Date _tmpDate;
        final Long _tmp;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(_cursorIndexOfDate);
        }
        _tmpDate = DateTypeConverter.toDate(_tmp);
        _item = new AccelerometerEntity(_tmpX_axis,_tmpY_axis,_tmpZ_axis,_tmpDate);
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
