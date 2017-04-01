package com.cyris.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements StudentAdapter.onItemClickListener{

    RecyclerView recyclerView;
    StudentAdapter studentAdapter;
    List<Student> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView)findViewById(R.id.rv_itemList);

        data=new ArrayList<>();
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

        studentAdapter=new StudentAdapter(data, this);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(studentAdapter);

    }

    @Override
    public void onItemClick(int position) {

        Student student=data.get(position);
        Toast.makeText(this, "Item clicked is :"+student.firstName+"-"+student.lastName, Toast.LENGTH_SHORT).show();

    }
}
