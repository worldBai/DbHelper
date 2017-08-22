package com.example.xiaobai.dbdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.xiaobai.dbdemo.adapter.StudentsAdapter;
import com.example.xiaobai.dbdemo.bean.Student;
import com.example.xiaobai.dbdemo.db.DbManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 需要进行检测的权限数组
     */
    protected String[] needPermissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
    };

    private static final int PERMISSON_REQUESTCODE = 0;
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

    /**
     *
     * @param
     * @since 2.5.0
     * requestPermissions方法是请求某一权限，
     */
    private void checkPermissions(String... permissions) {
        List<String> needRequestPermissonList = findDeniedPermissions(permissions);
        if (null != needRequestPermissonList
                && needRequestPermissonList.size() > 0) {
            ActivityCompat.requestPermissions(this,
                    needRequestPermissonList.toArray(
                            new String[needRequestPermissonList.size()]),
                    PERMISSON_REQUESTCODE);
        }
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     * @since 2.5.0
     * checkSelfPermission方法是在用来判断是否app已经获取到某一个权限
     * shouldShowRequestPermissionRationale方法用来判断是否
     * 显示申请权限对话框，如果同意了或者不在询问则返回false
     */
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        for (String perm : permissions) {
            if (ContextCompat.checkSelfPermission(this,
                    perm) != PackageManager.PERMISSION_GRANTED) {
                needRequestPermissonList.add(perm);
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this, perm)) {
                    needRequestPermissonList.add(perm);
                }
            }
        }
        return needRequestPermissonList;
    }

    /**
     * 检测是否所有的权限都已经授权
     * @param grantResults
     * @return
     * @since 2.5.0
     *
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
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
