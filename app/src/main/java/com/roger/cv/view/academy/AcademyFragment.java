package com.roger.cv.view.academy;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roger.cv.R;
import com.roger.cv.databinding.FragmentAcademyBinding;
import com.roger.cv.model.AcademicStudies;
import com.roger.cv.viewmodel.AcademyViewModel;

import java.util.ArrayList;
import java.util.List;

public class AcademyFragment extends Fragment {

    private AcademyViewModel viewModel;
    private FragmentAcademyBinding binding;
    private AcademyListAdapter academyListAdapter = new AcademyListAdapter(new ArrayList<AcademicStudies>());

    public AcademyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_academy, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(AcademyViewModel.class);
        viewModel.fetchData();

        binding.recyclerAcademyList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerAcademyList.setAdapter(academyListAdapter);

        binding.swiperefreshAcademy.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshManually();
                binding.swiperefreshAcademy.setRefreshing(false);
            }
        });
        observeViewModel();
    }

    private void refreshManually() {
        binding.recyclerAcademyList.setVisibility(View.GONE);
        binding.textErrorMessage.setVisibility(View.GONE);
        binding.progressLoading.setVisibility(View.VISIBLE);
        viewModel.fetchData();
    }

    private void observeViewModel() {
        viewModel.academicList.observe(getViewLifecycleOwner(), new Observer<List<AcademicStudies>>() {
            @Override
            public void onChanged(List<AcademicStudies> academicStudies) {
                binding.recyclerAcademyList.setVisibility(View.VISIBLE);
                academyListAdapter.updateAcademicStudiesList(academicStudies);
            }
        });

        viewModel.isError.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isError) {
                binding.progressLoading.setVisibility(View.GONE);
                binding.textErrorMessage.setVisibility(isError ? View.VISIBLE : View.GONE);
                binding.recyclerAcademyList.setVisibility(isError ? View.GONE : View.VISIBLE);
            }
        });

        viewModel.isLoading.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if(isLoading){
                    binding.textErrorMessage.setVisibility(View.GONE);
                    binding.recyclerAcademyList.setVisibility(View.GONE);
                }
            }
        });

    }
}