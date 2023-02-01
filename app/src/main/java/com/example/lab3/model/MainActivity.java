package com.example.lab3.model;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.lab3.R;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static com.example.lab3.model.Constants.VALIDATE_URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void validate(View view) {
        TextView login = findViewById(R.id.loginMain);
        TextView password = findViewById(R.id.passwordMain);
        String data = "{\"login\":\"" + login.getText().toString() + "\",\"password\":\"" + password.getText().toString() + "\"}";
        //System.out.println(data);
        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(() -> {
            try {
                String response = Rest.sendRequestWithBody(VALIDATE_URL, data,"POST");
                System.out.println(response);
                handler.post(() -> {
                    try {
                        if (!response.equals("Error") && !response.equals("No such user")) {
                            Intent intent = new Intent(MainActivity.this, MainPage.class);
                            intent.putExtra("USER_JSON", data);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Validation error", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}