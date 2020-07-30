package com.roger.cv.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.roger.cv.model.Job;
import com.roger.cv.model.TechnicalSkill;

import java.util.ArrayList;
import java.util.List;

public class TechnicalViewModel extends AndroidViewModel {

    public MutableLiveData<List<TechnicalSkill>> technicalSkills = new MutableLiveData<List<TechnicalSkill>>();
    public MutableLiveData<Boolean> isError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<Boolean>();

    private String USER_NAME = "CV_APP";
    private String REFERENCE = "User/Rogelio";

    private  String TECH_SKILL_KEY = "tech_skills";

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mCVDatabaseReference;

    private AsyncTask<List<Job>, Void, List<Job>> insertTask;
    private AsyncTask<Void, Void, List<Job>> retrieveTask;

    public TechnicalViewModel(@NonNull Application application) {
        super(application);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mCVDatabaseReference = mFirebaseDatabase.getReference(REFERENCE);
    }

    public void fetchData(){
        fetchFromFirebase();
    }

    private void fetchFromFirebase(){
        isLoading.setValue(true);
        mCVDatabaseReference.child(TECH_SKILL_KEY).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<TechnicalSkill> technicalSkillsList = new ArrayList<TechnicalSkill>();
                for (DataSnapshot ds : snapshot.getChildren()){
                    TechnicalSkill skill = ds.getValue(TechnicalSkill.class);
                    technicalSkillsList.add(skill);
                }
                technicalSkills.setValue(technicalSkillsList);
                isLoading.setValue(false);
                isError.setValue(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                isLoading.setValue(false);
                isError.setValue(true);
            }
        });
    }

}
