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
import com.roger.cv.model.Information;
import com.roger.cv.model.InformationDao;
import com.roger.cv.model.InformationDatabase;
import com.roger.cv.util.SharedPreferenceHelper;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    public MutableLiveData<Information> information = new MutableLiveData<Information>();
    public MutableLiveData<Boolean> isError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<Boolean>();

    private String USER_NAME = "CV_APP";
    private String REFERENCE = "User/Rogelio";

    private  String INFORMATION_KEY = "information";

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mCVDatabaseReference;

    private AsyncTask<Information, Void, Information> insertTask;
    private AsyncTask<Void, Void, Information> retrieveTask;

    private SharedPreferenceHelper preferenceHelper = SharedPreferenceHelper.getInstance(getApplication());
    private long refreshTime = 60 * 60 * 1_000 * 1_000 * 1_000L; //60 minutes in nanoseconds

    public HomeViewModel(@NonNull Application application) {
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
    }

    public void fetchData(){
        long updateTime = preferenceHelper.getInformationUpdateTime();
        long currentTime = System.nanoTime();

        if(updateTime != 0 && currentTime - updateTime < refreshTime) {
            fetchFromFirebase();
        }else {
            fetchFromLocalDatabase();
        }
    }

    private void informationRetrieved(Information info){
        information.setValue(info);
        isError.setValue(false);
        isLoading.setValue(false);
    }

    private void fetchFromFirebase(){
        isLoading.setValue(true);

        mCVDatabaseReference.child(INFORMATION_KEY).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Information info = snapshot.getValue(Information.class);

                insertTask = new InsertInformation();
                insertTask.execute(info);

                Toast.makeText(getApplication(), "Data Retrieved from Firebase", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                isLoading.setValue(false);
                isError.setValue(true);
            }
        });
    }

    private void fetchFromLocalDatabase(){
        retrieveTask = new RetrieveInformation();
        retrieveTask.execute();
    }

    private class InsertInformation extends AsyncTask<Information, Void, Information>{
        @Override
        protected Information doInBackground(Information... information) {
            Information info = information[0];
            InformationDao dao = InformationDatabase.getInstance(getApplication()).informationDao();

            dao.deleteInformation();

            List<Long> result = dao.insertAll(info);

            info.setUuid(result.get(0));

            return info;
        }

        @Override
        protected void onPostExecute(Information information) {
            informationRetrieved(information);
            preferenceHelper.saveInformationUpdateTime(System.nanoTime());
        }
    }

    private class RetrieveInformation extends AsyncTask<Void, Void, Information>{
        @Override
        protected Information doInBackground(Void... voids) {
            return InformationDatabase.getInstance(getApplication()).informationDao().getAllInformation().get(0);
        }

        @Override
        protected void onPostExecute(Information information) {
            informationRetrieved(information);
        }
    }
}
