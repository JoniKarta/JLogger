package com.example.jloggerlib.spinner;

public class LogLevelItem {
    private String tag;
    private int imageResource;

    public LogLevelItem() {

    }

    public LogLevelItem(String tag, int imageResource) {
        this.tag = tag;
        this.imageResource = imageResource;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    @Override
    public String toString() {
        return tag;
    }
}
