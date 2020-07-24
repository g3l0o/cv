package com.roger.cv.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface JobDao {
    @Insert
    List<Long> insertAll(Job... jobs);

    @Query("SELECT * FROM job")
    List<Job> getAllJobs();

    @Query("SELECT * FROM job WHERE uuid = :jobId")
    Job getJob(int jobId);

    @Query("DELETE FROM job")
    void deleteAllJobs();
}
