package com.cyris.recyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
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
        Student student3=new Student("Lovepreet", "Singh");
        Student student4=new Student("Dheeraj", "Asnani");
        Student student5=new Student("Panchal", "Mithilesh");
        Student student6=new Student("Jayant", "Sachdev");
        Student student7=new Student("Alpen", "lebe");

        data.add(student1);
        data.add(student2);
        data.add(student3);
        data.add(student4);
        data.add(student5);
        data.add(student6);
        data.add(student7);
        data.add(student1);
        data.add(student2);
        data.add(student3);
        data.add(student4);
        data.add(student5);
        data.add(student6);
        data.add(student7);

        data.add(student1);
        data.add(student2);
        data.add(student3);
        data.add(student4);
        data.add(student5);
        data.add(student6);
        data.add(student7);

        studentAdapter=new StudentAdapter(data, this);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(studentAdapter);


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    @Override
    public void onItemClick(int position) {

        Student student=data.get(position);
        //Toast.makeText(this, "Item clicked is :"+student.firstName+"-"+student.lastName, Toast.LENGTH_SHORT).show();

        Intent i=new Intent();
        i.setClass(this, DetailActivity.class);
        startActivity(i);

    }

    private ItemTouchHelper.Callback createHelperCallback() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        moveItem(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                        return true;
                    }

                    @Override
                    public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                        deleteItem(viewHolder.getAdapterPosition());
                    }
                };
        return simpleItemTouchCallback;
    }

    private void addItemToList() {

        //listData.add(item);
       // adapter.notifyItemInserted(listData.indexOf(item));
    }

    private void moveItem(int oldPos, int newPos) {

        Student item = (Student) data.get(oldPos);
        data.remove(oldPos);
        data.add(newPos, item);
        studentAdapter.notifyItemMoved(oldPos, newPos);
    }

    private void deleteItem(final int position) {
        data.remove(position);
        studentAdapter.notifyItemRemoved(position);
    }

}
