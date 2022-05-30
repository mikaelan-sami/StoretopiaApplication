package com.example.storetopiaapplication.ui.Collections;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CollectionsViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public CollectionsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the Collections fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}