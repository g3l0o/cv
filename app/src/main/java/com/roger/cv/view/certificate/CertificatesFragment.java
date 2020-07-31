package com.roger.cv.view.certificate;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roger.cv.R;
import com.roger.cv.databinding.CertificateItemBinding;
import com.roger.cv.databinding.FragmentCertificationsBinding;
import com.roger.cv.model.Certificate;
import com.roger.cv.viewmodel.CertificateViewModel;

import java.util.ArrayList;
import java.util.List;


public class CertificatesFragment extends Fragment {

    private CertificateViewModel viewModel;
    private FragmentCertificationsBinding binding;
    private CertificateListAdapter certificateListAdapter = new CertificateListAdapter(new ArrayList<Certificate>());


    public CertificatesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_certifications, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(CertificateViewModel.class);
        viewModel.fetchData();

        binding.recyclerCertificateList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerCertificateList.setAdapter(certificateListAdapter);

        binding.swiperefreshCertificates.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                binding.swiperefreshCertificates.setRefreshing(false);
            }
        });

        observeViewModel();

    }

    private void observeViewModel() {
        viewModel.certificatesList.observe(getViewLifecycleOwner(), new Observer<List<Certificate>>() {
            @Override
            public void onChanged(List<Certificate> certificates) {
                binding.recyclerCertificateList.setVisibility(View.VISIBLE);
                certificateListAdapter.updateCertificateList(certificates);
            }
        });

        viewModel.isError.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isError) {
                binding.progressLoading.setVisibility(View.GONE);
                binding.textErrorMessage.setVisibility(isError ? View.VISIBLE : View.GONE);
                binding.recyclerCertificateList.setVisibility(isError ? View.GONE : View.VISIBLE);
            }
        });

        viewModel.isLoading.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if(isLoading){
                    binding.textErrorMessage.setVisibility(View.GONE);
                    binding.recyclerCertificateList.setVisibility(View.GONE);
                }
            }
        });
    }
}