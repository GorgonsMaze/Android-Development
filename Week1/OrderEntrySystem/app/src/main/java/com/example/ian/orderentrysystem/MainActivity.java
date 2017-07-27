package com.example.ian.orderentrysystem;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<Order> CandyOrders = new ArrayList<>();

    private EditText firstName;
    private EditText lastName;
    private Spinner chocolateType;
    private EditText numberOfBars;
    private RadioGroup shipping;
    private RadioButton normalShipping;
    private RadioButton expeditedShipping;
    private Button completeOrderBtn;
    private TextView orderStatusDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // set var to findViewById's of each el
        firstName = (EditText) findViewById(R.id.editTextFName);
        lastName = (EditText) findViewById(R.id.editTextLName);
        chocolateType = (Spinner) findViewById(R.id.spinnerChocolateType);
        numberOfBars = (EditText) findViewById(R.id.editTextNumOfBars);
        shipping = (RadioGroup) findViewById(R.id.radioGroupShipping);
        normalShipping = (RadioButton) findViewById(R.id.radioButtonNormShipping);
        expeditedShipping = (RadioButton) findViewById(R.id.radioButtonExpShipping);
        completeOrderBtn = (Button) findViewById(R.id.buttonSave);
        orderStatusDisplay = (TextView) findViewById(R.id.textViewOrderDisplay);

//        shipping.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int id) {
//                String radioVal;
//                switch (id) {
//                    case R.id.radioButtonNormShipping:
//                        radioVal = normalShipping.getText().toString();
//                        break;
//                    case R.id.radioButtonExpShipping:
//                        radioVal = expeditedShipping.getText().toString();
//                        break;
//
//                }
//
//                return radioVal;
//            }
//        });

        // set on click listener for order completion
        completeOrderBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String fName = firstName.getText().toString();
                String lName = lastName.getText().toString();
                String chocType = chocolateType.getSelectedItem().toString();
                int numOfBars = Integer.valueOf(numberOfBars.getText().toString());
                String shippingType;
                if (normalShipping.isChecked()) {
                    shippingType = normalShipping.getText().toString();
                } else {
                    shippingType = expeditedShipping.getText().toString();
                }


                Order newOrder = new Order();
                newOrder.setFirstName(fName);
                newOrder.setLastName(lName);
                newOrder.setChocolateType(chocType);
                newOrder.setNumOfBarsPurchased(numOfBars);
                newOrder.setShippingType(shippingType);

                CandyOrders.add(newOrder);

                orderStatusDisplay.setText("Order Added. There are now " + CandyOrders.size() + " orders.");

            }


        });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
