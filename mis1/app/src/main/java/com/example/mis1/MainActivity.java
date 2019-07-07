package com.example.mis1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity {

    private Button btn1,btn2,btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button)findViewById(R.id.Buttonadd);
        btn2 = (Button)findViewById(R.id.Buttondelete);
        btn3 = (Button)findViewById(R.id.Buttonsearch);

        //添加监听器
        btn1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent(MainActivity.this,add_menu.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent(MainActivity.this,show_all.class);
                startActivity(intent);

            }
        });
        btn3.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent(MainActivity.this,search_menu.class);
                startActivity(intent);

            }
        });


    }
}
