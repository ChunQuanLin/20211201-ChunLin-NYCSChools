package com.example.a20211201_chunlin_nycschools.click_listeners;

import static com.example.a20211201_chunlin_nycschools.StringConstants.notApplicable;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.SpannableStringBuilder;
import android.view.View;

public class LocationClickListener extends BaseClickListener{
    private final Context context;
    public LocationClickListener(Context context) {
        this.context = context;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        String locationString = getData().get((Integer) v.getTag());
        SpannableStringBuilder gps = new SpannableStringBuilder();
        for (int i = 0; i < locationString.length(); i++) {
            if(locationString.charAt(i) == ' ') {
                gps.append("+");
            }
            else if (locationString.charAt(i) == ',') {
                gps.append("%2C");
            }
            else
                gps.append(locationString.charAt(i));
        }
        if (!notApplicable.equalsIgnoreCase(locationString)) {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(gps.toString()));
                context.startActivity(intent);
            }
            catch (Exception ignored) {
                //debug logger would go here
            }
        }
    }
}
