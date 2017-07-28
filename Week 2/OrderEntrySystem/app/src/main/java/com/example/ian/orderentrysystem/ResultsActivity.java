package com.example.ian.orderentrysystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import org.w3c.dom.Text;

public class ResultsActivity extends AppCompatActivity {

    static ArrayList<Order> CandyOrders = new ArrayList<>();

    private Button backToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        backToMain = (Button) findViewById(R.id.btnBackToMain);


        Bundle extras = getIntent().getExtras();
        Order newOrder = extras.getParcelable("yourkey");
        CandyOrders.add(newOrder);

        // Declare ListView
        ListView results = (ListView) findViewById(R.id.resultsList);
        // defining Adapter for List content

        //simple_list_item_1 contains only a TextView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);
        // adding entries in List

        for (int counter = 0; counter < CandyOrders.size(); counter++) {
            adapter.add(CandyOrders.get(counter).getFirstName() + " " + CandyOrders.get(counter).getLastName() + " - " + CandyOrders.get(counter).getChocolateType() + " - " + CandyOrders.get(counter).getNumOfBarsPurchased() + " bars");
        }
        // setting adapter to list
        results.setAdapter(adapter);

        backToMain.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }


    @Override
    public void finish() {
        Intent i = new Intent();
        String numberOfOrders = "Number of Orders = " + CandyOrders.size();
        i.putExtra("returnkey", numberOfOrders);
        setResult(RESULT_OK, i);
        // Close
        super.finish();
    }


}
