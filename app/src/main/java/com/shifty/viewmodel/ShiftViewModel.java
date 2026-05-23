package com.shifty.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.shifty.database.ShiftDao;
import com.shifty.model.Shift;

import java.util.List;

public class ShiftViewModel extends ViewModel {

    private final ShiftDao shiftDao;
    private final LiveData<List<Shift>> allShifts;

    public ShiftViewModel(ShiftDao shiftDao) {
        this.shiftDao = shiftDao;
        this.allShifts = shiftDao.getAllShifts();
    }

    public LiveData<List<Shift>> getAllShifts() {
        return allShifts;
    }

    public LiveData<Shift> getShiftById(int shiftId) {
        return shiftDao.getShiftById(shiftId);
    }

    public LiveData<List<Shift>> getShiftsByDate(String date) {
        return shiftDao.getShiftsByDate(date);
    }

    public void insertShift(Shift shift) {
        new Thread(() -> shiftDao.insertShift(shift)).start();
    }

    public void updateShift(Shift shift) {
        shift.setUpdatedAt(System.currentTimeMillis());
        new Thread(() -> shiftDao.updateShift(shift)).start();
    }

    public void deleteShift(Shift shift) {
        new Thread(() -> shiftDao.deleteShift(shift)).start();
    }

    public static class Factory implements ViewModelProvider.Factory {
        private final ShiftDao shiftDao;

        public Factory(ShiftDao shiftDao) {
            this.shiftDao = shiftDao;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            if (modelClass.isAssignableFrom(ShiftViewModel.class)) {
                return (T) new ShiftViewModel(shiftDao);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
