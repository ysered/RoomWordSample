package com.example.ysered.roomwordsample;

import android.app.Application;

import com.example.ysered.roomwordsample.persistence.Repository;
import com.example.ysered.roomwordsample.persistence.Word;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


public class WordViewModel extends AndroidViewModel {

    private Repository repository;

    private LiveData<List<Word>> allWords;

    public WordViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allWords = repository.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return allWords;
    }

    public void insert(Word word) {
        repository.insertWord(word);
    }
}
