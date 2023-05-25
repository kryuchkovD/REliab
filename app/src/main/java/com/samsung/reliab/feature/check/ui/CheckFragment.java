package com.samsung.reliab.feature.check.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.samsung.reliab.databinding.FragmentCheckBinding;
import com.samsung.reliab.databinding.FragmentPreviewBinding;
import com.samsung.reliab.domain.model.Sites;
import com.samsung.reliab.feature.check.ui.presentation.CheckStatus;
import com.samsung.reliab.feature.check.ui.presentation.CheckViewModel;
import com.samsung.reliab.feature.preview.ui.presentation.PreviewStatus;
import com.samsung.reliab.feature.preview.ui.presentation.PreviewViewModel;

public class CheckFragment extends Fragment {
    private CheckViewModel viewModel;
    private FragmentCheckBinding binding;

    private CheckFragmentArgs args;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        args = CheckFragmentArgs.fromBundle(requireArguments());
        binding = FragmentCheckBinding.inflate(inflater);
        viewModel = new ViewModelProvider(this).get(CheckViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.status.observe(getViewLifecycleOwner(), this::renderStatus);
        viewModel.site.observe(getViewLifecycleOwner(), this::renderSite);
        binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.delete(args.getId());
            }
        });
        if (savedInstanceState == null) viewModel.load(args.getId());

    }

    private void renderStatus(CheckStatus status) {

        switch (status) {
            case LOADING:
                binding.imageLayout.setVisibility(View.INVISIBLE);
                binding.name.setVisibility(View.INVISIBLE);
                binding.url.setVisibility(View.INVISIBLE);
                binding.status.setVisibility(View.INVISIBLE);
                binding.error.setVisibility(View.INVISIBLE);
                binding.progress.setVisibility(View.VISIBLE);
                break;
            case LOADED:
                binding.imageLayout.setVisibility(View.VISIBLE);
                binding.name.setVisibility(View.VISIBLE);
                binding.url.setVisibility(View.VISIBLE);
                binding.status.setVisibility(View.VISIBLE);
                binding.error.setVisibility(View.INVISIBLE);
                binding.progress.setVisibility(View.INVISIBLE);
                break;
            case FAILURE:
                binding.imageLayout.setVisibility(View.INVISIBLE);
                binding.name.setVisibility(View.INVISIBLE);
                binding.url.setVisibility(View.INVISIBLE);
                binding.status.setVisibility(View.INVISIBLE);
                binding.error.setVisibility(View.VISIBLE);
                binding.progress.setVisibility(View.INVISIBLE);
                break;
            case DELETED:
                Navigation.findNavController(binding.getRoot()).navigateUp();
                break;
        }
    }

    private void renderSite(Sites sites) {
        binding.name.setText(sites.getName());
        binding.url.setText(sites.getUrl());
        binding.status.setText(sites.getStatus());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}