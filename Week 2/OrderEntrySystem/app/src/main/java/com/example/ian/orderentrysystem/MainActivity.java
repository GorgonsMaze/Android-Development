package com.example.ian.orderentrysystem;

import android.content.Intent;
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
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 10;

    private EditText firstName;
    private EditText lastName;
    private Spinner chocolateType;
    private EditText numberOfBars;
    private RadioGroup shipping;
    private RadioButton normalShipping;
    private RadioButton expeditedShipping;
    private Button completeOrderBtn;
    private TextView orderStatusDisplay;
    private Button getResultsBtn;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            if (data.hasExtra("returnkey")) {
                String result = data.getExtras().getString("returnkey");
                orderStatusDisplay.setText(result);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

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

    }


    public void goToResults(View view) {

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

        Intent i = new Intent(this, com.example.ian.orderentrysystem.ResultsActivity.class);
        i.putExtra("yourkey", newOrder);
        startActivityForResult(i, REQUEST_CODE);
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
