package com.example.ian.orderentrysystem;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.IntDef;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
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


        // set on click listener for order completion
        completeOrderBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String fName = firstName.getText().toString();
                String lName = lastName.getText().toString();
                String chocType = chocolateType.getSelectedItem().toString();
                int numOfBars = Integer.valueOf(numberOfBars.getText().toString());
                boolean shippingType;
                if (normalShipping.isChecked()) {
                    shippingType = true;
                } else {
                    shippingType = false;
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

    public void clearInputFields() {
        firstName.setText("");
        lastName.setText("");
        chocolateType.setSelection(0);
        numberOfBars.setText("");
        normalShipping.setChecked(true);
    }

    public void doubleOrder() {
        int orderDoubled = Integer.parseInt(numberOfBars.getText().toString()) * 2;
        numberOfBars.setText(String.valueOf(orderDoubled));
    }

    public void getFirstOrder() {
        // Get chocolate type string value
        String chocType = CandyOrders.get(0).getChocolateType();

        firstName.setText(CandyOrders.get(0).getFirstName());
        lastName.setText(CandyOrders.get(0).getLastName());

        ArrayAdapter<String> adapter = (ArrayAdapter)chocolateType.getAdapter();

        int position = adapter.getPosition(chocType);
        chocolateType.setSelection(position);


        numberOfBars.setText(String.valueOf(CandyOrders.get(0).getNumOfBarsPurchased()));

        if (CandyOrders.get(0).getShippingType()) {
            normalShipping.setChecked(true);
        } else {
            expeditedShipping.setChecked(true);
        }

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

        switch (id) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_new:
                // User chose the "new file" icon action
                clearInputFields();
                return true;

            case R.id.action_double_order:
                // User chose the double order action
                doubleOrder();
                return true;

            case R.id.action_new_text:
                // User chose the new file <text> action from menu
                clearInputFields();
                return true;

            case R.id.action_get_first_order:
                // User chose the get first order detail action
                getFirstOrder();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }


    }
}
