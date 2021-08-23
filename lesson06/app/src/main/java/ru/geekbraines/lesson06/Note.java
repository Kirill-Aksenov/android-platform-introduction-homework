package ru.geekbraines.lesson06;

import java.util.Calendar;

public class Note {
    private String noteTitle;
    private String noteContent;
    private Calendar dateOfCreation;

    public Note(String noteTitle, String noteContent, Calendar dateOfCreation) {
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
        this.dateOfCreation = dateOfCreation;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public Calendar getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Calendar dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}
