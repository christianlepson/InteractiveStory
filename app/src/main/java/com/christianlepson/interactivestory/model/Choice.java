package com.christianlepson.interactivestory.model;


public class Choice {
    private String mChoiceText;
    private int mNextPageNumber;

    public Choice(String choiceText, int nextPageNumber) {
        this.mChoiceText = choiceText;
        this.mNextPageNumber = nextPageNumber;
    }

    public String getChoiceText() {
        return mChoiceText;
    }

    public void setChoiceText(String choiceText) {
        mChoiceText = choiceText;
    }

    public int getNextPageNumber() {
        return mNextPageNumber;
    }

    public void setNextPageNumber(int nextPageNumber) {
        mNextPageNumber = nextPageNumber;
    }
}
