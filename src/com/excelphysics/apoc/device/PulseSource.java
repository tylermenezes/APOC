package com.excelphysics.apoc.device;

import java.util.List;
import java.util.ArrayList;

/**
 * Copyright (c) 2013 Tyler Menezes <tylermenezes@gmail.com>
 */
public abstract class PulseSource implements Runnable {

    protected boolean IsRunning = true;

    private List<IPulseObservedListener> pulseObservedListeners = new ArrayList<IPulseObservedListener>();

    /**
     * Adds a listener to be called whenever a pulse is detected.
     * @param listener_to_register
     */
    public final void RegisterPulseObservedListener(IPulseObservedListener listener_to_register)
    {
        pulseObservedListeners.add(listener_to_register);
    }

    /**
     * Marks a pulse
     */
    protected final void PulseObserved()
    {
        for (IPulseObservedListener listener : pulseObservedListeners) {
            listener.PulseObserved();
        }
    }

    protected void SetUp() {}
    protected void Iteration() {}
    protected void TearDown() {}

    public void run()
    {
        SetUp();
        while(IsRunning)
        {
            Iteration();
        }
        TearDown();
    }

    public void Start()
    {
        (new Thread(this)).start();
    }

    public void Stop()
    {
        IsRunning = false;
    }
}
