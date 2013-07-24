package com.excelphysics.apoc.device;

import android.media.*;
import android.util.Log;

/**
 * Copyright (c) 2013 Tyler Menezes <tylermenezes@gmail.com>
 */
public class MicPulseSource extends PulseSource {
    private final int SampleRate = 44100;
    private final int Channels = AudioFormat.CHANNEL_CONFIGURATION_MONO;
    private final int Encoding = AudioFormat.ENCODING_PCM_16BIT;
    private final int Source = MediaRecorder.AudioSource.MIC;

    short[] audioDataBuffer;
    AudioRecord recorder;

    @Override
    public void SetUp()
    {
        // Set up the smallest buffer possible, because there's no need to get carried away.
        int bufferSize = AudioRecord.getMinBufferSize(SampleRate, Channels, Encoding);

        if (bufferSize == AudioRecord.ERROR_BAD_VALUE) {
            Log.e("APOC", "The hardware did not support the requested audio configuration");
        } else if (bufferSize == AudioRecord.ERROR) {
            Log.e("APOC", "Could not query hardware for audio support.");
        }

        audioDataBuffer = new short[bufferSize];

        // Set up the audio recorder
        recorder = new AudioRecord(Source, SampleRate, Channels, Encoding, audioDataBuffer.length);
        Log.d("APOC", "Created audio recorder at " + recorder.getSampleRate() + "Hz, " +
                recorder.getChannelCount() + " channels, " +
                recorder.getAudioFormat() + ", " +
                recorder.getAudioSource());

        // Start the recorder
        recorder.startRecording();
        Log.d("APOC", "Recording started.");
    }

    @Override
    public void Iteration()
    {
        int bufferedReadLength = recorder.read(audioDataBuffer, 0, audioDataBuffer.length);

        for (short s: audioDataBuffer) {
            Log.d("APOC", "" + s);
            if (s > 2000 || s < 0) {
                PulseObserved();
            }
        }

        audioDataBuffer = new short[audioDataBuffer.length];
    }

    @Override
    public void TearDown()
    {
        recorder.stop();
        recorder.release();
    }

}
