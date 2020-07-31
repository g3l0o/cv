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
import com.roger.cv.model.Certificate;

import java.util.ArrayList;
import java.util.List;

public class CertificateViewModel extends AndroidViewModel {

    public MutableLiveData<List<Certificate>> certificatesList = new MutableLiveData<List<Certificate>>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> isError = new MutableLiveData<Boolean>();

    private String USER_NAME = "CV_APP";
    private String REFERENCE = "User/Rogelio";

    private  String CERTIFICATES_KEY = "certifications";

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mCVDatabaseReference;

    public CertificateViewModel(@NonNull Application application) {
        super(application);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mCVDatabaseReference = mFirebaseDatabase.getReference(REFERENCE);
    }

    public void fetchData(){
        fetchFromFirebase();
    }

    private void fetchFromFirebase() {
        isLoading.setValue(false);
        mCVDatabaseReference.child(CERTIFICATES_KEY).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Certificate> certificates = new ArrayList<>();
                for (DataSnapshot ds: snapshot.getChildren()) {
                    Certificate certificate = ds.getValue(Certificate.class);
                    certificates.add(certificate);
                }

                certificatesList.setValue(certificates);
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
