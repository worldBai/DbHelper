package com.example.xiaobai.dbdemo.db;

import android.content.Context;
import android.database.Cursor;

import com.example.xiaobai.dbdemo.bean.Student;

import java.util.List;

/**
 * Created by Administrator on 2017/8/9.
 */

public class DbManager {
    private DataBaseHelper dataBaseHelper;
    private Context context;

    public DbManager(Context context) {
        this.context = context;
        dataBaseHelper = new DataBaseHelper(context);
    }

    //创建表
    public void createTable(String tableName){
        String sql = "";
        dataBaseHelper.getWritableDatabase().execSQL(sql);
    }

    //添加一个学生
    public void insertStudent(Student student){
        String sql = "insert into students (name,sex,age,school) values (" + student.getName() + student.getSex() + student.getAge() + student.getSchool() +")" ;
        dataBaseHelper.getWritableDatabase().execSQL(sql);
    }

    //插入多个学生
    public void insertStudents(List<Student> students){
        String sql = "";
        dataBaseHelper.getWritableDatabase().execSQL(sql);
    }

    //通过年龄删除学生
    public void deleteStuByAge(int age){
        String sql = "delete from where is age = " + age;
        dataBaseHelper.getWritableDatabase().execSQL(sql);
    }

    //通过姓名删除学生
    public void deleteStuByName(String name){
        String sql = "delete from students where is name = " + name;
        dataBaseHelper.getWritableDatabase().execSQL(sql);
    }

    //查询所有学生
    public Cursor queryAll(){
        String sql = "select * from students";
        return dataBaseHelper.getWritableDatabase().rawQuery(sql,null);
    }

    //通过年龄查询学生
    public Cursor queryStudentByAge(int age){
        String sql = "select * from students where is age = " + age;
        return dataBaseHelper.getWritableDatabase().rawQuery(sql,null);
    }
}
