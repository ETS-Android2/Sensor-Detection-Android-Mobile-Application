package com.example.sensordata.DaoClass;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.sensordata.DateTypeConverter;
import com.example.sensordata.EntityClass.TemperatureEntity;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("unchecked")
public final class TemperatureDao_Impl implements TemperatureDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfTemperatureEntity;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfTemperatureEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllData;

  public TemperatureDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTemperatureEntity = new EntityInsertionAdapter<TemperatureEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Temperature`(`ID`,`Value_0`,`date`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TemperatureEntity value) {
        stmt.bindLong(1, value.getID());
        stmt.bindDouble(2, value.getValue_0());
        final Long _tmp;
        _tmp = DateTypeConverter.fromDate(value.getDate());
        if (_tmp == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, _tmp);
        }
      }
    };
    this.__deletionAdapterOfTemperatureEntity = new EntityDeletionOrUpdateAdapter<TemperatureEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Temperature` WHERE `ID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TemperatureEntity value) {
        stmt.bindLong(1, value.getID());
      }
    };
    this.__preparedStmtOfDeleteAllData = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Temperature";
        return _query;
      }
    };
  }

  @Override
  public void temperature_insert(TemperatureEntity temperatureEntity) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfTemperatureEntity.insert(temperatureEntity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(TemperatureEntity temperatureEntity) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfTemperatureEntity.handle(temperatureEntity);
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
  public List<TemperatureEntity> getPastOneHourTemp(Date startDate, Date endDate) {
    final String _sql = "SELECT * FROM Temperature where date >= ? and date <= ?";
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
      final int _cursorIndexOfValue0 = _cursor.getColumnIndexOrThrow("Value_0");
      final int _cursorIndexOfDate = _cursor.getColumnIndexOrThrow("date");
      final List<TemperatureEntity> _result = new ArrayList<TemperatureEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TemperatureEntity _item;
        final double _tmpValue_0;
        _tmpValue_0 = _cursor.getDouble(_cursorIndexOfValue0);
        final Date _tmpDate;
        final Long _tmp_2;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmp_2 = null;
        } else {
          _tmp_2 = _cursor.getLong(_cursorIndexOfDate);
        }
        _tmpDate = DateTypeConverter.toDate(_tmp_2);
        _item = new TemperatureEntity(_tmpValue_0,_tmpDate);
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
  public List<TemperatureEntity> getAllValues() {
    final String _sql = "SELECT * FROM Temperature";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfID = _cursor.getColumnIndexOrThrow("ID");
      final int _cursorIndexOfValue0 = _cursor.getColumnIndexOrThrow("Value_0");
      final int _cursorIndexOfDate = _cursor.getColumnIndexOrThrow("date");
      final List<TemperatureEntity> _result = new ArrayList<TemperatureEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TemperatureEntity _item;
        final double _tmpValue_0;
        _tmpValue_0 = _cursor.getDouble(_cursorIndexOfValue0);
        final Date _tmpDate;
        final Long _tmp;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(_cursorIndexOfDate);
        }
        _tmpDate = DateTypeConverter.toDate(_tmp);
        _item = new TemperatureEntity(_tmpValue_0,_tmpDate);
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
