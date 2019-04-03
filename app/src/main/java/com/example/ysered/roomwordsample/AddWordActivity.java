package com.example.ysered.roomwordsample;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class AddWordActivity extends AppCompatActivity {

    public static final String EXTRA_WORD = "com.example.ysered.roomwordsample.EXTRA_WORD";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        final EditText wordText = findViewById(R.id.wordText);
        findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = wordText.getText().toString();
                Intent replyIntent = new Intent();
                if (!TextUtils.isEmpty(word)) {
                    replyIntent.putExtra(EXTRA_WORD, word);
                    setResult(RESULT_OK, replyIntent);
                } else {
                    setResult(RESULT_CANCELED, replyIntent);
                }
                finish();
            }
        });
    }
}
