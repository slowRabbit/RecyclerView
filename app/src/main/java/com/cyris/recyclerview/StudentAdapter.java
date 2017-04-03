package com.cyris.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.List;

/**
 * Created by cyris on 1/4/17.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    List<Student> data;

    final private onItemClickListener mOnClickListener;
    Context context;
    private int lastPosition = -1;

    public StudentAdapter(List<Student> data, onItemClickListener mOnClickListener) {

        this.data = data;
        this.mOnClickListener = mOnClickListener;
    }


    public interface onItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.student_list_item, parent, shouldAttachToParentImmediately);
        StudentViewHolder viewHolder = new StudentViewHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {

        Student student = data.get(position);
        holder.bind(student);
        setAnimation(holder.itemView, position);

    }

    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.move_up);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
        else if(position<lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.move_down);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvFirstName, tvLastName;

        public StudentViewHolder(View itemView) {
            super(itemView);
            tvFirstName = (TextView) itemView.findViewById(R.id.tv_firstName);
            tvLastName = (TextView) itemView.findViewById(R.id.tv_lastName);
            itemView.setOnClickListener(this);

            //for setting individual click listeners :
//            itemView.setOnClickListener(this);
//            iconTextView.setOnClickListener(this);
//            iconImageView.setOnLongClickListener(this);
            //

        }

        void bind(Student student) {
            //we will bind the data here with view holder
            tvFirstName.setText(student.firstName);
            tvLastName.setText(student.lastName);
        }


        @Override
        public void onClick(View v) {

            int clickPosition=getAdapterPosition();
            mOnClickListener.onItemClick(clickPosition);
//            if(v.getId()==R.id.tv_firstName)
//            {
//                mOnClickListener.onItemClick(data.get(clickPosition).firstName);
//            }
//            else
//            {
//                mOnClickListener.onItemClick(data.get(clickPosition).lastName);
//            }



        }
    }

}
