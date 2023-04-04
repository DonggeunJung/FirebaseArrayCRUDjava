package com.example.firebasearraycrudjava;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentsAdapter extends BaseAdapter {

    public StudentsAdapter(int layoutItem, ItemEvent itemEvent) {
        super(layoutItem, itemEvent);
    }

    public MainActivity.Student getData(int index) {
        return (MainActivity.Student)super.getData(index);
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
