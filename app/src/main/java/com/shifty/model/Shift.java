package com.shifty.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "shifts")
public class Shift implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String date;
    private String startTime;
    private String endTime;
    private String location;
    private String shiftType; // Regular, Overtime, etc.
    private String notes;
    private long createdAt;
    private long updatedAt;

    public Shift(String date, String startTime, String endTime, String location,
                 String shiftType, String notes) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.shiftType = shiftType;
        this.notes = notes;
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = System.currentTimeMillis();
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getLocation() {
        return location;
    }

    public String getShiftType() {
        return shiftType;
    }

    public String getNotes() {
        return notes;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setShiftType(String shiftType) {
        this.shiftType = shiftType;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }
}
