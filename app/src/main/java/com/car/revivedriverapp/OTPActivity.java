package com.car.revivedriverapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.car.revivedriverapp.Models.UserDetailModel;
import com.car.revivedriverapp.Utils.Network;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OTPActivity extends AppCompatActivity {
 UserDetailModel userDetailModel;
 EditText editText;
 Network network;
 ProgressDialog progressDialog;
 Button submit;
 String Otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        editText =(EditText)findViewById(R.id.otp);
        submit =(Button)findViewById(R.id.submit);
        progressDialog =new ProgressDialog(this);
        network =new Network();
        Intent intent=getIntent();
        userDetailModel = intent.getParcelableExtra("userDetailModel");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Otp = editText.getText().toString();
             if(Otp!=null)
             {
                 VerifyOtp();
             }
             else{
                 editText.setError("Enter your otp.");
             }

            }
        });

    }

    public void VerifyOtp() {

        RequestQueue queue = Volley.newRequestQueue(this);

        String URL = network.Base_Url + network.Otp_verify;

        // progressDialog.show();

        StringRequest jsObjRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        progressDialog.hide();
                        Log.e("Response", response.toString());
                        String responsemessage = null;
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            String resposne_message = jsonObject.getString("message");
                            String resposne_sucess = jsonObject.getString("status");

                            if (resposne_sucess.equals("true")) {

                                JSONObject resposne_userId = jsonObject.getJSONObject("data");
                                Intent intent=new Intent(OTPActivity.this,CreateJobCardActivity.class);
                                intent.putExtra("userDetailModel",userDetailModel);
                                startActivity(intent);


                            } else {

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("code",Otp);
                return params;
            }
        };
        Volley.newRequestQueue(getApplicationContext()).add(jsObjRequest);
    }
}
