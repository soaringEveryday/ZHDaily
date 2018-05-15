package com.jason.zhdaily.view;

import android.support.v4.app.Fragment;

public class NewsActivity extends BaseActivity {

    @Override
    Fragment onCreateFragment() {
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(getIntent().getExtras());
        return fragment;
    }
}
