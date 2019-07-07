package com.example.mis1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;

public class add_information extends AppCompatActivity {
    private TextView textView;
    private EditText shape,company,productnum,username,phonenum,userange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_information);
        textView=(TextView)findViewById(R.id.text);
        shape=(EditText)findViewById(R.id.shape);
        company=(EditText)findViewById(R.id.company);
        productnum=(EditText)findViewById(R.id.productnum);
        username=(EditText)findViewById(R.id.username);
        phonenum=(EditText)findViewById(R.id.phonenum);
        userange=(EditText)findViewById(R.id.userange);
    }

    public void insert(View v){
        String shape1=shape.getText().toString();
        String company1 =company.getText().toString();
        String productnum1=productnum.getText().toString();
        String usename1=username.getText().toString();
        String phonenum1=phonenum.getText().toString();
        String userange1=userange.getText().toString();

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest("http://192.168.43.224:8020/addinfo.php?shape="+shape1+"&&company="+company1+"&&product_num="+productnum1+"" +
                "&&username="+usename1+"&&phone_num="+phonenum1+"&&userange="+userange1, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                try {
                    s =new String(s.getBytes("ISO-8859-1"),"UTF-8");
                    textView.setText(s);
                }catch (UnsupportedEncodingException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Toast.makeText(add_information.this,volleyError.toString(),Toast.LENGTH_SHORT).show();

            }
        });

        ((RequestQueue) requestQueue).add(stringRequest);



    }
}
