package com.example.storetopiaapplication.ui.Collections;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.storetopiaapplication.CreateCollections;
import com.example.storetopiaapplication.databinding.CollectionsFragmentBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class collections extends Fragment {

    private CollectionsFragmentBinding binding;
    FloatingActionButton categoryPage;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CollectionsViewModel collectionsViewModel =
                new ViewModelProvider(this).get(CollectionsViewModel.class);

        binding = CollectionsFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.addCategoryScreen.setOnClickListener(view -> {
            addCatScreen();
        });

        return root;
    }
    public void addCatScreen() {
        Intent intent = new Intent(getContext(), CreateCollections.class);
        startActivity(intent);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}