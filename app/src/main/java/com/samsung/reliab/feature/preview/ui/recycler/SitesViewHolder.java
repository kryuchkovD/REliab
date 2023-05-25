package com.samsung.reliab.feature.preview.ui.recycler;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.samsung.reliab.domain.model.Sites;

public class SitesViewHolder extends ViewHolder {

    private final ItemSitesBinding binding;
    private final SitesClickListener listener;

    public SitesViewHolder(ItemSitesBinding binding, SitesClickListener listener) {
        super(binding.getRoot());
        this.binding = binding;
        this.listener = listener;
    }

    public void bind(Sites item) {
        binding.name.setText(item.getName());
        binding.url.setText(item.getUrl());
        binding.status.setText(item.getStatus());
        binding.getRoot().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onClick(item.getId()));
            }
        }
    }
}
