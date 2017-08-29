package com.example.ian.orderentrysystem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import static android.R.attr.data;
import static android.R.attr.duration;
import static android.widget.Toast.makeText;

public class ResultsActivity extends AppCompatActivity {

    private Button backToMain;
    private Button searchByPrice;
    private EditText priceToSearch;
    DatabaseHandler db;
    ListView results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DatabaseHandler(this);
        searchByPrice = (Button) findViewById(R.id.btnSearchByPrice);
        priceToSearch = (EditText) findViewById(R.id.editTextPriceToSearch);
        backToMain = (Button) findViewById(R.id.btnBackToMain);
        results = (ListView) findViewById(R.id.resultsList);


        Bundle extras = getIntent().getExtras();
        Order newOrder = extras.getParcelable("yourkey");

        db.addOrder(newOrder);

        List<Order> orders = db.getAllOrders();


        //simple_list_item_1 contains only a TextView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);

        // adding entries in List
        for (int counter = 0; counter < orders.size(); counter++) {
            adapter.add(orders.get(counter).getFirstName() + " " + orders.get(counter).getLastName() + " ID = " + orders.get(counter).getId() + " - $" +  String.format("%.2f", orders.get(counter).getPrice()));
        }

        // setting adapter to list
        results.setAdapter(adapter);


        searchByPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (priceToSearch.getText().toString().matches("")) {
                    String text = "Enter a price to search!";

                    Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                    toast.show();
                    return;

                }


                List<Order> ordersByPrice = db.getOrderByPrice(Float.valueOf(priceToSearch.getText().toString()));

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_list_item_1);


                for (int counter = 0; counter < ordersByPrice.size(); counter++) {
                    adapter.add(ordersByPrice.get(counter).getFirstName() + " " + ordersByPrice.get(counter).getLastName() + " ID = " + ordersByPrice.get(counter).getId() + " - $" +  String.format("%.2f", ordersByPrice.get(counter).getPrice()));
                }


                results.setAdapter(adapter);

            }
        });


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
        String numberOfOrders = "Number of Orders = " + db.getOrdersCount();
        i.putExtra("returnkey", numberOfOrders);
        setResult(RESULT_OK, i);
        // Close
        super.finish();
    }


}
