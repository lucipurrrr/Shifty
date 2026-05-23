package com.shifty.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.shifty.database.ShiftDatabase;
import com.shifty.databinding.ActivityAddEditShiftBinding;
import com.shifty.model.Shift;
import com.shifty.viewmodel.ShiftViewModel;

public class AddEditShiftActivity extends AppCompatActivity {

    private ActivityAddEditShiftBinding binding;
    private ShiftViewModel shiftViewModel;
    private Shift shiftToEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEditShiftBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize ViewModel
        ShiftDatabase database = ShiftDatabase.getInstance(this);
        ShiftViewModel.Factory factory = new ShiftViewModel.Factory(database.shiftDao());
        shiftViewModel = new ViewModelProvider(this, factory).get(ShiftViewModel.class);

        // Check if editing existing shift
        if (getIntent().hasExtra("shift")) {
            shiftToEdit = (Shift) getIntent().getSerializableExtra("shift");
            if (shiftToEdit != null) {
                populateFields();
                setTitle("Edit Shift");
            }
        } else {
            setTitle("Add New Shift");
        }

        // Setup save button
        binding.buttonSave.setOnClickListener(v -> saveShift());
    }

    private void populateFields() {
        binding.editDate.setText(shiftToEdit.getDate());
        binding.editStartTime.setText(shiftToEdit.getStartTime());
        binding.editEndTime.setText(shiftToEdit.getEndTime());
        binding.editLocation.setText(shiftToEdit.getLocation());
        binding.editType.setText(shiftToEdit.getShiftType());
        binding.editNotes.setText(shiftToEdit.getNotes());
    }

    private void saveShift() {
        String date = binding.editDate.getText().toString().trim();
        String startTime = binding.editStartTime.getText().toString().trim();
        String endTime = binding.editEndTime.getText().toString().trim();
        String location = binding.editLocation.getText().toString().trim();
        String type = binding.editType.getText().toString().trim();
        String notes = binding.editNotes.getText().toString().trim();

        if (date.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
            Toast.makeText(this, "Please fill in required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (shiftToEdit != null) {
            // Update existing shift
            shiftToEdit.setDate(date);
            shiftToEdit.setStartTime(startTime);
            shiftToEdit.setEndTime(endTime);
            shiftToEdit.setLocation(location);
            shiftToEdit.setShiftType(type);
            shiftToEdit.setNotes(notes);
            shiftViewModel.updateShift(shiftToEdit);
        } else {
            // Create new shift
            Shift newShift = new Shift(date, startTime, endTime, location, type, notes);
            shiftViewModel.insertShift(newShift);
        }

        Toast.makeText(this, "Shift saved successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}
