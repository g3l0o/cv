package com.roger.cv.view.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.roger.cv.R;
import com.roger.cv.databinding.FragmentProfileBinding;
import com.roger.cv.model.Information;
import com.roger.cv.viewmodel.ProfileViewModel;


public class ProfileFragment extends Fragment implements ProfileClickListener{

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

        binding.setClickListener(this);

        observeViewModel();

    }

    @Override
    public void onClickWhatsApp(View v) {
        String url = binding.getInformation().getWhatsApp();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    private void setInformationBinding(Information information){
        binding.setInformation(information);
        binding.textProfileAge.setText(getResources().getQuantityString(R.plurals.yearsOld, information.getAge(), information.getAge()));
    }

    private void observeViewModel() {
        viewModel.informationLiveData.observe(getViewLifecycleOwner(), new Observer<Information>() {
            @Override
            public void onChanged(Information information) {
                setInformationBinding(information);
            }
        });
    }
}