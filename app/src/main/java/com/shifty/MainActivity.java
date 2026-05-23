package com.shifty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shifty.activity.AddEditShiftActivity;
import com.shifty.adapter.ShiftAdapter;
import com.shifty.database.ShiftDatabase;
import com.shifty.viewmodel.ShiftViewModel;

public class MainActivity extends AppCompatActivity {

    private ShiftViewModel shiftViewModel;
    private ShiftAdapter shiftAdapter;
    private RecyclerView shiftsRecyclerView;
    private FloatingActionButton fabAddShift;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        shiftsRecyclerView = findViewById(R.id.shifts_recycler_view);
        fabAddShift = findViewById(R.id.fab_add_shift);

        // Setup RecyclerView
        shiftAdapter = new ShiftAdapter();
        shiftsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        shiftsRecyclerView.setAdapter(shiftAdapter);

        // Initialize ViewModel
        ShiftDatabase database = ShiftDatabase.getInstance(this);
        ShiftViewModel.Factory factory = new ShiftViewModel.Factory(database.shiftDao());
        shiftViewModel = new ViewModelProvider(this, factory).get(ShiftViewModel.class);

        // Observe shifts
        shiftViewModel.getAllShifts().observe(this, shifts -> {
            if (shifts != null && !shifts.isEmpty()) {
                shiftAdapter.submitList(shifts);
                shiftsRecyclerView.setVisibility(View.VISIBLE);
            } else {
                shiftsRecyclerView.setVisibility(View.GONE);
            }
        });

        // Setup FAB click listener
        fabAddShift.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEditShiftActivity.class);
            startActivity(intent);
        });
    }
}
