package com.roger.cv.view.profile;

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
import com.roger.cv.databinding.FragmentProfileBinding;
import com.roger.cv.model.Information;
import com.roger.cv.viewmodel.ProfileViewModel;


public class ProfileFragment extends Fragment {

    private ProfileViewModel viewModel;
    private FragmentProfileBinding binding;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        long profileUuid = 0L;

        if(getArguments() != null){
            profileUuid = ProfileFragmentArgs.fromBundle(getArguments()).getProfileUuid();
        }

        viewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        viewModel.fetch(profileUuid);

        observeViewModel();

    }

    private void observeViewModel() {
        viewModel.informationLiveData.observe(getViewLifecycleOwner(), new Observer<Information>() {
            @Override
            public void onChanged(Information information) {
                binding.setInformation(information);
            }
        });
    }
}