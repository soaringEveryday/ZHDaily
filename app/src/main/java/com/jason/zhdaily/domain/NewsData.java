package com.jason.zhdaily.domain;

public class NewsData {

    Latest latest;
    StoryExtra extra;

    public Latest getLatest() {
        return latest;
    }

    public void setLatest(Latest latest) {
        this.latest = latest;
    }

    public StoryExtra getExtra() {
        return extra;
    }

    public void setExtra(StoryExtra extra) {
        this.extra = extra;
    }
}
