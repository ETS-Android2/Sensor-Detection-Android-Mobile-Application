package com.example.sensordata.DaoClass;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.sensordata.EntityClass.LinearAccelerationEntity;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class LinearAccelerationDao_Impl implements LinearAccelerationDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfLinearAccelerationEntity;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfLinearAccelerationEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllData;

  public LinearAccelerationDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLinearAccelerationEntity = new EntityInsertionAdapter<LinearAccelerationEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `LinearAcceleration`(`ID`,`X_Axis`,`Y_Axis`,`Z_Axis`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, LinearAccelerationEntity value) {
        stmt.bindLong(1, value.getID());
        stmt.bindDouble(2, value.getX_axis());
        stmt.bindDouble(3, value.getY_axis());
        stmt.bindDouble(4, value.getZ_axis());
      }
    };
    this.__deletionAdapterOfLinearAccelerationEntity = new EntityDeletionOrUpdateAdapter<LinearAccelerationEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `LinearAcceleration` WHERE `ID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, LinearAccelerationEntity value) {
        stmt.bindLong(1, value.getID());
      }
    };
    this.__preparedStmtOfDeleteAllData = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM LinearAcceleration";
        return _query;
      }
    };
  }

  @Override
  public void linearAcceleration_insert(LinearAccelerationEntity linearAccelerationEntity) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfLinearAccelerationEntity.insert(linearAccelerationEntity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(LinearAccelerationEntity linearAccelerationEntity) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfLinearAccelerationEntity.handle(linearAccelerationEntity);
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
  public List<LinearAccelerationEntity> getAllValues() {
    final String _sql = "SELECT * FROM LinearAcceleration";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfID = _cursor.getColumnIndexOrThrow("ID");
      final int _cursorIndexOfXAxis = _cursor.getColumnIndexOrThrow("X_Axis");
      final int _cursorIndexOfYAxis = _cursor.getColumnIndexOrThrow("Y_Axis");
      final int _cursorIndexOfZAxis = _cursor.getColumnIndexOrThrow("Z_Axis");
      final List<LinearAccelerationEntity> _result = new ArrayList<LinearAccelerationEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final LinearAccelerationEntity _item;
        final double _tmpX_axis;
        _tmpX_axis = _cursor.getDouble(_cursorIndexOfXAxis);
        final double _tmpY_axis;
        _tmpY_axis = _cursor.getDouble(_cursorIndexOfYAxis);
        final double _tmpZ_axis;
        _tmpZ_axis = _cursor.getDouble(_cursorIndexOfZAxis);
        _item = new LinearAccelerationEntity(_tmpX_axis,_tmpY_axis,_tmpZ_axis);
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
