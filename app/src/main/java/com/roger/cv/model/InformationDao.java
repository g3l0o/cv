package com.roger.cv.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface InformationDao {

    @Insert
    Long insert(Information information);

    @Query("SELECT * FROM information")
    Information getInformation();

    @Query("DELETE FROM information")
    void deleteInformation();

}
