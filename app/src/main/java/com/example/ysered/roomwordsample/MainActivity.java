package com.example.ysered.roomwordsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ysered.roomwordsample.persistence.Word;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WordViewModel wordViewModel;

    private WordsAdapter wordsAdapter;
    private RecyclerView wordsRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wordsRv = findViewById(R.id.wordsRv);
        wordsRv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        wordsAdapter = new WordsAdapter();

        wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        wordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> wordList) {
                wordsAdapter.updateWords(wordList);
            }
        });
    }
}
