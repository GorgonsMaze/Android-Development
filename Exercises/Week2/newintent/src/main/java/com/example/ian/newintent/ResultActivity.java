package com.example.ian.newintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Get bundle for all putExtras to ==> go get them!
        Bundle extras = getIntent().getExtras();
        String inputString = extras.getString("yourkey");
        TextView myText = (TextView) findViewById(R.id.textView);
        myText.setText(inputString);

    }

    @Override
    public void finish() {
        Intent i = new Intent();
        EditText myText = (EditText)findViewById(R.id.editText2);
        String resultstring = myText.getText().toString();

        i.putExtra("returnkey",resultstring);
        setResult(RESULT_OK, i);

        // Close
        super.finish();
    }


}
