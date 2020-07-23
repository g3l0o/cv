package com.roger.cv.view.job;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.roger.cv.R;
import com.roger.cv.model.Job;

import java.util.ArrayList;
import java.util.List;

public class JobListAdapter extends RecyclerView.Adapter<JobListAdapter.JobViewHolder> {

    private ArrayList<Job> jobsList;

    public JobListAdapter(ArrayList<Job> jobsList) {
        this.jobsList = jobsList;
    }

    public void updateJobsList(List<Job> jobs){
        jobsList.clear();
        jobsList.addAll(jobs);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_item, parent, false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        ImageButton mImageButtonLogo = holder.itemView.findViewById(R.id.imagebutton_company_logo);
        ImageButton mImageButtonViewMore = holder.itemView.findViewById(R.id.imagebutton_view_more);
        TextView mTextName = holder.itemView.findViewById(R.id.text_company_name);
        TextView mTextPosition = holder.itemView.findViewById(R.id.text_position);

        mTextName.setText(jobsList.get(position).getName());
        mTextPosition.setText(jobsList.get(position).getJobPosition());
    }

    @Override
    public int getItemCount() {
        return jobsList.size();
    }

    class JobViewHolder extends RecyclerView.ViewHolder{

        public View itemView;

        public JobViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }

}
