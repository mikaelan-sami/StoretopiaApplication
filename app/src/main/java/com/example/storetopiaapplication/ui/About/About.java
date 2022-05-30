package com.example.storetopiaapplication.ui.About;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.storetopiaapplication.R;
import com.example.storetopiaapplication.databinding.AboutFragmentBinding;
import com.example.storetopiaapplication.databinding.CollectionsFragmentBinding;
import com.example.storetopiaapplication.ui.Collections.CollectionsViewModel;

public class About extends Fragment {

    private AboutFragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AboutViewModel aboutViewModel =
                new ViewModelProvider(this).get(AboutViewModel.class);

        binding = AboutFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}