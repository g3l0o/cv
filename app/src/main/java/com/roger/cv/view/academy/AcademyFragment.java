package com.roger.cv.view.academy;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roger.cv.R;
import com.roger.cv.model.AcademicStudies;
import com.roger.cv.viewmodel.AcademyViewModel;

import java.util.List;

public class AcademyFragment extends Fragment {

    private AcademyViewModel viewModel;


    public AcademyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_academy, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(AcademyViewModel.class);
        viewModel.fetchData();

        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.academicList.observe(getViewLifecycleOwner(), new Observer<List<AcademicStudies>>() {
            @Override
            public void onChanged(List<AcademicStudies> academicStudies) {

            }
        });

        viewModel.isError.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isError) {

            }
        });

        viewModel.isLoading.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {

            }
        });

    }
}