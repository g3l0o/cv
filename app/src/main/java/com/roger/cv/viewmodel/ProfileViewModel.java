package com.roger.cv.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.roger.cv.model.Information;
import com.roger.cv.model.InformationDatabase;

public class ProfileViewModel extends AndroidViewModel {

    MutableLiveData<Information> informationLiveData = new MutableLiveData<Information>();
    private RetrieveInformationTask informationTask;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetch(long uuid){
        informationTask = new RetrieveInformationTask();
        informationTask.execute(uuid);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        
        if(informationTask != null){
            informationTask.cancel(true);
            informationTask = null;
        }
    }

    private class RetrieveInformationTask extends AsyncTask<Long, Void, Information>{
        @Override
        protected Information doInBackground(Long... longs) {
            long uuid = longs[0];
            return InformationDatabase.getInstance(getApplication()).informationDao().getInformation(uuid);
        }

        @Override
        protected void onPostExecute(Information information) {
            informationLiveData.setValue(information);
        }
    }

}
