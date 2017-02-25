package com.christianlepson.interactivestory.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.christianlepson.interactivestory.R;
import com.christianlepson.interactivestory.model.Page;
import com.christianlepson.interactivestory.model.Story;

public class StoryActivity extends AppCompatActivity {
    private Story mStory = new Story();
    private ImageView mImageView;
    private TextView mTextView;
    private Button mChoice1;
    private Button mChoice2;
    private String mUsersName;
    private Page mCurrentPage;
    public static final String TAG = StoryActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        makeAppFullscreen();
        mUsersName = getUsersName();
        Log.d(TAG, mUsersName);

        mImageView = (ImageView)findViewById(R.id.storyImageView);
        mTextView = (TextView)findViewById(R.id.storyTextView);
        mChoice1 = (Button)findViewById(R.id.choiceButton1);
        mChoice2 = (Button)findViewById(R.id.choiceButton2);

        loadPage(0);
    }

    private String getUsersName() {
        Intent intent = getIntent();
        mUsersName = intent.getStringExtra(getString(R.string.key_name));
        if (mUsersName == null) {
            mUsersName = "Friend";
        }
        return mUsersName;
    }

    private void loadPage(int choice) {
        mCurrentPage = mStory.getPage(choice);
        loadImageFromPage();
        loadTextFromPage();
        if (mCurrentPage.isFinalPage()) {
            mChoice1.setVisibility(View.INVISIBLE);
            mChoice2.setText("Play Again");
            mChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } else {
            loadChoicesFromPage();
        }
    }

    private void loadImageFromPage() {
        Drawable drawable = getResources().getDrawable(mCurrentPage.getImageId());
        mImageView.setImageDrawable(drawable);
    }

    private void loadTextFromPage() {
        String pageText = mCurrentPage.getStoryText();
        // Add name to page text if placeholder is present
        pageText = String.format(pageText, mUsersName);
        mTextView.setText(pageText);
    }


    private void loadChoicesFromPage() {
        mChoice1.setText(mCurrentPage.getChoice1().getChoiceText());
        mChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int choice1PageNumber = mCurrentPage.getChoice1().getNextPageNumber();
                loadPage(choice1PageNumber);
            }
        });
        mChoice2.setText(mCurrentPage.getChoice2().getChoiceText());
        mChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int choice2PageNumber = mCurrentPage.getChoice2().getNextPageNumber();
                loadPage(choice2PageNumber);
            }
        });
    }

    private void makeAppFullscreen() {
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }
 }
