package com.example.deeksha.mortagecalculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    int progressChange = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final SeekBar interestRate = (SeekBar) findViewById(R.id.seekBar);

        interestRate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChange = progress;
                Toast.makeText(getApplicationContext(), "interest rate is "+ progressChange,Toast.LENGTH_SHORT).show();
//                interestRate
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        // button Listener
        Button calculateButton = (Button) findViewById(R.id.calculate_button);
        final EditText borrowed = (EditText) findViewById(R.id.amtBorrowed);
        final TextView payment = (TextView) findViewById(R.id.payment);

        calculateButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amtBorrowed = borrowed.getText().toString(); // check this
                Double borrow = Double.parseDouble(amtBorrowed);

                double total = borrow * progressChange;
                payment.setText(Double.toString(total));
            }
        });
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
