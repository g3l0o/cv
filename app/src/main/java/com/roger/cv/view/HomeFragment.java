package com.roger.cv.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.roger.cv.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {

    @BindView(R.id.card_job)
    CardView mCardViewJob;

    @BindView(R.id.card_academy)
    CardView mCardViewAcademy;

    @BindView(R.id.card_courses)
    CardView mCardViewCourse;

    @BindView(R.id.card_certifications)
    CardView mCardViewCertifications;

    @BindView(R.id.card_technical)
    CardView mCardViewTechnical;

    @BindView(R.id.card_exracurricular)
    CardView mCardViewExtracurricular;

    @BindView(R.id.card_contests)
    CardView mCardViewContests;

    @BindView(R.id.card_books)
    CardView mCardViewBooks;

    @BindView(R.id.card_facts)
    CardView mCardViewFacts;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCardViewJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGoToDetails();
            }
        });
    }

    private void onGoToDetails(){
        NavDirections action = HomeFragmentDirections.actionExperience();
        Navigation.findNavController(mCardViewJob).navigate(action);
    }

}