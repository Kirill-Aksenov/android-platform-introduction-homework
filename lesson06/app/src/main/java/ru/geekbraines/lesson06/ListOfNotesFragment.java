package ru.geekbraines.lesson06;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_of_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout linearView = (LinearLayout)view;
        notes = new Note[]{
                new Note(getString(R.string.title01), getString(R.string.content01), Calendar.getInstance()),
                new Note(getString(R.string.title02), getString(R.string.content02), Calendar.getInstance()),
                new Note(getString(R.string.title03), getString(R.string.content03), Calendar.getInstance())
        };

        for (Note note : notes) {
            TextView tv1 = new TextView(getContext());
            TextView tv2 = new TextView(getContext());
            tv1.setText(note.getNoteTitle());
            tv1.setTextSize(22);
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
            tv2.setText(formatter.format(note.getDateOfCreation().getTime()));
            linearView.addView(tv1);
            linearView.addView(tv2);
        }
    }
}