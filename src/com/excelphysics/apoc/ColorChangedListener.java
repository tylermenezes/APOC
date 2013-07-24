package com.excelphysics.apoc;

import android.graphics.Color;
import android.view.View;
import android.app.Activity;
import com.excelphysics.apoc.device.IPulseObservedListener;

/**
 * Copyright (c) 2013 Tyler Menezes <tylermenezes@gmail.com>
 */
public class ColorChangedListener implements IPulseObservedListener {
    View view;
    Activity activity;
    boolean isBlack = false;
    public ColorChangedListener(View v, Activity a) {
        view = v;
        activity = a;
    }

    @Override
    public void PulseObserved()
    {
        final int c = isBlack ? Color.BLACK : Color.WHITE;
        isBlack = !isBlack;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view.setBackgroundColor(c);
            }
        });
    }
}
