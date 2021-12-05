package com.example.a20211201_chunlin_nycschools.click_listeners;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

public class EmailClickListener extends BaseClickListener{
    private final Context context;
    public EmailClickListener(Context context) {
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
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, new String[] {getData().get((Integer) v.getTag())});
            context.startActivity(intent);
        } catch (Exception Ignored) {
            //debug logger would go here
        }
    }
}
