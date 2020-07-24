package com.roger.cv.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.roger.cv.model.Job;

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


    public JobListViewModel(@NonNull Application application) {
        super(application);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mCVDatabaseReference = mFirebaseDatabase.getReference(REFERENCE);

    }

    public void refresh(){
        fetchFromRemote();
    }

    private void fetchFromRemote(){
        isLoading.setValue(true);
        mCVDatabaseReference.child(EXPERIENCE_KEY).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Job> jobsList = new ArrayList<Job>();
                for(DataSnapshot ds : snapshot.getChildren()){
                    Job job = ds.getValue(Job.class);
                    jobsList.add(job);
                }

                jobs.setValue(jobsList);
                isError.setValue(false);
                isLoading.setValue(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                isError.setValue(true);
                isLoading.setValue(false);
            }

        });
    }

}
