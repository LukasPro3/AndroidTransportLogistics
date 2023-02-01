package com.example.lab3.model;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.lab3.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static com.example.lab3.model.Constants.TRUCK_ALL_URL;

public class MainPage extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);


        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        Gson gson = new Gson();
        Intent currentIntent = getIntent();
        String userDataJson = currentIntent.getStringExtra("USER_JSON");
        System.out.println(userDataJson);
        Properties properties = gson.fromJson(userDataJson, Properties.class);
        //String assignedTruckId = "{\"assignedTruckId\":\"" + String.valueOf(properties.getProperty("assignedTruckId")) + "\"}";


            executor.execute(() -> {
                try {
                    String response = Rest.sendRequest(TRUCK_ALL_URL, "GET");
                    System.out.println(response);
                    handler.post(() -> {
                        try {
                            if (!response.equals("Error")) {

                                Gson builder = new GsonBuilder().create();

                                Type truckType = new TypeToken<List<Truck>>() {
                                }.getType();
                                final List<Truck> truckListFromJson = builder.fromJson(response, truckType);


                                ListView truckListView = findViewById(R.id.truckList);

                                ArrayAdapter<Truck> arrayAdapter = new ArrayAdapter<>(MainPage.this, android.R.layout.simple_list_item_1, truckListFromJson);
                                truckListView.setAdapter(arrayAdapter);

                                truckListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Truck selectedTruck = truckListFromJson.get(position);
                                        Toast.makeText(MainPage.this, "Selected truck: " + selectedTruck.getLicensePlate(), Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(MainPage.this, TruckDetails.class);
                                        intent.putExtra("TruckId", String.valueOf(selectedTruck.getId()));
                                        startActivity(intent);
                                    }
                                });


                            } else {
                                Toast.makeText(MainPage.this, "Validation error", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
//        executor.execute(() -> {
//            try {
//                String response = Rest.sendRequestWithBody(ASSIGNED_TRUCK_URL,assignedTruckId, "GET");
//                System.out.println(response);
//                handler.post(() -> {
//                    try {
//                        if (!response.equals("Error")&&!response.equals("No such truck by given Id")) {
//
//                            Gson builder = new GsonBuilder().create();
//
//                            Type truckType = new TypeToken<List<Truck>>() {
//                            }.getType();
//                            final List<Truck> assignedTruckListFromJson = builder.fromJson(response, truckType);
//
//
//                            ListView assignedTruckListView = findViewById(R.id.assignedTruckList);
//
//                            ArrayAdapter<Truck> arrayAdapter = new ArrayAdapter<>(MainPage.this, android.R.layout.simple_list_item_1, assignedTruckListFromJson);
//                            assignedTruckListView.setAdapter(arrayAdapter);
//
//                        } else {
//                            Toast.makeText(MainPage.this, "No assigned trucks", Toast.LENGTH_LONG).show();
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                });
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });

    }

    public void logOff(View view) {
        Intent intent = new Intent(MainPage.this, MainActivity.class);
        startActivity(intent);
    }
}