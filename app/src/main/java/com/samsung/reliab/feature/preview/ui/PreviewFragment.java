package com.samsung.reliab.feature.preview.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.samsung.reliab.databinding.FragmentPreviewBinding;
import com.samsung.reliab.domain.model.Sites;
import com.samsung.reliab.feature.preview.ui.presentation.PreviewStatus;
import com.samsung.reliab.feature.preview.ui.presentation.PreviewViewModel;
import com.samsung.reliab.feature.preview.ui.recycler.SitesAdapter;


public class PreviewFragment extends Fragment {

    private PreviewViewModel viewModel;
    private FragmentPreviewBinding binding;
    private SitesAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPreviewBinding.inflate(inflater);
        viewModel = new ViewModelProvider(this).get(PreviewViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new SitesAdapter(id -> Navigation.findNavController(binding.getRoot()).navigate(PreviewFragmentDirections.actionPreviewToCheck(id)));
        viewModel.status.observe(getViewLifecycleOwner(), this::renderStatus);
        viewModel.sites.observe(getViewLifecycleOwner(), this::renderSites);
        if (savedInstanceState == null) viewModel.load();

    }

    private void renderStatus(PreviewStatus status) {
        switch (status) {
            case LOADING:
                binding.empty.setVisibility(View.INVISIBLE);
                binding.error.setVisibility(View.INVISIBLE);
                binding.progress.setVisibility(View.VISIBLE);
                break;
            case LOADED:
                binding.error.setVisibility(View.INVISIBLE);
                binding.progress.setVisibility(View.INVISIBLE);
                break;
            case FAILURE:
                binding.empty.setVisibility(View.INVISIBLE);
                binding.error.setVisibility(View.VISIBLE);
                binding.progress.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void renderSites(Sites sites) {
        binding.empty.setVisibility(sites.isEmpty() ? View.VISIBLE : View.INVISIBLE);
        adapter.setItems(sites);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
