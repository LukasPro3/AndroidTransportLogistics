package com.example.lab3.model;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.lab3.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static com.example.lab3.model.Constants.*;

public class TruckDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truck_details);

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        Gson gson = new Gson();
        Intent currentIntent = getIntent();
        String truckId = currentIntent.getStringExtra("TruckId");
        System.out.println(truckId);


        executor.execute(() -> {
            try {
                String response = Rest.sendRequest(TRUCK_BY_ID_URL + truckId,"GET");
                System.out.println(response);
                if(!response.equals("Error")) {
                    handler.post(() -> {
                        //Gson gson = new Gson();
                        Truck truck = gson.fromJson(response, Truck.class);
                        EditText license = findViewById(R.id.LicensePlateField);
                        license.setText(truck.getLicensePlate());
                        EditText vin = findViewById(R.id.VinField);
                        vin.setText(truck.getVin());
                        EditText euroStandard = findViewById(R.id.euroStandartField);
                        euroStandard.setText(String.valueOf(truck.getEuroStandard()));
                        EditText mileage = findViewById(R.id.mileageField);
                        mileage.setText(String.valueOf(truck.getMileage()));
                        EditText horsePower = findViewById(R.id.horsePowerField);
                        horsePower.setText(String.valueOf(truck.getHorsePower()));
                        EditText power = findViewById(R.id.powerField);
                        power.setText(String.valueOf(truck.getKwPower()));
                        EditText color = findViewById(R.id.colorField);
                        color.setText(truck.getColor());
                        EditText fuel = findViewById(R.id.fuelTankCapacity);
                        fuel.setText(String.valueOf(truck.getFuelTankCapacity()));
                        EditText date = findViewById(R.id.TechDateField);
                        date.setText(String.valueOf(truck.getTechnicalInspectionUntil()));

                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void updateTruck(View view) {
        EditText license = findViewById(R.id.LicensePlateField);
        String new_license = String.valueOf(license.getText());
        EditText vin = findViewById(R.id.VinField);
        String new_vin= String.valueOf(vin.getText());
        EditText euroStandard = findViewById(R.id.euroStandartField);
        String new_euro = String.valueOf(euroStandard.getText());
        EditText mileage = findViewById(R.id.mileageField);
        String new_mileage = String.valueOf(mileage.getText());
        EditText horsePower = findViewById(R.id.horsePowerField);
        String new_hp = String.valueOf(horsePower.getText());
        EditText power = findViewById(R.id.powerField);
        String new_power = String.valueOf(power.getText());
        EditText color = findViewById(R.id.colorField);
        String new_color = String.valueOf(color.getText());
        EditText fuel = findViewById(R.id.fuelTankCapacity);
        String new_fuel = String.valueOf(fuel.getText());
        EditText date = findViewById(R.id.TechDateField);
        String new_date = String.valueOf(date.getText());

        String data = "{\"techInspectionUntil\":\"" + "2024-12-22" + "\",\"mileage\":\""+ new_mileage +"\",\"fuelTank\":\""+ new_fuel +"\",\"color\":\""+ new_color +"\",\"horsePower\":\""+ new_hp +"\",\"power\":\""+ new_power +"\",\"licensePlate\":\""+ new_license +"\",\"vin\":\""+ new_vin +"\",\"assignedTo\":\""+ "null" +"\",\"currentStatus\":\""+ "FREE" +"\",\"euroStandard\":\"" + new_euro + "\"}";

        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                String response = Rest.sendRequestWithBody(TRUCK_UPDATE_URL,data,"PUT");
                System.out.println(data);
                System.out.println(response);
                if(response.equals("Update successful")){
                    Intent intent = new Intent(TruckDetails.this, MainPage.class);
                    startActivity(intent);
                }
                }
             catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void deleteTruck(View view) {
        Executor executor = Executors.newSingleThreadExecutor();
        Intent currentIntent = getIntent();
        String truckId = currentIntent.getStringExtra("TruckId");
        System.out.println(truckId);
        executor.execute(() -> {
            try {
                String response = Rest.sendRequest(TRUCK_DELETE_URL + truckId,"DELETE");
                System.out.println(response);
                Intent intent = new Intent(TruckDetails.this, MainPage.class);
                startActivity(intent);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}