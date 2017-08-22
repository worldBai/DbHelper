package com.example.xiaobai.dbdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xiaobai.dbdemo.R;
import com.example.xiaobai.dbdemo.bean.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bai on 2017/8/16.
 */

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.ViewHolder> {
    private List<Student> students;
    private Context context;
    private LayoutInflater inflater;

    public StudentsAdapter (Context context){
        students = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.student_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.ageTv .setText(students.get(position).getAge()+"");
        holder.nameTv.setText(students.get(position).getName());
        holder.sexTv.setText(students.get(position).getSex());
        holder.schoolTv.setText(students.get(position).getSchool());
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView nameTv;
        private final TextView ageTv;
        private final TextView sexTv;
        private final TextView schoolTv;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTv = ((TextView) itemView.findViewById(R.id.student_name));
            ageTv = ((TextView) itemView.findViewById(R.id.student_age));
            sexTv = ((TextView) itemView.findViewById(R.id.student_sex));
            schoolTv = ((TextView) itemView.findViewById(R.id.student_school));
        }
    }

    public void update(List<Student> students){
        this.students = students;
        notifyDataSetChanged();
    }
}
