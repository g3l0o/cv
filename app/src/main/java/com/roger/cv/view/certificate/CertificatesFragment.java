package com.roger.cv.view.certificate;

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
import com.roger.cv.model.Certificate;
import com.roger.cv.viewmodel.CertificateViewModel;

import java.util.List;


public class CertificatesFragment extends Fragment {

    private CertificateViewModel viewModel;


    public CertificatesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_certifications, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(CertificateViewModel.class);
        viewModel.fetchData();

        observeViewModel();

    }

    private void observeViewModel() {
        viewModel.certificatesList.observe(getViewLifecycleOwner(), new Observer<List<Certificate>>() {
            @Override
            public void onChanged(List<Certificate> certificates) {

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