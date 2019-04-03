package com.example.ysered.roomwordsample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ysered.roomwordsample.persistence.Word;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int ADD_WORD_REQUEST_CODE = 111;
    private WordViewModel wordViewModel;

    private WordsAdapter wordsAdapter;
    private RecyclerView wordsRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWordsRecyclerView();

        FloatingActionButton addWordButton = findViewById(R.id.addWordFab);
        addWordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddWordActivity.class);
                startActivityForResult(intent, ADD_WORD_REQUEST_CODE);
            }
        });

        wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        wordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> wordList) {
                wordsAdapter.updateWords(wordList);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_WORD_REQUEST_CODE
                && resultCode == RESULT_OK
                && data != null) {
            String newWord = data.getStringExtra(AddWordActivity.EXTRA_WORD);
            wordViewModel.insertWord(newWord);
        }
    }

    private void initWordsRecyclerView() {
        wordsRv = findViewById(R.id.wordsRv);
        wordsRv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        wordsRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        wordsAdapter = new WordsAdapter();
        wordsRv.setAdapter(wordsAdapter);
    }
}
