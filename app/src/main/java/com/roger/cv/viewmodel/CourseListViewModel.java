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
import com.roger.cv.model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseListViewModel extends AndroidViewModel {

    public MutableLiveData<List<Course>> courseList = new MutableLiveData<List<Course>>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> isError = new MutableLiveData<Boolean>();

    private String USER_NAME = "CV_APP";
    private String REFERENCE = "User/Rogelio";

    private  String COURSES_KEY = "courses";

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mCVDatabaseReference;

    public CourseListViewModel(@NonNull Application application) {
        super(application);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mCVDatabaseReference = mFirebaseDatabase.getReference(REFERENCE);
    }

    public void fetchData(){
        fetchFromFirebase();
    }

    private void fetchFromFirebase() {
        isLoading.setValue(false);
        mCVDatabaseReference.child(COURSES_KEY).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Course> courses = new ArrayList<>();
                for (DataSnapshot ds: snapshot.getChildren()) {
                    Course course = ds.getValue(Course.class);
                    courses.add(course);
                }

                courseList.setValue(courses);
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