package com.example.xiaobai.dbdemo.db;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.xiaobai.dbdemo.bean.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Administrator on 2017/8/9.
 */

public class DbManager {
    private static final String TAG = "DbManager";
    private DataBaseHelper dataBaseHelper;
    private Context context;
    private List<Student> students = new ArrayList<>();

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
        String sql = "insert into students (name,sex,age,school) values (?,?,?,?)";
        Object [] arrays = {student.getName(),student.getSex(),student.getAge(),student.getSchool()};

        dataBaseHelper.getWritableDatabase().execSQL(sql,arrays);
    }

    //插入多个学生
    public void insertStudents(List<Student> students){
        String sql = "";
        dataBaseHelper.getWritableDatabase().execSQL(sql);
    }

    //通过年龄删除学生
    public void deleteStuByAge(int age){
        String sql = "delete from students where age = " + age;
        dataBaseHelper.getWritableDatabase().execSQL(sql);
    }

    //通过姓名删除学生
    public void deleteStuByName(String name){
        String sql = "delete from students where is name = " + name;
        dataBaseHelper.getWritableDatabase().execSQL(sql);
    }

    //查询所有学生
    public List<Student> queryAll(){
        String sql = "select * from students";
        Cursor cursor = dataBaseHelper.getWritableDatabase().rawQuery(sql,null);
        return getStudents(cursor);
    }

    //通过年龄查询学生
    public List<Student> queryStudentByAge(int age){
        String sql = "select * from students where is age = " + age;
        Cursor cursor = dataBaseHelper.getWritableDatabase().rawQuery(sql,null);
        return getStudents(cursor);
    }

    public List<Student> getStudents(Cursor cursor){
        students.clear();
        while (cursor.moveToNext()){
            Student student = new Student(cursor.getString(1),cursor.getString(2),cursor.getString(4),cursor.getInt(3));
            students.add(student);
            Log.i(TAG, "studnet: " + student.toString());
        }
        Log.i(TAG, "getStudents: " + students.size());
        return students;
    }
}
