package com.samsung.reliab.feature.preview.ui.recycler;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samsung.reliab.domain.model.Sites;

import java.util.ArrayList;
import java.util.List;

public class SitesAdapter extends RecyclerView.Adapter<SitesViewHolder> {

    private final SitesClickListener listener;

    private List<Sites> items = new ArrayList<>();

    public SitesAdapter(SitesClickListener listener) {
        this.listener = listener;
    }



    @NonNull
    @Override
    public SitesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSitesBinding binding = ItemSitesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SitesViewHolder(binding,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull SitesViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Sites> items) {
        int count = getItemCount();
        this.items = new ArrayList<>(items);
        notifyItemRangeChanged(0, Math.max(count, getItemCount()));

    }
}
