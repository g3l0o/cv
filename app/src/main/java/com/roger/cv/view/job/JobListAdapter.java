package com.roger.cv.view.job;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.roger.cv.R;
import com.roger.cv.databinding.JobItemBinding;
import com.roger.cv.model.Job;
import com.roger.cv.view.JobClickListener;

import androidx.databinding.DataBindingUtil;

import java.util.ArrayList;
import java.util.List;

public class JobListAdapter extends RecyclerView.Adapter<JobListAdapter.JobViewHolder> implements JobClickListener {

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
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        JobItemBinding view = DataBindingUtil.inflate(inflater, R.layout.job_item, parent, false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, final int position) {
        Job job = jobsList.get(position);
        holder.itemView.setJob(job);
        holder.itemView.setClickListener(this);
        holder.itemView.textCurrent.setVisibility(job.getCurrent() ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return jobsList.size();
    }

    @Override
    public void onJobClicked(View v) {
        String uuidString = ((TextView) v.findViewById(R.id.text_jobId)).getText().toString();
        long uuid = Long.parseLong(uuidString);

        JobListFragmentDirections.ActionJobDetail action = JobListFragmentDirections.actionJobDetail();
        action.setJobUuid(uuid);
        Navigation.findNavController(v).navigate(action);
    }

    class JobViewHolder extends RecyclerView.ViewHolder{

        public JobItemBinding itemView;

        public JobViewHolder(@NonNull JobItemBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }

}
