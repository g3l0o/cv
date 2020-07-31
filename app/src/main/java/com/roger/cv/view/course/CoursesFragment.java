package com.roger.cv.view.course;

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
import com.roger.cv.databinding.FragmentCertificationsBinding;
import com.roger.cv.databinding.FragmentCoursesBinding;
import com.roger.cv.model.Certificate;
import com.roger.cv.model.Course;
import com.roger.cv.view.certificate.CertificateListAdapter;
import com.roger.cv.viewmodel.CertificateViewModel;
import com.roger.cv.viewmodel.CourseListViewModel;

import java.util.ArrayList;
import java.util.List;


public class CoursesFragment extends Fragment {

    private CourseListViewModel viewModel;
    private FragmentCoursesBinding binding;
    private CourseListAdapter courseListAdapter = new CourseListAdapter(new ArrayList<Course>());


    public CoursesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_courses, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(CourseListViewModel.class);
        viewModel.fetchData();

        binding.recyclerCourseList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerCourseList.setAdapter(courseListAdapter);

        binding.swiperefreshCourse.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                binding.swiperefreshCourse.setRefreshing(false);
                refreshManually();
            }
        });

        observeViewModel();
    }

    private void refreshManually() {
        binding.recyclerCourseList.setVisibility(View.GONE);
        binding.textErrorMessage.setVisibility(View.GONE);
        binding.progressLoading.setVisibility(View.VISIBLE);
        viewModel.fetchData();
    }

    private void observeViewModel() {
        viewModel.courseList.observe(getViewLifecycleOwner(), new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                binding.recyclerCourseList.setVisibility(View.VISIBLE);
                courseListAdapter.updateCourseList(courses);
            }
        });

        viewModel.isError.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isError) {
                binding.progressLoading.setVisibility(View.GONE);
                binding.textErrorMessage.setVisibility(isError ? View.VISIBLE : View.GONE);
                binding.recyclerCourseList.setVisibility(isError ? View.GONE : View.VISIBLE);
            }
        });

        viewModel.isLoading.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (isLoading) {
                    binding.textErrorMessage.setVisibility(View.GONE);
                    binding.recyclerCourseList.setVisibility(View.GONE);
                }
            }
        });
    }
}