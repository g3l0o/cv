package com.roger.cv.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.roger.cv.model.Job;

import java.util.ArrayList;
import java.util.List;

public class JobListViewModel extends AndroidViewModel {

    public MutableLiveData<List<Job>> jobs = new MutableLiveData<List<Job>>();
    public MutableLiveData<Boolean> isError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<Boolean>();

    public JobListViewModel(@NonNull Application application) {
        super(application);
    }

    public void refresh(){
        Job job1 = new Job("false", "trabajo 1", "01/02/2020", "", "CELERIS", "01/06/2020", "Consultor BI");
        Job job2 = new Job("false", "trabajo 2", "01/02/2020", "", "MaTTica", "01/06/2020", "Consultor BI");
        Job job3 = new Job("false", "trabajo 3", "01/02/2020", "", "Wesay", "01/06/2020", "Consultor BI");

        ArrayList<Job> jobsList = new ArrayList<Job>();
        jobsList.add(job1);
        jobsList.add(job2);
        jobsList.add(job3);

        jobs.setValue(jobsList);
        isError.setValue(false);
        isLoading.setValue(false);
    }

}
