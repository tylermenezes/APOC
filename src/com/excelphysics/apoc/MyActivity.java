package com.excelphysics.apoc;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.*;
import android.util.Log;

import com.excelphysics.apoc.device.*;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void OnButtonClicked(View v)
    {
        MicPulseSource mps = new MicPulseSource();
        mps.Start();
        mps.RegisterPulseObservedListener(new ColorChangedListener(v, this));
    }
}
