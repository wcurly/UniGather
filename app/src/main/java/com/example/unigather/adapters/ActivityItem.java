package com.example.unigather.adapters;

public class ActivityItem {
    private String title;
    private int imageResourceId;;
    private int collectionCount;

    public ActivityItem(String title, int imageResourceId, int collectionCount) {
        this.title = title;
        this.imageResourceId = imageResourceId;
        this.collectionCount = collectionCount;
    }

    public String getTitle() {
        return title;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public int getCollectionCount() {
        return collectionCount;
    }
}
