package com.bignerdranch.android.beatbox;

public class SoundViewModel {
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
    }

    public String getTitle() {
        return mSound.getName();
    }
}
