package com.roger.cv.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.roger.cv.model.Information;

public class HomeViewModel extends AndroidViewModel {

    public MutableLiveData<Information> information = new MutableLiveData<Information>();
    public MutableLiveData<Boolean> isError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<Boolean>();

    private String USER_NAME = "CV_APP";
    private String REFERENCE = "User/Rogelio";

    private  String INFORMATION_KEY = "information";

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mCVDatabaseReference;

    public HomeViewModel(@NonNull Application application) {
        super(application);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mCVDatabaseReference = mFirebaseDatabase.getReference(REFERENCE);

    }

    public void fetchData(){
        fetchFromDatabase();
    }

    private void fetchFromDatabase(){
        isLoading.setValue(true);

        Information info = new Information("dirección", "cumpleaños", "celular", "imagen", "mail@correo.com", "Roger Rivera", "Ingeniero");
        information.setValue(info);

        isLoading.setValue(false);
    }
}
