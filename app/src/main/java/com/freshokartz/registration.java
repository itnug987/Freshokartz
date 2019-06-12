package com.freshokartz;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class registration extends AppCompatActivity {
    Button relogin;
    ImageButton backhome1;

    private EditText first_name, middle_name, last_name, organization_name, email, mobile_number, password;
    Spinner spinnerCity;
    Spinner spinnerArea;
    Integer areaUser;
    Integer cityUser;
    private Button register_btn;

    //hashmap contains all CITIES with unique Id
    HashMap<String, Integer> cityList;

    //hashmap contains all AREAS with unique Id
    HashMap<String, Integer> areaList;

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(RegisterApi.DJANGO_SITE)
            .addConverterFactory(GsonConverterFactory.create());
    Retrofit retrofit = builder.build();
    RegisterApi registerApi = retrofit.create(RegisterApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        first_name = findViewById(R.id.first_name);
        middle_name = findViewById(R.id.middle_name);
        last_name = findViewById(R.id.last_name);
        organization_name = findViewById(R.id.organization_name);
        email = findViewById(R.id.email);
        mobile_number = findViewById(R.id.mobile_number);
        password = findViewById(R.id.password);


        register_btn = findViewById(R.id.register);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        // Creating all CITIES
        City city1 = (new City(3, "Hyderabad", 3));
        City city2 = (new City(2, "Gurugram", 2));
        City city3 = (new City(1,"Jaipur", 1));

        Area area1 = (new Area(1, "LAL KOTHI", 1));
        Area area2 = (new Area(2, "SODALA", 1));
        Area area3 = (new Area(3, "SOHNA ROAD", 2));
        Area area4 = (new Area(4,"Banjara hills", 3));


        //spinner for CITIES
        spinnerCity = findViewById(R.id.spinCity);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.cities, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(adapter);

        //spinner for AREAS
        spinnerArea = findViewById(R.id.spinArea);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.areas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArea.setAdapter(adapter2);


        // Creating all AREAS
        areaList = new HashMap<>();
        areaList.put(area1.getArea(), 1);
        areaList.put(area2.getArea(), 2);
        areaList.put(area3.getArea(), 3);
        areaList.put(area4.getArea(), 4);


        cityList = new HashMap<>();
        cityList.put(city1.getDistrict(), 3);
        cityList.put(city2.getDistrict(), 2);
        cityList.put(city3.getDistrict(), 1);

        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                Integer i = cityList.get(text);
                cityUser = cityList.get(text);

                Log.i("vvpo", String.valueOf(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                Integer i = areaList.get(text);
                areaUser = areaList.get(text);
                Log.i("oopo", String.valueOf(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        relogin = findViewById(R.id.relogin);
        relogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(registration.this, LoginAndRegistration.class);
                startActivity(i);
            }
        });

        backhome1 = findViewById(R.id.backhome1);
        backhome1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(registration.this, ActivityMain.class);
                startActivity(i);
            }
        });
    }

    private void register(){
        RegisterDetail reg = new RegisterDetail(first_name.getText().toString(), middle_name.getText().toString(), last_name.getText().toString(), organization_name.getText().toString(), email.getText().toString(), password.getText().toString(), mobile_number.getText().toString(), areaUser, cityUser);

        Call<RegisterDetail> call = registerApi.register(reg);

        call.enqueue(new Callback<RegisterDetail>() {
            @Override
            public void onResponse(Call<RegisterDetail> call, Response<RegisterDetail> response) {
                if (response.isSuccessful()) {

                    if (response.body() != null) {

                        Toast.makeText(registration.this, "registration success", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(registration.this, ActivityMain.class);
                        startActivity(i);

                    }
                } else {
                    Log.d("fail", "fail");
                }
            }

            @Override
            public void onFailure(Call<RegisterDetail> call, Throwable t) {

            }
        });
    }
}
