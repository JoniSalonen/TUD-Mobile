package com.example.passwordmanager;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

// Adding annotation to our Dao class
@androidx.room.Dao
public interface Dao {

    // below method is use to add data to database.
    @Insert
    void insert(LoginDetailsModal model);

    // below method is use to update the data in our database.
    @Update
    void update(LoginDetailsModal model);


    // delete details in our database.
    @Delete
    void delete(LoginDetailsModal model);

    // delete all details from our database.
    @Query("DELETE FROM details_table")
    void deleteAllDetails();


    // in this we are ordering our details in ascending order with our platform name
    @Query("SELECT * FROM details_table ORDER BY platformName ASC")
    LiveData<List<LoginDetailsModal>> getAllDetails();
}
