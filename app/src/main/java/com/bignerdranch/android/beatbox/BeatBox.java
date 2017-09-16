package com.bignerdranch.android.beatbox;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.widget.SeekBar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BeatBox {
    private static final String TAG = "BeatBox";
    private static final int MAX_SOUNDS = 5;
    private static final String SOUNDS_FOLDER = "sample_sounds";
    private AssetManager mAssets;
    private List<Sound> mSounds = new ArrayList<>();
    private SoundPool mSoundPool;
    private float mSoundRate;

    public BeatBox(Context context) {
        mAssets = context.getAssets();
        // Thsi old constructor is deprecated but needed for compatibility
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC,0);
        loadSounds();
    }

    private void load(Sound sound) throws IOException {
        AssetFileDescriptor afd= mAssets.openFd(sound.getAssetPath());
        int soundId = mSoundPool.load(afd,1);
        sound.setSoundId(soundId);
    }


    public void play(Sound sound) {
        Integer soundID = sound.getSoundId();
        if (sound == null) {
            return;
        }
        mSoundPool.play(soundID,1.0f,1.0f,1,0,1.0f);
    }

    private void loadSounds() {
        String[] soundNames;
        try {
            soundNames = mAssets.list(SOUNDS_FOLDER);
            Log.i(TAG, "Found " + soundNames.length + " sounds");
        } catch (IOException ioe) {
            Log.e(TAG, "Could not list assets", ioe);
            return;
        }

        for (String filename : soundNames) {
            try {
                String assetPath = SOUNDS_FOLDER + "/" + filename;
                Sound sound = new Sound(assetPath);
                load(sound);
                mSounds.add(sound);
            } catch (IOException ioe) {
                Log.e(TAG, "Could not load sound " + filename, ioe);
            }
        }
    }

    public List<Sound> getSounds() {
        return mSounds;
    }

    public void release() {
        mSoundPool.release();
    }
}
