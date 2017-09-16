package com.bignerdranch.android.beatbox;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.widget.SeekBar;

public class SoundViewModel extends BaseObservable {
    Sound mSound;
    BeatBox mBeatBox;

    public SoundViewModel(BeatBox beatBox) {
        mBeatBox = beatBox;
    }

    public Sound getSound() {
        return mSound;
    }

    public void setSound(Sound sound) {
        mSound = sound;
        notifyChange();
    }

    @Bindable
    public String getTitle() {
        return mSound.getName();
    }

    public void onButtonClicked() {
        mBeatBox.play(mSound);
    }

}
