package ru.geekbraines.lesson06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

// Эта activity для показа заметки в портретной ориентации
public class NoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        // Если устройство перевернули в альбомную ориентацию, то надо эту activity закрыть
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        if (savedInstanceState == null) {
            // Если эта activity запускается первый раз (с каждой новой заметкой первый раз),
            // то перенаправим параметр фрагменту
            NoteFragment noteFragment = new NoteFragment();
            noteFragment.setArguments(getIntent().getExtras());
            // Добавим фрагмент на activity
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, noteFragment)
                    .commit();
        }
    }
}