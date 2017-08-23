package com.example.xiaobai.dbdemo.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.xiaobai.dbdemo.R;

/**
 * Created by bai on 2017/8/23.
 */

public class StudentInfoDialog extends Activity implements View.OnClickListener {
    private TextView title;
    private EditText nameEt;
    private EditText sexEt;
    private EditText ageEt;
    private EditText schoolEt;
    private Button sureBtn;
    private Button cancelBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_info_dialog_layout);
        initView();
        setClick();
    }

    private void setClick() {
        sureBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
    }

    private void initView() {
        title = ((TextView) findViewById(R.id.pop_title));
        nameEt = ((EditText) findViewById(R.id.student_name_et));
        sexEt = ((EditText) findViewById(R.id.student_sex_et));
        ageEt = ((EditText) findViewById(R.id.student_age_et));
        schoolEt = ((EditText) findViewById(R.id.student_school_et));
        sureBtn = ((Button) findViewById(R.id.pop_sure_btn));
        cancelBtn = ((Button) findViewById(R.id.pop_cancel_btn));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pop_cancel_btn:
                finish();
                break;
            case R.id.pop_sure_btn:
                finish();
                break;
        }
    }
}
