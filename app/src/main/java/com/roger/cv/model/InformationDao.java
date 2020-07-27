package com.roger.cv.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface InformationDao {

    @Insert
    List<Long> insertAll(Information... information);

    @Query("SELECT * FROM information")
    List<Information> getAllInformation();

    @Query("DELETE FROM information")
    void deleteInformation();

}
