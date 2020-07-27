package com.roger.cv.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.roger.cv.R;
import com.roger.cv.databinding.FragmentHomeBinding;
import com.roger.cv.model.Information;
import com.roger.cv.view.home.HomeClickListener;
import com.roger.cv.viewmodel.HomeViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment implements HomeClickListener {

    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;

    private long uuidProfile = 0L;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setClickListener(this);

        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        viewModel.fetchData();

        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.information.observe(getViewLifecycleOwner(), new Observer<Information>() {
            @Override
            public void onChanged(Information information) {
                if(information != null){
                    binding.cardHomeMenu.setVisibility(View.VISIBLE);
                    binding.textErrorHome.setVisibility(View.GONE);
                    binding.progressHome.setVisibility(View.GONE);

                    binding.setInformation(information);

                    uuidProfile = information.getUuid();
                }
            }
        });

        viewModel.isError.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isError) {
                binding.progressHome.setVisibility(View.GONE);
                binding.textErrorHome.setVisibility(isError ? View.VISIBLE : View.GONE);
                binding.cardHomeMenu.setVisibility(isError ? View.GONE : View.VISIBLE);
            }
        });

        viewModel.isLoading.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if(isLoading) {
                    binding.textErrorHome.setVisibility(View.GONE);
                    binding.cardHomeMenu.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onJobExperienceClicked(View v) {
        NavDirections action = HomeFragmentDirections.actionExperience();
        Navigation.findNavController(v).navigate(action);

    }

    @Override
    public void onAcademyClicked(View v) {

    }

    @Override
    public void onCoursesClicked(View v) {

    }

    @Override
    public void onCertificationsClicked(View v) {

    }

    @Override
    public void onTechnicalClicked(View v) {

    }

    @Override
    public void onExtracurricularClicked(View v) {

    }

    @Override
    public void onContestsClicked(View v) {

    }

    @Override
    public void onBooksClicked(View v) {

    }

    @Override
    public void onFactsClicked(View v) {

    }

    @Override
    public void onProfileClicked(View v) {
        HomeFragmentDirections.ActionProfile action = HomeFragmentDirections.actionProfile();
        action.setProfileUuid(uuidProfile);
        Navigation.findNavController(v).navigate(action);
    }
}