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
import com.roger.cv.model.AcademicStudies;
import com.roger.cv.model.Study;

import java.util.ArrayList;
import java.util.List;

public class AcademyViewModel extends AndroidViewModel {

    public MutableLiveData<List<AcademicStudies>> academicList = new MutableLiveData<List<AcademicStudies>>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> isError = new MutableLiveData<Boolean>();

    private String USER_NAME = "CV_APP";
    private String REFERENCE = "User/Rogelio";

    private  String ACADEMY_KEY = "academy";

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mCVDatabaseReference;

    public AcademyViewModel(@NonNull Application application) {
        super(application);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mCVDatabaseReference = mFirebaseDatabase.getReference(REFERENCE);
    }

    public void fetchData(){
        fetchFromFirebase();
    }

    private void fetchFromFirebase() {
        isLoading.setValue(false);
        mCVDatabaseReference.child(ACADEMY_KEY).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<AcademicStudies> academicStudiesList = new ArrayList<>();
                for (DataSnapshot ds: snapshot.getChildren()) {
                    AcademicStudies academicStudies = ds.getValue(AcademicStudies.class);
                    academicStudiesList.add(academicStudies);
                }
                academicList.setValue(academicStudiesList);
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
