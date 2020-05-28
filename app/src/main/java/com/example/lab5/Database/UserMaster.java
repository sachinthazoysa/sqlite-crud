package com.example.lab5.Database;

import android.provider.BaseColumns;

public final class UserMaster {
    private UserMaster() { }

     public static final class Users implements BaseColumns{
        public static final String TABLE_NAME = "Users";
        public static final String COLUMN_NAME_USER_NAME = "userName";
        public static final String COLUMN_NAME_PASSWORD = "password";
    }

}
