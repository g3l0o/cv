package com.roger.cv.viewmodel;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.roger.cv.model.Job;
import com.roger.cv.model.JobDao;
import com.roger.cv.model.JobDatabase;
import com.roger.cv.util.SharedPreferenceHelper;

import java.util.ArrayList;
import java.util.List;

public class JobListViewModel extends AndroidViewModel {

    public MutableLiveData<List<Job>> jobs = new MutableLiveData<List<Job>>();
    public MutableLiveData<Boolean> isError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<Boolean>();

    private String USER_NAME = "CV_APP";
    private String REFERENCE = "User/Rogelio";

    private  String EXPERIENCE_KEY = "experience";

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mCVDatabaseReference;

    private AsyncTask<List<Job>, Void, List<Job>> insertTask;
    private AsyncTask<Void, Void, List<Job>> retrieveTask;

    private SharedPreferenceHelper preferenceHelper = SharedPreferenceHelper.getInstance(getApplication());
    private long refreshTime = 60 * 60 * 1_000 * 1_000 * 1_000L; //60 minutes in nanoseconds

    public JobListViewModel(@NonNull Application application) {
        super(application);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mCVDatabaseReference = mFirebaseDatabase.getReference(REFERENCE);
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        if(insertTask != null){
            insertTask.cancel(true);
            insertTask = null;
        }

        if(retrieveTask != null){
            retrieveTask.cancel(true);
            retrieveTask = null;
        }
    }

    public void refresh(){
        long updateTime = preferenceHelper.getJobsUpdateTime();
        long currentTime = System.nanoTime();

        if(updateTime != 0 && currentTime - updateTime < refreshTime) {
            fetchFromFirebase();
        }else {
            fetchFromLocalDatabase();
        }
    }

    public void refreshBypassCache(){
        fetchFromFirebase();
    }

    private void fetchFromLocalDatabase(){
        retrieveTask = new RetrieveJobsTask();
        retrieveTask.execute();
    }

    private void fetchFromFirebase(){
        isLoading.setValue(true);
        mCVDatabaseReference.child(EXPERIENCE_KEY).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Job> jobsList = new ArrayList<Job>();
                for(DataSnapshot ds : snapshot.getChildren()){
                    Job job = ds.getValue(Job.class);
                    jobsList.add(job);
                }
                insertTask = new InsertJobsTask();
                insertTask.execute(jobsList);

                Toast.makeText(getApplication(), "Data Retrieved from Firebase", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                isError.setValue(true);
                isLoading.setValue(false);
            }

        });
    }

    private void jobsRetrieved(List<Job> jobsList) {
        jobs.setValue(jobsList);
        isError.setValue(false);
        isLoading.setValue(false);
    }

    private class InsertJobsTask extends AsyncTask<List<Job>, Void, List<Job>>{
        @Override
        protected List<Job> doInBackground(List<Job>... lists) {
            List<Job> list = lists[0];
            JobDao dao = JobDatabase.getInstance(getApplication()).jobDao();

            //clean the database
            dao.deleteAllJobs();

            ArrayList<Job> newList = new ArrayList<>(list);
            List<Long> result = dao.insertAll(newList.toArray(new Job[0]));

            for (int i = 0; i < list.size(); i++){
                list.get(i).setUuid(result.get(i));
            }

            return list;
        }

        @Override
        protected void onPostExecute(List<Job> jobs) {
            jobsRetrieved(jobs);
            preferenceHelper.saveJobsUpdateTime(System.nanoTime());
        }
    }

    private class RetrieveJobsTask extends AsyncTask<Void, Void, List<Job>>{

        @Override
        protected List<Job> doInBackground(Void... voids) {
            return JobDatabase.getInstance(getApplication()).jobDao().getAllJobs();
        }

        @Override
        protected void onPostExecute(List<Job> jobs) {
            jobsRetrieved(jobs);
        }
    }

}
