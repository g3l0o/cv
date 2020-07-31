package com.roger.cv.view.course;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.roger.cv.R;
import com.roger.cv.databinding.CourseItemBinding;
import com.roger.cv.model.Course;
import com.roger.cv.viewmodel.CourseListViewModel;

import java.util.ArrayList;
import java.util.List;

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.CourseViewHolder> {

    private ArrayList<Course> courseList;

    public CourseListAdapter(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }

    public void updateCourseList(List<Course> courses){
        courseList.clear();
        courseList.addAll(courses);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CourseItemBinding view = DataBindingUtil.inflate(inflater, R.layout.course_item, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courseList.get(position);
        holder.itemView.setCourse(course);
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }


    public class CourseViewHolder extends RecyclerView.ViewHolder{

        public CourseItemBinding itemView;

        public CourseViewHolder(@NonNull CourseItemBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }

}

