package com.shifty.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.shifty.activity.ShiftDetailActivity;
import com.shifty.databinding.ItemShiftBinding;
import com.shifty.model.Shift;

public class ShiftAdapter extends ListAdapter<Shift, ShiftAdapter.ShiftViewHolder> {

    public ShiftAdapter() {
        super(new DiffUtil.ItemCallback<Shift>() {
            @Override
            public boolean areItemsTheSame(@NonNull Shift oldItem, @NonNull Shift newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Shift oldItem, @NonNull Shift newItem) {
                return oldItem.getDate().equals(newItem.getDate()) &&
                       oldItem.getStartTime().equals(newItem.getStartTime()) &&
                       oldItem.getEndTime().equals(newItem.getEndTime());
            }
        });
    }

    @NonNull
    @Override
    public ShiftViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemShiftBinding binding = ItemShiftBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ShiftViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShiftViewHolder holder, int position) {
        Shift shift = getItem(position);
        holder.bind(shift);
    }

    public static class ShiftViewHolder extends RecyclerView.ViewHolder {
        private final ItemShiftBinding binding;

        public ShiftViewHolder(ItemShiftBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Shift shift) {
            binding.shiftDate.setText(shift.getDate());
            binding.shiftTime.setText(shift.getStartTime() + " - " + shift.getEndTime());
            binding.shiftType.setText(shift.getShiftType());
            binding.shiftLocation.setText(shift.getLocation());

            binding.getRoot().setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), ShiftDetailActivity.class);
                intent.putExtra("shift_id", shift.getId());
                v.getContext().startActivity(intent);
            });
        }
    }
}
