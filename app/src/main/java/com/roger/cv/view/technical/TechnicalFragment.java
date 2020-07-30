package com.roger.cv.view.technical;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roger.cv.R;
import com.roger.cv.databinding.FragmentTechnicalBinding;
import com.roger.cv.model.TechnicalSkill;
import com.roger.cv.viewmodel.TechnicalViewModel;

import java.util.ArrayList;
import java.util.List;


public class TechnicalFragment extends Fragment {

    private TechnicalViewModel viewModel;
    private FragmentTechnicalBinding binding;
    private TechnicalListAdapter technicalListAdapter = new TechnicalListAdapter(new ArrayList<TechnicalSkill>());

    public TechnicalFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_technical, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(TechnicalViewModel.class);
        viewModel.fetchData();

        binding.recyclerTechnicalList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerTechnicalList.setAdapter(technicalListAdapter);

        observeViewModel();

    }

    private void observeViewModel() {
        viewModel.technicalSkills.observe(getViewLifecycleOwner(), new Observer<List<TechnicalSkill>>() {
            @Override
            public void onChanged(List<TechnicalSkill> technicalSkills) {
                if(technicalSkills != null){
                    binding.recyclerTechnicalList.setVisibility(View.VISIBLE);
                    technicalListAdapter.updateTechnicalList(technicalSkills);
                }
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