package com.roger.cv.view.job;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.roger.cv.R;
import com.roger.cv.model.Job;
import com.roger.cv.viewmodel.JobListViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class JobListFragment extends Fragment {

    private JobListViewModel viewModel;
    private JobListAdapter jobListAdapter = new JobListAdapter(new ArrayList<Job>());

    @BindView(R.id.recycler_job_list)
    RecyclerView mRecyclerViewJobs;

    @BindView(R.id.text_error_message)
    TextView mTextViewError;

    @BindView(R.id.progress_loading)
    ProgressBar mProgressBarLoading;

    public JobListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(JobListViewModel.class);
        viewModel.refresh();

        mRecyclerViewJobs.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerViewJobs.setAdapter(jobListAdapter);

        observeViewModel();
    }

    private void observeViewModel(){
        viewModel.jobs.observe(getViewLifecycleOwner(), new Observer<List<Job>>() {
            @Override
            public void onChanged(List<Job> jobs) {
                if(jobs != null){
                    mRecyclerViewJobs.setVisibility(View.VISIBLE);
                    jobListAdapter.updateJobsList(jobs);
                }
            }
        });

        viewModel.isError.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isError) {
                mProgressBarLoading.setVisibility(View.GONE);

                if(isError){
                    mTextViewError.setVisibility(View.VISIBLE);
                    mRecyclerViewJobs.setVisibility(View.GONE);
                }else{
                    mTextViewError.setVisibility(View.GONE);
                    mRecyclerViewJobs.setVisibility(View.VISIBLE);
                }

            }
        });

        viewModel.isLoading.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if(isLoading){
                    mTextViewError.setVisibility(View.GONE);
                    mRecyclerViewJobs.setVisibility(View.GONE);
                }
            }
        });
    }
}