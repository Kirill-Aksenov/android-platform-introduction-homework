package ru.geekbraines.lesson03;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DecimalFormat;

public class Calculator implements Parcelable {

    private double firstArg;
    private double secondArg;
    private int actionSelected;

    private StringBuilder inputStr = new StringBuilder();
    private DecimalFormat decimalFormat = new DecimalFormat("#.###");
    private String result;
    private State state;

    protected Calculator(Parcel in) {
        firstArg = in.readDouble();
        secondArg = in.readDouble();
        actionSelected = in.readInt();
        result = in.readString();
    }

    public static final Creator<Calculator> CREATOR = new Creator<Calculator>() {
        @Override
        public Calculator createFromParcel(Parcel in) {
            return new Calculator(in);
        }

        @Override
        public Calculator[] newArray(int size) {
            return new Calculator[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(firstArg);
        dest.writeDouble(secondArg);
        dest.writeInt(actionSelected);
        dest.writeString(result);
    }

    private enum State {
        firstArgInput,
        secondArgInput,
        resultShow
    }

    public Calculator() {
        state = State.firstArgInput;
    }

    public void onNumPressed(int buttonId) {


        if (state == State.resultShow) {
            state = State.firstArgInput;
            inputStr.setLength(0);
            result = null;
        }

        if (inputStr.length() < 9 || state == State.secondArgInput && inputStr.length() < 21) {
            switch (buttonId) {
                case R.id.num_0:
                    if (inputStr.length() != 0) {
                        inputStr.append("0");
                    }
                    break;
                case R.id.num_1:
                    inputStr.append("1");
                    break;
                case R.id.num_2:
                    inputStr.append("2");
                    break;
                case R.id.num_3:
                    inputStr.append("3");
                    break;
                case R.id.num_4:
                    inputStr.append("4");
                    break;
                case R.id.num_5:
                    inputStr.append("5");
                    break;
                case R.id.num_6:
                    inputStr.append("6");
                    break;
                case R.id.num_7:
                    inputStr.append("7");
                    break;
                case R.id.num_8:
                    inputStr.append("8");
                    break;
                case R.id.num_9:
                    inputStr.append("9");
                    break;
                case R.id.point:
                    inputStr.append(".");
                    break;
            }
        }
    }

    public void onActionPressed(int actionId) {

        if (actionId == R.id.clear) {
            inputStr.setLength(0);
            result = null;
        }

        if (result != null) {
            inputStr.replace(0, inputStr.length(), result);
            result = null;
            firstArg = Double.parseDouble(inputStr.toString());
            state = State.secondArgInput;

            addsAction(actionId);
        }

        if (actionId == R.id.equals && state == State.secondArgInput) {
            int position = inputStr.indexOf(" ");
            String temp = inputStr.substring(position + 3);
            secondArg = Double.parseDouble(temp);
            state = State.resultShow;

            switch (actionSelected) {
                case R.id.plus:
                    result = decimalFormat.format(firstArg + secondArg);
                    break;
                case R.id.minus:
                    result = decimalFormat.format(firstArg - secondArg);
                    break;
                case R.id.multiply:
                    result = decimalFormat.format(firstArg * secondArg);
                    break;
                case R.id.divide:
                    result = decimalFormat.format(firstArg / secondArg);
                    break;
            }

        } else if (inputStr.length() > 0 && state == State.firstArgInput) {
            firstArg = Double.parseDouble(inputStr.toString());
            state = State.secondArgInput;

            addsAction(actionId);
        }
    }

    private void addsAction(int actionId) {
        switch (actionId) {
            case R.id.plus:
                actionSelected = R.id.plus;
                inputStr.append(" + ");
                break;
            case R.id.minus:
                actionSelected = R.id.minus;
                inputStr.append(" - ");
                break;
            case R.id.multiply:
                actionSelected = R.id.multiply;
                inputStr.append(" × ");
                break;
            case R.id.divide:
                actionSelected = R.id.divide;
                inputStr.append(" ÷ ");
                break;
        }
    }

    public String getActions() {
        return inputStr.toString();
    }

    public String getResult() {
        return result;
    }
}