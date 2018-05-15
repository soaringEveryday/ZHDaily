package com.jason.zhdaily.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.jason.zhdaily.R;

import static com.jason.zhdaily.view.NewsFragment.KEY_ID;

public class NewsActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

    }

    @Override
    Fragment onCreateFragment() {
        NewsFragment fragment = new NewsFragment();
        String id = getIntent().getStringExtra(KEY_ID);
        Bundle args = new Bundle();
        args.putString(KEY_ID, id);
        fragment.setArguments(args);

        return fragment;
    }
}
