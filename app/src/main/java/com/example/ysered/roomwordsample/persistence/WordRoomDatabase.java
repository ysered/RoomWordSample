package com.example.ysered.roomwordsample.persistence;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {Word.class}, version = 1)
abstract class WordRoomDatabase extends RoomDatabase {

    private static volatile WordRoomDatabase INSTANCE;

    private static final RoomDatabase.Callback cleanUpCallback = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDatabaseTask(INSTANCE).execute();
        }
    };

    abstract WordDao wordDao();

    static WordRoomDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, WordRoomDatabase.class, "word_database")
                            .addCallback(cleanUpCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final class PopulateDatabaseTask extends AsyncTask<Void, Void, Void> {

        private final WordDao wordDao;

        PopulateDatabaseTask(WordRoomDatabase database) {
            this.wordDao = database.wordDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Word hello = new Word("Hello");
            Word world = new Word("World");

            wordDao.deleteAll();
            wordDao.insert(hello);
            wordDao.insert(world);
            return null;
        }
    }
}
