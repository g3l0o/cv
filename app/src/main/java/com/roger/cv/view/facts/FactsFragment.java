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


public class FactsFragment extends Fragment implements FactClickListener{

    private FactsViewModel viewModel;
    private FragmentFactsBinding binding;
    private List<Fact> factList;

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

        binding.setClickListener(this);

        viewModel = ViewModelProviders.of(this).get(FactsViewModel.class);
        viewModel.fetchFacts();

        observeViewModel();
    }

    @Override
    public void onRandomClicked(View v) {
        getNewRandomFact();
    }

    private void setRandomFactList(List<Fact> factList){
        this.factList = factList;

        binding.textFactError.setVisibility(View.GONE);
        binding.progressFact.setVisibility(View.GONE);
        binding.textFact.setVisibility(View.VISIBLE);
        binding.buttonFact.setVisibility(View.VISIBLE);

        getNewRandomFact();

    }

    private void getNewRandomFact(){
        int randomFactPosition = Util.getRandomNumber(factList.size() - 1);
        binding.setFact(factList.get(randomFactPosition));
    }


    private void observeViewModel() {
        viewModel.facts.observe(getViewLifecycleOwner(), new Observer<List<Fact>>() {
            @Override
            public void onChanged(List<Fact> facts) {
                setRandomFactList(facts);
            }
        });

        viewModel.isError.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isError) {
                binding.progressFact.setVisibility(View.GONE);
                binding.textFactError.setVisibility(isError ? View.VISIBLE : View.GONE);
                binding.textFact.setVisibility(isError ? View.GONE : View.VISIBLE);
                binding.buttonFact.setVisibility(isError ? View.GONE : View.VISIBLE);
            }
        });

        viewModel.isLoading.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if(isLoading){
                    binding.textFactError.setVisibility(View.GONE);
                    binding.textFact.setVisibility(View.GONE);
                    binding.buttonFact.setVisibility(View.GONE);
                }
            }
        });

    }
}