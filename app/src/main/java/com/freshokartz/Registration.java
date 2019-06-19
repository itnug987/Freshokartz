package com.freshokartz;

import android.content.Intent;
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

import com.freshokartz.Area.AreaList;
import com.freshokartz.City.CityList;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Registration extends AppCompatActivity {
    Button relogin;
    ImageButton backhome1;
    public ArrayList<String> areasL;
    public ArrayAdapter<String> arrayAdapterArea;
    public ArrayList<String> citiesL;
    public ArrayAdapter<String> arrayAdapterCity;
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


        //spinner for CITIES
//        spinnerCity = findViewById(R.id.spinCity);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.cities, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerCity.setAdapter(adapter);

        //spinner for AREAS
        spinnerArea = findViewById(R.id.spinArea);
        spinnerCity = findViewById(R.id.spinCity);
//        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.areas, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerArea.setAdapter(adapter2);


        // Creating all AREAS

//        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String text = parent.getItemAtPosition(position).toString();
//                Integer i = cityList.get(text);
//                cityUser = cityList.get(text);
//
//                Log.i("vvpo", String.valueOf(i));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
       // });

//        spinnerArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String text = parent.getItemAtPosition(position).toString();
//                Integer i = areaList.get(text);
//                areaUser = areaList.get(text);
//                Log.i("oopo", String.valueOf(i));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        relogin = findViewById(R.id.relogin);
        relogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Registration.this, LoginAndRegistration.class);
                startActivity(i);
            }
        });

        backhome1 = findViewById(R.id.backhome1);
        backhome1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Registration.this, ActivityMain.class);
                startActivity(i);
            }
        });
        getCitydata();
        getAreaDe();
    }

    private void register(){
        RegisterDetail reg = new RegisterDetail(first_name.getText().toString(), middle_name.getText().toString(), last_name.getText().toString(), organization_name.getText().toString(), email.getText().toString(), password.getText().toString(), mobile_number.getText().toString(), areaUser, cityUser);

        Call<RegisterDetail> call = registerApi.register(reg);

        call.enqueue(new Callback<RegisterDetail>() {
            @Override
            public void onResponse(Call<RegisterDetail> call, Response<RegisterDetail> response) {
                if (response.isSuccessful()) {

                    if (response.body() != null) {

                        Toast.makeText(Registration.this, "Registration success", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Registration.this, ActivityMain.class);
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


    private void getAreaDe(){
        areasL = new ArrayList<>();
        arrayAdapterArea = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,areasL);
        spinnerArea.setAdapter(arrayAdapterArea);
        Call<AreaList> call = CatApi.getService().getArea();
        call.enqueue(new Callback<AreaList>() {
            @Override
            public void onResponse(Call<AreaList> call, Response<AreaList> response) {
                AreaList areaList = response.body();
                for (int i=0; i<areaList.getResults().size();i++) {
                    areasL.add(areaList.getResults().get(i).getArea());
                    Log.i("killer", areasL.get(i).toString());
                    arrayAdapterArea.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<AreaList> call, Throwable t) {

            }
        });
        Log.i("itra",String.valueOf(areasL.size()));
    }

    private void getCitydata(){
        citiesL = new ArrayList<>();
        arrayAdapterCity= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,citiesL);
        spinnerCity.setAdapter(arrayAdapterCity);
        Call<CityList> call = CatApi.getService().getCity();
        call.enqueue(new Callback<CityList>() {
            @Override
            public void onResponse(Call<CityList> call, Response<CityList> response) {
                CityList cityList = response.body();
                for (int i=0; i<cityList.getResults().size();i++) {
                    citiesL.add(cityList.getResults().get(i).getDistrict());
                    Log.i("tusha",cityList.getResults().get(i).toString());
                    arrayAdapterCity.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<CityList> call, Throwable t) {

            }
        });
    }
}
