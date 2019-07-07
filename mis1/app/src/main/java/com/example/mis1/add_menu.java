package com.example.mis1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.view.View;

public class add_menu extends AppCompatActivity {
    private Button addbyput,addbyscan,test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);

        addbyput=(Button)findViewById(R.id.addbyput);
        addbyscan=(Button)findViewById(R.id.addbyscan);

        addbyput.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent(add_menu.this,add_information.class);
                startActivity(intent);
            }
        });
        addbyscan.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent(add_menu.this,addbyscan.class);
                startActivity(intent);
            }
        });


    }
}
