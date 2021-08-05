package ru.geekbraines.lesson03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Calculator calculator;

    private TextView tvActions;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] numbersIds = new int[]{
                R.id.num_0,
                R.id.num_1,
                R.id.num_2,
                R.id.num_3,
                R.id.num_4,
                R.id.num_5,
                R.id.num_6,
                R.id.num_7,
                R.id.num_8,
                R.id.num_9,
                R.id.point
        };

        int[] actionsIds = new int[]{
                R.id.plus,
                R.id.minus,
                R.id.divide,
                R.id.multiply,
                R.id.equals,
                R.id.clear
        };

        tvActions = findViewById(R.id.tv_actions);
        tvResult = findViewById(R.id.tv_result);

        calculator = new Calculator();

        View.OnClickListener numberButtonOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.onNumPressed(v.getId());
                tvActions.setText(calculator.getActions());
                tvResult.setText(calculator.getResult());
            }
        };

        View.OnClickListener actionButtonOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.onActionPressed(v.getId());
                if (v.getId() != R.id.equals) {
                    tvActions.setText(calculator.getActions());
                }
                tvResult.setText(calculator.getResult());
            }
        };

        for (int i = 0; i < numbersIds.length; i++) {
            findViewById(numbersIds[i]).setOnClickListener(numberButtonOnClickListener);
        }

        for (int i = 0; i < actionsIds.length; i++) {
            findViewById(actionsIds[i]).setOnClickListener(actionButtonOnClickListener);
        }
    }
}