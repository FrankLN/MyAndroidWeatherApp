package com.example.myweatherapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    String city;
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city = "Randers";

        final TextView location = (TextView) findViewById(R.id.location);
        location.setText(city);

        final TextView temp = (TextView) findViewById(R.id.temp);

        queue = Volley.newRequestQueue(this);
        String url ="http://api.apixu.com/v1/current.json?key=c97fdf5e9ad949909b3130829172803&q=" + city;


        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            JSONObject result = new JSONObject(response);
                            String temp_c = result.getJSONObject("current").getString("temp_c") + "°C";
                            temp.setText(temp_c);
                        }
                        catch (Exception e) {
                            Log.d("Err", e.getMessage());
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);

        final TextView txtCity = (TextView)findViewById(R.id.city);
        Button btnCity = (Button)findViewById(R.id.btnCity);

        btnCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city = String.valueOf(txtCity.getText());
                location.setText(city);
                temp.setText("N/A");

                //RequestQueue queue = Volley.newRequestQueue(this);
                String url ="http://api.apixu.com/v1/current.json?key=c97fdf5e9ad949909b3130829172803&q=" + city;

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                try {
                                    JSONObject result = new JSONObject(response);
                                    String temp_c = result.getJSONObject("current").getString("temp_c") + "°C";
                                    temp.setText(temp_c);
                                }
                                catch (Exception e) {
                                    Log.d("Err", e.getMessage());
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
                // Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });

    }

}
