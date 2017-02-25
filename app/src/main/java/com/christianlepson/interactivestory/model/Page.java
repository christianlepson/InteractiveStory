package com.christianlepson.interactivestory.model;


public class Page {
    private int mImageId;
    private String mStoryText;
    private Choice mChoice1;
    private Choice mChoice2;
    private Boolean mIsAFinalPage = false;

    public Page(int imageId, String storyText, Choice choice1, Choice choice2) {
        this.mImageId = imageId;
        this.mStoryText = storyText;
        this.mChoice1 = choice1;
        this.mChoice2 = choice2;

    }

    public Page(int imageId, String storyText) {
        this.mImageId = imageId;
        this.mStoryText = storyText;
        this.mChoice1 = null;
        this.mChoice2 = null;
        this.mIsAFinalPage = true;
    }

    public int getImageId() {
        return mImageId;
    }

    public void setImageId(int imageId) {
        mImageId = imageId;
    }

    public String getStoryText() {
        return mStoryText;
    }

    public void setStoryText(String storyText) {
        mStoryText = storyText;
    }

    public Choice getChoice1() {
        return mChoice1;
    }

    public void setChoice1(Choice choice1) {
        mChoice1 = choice1;
    }

    public Choice getChoice2() {
        return mChoice2;
    }

    public void setChoice2(Choice choice2) {
        mChoice2 = choice2;
    }

    public Boolean isFinalPage() {
        return mIsAFinalPage;
    }

}