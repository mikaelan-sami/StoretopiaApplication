package com.example.storetopiaapplication.ui.Collections;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.storetopiaapplication.databinding.CollectionsFragmentBinding;
import com.example.storetopiaapplication.databinding.FragmentGalleryBinding;

public class collections extends Fragment {

    private CollectionsFragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CollectionsViewModel collectionsViewModel =
                new ViewModelProvider(this).get(CollectionsViewModel.class);

        binding = CollectionsFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}