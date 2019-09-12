package com.freshokartz2;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.freshokartz2.Area.AreaList;
import com.freshokartz2.City.CityList;
import com.freshokartz2.Country.CountryList;
import com.freshokartz2.State.StateList;
import com.freshokartz2.utils.Tools;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityAddAddress extends AppCompatActivity {

    public ArrayList<String> areasL;
    public ArrayAdapter<String> arrayAdapterArea;
    public ArrayList<String> citiesL;
    public ArrayAdapter<String> arrayAdapterCity;

    public ArrayList<String> statesL;
    public ArrayAdapter<String> arrayAdapterState;
    public ArrayList<String> countriesL;
    public ArrayAdapter<String> arrayAdapterCountry;

    private EditText name, nick_name, house_flat_shop_number, detail_address, pincode, contact_number;
    Spinner spinnerCity;
    Spinner spinnerArea;
    Spinner spinnerState;
    Spinner spinnerCountry;

    Integer areaUser;
    Integer cityUser;
    Integer stateUser;
    Integer countryUser;

    private Button add_address;

    SessionManagement session;

    //hashmap contains all CITIES with unique Id
    HashMap<String, Integer> city_List;

    //hashmap contains all AREAS with unique Id
    HashMap<String, Integer> area_List;

    //hashmap contains all STATES with unique Id
    HashMap<String, Integer> state_List;

    //hashmap contains all COUNTRIES with unique Id
    HashMap<String, Integer> country_List;

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(AddressApi.DJANGO_SITE)
            .addConverterFactory(GsonConverterFactory.create());
    Retrofit retrofit = builder.build();
    AddressApi addressApi = retrofit.create(AddressApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        name =findViewById(R.id.name);
        nick_name = findViewById(R.id.nick_name);
        house_flat_shop_number = findViewById(R.id.house_flat_shop_number);
        detail_address = findViewById(R.id.detail_address);
        spinnerArea = findViewById(R.id.spinArea);
        spinnerCity = findViewById(R.id.spinCity);
        spinnerState = findViewById(R.id.spinState);
        spinnerCountry = findViewById(R.id.spinCountry);
        pincode = findViewById(R.id.pincode);
        contact_number = findViewById(R.id.contact_number);

        add_address = findViewById(R.id.add_address);

        getAreaData();
        getCityData();
        getStateData();
        getCountryData();

        spinnerArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                areaUser = area_List.get(text);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                cityUser = city_List.get(text);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                stateUser = state_List.get(text);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                countryUser = country_List.get(text);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        initToolbar();

        add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAddress();
            }
        });


    }

    private void initToolbar() {
        ActionBar actionBar;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("Add Address");
        Tools.systemBarLolipop(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int item_id = item.getItemId();
        if (item_id == android.R.id.home) {
            super.onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }


    private void addAddress(){
        AddressDetail add = new AddressDetail(name.getText().toString(),nick_name.getText().toString(), house_flat_shop_number.getText().toString(),detail_address.getText().toString(), areaUser, cityUser, stateUser, countryUser, pincode.getText().toString(), contact_number.getText().toString(),true,true );

        session = new SessionManagement(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();

        String token = user.get(SessionManagement.KEY_TOKEN);

        String Token_request = "Token " + token;
        Call<AddressDetail> call = addressApi.add_address(Token_request, add);

        call.enqueue(new Callback<AddressDetail>() {
            @Override
            public void onResponse(Call<AddressDetail> call, Response<AddressDetail> response) {
                if (response.isSuccessful()) {

                    if (response.body() != null) {

                        Toast.makeText(ActivityAddAddress.this, "Add address is successful", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(ActivityAddAddress.this, ActivityProfile.class);
                        startActivity(i);

                    }
                } else {
                    Log.d("fail", "fail");
                }
            }

            @Override
            public void onFailure(Call<AddressDetail> call, Throwable t) {

            }
        });

    }


    private void getAreaData(){
        areasL = new ArrayList<>();
        arrayAdapterArea = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,areasL);
        spinnerArea.setAdapter(arrayAdapterArea);
        Call<AreaList> call = CatApi.getService().getArea();
        call.enqueue(new Callback<AreaList>() {
            @Override
            public void onResponse(Call<AreaList> call, Response<AreaList> response) {
                AreaList areaList = response.body();
                area_List = new HashMap<>();
                for (int i=0; i<areaList.getResults().size();i++) {
                    areasL.add(areaList.getResults().get(i).getArea());
                    area_List.put(areaList.getResults().get(i).getArea(), areaList.getResults().get(i).getId());
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

    private void getCityData(){
        citiesL = new ArrayList<>();
        arrayAdapterCity= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,citiesL);
        spinnerCity.setAdapter(arrayAdapterCity);
        Call<CityList> call = CatApi.getService().getCity();
        call.enqueue(new Callback<CityList>() {
            @Override
            public void onResponse(Call<CityList> call, Response<CityList> response) {
                CityList cityList = response.body();
                city_List = new HashMap<>();
                for (int i=0; i<cityList.getResults().size();i++) {
                    citiesL.add(cityList.getResults().get(i).getDistrict());
                    city_List.put(cityList.getResults().get(i).getDistrict(), cityList.getResults().get(i).getId());
                    Log.i("tusha",cityList.getResults().get(i).toString());
                    arrayAdapterCity.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<CityList> call, Throwable t) {

            }
        });
    }

    private void getStateData(){
        statesL = new ArrayList<>();
        arrayAdapterState= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,statesL);
        spinnerState.setAdapter(arrayAdapterState);
        Call<StateList> call = addressApi.getState();
        call.enqueue(new Callback<StateList>() {
            @Override
            public void onResponse(Call<StateList> call, Response<StateList> response) {
                StateList stateList = response.body();
                state_List = new HashMap<>();
                for (int i=0; i<stateList.getResults().size();i++) {
                    statesL.add(stateList.getResults().get(i).getState());
                    state_List.put(stateList.getResults().get(i).getState(), stateList.getResults().get(i).getId());
                    Log.i("tusha",stateList.getResults().get(i).toString());
                    arrayAdapterState.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<StateList> call, Throwable t) {

            }
        });
    }

    private void getCountryData(){
        countriesL = new ArrayList<>();
        arrayAdapterCountry= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,countriesL);
        spinnerCountry.setAdapter(arrayAdapterCountry);
        Call<CountryList> call = addressApi.getCountry();
        call.enqueue(new Callback<CountryList>() {
            @Override
            public void onResponse(Call<CountryList> call, Response<CountryList> response) {
                CountryList countryList = response.body();
                country_List = new HashMap<>();
                for (int i=0; i<countryList.getResults().size();i++) {
                    countriesL.add(countryList.getResults().get(i).getCountry());
                    country_List.put(countryList.getResults().get(i).getCountry(), countryList.getResults().get(i).getId());
                    Log.i("tusha",countryList.getResults().get(i).toString());
                    arrayAdapterCountry.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<CountryList> call, Throwable t) {

            }
        });
    }
}
