package com.example.sensordata.DaoClass;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.sensordata.EntityClass.LightEntity;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class LightDao_Impl implements LightDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfLightEntity;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfLightEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllData;

  public LightDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLightEntity = new EntityInsertionAdapter<LightEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Light`(`ID`,`Value_0`) VALUES (nullif(?, 0),?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, LightEntity value) {
        stmt.bindLong(1, value.getID());
        stmt.bindDouble(2, value.getValue_0());
      }
    };
    this.__deletionAdapterOfLightEntity = new EntityDeletionOrUpdateAdapter<LightEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Light` WHERE `ID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, LightEntity value) {
        stmt.bindLong(1, value.getID());
      }
    };
    this.__preparedStmtOfDeleteAllData = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Light";
        return _query;
      }
    };
  }

  @Override
  public void light_insert(LightEntity lightEntity) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfLightEntity.insert(lightEntity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(LightEntity lightEntity) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfLightEntity.handle(lightEntity);
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
  public List<LightEntity> getAllValues() {
    final String _sql = "SELECT * FROM Light";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfID = _cursor.getColumnIndexOrThrow("ID");
      final int _cursorIndexOfValue0 = _cursor.getColumnIndexOrThrow("Value_0");
      final List<LightEntity> _result = new ArrayList<LightEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final LightEntity _item;
        final double _tmpValue_0;
        _tmpValue_0 = _cursor.getDouble(_cursorIndexOfValue0);
        _item = new LightEntity(_tmpValue_0);
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
