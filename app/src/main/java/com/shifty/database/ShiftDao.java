package com.shifty.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.shifty.model.Shift;

import java.util.List;

@Dao
public interface ShiftDao {

    @Insert
    long insertShift(Shift shift);

    @Update
    void updateShift(Shift shift);

    @Delete
    void deleteShift(Shift shift);

    @Query("SELECT * FROM shifts ORDER BY date DESC")
    LiveData<List<Shift>> getAllShifts();

    @Query("SELECT * FROM shifts WHERE id = :shiftId")
    LiveData<Shift> getShiftById(int shiftId);

    @Query("SELECT * FROM shifts WHERE date = :date ORDER BY startTime ASC")
    LiveData<List<Shift>> getShiftsByDate(String date);

    @Query("DELETE FROM shifts WHERE id = :shiftId")
    void deleteShiftById(int shiftId);

    @Query("SELECT COUNT(*) FROM shifts")
    LiveData<Integer> getShiftCount();
}
