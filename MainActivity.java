package com.example.sony.gpstest1;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.location.Location;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    private ProgressDialog pDialog;
    TextView text;
    // url to create new product
    private static String url = "http://192.168.68.171/GPSWebTest/TestGPS.php"; //heba
    JSONParser jsonParser = new JSONParser();
    String lat, lon, dev_model, dev_id;
    int res=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text= (TextView) findViewById(R.id.textView);
        getCurrentLocation();
    }
    private void getCurrentLocation() {
        CustomLocationManager.getCustomLocationManager().getCurrentLocation(this, locationValue);
    }

    public LocationValue locationValue = new LocationValue() {
        @Override
        public void getCurrentLocation(Location location) {
            // You will get location here if the GPS is enabled
            if(location != null) {
                Log.d("LOCATION", location.getLatitude() + ", " + location.getLongitude());
                text.setText("Latit:" + location.getLatitude() + "  Long:" + location.getLongitude());
                lat= String.valueOf(location.getLatitude());
                lon= String.valueOf(location.getLongitude());
                dev_id= android.os.Build.ID;
                dev_model=android.os.Build.MODEL;

                new Add().execute(lat, lon, dev_id, dev_model);
            }
        }
    };

    class Add extends AsyncTask<String, String, String> {
        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Sending Location..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating product
         */
        protected String doInBackground(String... args) {

            ContentValues values = new ContentValues();
            values.put("latitude", args[0]);
            values.put("longitude", args[1]);
            values.put("dev_id", args[2]);
            values.put("dev_mod", args[3]);
            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url, "GET", values);

            // check log cat fro response
           // Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt("success");

                if (success == 1) {
                    res = json.getInt("result");
                    //Toast.makeText(MainActivity.this, res, Toast.LENGTH_LONG).show();

                } else {
                    // failed to create product
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();

            }

            return res+"";
        }
        /**
         * After completing background task Dismiss the progress dialog
         * *
         */
        protected void onPostExecute(String data) {
            // dismiss the dialog once done
            pDialog.dismiss();
            if(res==1)
                Toast.makeText(MainActivity.this, "Sent & Saved", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this, "Sent & Not Saved", Toast.LENGTH_LONG).show();
        }

    }


}
