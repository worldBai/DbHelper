package com.example.xiaobai.dbdemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/8/9.
 */

public class DataBaseHelper extends SQLiteOpenHelper{
    private static final String DATA_BASE_NAME = "myDb.db"; //数据库名称
    private static final int DATA_BASE_VERSION = 1; //数据库版本

    public DataBaseHelper(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
    }

    //创建数据哭
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists students (Id integer primary key  autoincrement, name text, sex text, age integer, school text)";
        db.execSQL(sql);
    }

    //更新数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion>oldVersion){
            //更新数据库
        }
    }
}
