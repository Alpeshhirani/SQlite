package com.smartappsarena.daybook.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.widget.Toast;

public class DataBase {

    static Context ct;

    public static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context ctx) {

            super(ctx, Environment.getExternalStorageDirectory() + "/FolderName/Database/" + DATABASE_NAME, null, DATABASE_VERSION);
            ct = ctx;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            try {

                db.execSQL(buildtypeTableQuery());
                db.execSQL(buildmainTableQuery());
                db.execSQL(buildcategoryTableQuery());
                db.execSQL(buildamounttypeTableQuery());
                db.execSQL(buildbankTableQuery());
            } catch (SQLException e) {
                Toast.makeText(ct, "Table not created", Toast.LENGTH_LONG)
                        .show();
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + tbl_type);
                 onCreate(db);
        }

        @Override
        public synchronized SQLiteDatabase getWritableDatabase() {
            return super.getWritableDatabase();
        }
    }

    public DatabaseHelper dbHelper;
    public SQLiteDatabase sqLiteDb;
    private static Context HCtx = null;

    public static final String PACKAGE_NAME = "com.example.name";
    public static final String DATABASE_NAME = "data.db";

    private static final int DATABASE_VERSION = 1;

    public static final String tbl_type = "tblType";
    
    private static String buildtypeTableQuery() {

        String smsquery = "create table " + tbl_type + "(" + DataBaseField.TYPE_ID
                + " INTEGER, "
                + DataBaseField.TYPE_NAME + " TEXT); ";
        return smsquery;

    }

    
    private static String buildbankTableQuery() {

        String smsquery = "create table " + tbl_bank + "(" + DataBaseField.BANK_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DataBaseField.BANK_NAME + " TEXT); ";
        return smsquery;

    }

    public DataBase(Context ctx) {
        HCtx = ctx;
    }

    public DataBase open() throws SQLException {
        dbHelper = new DatabaseHelper(HCtx);
        sqLiteDb = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public synchronized long insert(String DATABASE_TABLE, ContentValues values) {
        return sqLiteDb.insert(DATABASE_TABLE, null, values);
    }

    public synchronized long update(String DATABASE_TABLE, ContentValues values, String whereClause) {
        return sqLiteDb.update(DATABASE_TABLE, values, whereClause, null);
    }

    public synchronized boolean delete(String DATABASE_TABLE, String whereCause, String[] strings) {
        return sqLiteDb.delete(DATABASE_TABLE, whereCause, strings) > 0;
    }

    public synchronized boolean deleteall(String DATABASE_TABLE) {
        return sqLiteDb.delete(DATABASE_TABLE, null, null) > 0;
    }

    public synchronized Cursor fetch(String DATABASE_TABLE, String where) throws SQLException {

        Cursor ret = sqLiteDb.query(DATABASE_TABLE, null, where, null, null,
                null, null);

        return ret;
    }

    public synchronized Cursor select(String DATABASE_TABLE, String[] column, String where, String[] strings, String group, String order)
            throws SQLException {

        Cursor ret = sqLiteDb.query(DATABASE_TABLE, column, where, strings, group, null, order);

        return ret;
    }

    public synchronized Cursor fetchAll(String DATABASE_TABLE) {
        try {
            return sqLiteDb.query(DATABASE_TABLE, null, null, null, null, null, null);
        } catch (Exception e) {
            return null;
        }
    }
}