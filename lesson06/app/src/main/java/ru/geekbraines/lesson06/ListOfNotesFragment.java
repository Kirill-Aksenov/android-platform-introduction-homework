package ru.geekbraines.lesson06;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ListOfNotesFragment extends Fragment {
    private Note[] notes;
    private Note currentNote;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_of_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initNotes(view);
    }

    private void initNotes(View view) {
        notes = new Note[]{
                new Note(getString(R.string.title01), getString(R.string.content01), Calendar.getInstance()),
                new Note(getString(R.string.title02), getString(R.string.content02), Calendar.getInstance()),
                new Note(getString(R.string.title03), getString(R.string.content03), Calendar.getInstance())
        };

        for (Note note : notes) {
            LinearLayout linearView = (LinearLayout) view;
            TextView tvTitle = new TextView(getContext());
            TextView tvDate = new TextView(getContext());
            tvTitle.setText(note.getNoteTitle());
            tvTitle.setTextSize(22);
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
            tvDate.setText(formatter.format(note.getDateOfCreation().getTime()));
            linearView.addView(tvTitle);
            linearView.addView(tvDate);
            tvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentNote = note;
                    showPortNote(currentNote);
                }
            });
        }
    }

    // показать заметку в портретной ориентации
    private void showPortNote(Note currentNote){
        // Откроем вторую activity
        Intent intent = new Intent();
        intent.setClass(getActivity(), NoteActivity.class);
        // и передадим туда параметры
        intent.putExtra(NoteFragment.CURRENT_NOTE, currentNote);
        startActivity(intent);
    }
}