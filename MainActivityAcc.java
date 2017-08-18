package com.mycompany.myapp;

import android.app.*;
import android.os.*;
import android.hardware.SensorEventListener;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.widget.*;
import android.view.*; 
//import android.app.Activity; // might not need this line ***********************************************
import android.content.res.Resources;
//import android.os.Bundle; // might not need this line ***********************************************
//import android.view.View; // might not need this line ***********************************************
//import android.view.WindowManager; // might not need this line ***********************************************
//import android.widget.Button; // might not need this line ***********************************************

import com.mycompany.myapp.PieChart;


public class MainActivity extends Activity implements SensorEventListener
{
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 600;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        senSensorManager = (SensorManager) getSystemService(this.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);
     
        TextView tvNote = (TextView)findViewById(R.id.noteTextView);
        tvNote.setText("into phone is z-axis" + "\n x & y are nomal when in portrait mode.");
 
        /*   Resources res = getResources();

        setContentView(R.layout.main);
        final PieChart pie = (PieChart) this.findViewById(R.id.Pie);
        pie.addItem("Agamemnon", 2, res.getColor(R.color.seafoam));
        pie.addItem("Bocephus", 3.5f, res.getColor(R.color.chartreuse));
        pie.addItem("Calliope", 2.5f, res.getColor(R.color.emerald));
        pie.addItem("Daedalus", 3, res.getColor(R.color.bluegrass));
        pie.addItem("Euripides", 1, res.getColor(R.color.turquoise));
        pie.addItem("Ganymede", 3, res.getColor(R.color.slate));

        // This sets onClickListener for Button without declaring the button as a object in the code.
        ((Button) findViewById(R.id.Reset)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    pie.setCurrentItem(0);
                }
        });
        */
    }
       
    
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        
        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 500) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;
                
                float speed = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 10000;

                /*if (speed > SHAKE_THRESHOLD) {
                    TextView tvShake = (TextView)findViewById(R.id.mainTextView);
                    tvShake.setText("shaken");
                }*/
                
                TextView tvShake = (TextView)findViewById(R.id.mainTextView);
                tvShake.setText("(" + x + ", " + y + ", " + z + ")");
                
                last_x = x;
                last_y = y;
                last_z = z;
            }
            
        }
        
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    
    protected void onPause() {
        super.onPause();
        senSensorManager.unregisterListener(this);
    }
   
    protected void onResume() {
        super.onResume();
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
    
    
    
}
