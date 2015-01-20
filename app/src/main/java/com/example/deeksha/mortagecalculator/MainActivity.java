package com.example.deeksha.mortagecalculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private int progressChange = 0;
    private RadioGroup loanRadioGroup;
    private RadioButton selectedRadioButton;
    private CheckBox checkYes;
    private Button calculateButton;
    private SeekBar interestSeekBar;
    private EditText borrowed;
    private TextView paymentValue;
    private TextView seekBarValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        interestSeekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBarValue = (TextView) findViewById(R.id.seekBarValue);

        interestSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                progressChange = progress;
                seekBarValue.setText(String.valueOf(progress) +".0%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        addButtonActionListener();
    }


    protected void addButtonActionListener() {
        calculateButton = (Button) findViewById(R.id.calculate_button);
        borrowed = (EditText) findViewById(R.id.amtBorrowed);
        paymentValue = (TextView) findViewById(R.id.paymentValue);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Amt Borrowed
                String amtBorrowed = borrowed.getText().toString();
                float principal = 0;
                if(amtBorrowed != null && !(amtBorrowed.isEmpty()))
                {
                    principal = Float.parseFloat(amtBorrowed);
                }

                // Interest rate slider
                float annualInterest = (float) progressChange;

                // Loan Term Radio buttons
                loanRadioGroup = (RadioGroup) findViewById(R.id.loanRadioGroup);
                int radioButtonId = loanRadioGroup.getCheckedRadioButtonId();
                selectedRadioButton = (RadioButton) findViewById(radioButtonId);

                int numOfYears;
                if (selectedRadioButton.getText().equals("7")) {
                    numOfYears = 7;
                } else if (selectedRadioButton.getText().equals("15")) {
                    numOfYears = 15;
                } else {
                    numOfYears = 30;
                }

                // Taxes and Insurance
                boolean taxes;
                checkYes = (CheckBox) findViewById(R.id.checkYes);
                if (checkYes.isChecked()) {
                    taxes = true;
                } else {
                    taxes = false;
                }

                double monthlyPayment = getMonthlyPayment(principal,annualInterest,numOfYears,taxes);

                // Display value of Monthly payment
                paymentValue.setText("Your payment is $"+Double.toString(monthlyPayment));
            }
        });
    }


    /*
    This method calculates the monthly payment based on inputs from the user
     */
    protected double getMonthlyPayment(float principal, float interest, int numOfYears, boolean taxes) {
        float monthlyInterest = interest / 1200;
        int numOfMonths = numOfYears * 12;
        float taxAmount;
        double monthlyPayment = 0;

        if (taxes)
        {
            taxAmount = (principal/1000);
        } else
        {
            taxAmount = 0;
        }
        if (interest > 0.0) {
            monthlyPayment = (float) ((principal * (monthlyInterest/(1 - (Math.pow(1/(1+monthlyInterest), numOfMonths)))))+taxAmount);

        } else {
            monthlyPayment = (principal / numOfMonths) + taxAmount;
        }

        return monthlyPayment;
    }

}
