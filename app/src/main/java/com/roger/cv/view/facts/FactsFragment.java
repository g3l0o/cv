package com.roger.cv.view.facts;

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

import com.roger.cv.R;
import com.roger.cv.databinding.FragmentFactsBinding;
import com.roger.cv.model.Fact;
import com.roger.cv.util.Util;
import com.roger.cv.viewmodel.FactsViewModel;

import java.util.List;


public class FactsFragment extends Fragment {

    private FactsViewModel viewModel;
    private FragmentFactsBinding binding;

    public FactsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_facts, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(FactsViewModel.class);
        viewModel.fetchFacts();

        observeViewModel();
    }

    private void setRandomFact(List<Fact> factList){
        int totalFacts = factList.size() - 1;
        int randomFactPosition = Util.getRandomNumber(totalFacts);
        binding.setFact(factList.get(randomFactPosition));
    }

    private void observeViewModel() {
        viewModel.facts.observe(getViewLifecycleOwner(), new Observer<List<Fact>>() {
            @Override
            public void onChanged(List<Fact> facts) {
                setRandomFact(facts);
            }
        });
    }
}