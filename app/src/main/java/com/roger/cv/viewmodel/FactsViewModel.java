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
import com.roger.cv.model.Fact;
import com.roger.cv.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FactsViewModel extends AndroidViewModel {

    public MutableLiveData<List<Fact>> facts = new MutableLiveData<List<Fact>>();

    private String USER_NAME = "CV_APP";
    private String REFERENCE = "User/Rogelio";

    private  String FACTS_KEY = "facts";

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mCVDatabaseReference;

    public FactsViewModel(@NonNull Application application) {
        super(application);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mCVDatabaseReference = mFirebaseDatabase.getReference(REFERENCE);

    }

    public void fetchFacts(){
        fetchFromFirebase();
    }

    private void updateFactsList(List<Fact> factList){
        facts.setValue(factList);
    }

    private void fetchFromFirebase(){
        mCVDatabaseReference.child(FACTS_KEY).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Fact> facts = new ArrayList<Fact>();
                for(DataSnapshot ds : snapshot.getChildren()){
                    facts.add(new Fact(ds.getValue(String.class)));
                }
                updateFactsList(facts);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
