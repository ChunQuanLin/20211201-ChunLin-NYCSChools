package com.example.a20211201_chunlin_nycschools.click_listeners;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

public class SiteClickListener extends BaseClickListener{
    private final Context context;
    public SiteClickListener(Context context) {
        this.context = context;
    }
    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        try {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https:" + getData().get((Integer) v.getTag())));
            context.startActivity(intent);
        } catch (Exception Ignored) {
            //debug logger would go here
        }
    }
}
