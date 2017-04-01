package com.cyris.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView)findViewById(R.id.rv_itemList);

        List<Student> data=new ArrayList<>();
        Student student1=new Student("Chetan", "Mann");
        Student student2=new Student("Monty", "Panesar");
        data.add(student1);
        data.add(student2);
        data.add(student1);
        data.add(student2);
        data.add(student1);
        data.add(student2);
        data.add(student1);
        data.add(student2);
        data.add(student1);
        data.add(student2);
        data.add(student1);
        data.add(student2);
        data.add(student1);
        data.add(student2);
        data.add(student1);
        data.add(student2);

        studentAdapter=new StudentAdapter(this, data);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(studentAdapter);

    }
}
