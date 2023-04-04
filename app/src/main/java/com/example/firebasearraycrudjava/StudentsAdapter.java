package com.example.firebasearraycrudjava;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentsAdapter extends BaseAdapter {

    public MainActivity.Student getData(int index) {
        return (MainActivity.Student)super.getData(index);
    }

    public static StudentsAdapter makeInstance(RecyclerView rv, ItemEvent itemEvent) {
        StudentsAdapter adapter = new StudentsAdapter();
        adapter.makeLayoutVertical(adapter, rv, R.layout.student_item, itemEvent);
        return adapter;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        View itemView = setClickable(holder, position);
        if(itemView == null) return;
        MainActivity.Student student = getData(position);

        TextView tvStudent = itemView.findViewById(R.id.tvStudent);
        View dividerBottom = itemView.findViewById(R.id.dividerBottom);

        if(student == null) {
            tvStudent.setVisibility(View.GONE);
            dividerBottom.setVisibility(View.GONE);
        } else {
            tvStudent.setVisibility(View.VISIBLE);
            tvStudent.setText(student.getInfo());
            dividerBottom.setVisibility(View.VISIBLE);
        }
    }

}
