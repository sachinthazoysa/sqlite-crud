package com.example.lab5.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "UserInfo.db";

    public DBHandler(@Nullable Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + UserMaster.Users.TABLE_NAME + " ("+
                  UserMaster.Users._ID + " INTEGER PRIMARY KEY," +
                        UserMaster.Users.COLUMN_NAME_USER_NAME + " TEXT," +
                        UserMaster.Users.COLUMN_NAME_PASSWORD + " TEXT)";

        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public long addInfo(String userName, String password){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(UserMaster.Users.COLUMN_NAME_USER_NAME, userName);
        contentValues.put(UserMaster.Users.COLUMN_NAME_PASSWORD, password);

        long insertReturn = db.insert(UserMaster.Users.TABLE_NAME,null,contentValues);

        return insertReturn;
    }

    public List ReadAllInfo(){
        SQLiteDatabase db = getReadableDatabase();

    String[] projection = {
            UserMaster.Users._ID,
            UserMaster.Users.COLUMN_NAME_USER_NAME,
            UserMaster.Users.COLUMN_NAME_PASSWORD
    };
    String sortOrder =
            UserMaster.Users.COLUMN_NAME_USER_NAME + " DESC ";

    Cursor cursor = db.query(
            UserMaster.Users.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            null,              // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder                   // The sort order
    );
    List userName = new ArrayList<>();
        while(cursor.moveToNext()){
        String username = cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.Users.COLUMN_NAME_USER_NAME));
        userName.add(username);
    }
        cursor.close();
        return userName;
}

    public boolean readInfo(String username, String password){
        SQLiteDatabase db = getReadableDatabase();
        boolean value = false;
        String[] projection = {
                UserMaster.Users._ID,
                UserMaster.Users.COLUMN_NAME_USER_NAME,
                UserMaster.Users.COLUMN_NAME_PASSWORD
        };

        String selection = UserMaster.Users.COLUMN_NAME_USER_NAME+ " = ?";
        String[] selectionArgs = { username};

        Cursor cursor = db.query(
                UserMaster.Users.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null                   // The sort order
        );

        while(cursor.moveToNext()){
            String userName = cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.Users.COLUMN_NAME_USER_NAME));
            String Passowrd = cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.Users.COLUMN_NAME_PASSWORD));

            value = username.equals(userName) && password.equals(Passowrd);
        }
        cursor.close();
        return value;
    }

    public int deleteInfo(String username, String password){
        boolean v = readInfo(username,password);
        SQLiteDatabase db = getReadableDatabase();

        if(v){
            String selection = UserMaster.Users.COLUMN_NAME_USER_NAME + " LIKE ?";
            String[] selectionArgs = { username };
            int deletedRows = db.delete(UserMaster.Users.TABLE_NAME, selection, selectionArgs);

            return deletedRows;
        }else{
            return -1;
        }
    }

    public void updateUser(String username, String password){
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(UserMaster.Users.COLUMN_NAME_PASSWORD,password);

            String selection = UserMaster.Users.COLUMN_NAME_USER_NAME + " LIKE ?";
            String[] selectionArgs = { username};

             int count = db.update(
                UserMaster.Users.TABLE_NAME,
                values,
                selection,
                selectionArgs);

    }
}
