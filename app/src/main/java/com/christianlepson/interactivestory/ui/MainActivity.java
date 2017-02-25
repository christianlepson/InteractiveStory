package com.christianlepson.interactivestory.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;

import com.christianlepson.interactivestory.R;

public class MainActivity extends AppCompatActivity {

    private EditText mNameField;
    private Button mStartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeAppFullscreen();
        this.getWindow().findViewById(R.id.activity_main).addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                makeAppFullscreen();
            }
        });
        mNameField = (EditText) findViewById(R.id.nameEditText);
        mStartButton = (Button) findViewById(R.id.startButton);
        mStartButton.setOnClickListener(startButtonHandler);
        final View activityRootView = findViewById(R.id.activity_main);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int heightDiff = activityRootView.getRootView().getHeight() - activityRootView.getHeight();
                if (heightDiff > 0) {
                    makeAppFullscreen();
                }
            }
        });
    }

    public static float dpToPx(Context context, float valueInDp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp, metrics);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        makeAppFullscreen();
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

    private void startStoryActivity(String usersName) {
        Intent intent = new Intent(MainActivity.this, StoryActivity.class);
        intent.putExtra(getString(R.string.key_name), usersName);
        startActivity(intent);
    }

    private String getUsersNameAsString() {
        String usersNameAsString = mNameField.getText().toString();
        return usersNameAsString;
    }

    View.OnClickListener startButtonHandler = new View.OnClickListener() {
        public void onClick(View v) {
            String usersName = getUsersNameAsString();
            startStoryActivity(usersName);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        mNameField.setText("");
        makeAppFullscreen();
    }



}
