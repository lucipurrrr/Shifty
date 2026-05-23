package com.shifty.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.shifty.R;
import com.shifty.database.ShiftDatabase;
import com.shifty.databinding.ActivityShiftDetailBinding;
import com.shifty.model.Shift;
import com.shifty.viewmodel.ShiftViewModel;

public class ShiftDetailActivity extends AppCompatActivity {

    private ActivityShiftDetailBinding binding;
    private ShiftViewModel shiftViewModel;
    private Shift currentShift;
    private int shiftId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShiftDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        shiftId = getIntent().getIntExtra("shift_id", -1);

        // Initialize ViewModel
        ShiftDatabase database = ShiftDatabase.getInstance(this);
        ShiftViewModel.Factory factory = new ShiftViewModel.Factory(database.shiftDao());
        shiftViewModel = new ViewModelProvider(this, factory).get(ShiftViewModel.class);

        // Load shift data
        if (shiftId != -1) {
            shiftViewModel.getShiftById(shiftId).observe(this, shift -> {
                if (shift != null) {
                    currentShift = shift;
                    displayShiftDetails();
                }
            });
        }
    }

    private void displayShiftDetails() {
        binding.detailDate.setText(currentShift.getDate());
        binding.detailStartTime.setText(currentShift.getStartTime());
        binding.detailEndTime.setText(currentShift.getEndTime());
        binding.detailLocation.setText(currentShift.getLocation());
        binding.detailType.setText(currentShift.getShiftType());
        binding.detailNotes.setText(currentShift.getNotes());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_shift_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else if (item.getItemId() == R.id.action_edit) {
            Intent intent = new Intent(this, AddEditShiftActivity.class);
            intent.putExtra("shift", currentShift);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.action_delete) {
            shiftViewModel.deleteShift(currentShift);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
