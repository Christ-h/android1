package com.example.mis1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class show_all extends AppCompatActivity {
    //private TextView textView;
    private ListView listView;
    private int idjosn[]=new int[20],phonenumjson[]=new int[20];
    private String shapejosn[]=new String[20],companyjson[]=new String[20],
            productnumjson[]=new String[20],usernamejson[]=new String[20],
            userangejson[]=new String[20];

    private List<Map<String,Object>> mapList;
    private SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);
        //textView=(TextView)findViewById(R.id.text);
        listView=(ListView)findViewById(R.id.listview);
        mapList=new ArrayList<>();

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest("http://192.168.43.224:8020/showall.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                try {
                    s =new String(s.getBytes("ISO-8859-1"),"UTF-8");
                    //textView.setText(s);
                    //StringBuffer stringBuffer=new StringBuffer();
                    JSONArray jsonArray=new JSONArray(s);
                    for (int i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Map<String,Object> map=new HashMap<>();

                        //int id=jsonObject.getInt("id");
                        //String shape=jsonObject.getString("shape");
                        //String company=jsonObject.getString("company");
                        //String productnum=jsonObject.getString("product_num");
                        //String username=jsonObject.getString("username");
                        //int phonenum=jsonObject.getInt("phone_num");
                        //String userange=jsonObject.getString("userange");

                        idjosn[i]=jsonObject.getInt("id");
                        shapejosn[i]=jsonObject.getString("shape");
                        companyjson[i]=jsonObject.getString("company");
                        productnumjson[i]=jsonObject.getString("product_num");
                        usernamejson[i]=jsonObject.getString("username");
                        phonenumjson[i]=jsonObject.getInt("phone_num");
                        userangejson[i]=jsonObject.getString("userange");

                        //stringBuffer.append(id+"\t");
                        //stringBuffer.append(shape+"\t");
                        //stringBuffer.append(company+"\t");
                        //stringBuffer.append(productnum+"\t");
                        //stringBuffer.append(username+"\t");
                        //stringBuffer.append(phonenum+"\t");
                        //stringBuffer.append(userange+"\n");

                        map.put("id",idjosn[i]);
                        map.put("shape",shapejosn[i]);
                        map.put("company",companyjson[i]);
                        map.put("product_num",productnumjson[i]);
                        map.put("username",usernamejson[i]);
                        map.put("phone_num",phonenumjson[i]);
                        map.put("userange",userangejson[i]);

                        mapList.add(map);
                    }
                    //textView.setText(stringBuffer.toString());

                    simpleAdapter=new SimpleAdapter(show_all.this,mapList,R.layout.user,new String[]{
                            "id","shape","company","product_num","username","phone_num","userange"},new int[]{R.id.id,R.id.shape,R.id.company,
                    R.id.product_num,R.id.username,R.id.phone_num,R.id.userange});
                    listView.setAdapter(simpleAdapter);

                }catch (UnsupportedEncodingException e){
                    e.printStackTrace();
                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Toast.makeText(show_all.this,volleyError.toString(),Toast.LENGTH_SHORT).show();

            }
        });

        ((RequestQueue) requestQueue).add(stringRequest);
    }
}
