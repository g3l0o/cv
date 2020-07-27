package com.roger.cv.view.job;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.roger.cv.R;
import com.roger.cv.databinding.FragmentJobDetailBinding;
import com.roger.cv.model.Job;
import com.roger.cv.util.Util;
import com.roger.cv.viewmodel.JobDetailViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JobDetailFragment extends Fragment {

    private JobDetailViewModel viewModel;
    private FragmentJobDetailBinding binding;

    public JobDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_job_detail, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        long jobUuid = 0L;

        if(getArguments() != null){
            jobUuid = JobDetailFragmentArgs.fromBundle(getArguments()).getJobUuid();
        }

        viewModel = ViewModelProviders.of(this).get(JobDetailViewModel.class);
        viewModel.fetch(jobUuid);

        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.jobLiveData.observe(getViewLifecycleOwner(), new Observer<Job>() {
            @Override
            public void onChanged(Job job) {
               binding.setJob(job);
            }
        });
    }
}