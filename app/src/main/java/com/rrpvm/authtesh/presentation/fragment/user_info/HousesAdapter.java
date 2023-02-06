package com.rrpvm.authtesh.presentation.fragment.user_info;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.rrpvm.authtesh.data.network.sub_data.HouseShortDataDto;
import com.rrpvm.authtesh.databinding.ItemHouseBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HousesAdapter extends RecyclerView.Adapter<HousesAdapter.ViewHolder> {
    private final HouseAdapterCallbacks houseAdapterCallbacks = new HouseAdapterCallbacks();
    private ArrayList<HouseShortDataDto> data = new ArrayList<>();

    public void setData(List<HouseShortDataDto> list) {
        data = new ArrayList<>(list);
        notifyDataSetChanged();//да,так плохо, надо диф утилу подключить,но опять же - время
    }

    public List<HouseShortDataDto> getData() {//immutable
        return Collections.unmodifiableList(data);
    }

    public void setOnItemClickListener(HouseAdapterCallbacks.ItemClickListener itemClickListener) {
        this.houseAdapterCallbacks.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemHouseBinding.inflate(LayoutInflater.from(parent.getContext())).getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ItemHouseBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemHouseBinding.bind(itemView);
        }

        public void onBind(HouseShortDataDto item) {
            binding.tvItemHouse.setText(item.title);
            binding.getRoot().setOnClickListener(view -> {
                if (houseAdapterCallbacks.itemClickListener != null) {
                    if (item.houseId != null)
                        houseAdapterCallbacks.itemClickListener.onItemClick(item.houseId);
                }
            });
        }
    }

    public static class HouseAdapterCallbacks {
        public interface ItemClickListener {
            void onItemClick(String houseId);
        }

        public ItemClickListener itemClickListener = null;
    }

}
