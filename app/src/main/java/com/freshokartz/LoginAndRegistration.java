package com.freshokartz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginAndRegistration extends AppCompatActivity {

    private Button register;
    private EditText email, password;
    private Button button_login;
    private ImageButton backhome;
    private String token;

    private SharedPreferences mPreferences;

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(LoginApi.DJANGO_SITE)
            .addConverterFactory(GsonConverterFactory.create());
    Retrofit retrofit = builder.build();
    LoginApi loginApi = retrofit.create(LoginApi.class);

    CartApi cartApi = retrofit.create(CartApi.class);

    SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_and_registration);

        email = findViewById(R.id.email);
        password =findViewById(R.id.password);

        session = new SessionManagement(getApplicationContext());

        button_login = findViewById(R.id.login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginAndRegistration.this, Registration.class);
                startActivity(i);
            }
        });

        backhome = findViewById(R.id.backhome);
        backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(LoginAndRegistration.this, ActivityMain.class);
                startActivity(i);
            }
        });


    }

    private void login() {

        Login login = new Login(email.getText().toString() , password.getText().toString());

        Call<Bptoken> call = loginApi.login(login);

        call.enqueue(new Callback<Bptoken>() {
            @Override
            public void onResponse(Call<Bptoken> call, Response<Bptoken> response) {
                Log.i("ghjk", "ghjk");
                if (response.isSuccessful()) {
                    Log.i("vvpo", "vvpo");
                    if (response.body() != null) {

                        token = response.body().getToken();
                        Toast.makeText(getApplicationContext(), token, Toast.LENGTH_SHORT).show();
                        showPost(token);
                        Toast.makeText(LoginAndRegistration.this, "12345", Toast.LENGTH_SHORT).show();
                        Log.i("sperer", "gyjvhj");

                        session.createLoginSession(token);
                        if(session.isFirstTime()){
                            create_new_cart();
                            Intent i = new Intent(LoginAndRegistration.this, ActivityMain.class);
                            startActivity(i);
                        }
                        else {
                            Intent i = new Intent(LoginAndRegistration.this, ActivityMain.class);
                            startActivity(i);
                        }
                    }
                } else {
                    Log.d("fail", "fail");
                }
            }

            @Override
            public void onFailure(Call<Bptoken> call, Throwable t) {
                Toast.makeText(LoginAndRegistration.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showPost(String token) {

        String Token_request = "Token " + token;

        Call<BpResponseBody> call = loginApi.getDetail(Token_request);

        call.enqueue(new Callback<BpResponseBody>() {
            @Override
            public void onResponse(Call<BpResponseBody> call, Response<BpResponseBody> response) {
                BpResponseBody finalResponseBody = response.body();

                Log.i("tuy", "hghyjv");
                Toast.makeText(LoginAndRegistration.this, "Success", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<BpResponseBody> call, Throwable t) {
                Toast.makeText(LoginAndRegistration.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void create_new_cart(){
                List<CartItem> cart = new ArrayList<CartItem>();
                CartOrder cartOrder = new CartOrder(cart);

                session = new SessionManagement(getApplicationContext());
                HashMap<String, String> user = session.getUserDetails();

                String token = user.get(SessionManagement.KEY_TOKEN);

                String TokenRequest = "Token "+ token;
                Call<CartOrder> call = cartApi.new_addtocart(TokenRequest, cartOrder);

                call.enqueue(new Callback<CartOrder>() {
                    @Override
                    public void onResponse(Call<CartOrder> call, Response<CartOrder> response) {
                        if (response.isSuccessful()) {

                            if (response.body() != null) {

                                Toast.makeText(LoginAndRegistration.this, "New cart is created", Toast.LENGTH_SHORT).show();


                            }
                        } else {
                            Log.d("fail", "fail");
                        }
                    }

                    @Override
                    public void onFailure(Call<CartOrder> call, Throwable t) {

                    }
                });
            }

}




