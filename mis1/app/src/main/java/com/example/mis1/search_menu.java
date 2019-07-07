package com.example.mis1;

import android.content.Intent;
import android.view.View.OnClickListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class search_menu extends AppCompatActivity {
    private Button search1,search2,search3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_menu);
        search1=(Button)findViewById(R.id.seachByshape);
        search2=(Button)findViewById(R.id.searchBycompany);
        search3=(Button)findViewById(R.id.searchByuserange);

        search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent(search_menu.this,searchbyshape.class);
                startActivity(intent);
            }
        });
        search2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent(search_menu.this,seachbycompany.class);
                startActivity(intent);
            }
        });
        search3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent(search_menu.this,searchbyuserange.class);
                startActivity(intent);
            }
        });

    }
}
