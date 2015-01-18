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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



         interestSeekBar = (SeekBar) findViewById(R.id.seekBar);

        interestSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChange = progress;
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


    protected void addButtonActionListener()
    {
        calculateButton = (Button) findViewById(R.id.calculate_button);
        final EditText borrowed = (EditText) findViewById(R.id.amtBorrowed);
        final TextView paymentValue = (TextView) findViewById(R.id.paymentValue);

        calculateButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Amt Borrowed
                String amtBorrowed = borrowed.getText().toString(); // check this
                Float borrow = Float.parseFloat(amtBorrowed);


                // Interest rate slider

                float interestToDouble = (float)progressChange;
//                double total = borrow * progressChange;
//                payment.setText(Double.toString(total));


                // Loan Term Radion buttons
                loanRadioGroup = (RadioGroup) findViewById(R.id.loanRadioGroup);
                int radioButtonId = loanRadioGroup.getCheckedRadioButtonId();
                selectedRadioButton = (RadioButton)findViewById(radioButtonId);

                // Taxes and Insurance
                checkYes = (CheckBox) findViewById(R.id.checkYes);
                if(checkYes.isChecked())
                {
//                    paymentValue.setText(checkYes.getText());
                }
                else
                {
//                    paymentValue.setText(checkNo.getText());
                }

                // Display value of Monthly payment
//                paymentValue.setText(selectedRadioButton.getText());
            }
        });
    }

    protected float getMonthlyPayment(float principal, float interest,int numOfYears,boolean taxes)
    {
        float monthlyInterest = interest/1200;
        int numOfMonths = numOfYears * 12;
        float taxAmount;
        float monthlyPayment = 0;

        if(taxes == true)
        {
            taxAmount = (1/1000)* principal;
        }
        else
        {
            taxAmount = 0;
        }
        if(interest != 0.0)
        {
             monthlyPayment = (float) ((principal * (monthlyInterest/1 - (1/Math.pow(1+monthlyInterest,numOfMonths)))) + taxAmount);

        }
        else if(interest == 0.0)
        {
            monthlyPayment = (principal/numOfMonths) + taxAmount;
        }

        return monthlyPayment;
    }
}
