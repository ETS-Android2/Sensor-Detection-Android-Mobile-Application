package com.example.sensordata.DaoClass;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.sensordata.EntityClass.ProximityEntity;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class ProximityDao_Impl implements ProximityDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfProximityEntity;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfProximityEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllData;

  public ProximityDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfProximityEntity = new EntityInsertionAdapter<ProximityEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Proximity`(`ID`,`Value_0`) VALUES (nullif(?, 0),?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ProximityEntity value) {
        stmt.bindLong(1, value.getID());
        stmt.bindDouble(2, value.getValue_0());
      }
    };
    this.__deletionAdapterOfProximityEntity = new EntityDeletionOrUpdateAdapter<ProximityEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Proximity` WHERE `ID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ProximityEntity value) {
        stmt.bindLong(1, value.getID());
      }
    };
    this.__preparedStmtOfDeleteAllData = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Proximity";
        return _query;
      }
    };
  }

  @Override
  public void proximity_insert(ProximityEntity proximityEntity) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfProximityEntity.insert(proximityEntity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(ProximityEntity proximityEntity) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfProximityEntity.handle(proximityEntity);
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
  public List<ProximityEntity> getAllValues() {
    final String _sql = "SELECT * FROM Proximity";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfID = _cursor.getColumnIndexOrThrow("ID");
      final int _cursorIndexOfValue0 = _cursor.getColumnIndexOrThrow("Value_0");
      final List<ProximityEntity> _result = new ArrayList<ProximityEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final ProximityEntity _item;
        final double _tmpValue_0;
        _tmpValue_0 = _cursor.getDouble(_cursorIndexOfValue0);
        _item = new ProximityEntity(_tmpValue_0);
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
