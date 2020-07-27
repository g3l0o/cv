package com.roger.cv.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.roger.cv.model.Job;
import com.roger.cv.model.JobDatabase;

public class JobDetailViewModel extends AndroidViewModel {

    public MutableLiveData<Job> jobLiveData = new MutableLiveData<Job>();
    private RetrieveJobTask jobTask;

    public JobDetailViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetch(long uuid){
        jobTask = new RetrieveJobTask();
        jobTask.execute(uuid);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (jobTask != null){
            jobTask.cancel(true);
            jobTask = null;
        }
    }

    private class RetrieveJobTask extends AsyncTask<Long, Void, Job>{

        @Override
        protected Job doInBackground(Long... longs) {
            long uuid = longs[0];
            return JobDatabase.getInstance(getApplication()).jobDao().getJob(uuid);
        }

        @Override
        protected void onPostExecute(Job job) {
            jobLiveData.setValue(job);
        }
    }
}
