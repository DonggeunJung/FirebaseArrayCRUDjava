package com.example.firebasearraycrudjava;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StudentsAdapter extends BaseAdapter {
    List<MainActivity.Student> list = null;

    public void setList(List<MainActivity.Student> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.student_item, parent, false);
        return new BaseVH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(list == null || list.size() <= position) return;
        MainActivity.Student student = list.get(position);
        View itemView = ((BaseVH)holder).itemView;
        View layoutMain = itemView.findViewById(R.id.layoutMain);
        TextView tvStudent = itemView.findViewById(R.id.tvStudent);
        View dividerBottom = itemView.findViewById(R.id.dividerBottom);

        if(student == null) {
            tvStudent.setVisibility(View.GONE);
            dividerBottom.setVisibility(View.GONE);
        } else {
            tvStudent.setVisibility(View.VISIBLE);
            dividerBottom.setVisibility(View.VISIBLE);
            tvStudent.setText(student.getInfo());

            setClickable(layoutMain, position);
        }
    }

    @Override
    public int getItemCount() {
        if(list == null)
            return 0;
        return list.size();
    }

}
