package com.jason.zhdaily.view;

import android.support.v4.app.Fragment;

public class LatestActivity extends BaseActivity {

    @Override
    Fragment onCreateFragment() {
        return new LatestFragment();
    }
}
