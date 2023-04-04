package com.example.firebasearraycrudjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements BaseAdapter.ItemEvent {
    RecyclerView rvStudents;
    EditText etName;
    EditText etMath;
    EditText etScience;
    StudentsAdapter adapter;
    int selIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tvUrl = findViewById(R.id.tvUrl);
        tvUrl.setText("URL : " + RestApi.BASE_URL);
        etName = findViewById(R.id.etName);
        etMath = findViewById(R.id.etMath);
        etScience = findViewById(R.id.etScience);

        rvStudents = findViewById(R.id.rvStudents);
        adapter = StudentsAdapter.makeInstance(rvStudents, this);

        readData();
    }

    void updateRecyclerView(List<Student> students) {
        adapter.setList(students);
        adapter.notifyDataSetChanged();
    }

    void readData() {
        Call<List<Student>> call = RestApi.api.getStudents();
        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                List<Student> students = response.body();
                updateRecyclerView(students);
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) { }
        });
    }

    public class Student {
        String name;
        int math;
        int science;

        public Student(String name, int math, int science) {
            this.name = name;
            this.math = math;
            this.science = science;
        }

        public String getInfo() {
            return name + " | Math:" + math + " | Science: " + science;
        }
    }

    public void onBtnAdd(View v) {
        Student student = getInput();
        updateData(adapter.list.size(), student);
    }

    public void onBtnUpdate(View v) {
        if(selIndex < 0) return;
        Student student = getInput();
        updateData(selIndex, student);
        selIndex = -1;
    }

    void updateData(int index, Student student) {
        Call<Student> call = RestApi.api.updateStudent(index, student);
        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                readData();
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                readData();
            }
        });
    }

    public void onBtnDel(View v) {
        if(selIndex < 0) return;
        delData(selIndex);
        selIndex = -1;
    }

    void delData(int index) {
        Call<String> call = RestApi.api.delStudent(index);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                readData();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                readData();
            }
        });
    }

    Student getInput() {
        String name = etName.getText().toString();
        int math = Integer.parseInt(etMath.getText().toString());
        int science = Integer.parseInt(etScience.getText().toString());
        return new Student(name, math, science);
    }

    @Override
    public void onClickItem(int index) {
        selIndex = index;
        Student student = adapter.getData(index);
        etName.setText(student.name);
        etMath.setText(student.math + "");
        etScience.setText(student.science + "");
    }

}