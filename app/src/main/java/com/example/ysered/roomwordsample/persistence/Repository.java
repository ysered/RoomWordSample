package com.example.ysered.roomwordsample.persistence;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;


public class Repository {

    private WordDao wordDao;
    private LiveData<List<Word>> allWords;

    public Repository(Application application) {
        WordRoomDatabase database = WordRoomDatabase.getDatabase(application);
        wordDao = database.wordDao();
        allWords = wordDao.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return allWords;
    }

    public void insertWord(Word word) {
        new InsertAsyncTask(wordDao).execute(word);
    }

    private static class InsertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao wordDao;

        InsertAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            Word word = words[0];
            wordDao.insert(word);
            return null;
        }
    }
}
