package com.cyris.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by cyris on 1/4/17.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder>{

    List<Student> data;
    Context context;

    public StudentAdapter(Context context, List<Student> data)
    {
        this.context=context;
        this.data=data;
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.student_list_item, parent, shouldAttachToParentImmediately);
        StudentViewHolder viewHolder = new StudentViewHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {

        Student student=data.get(position);
        holder.bind(student);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder {

        TextView tvFirstName, tvLastName;

        public StudentViewHolder(View itemView) {
            super(itemView);
            tvFirstName=(TextView)itemView.findViewById(R.id.tv_firstName);
            tvLastName=(TextView)itemView.findViewById(R.id.tv_lastName);

        }

        void bind(Student student)
        {
            //we will bind the data here with view holder
            tvFirstName.setText(student.firstName);
            tvLastName.setText(student.lastName);
        }

    }

}
