package sensors.ian.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import static android.hardware.Sensor.REPORTING_MODE_ON_CHANGE;
import static android.hardware.Sensor.TYPE_GRAVITY;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mySensorManager; // global variable
    private ListView sensorsList;
    private TextView txtView1;
    private TextView txtView2;
    private TextView txtView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtView1 = (TextView) findViewById(R.id.txtView);
        txtView2 = (TextView) findViewById(R.id.txtView2);
        txtView3 = (TextView) findViewById(R.id.txtView3);

        sensorsList = (ListView) findViewById(R.id.listView);

        mySensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        List<Sensor> list = mySensorManager.getSensorList(Sensor.TYPE_ALL);

        sensorsList.setAdapter(new ArrayAdapter<Sensor>(this, android.R.layout.simple_list_item_1, list));

        mySensorManager.registerListener(this, mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);


    }

    @Override
    protected void onResume() {
        super.onResume();
        mySensorManager.registerListener(this, mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    protected void onPause() {
        super.onPause();
        mySensorManager.unregisterListener(this);
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

    public void processSensor(SensorEvent event) {
        txtView1.setText(String.valueOf(event.values[0]));
        txtView2.setText(String.valueOf(event.values[1]));
        txtView3.setText(String.valueOf(event.values[2]));
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            // this is a method that you write to parse out the sensorEvent data
            processSensor(sensorEvent);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
