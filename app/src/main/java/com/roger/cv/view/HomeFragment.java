package com.roger.cv.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.roger.cv.R;
import com.roger.cv.databinding.FragmentHomeBinding;
import com.roger.cv.view.home.HomeClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment implements HomeClickListener {

    private FragmentHomeBinding binding;

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
}