package ru.geekbraines.lesson05;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class SettingsActivity extends AppCompatActivity {

    protected static final String KEY_THEME = "keyTheme";

    private SharedPreferences sharedPreferences;

    private RadioButton radioButtonLight;
    private RadioButton radioButtonDark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedPreferences = getSharedPreferences(KEY_THEME, MODE_PRIVATE);

        radioButtonLight = findViewById(R.id.radioButtonLight);
        radioButtonDark = findViewById(R.id.radioButtonDark);
        Button buttonReturn = findViewById(R.id.button_return);

        checkTheme();

        radioButtonLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                saveThemeState(false);
            }
        });

        radioButtonDark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                saveThemeState(true);
            }
        });

        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // сохраняем выбранную тему через SharedPreferences при помощи булева значения isDarkTheme
    private void saveThemeState(boolean isDarkTheme) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_THEME, isDarkTheme).apply();
    }

    //получаем сохраненные настройки
    public void checkTheme() {
        if (sharedPreferences.getBoolean(KEY_THEME, false)) {
            radioButtonDark.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            radioButtonLight.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}