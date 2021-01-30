package com.android.washer;

import androidx.annotation.Nullable;

public class WashSingleton {

    static WashSingleton sharedInstance = new WashSingleton();

    WashSingleton() {
        // do nothing
    }

    void reset() {
        sharedInstance = new WashSingleton();
    }

    @Nullable
    WasherModel washerModel;
}
