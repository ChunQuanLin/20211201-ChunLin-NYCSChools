package com.example.a20211201_chunlin_nycschools.click_listeners;

import android.util.SparseArray;
import android.view.View;

/*
* Provides the skeleton for the other click listeners.
* */
public abstract class BaseClickListener implements View.OnClickListener {
    private final SparseArray<String> data = new SparseArray<>();

    public View.OnClickListener setData(String data, int index) {
        this.data.put(index, data);
        return this;
    }
    public SparseArray<String> getData() {
        return data;
    }

}
