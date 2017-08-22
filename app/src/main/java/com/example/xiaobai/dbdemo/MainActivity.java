package com.example.xiaobai.dbdemo;

import android.Manifest;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.xiaobai.dbdemo.adapter.StudentsAdapter;
import com.example.xiaobai.dbdemo.base.BaseActivity;
import com.example.xiaobai.dbdemo.bean.Student;
import com.example.xiaobai.dbdemo.db.DbManager;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 需要进行检测的权限数组
     */
    protected String[] needPermissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
    };


    private static final String TAG = "MainActivity";
    private DbManager manager;
    private Button deleteStu;
    private Button addStu;
    private Button allStu;
    private RecyclerView stuList;
    private StudentsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkPermissions(needPermissions);
        setContentView(R.layout.activity_main);
        manager = new DbManager(this);
        initView();
        setClick();
    }

    private void initView() {
        deleteStu = ((Button) findViewById(R.id.delete_student));
        addStu = ((Button) findViewById(R.id.add_student));
        allStu = ((Button) findViewById(R.id.all_student));
        stuList = ((RecyclerView) findViewById(R.id.recycler));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        stuList.setLayoutManager(layoutManager);
        adapter = new StudentsAdapter(this);stuList.setAdapter(adapter);
    }

    private void setClick() {
        deleteStu.setOnClickListener(this);
        addStu.setOnClickListener(this);
        allStu.setOnClickListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSON_REQUESTCODE){
            if (verifyPermissions(grantResults)){
                Log.i(TAG, "onRequestPermissionsResult: 已获取全部权限" );
            }else {
                Log.i(TAG, "onRequestPermissionsResult: 部分权限未获取");
            }
        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.all_student:
                adapter.update(manager.queryAll());
                break;
            case R.id.add_student:
                Student student = new Student("张三","男","剑桥",18);
                manager.insertStudent(student);
                break;
            case R.id.delete_student:
                manager.deleteStuByAge(18);
                break;
        }
    }
}
