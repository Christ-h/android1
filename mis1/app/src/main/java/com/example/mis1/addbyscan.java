package com.example.mis1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mis1.util.Constant;
import com.google.zxing.activity.CaptureActivity;
import com.google.zxing.util.QrCodeGenerator;

import java.io.UnsupportedEncodingException;

public class addbyscan extends AppCompatActivity implements View.OnClickListener {
    Button btnQrCode; // 扫码
    TextView tvResult; // 结果

    Button btnGenerate,insert; // 生成二维码
    EditText etContent; // 待生成内容
    ImageView imgQrcode; // 二维码图片
    String resultString;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbyscan);
        initView();
    }

    private void initView() {
        btnQrCode = (Button) findViewById(R.id.btn_qrcode);
        btnQrCode.setOnClickListener(this);
        btnGenerate = (Button) findViewById(R.id.btn_generate);
        btnGenerate.setOnClickListener(this);
        insert=(Button)findViewById(R.id.insert1);
        insert.setOnClickListener(this);

        tvResult = (TextView) findViewById(R.id.txt_result);
        etContent = (EditText) findViewById(R.id.et_content);
        imgQrcode = (ImageView) findViewById(R.id.img_qrcode);
        textView=(TextView)findViewById(R.id.text);
    }

    // 开始扫码
    private void startQrCode() {
        // 申请相机权限
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // 申请权限
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission
                    .CAMERA)) {
                Toast.makeText(this, "请至权限中心打开本应用的相机访问权限", Toast.LENGTH_SHORT).show();
            }
            ActivityCompat.requestPermissions(addbyscan.this, new String[]{Manifest.permission.CAMERA}, Constant.REQ_PERM_CAMERA);
            return;
        }
        // 申请文件读写权限（部分朋友遇到相册选图需要读写权限的情况，这里一并写一下）
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // 申请权限
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission
                    .WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "请至权限中心打开本应用的文件读写权限", Toast.LENGTH_SHORT).show();
            }
            ActivityCompat.requestPermissions(addbyscan.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Constant.REQ_PERM_EXTERNAL_STORAGE);
            return;
        }
        // 二维码扫码
        Intent intent = new Intent(addbyscan.this, CaptureActivity.class);
        startActivityForResult(intent, Constant.REQ_QR_CODE);
    }

    /**
     * 生成二维码
     */
    private void generateQrCode() {
        if (etContent.getText().toString().equals("")) {
            Toast.makeText(this, "请输入二维码内容", Toast.LENGTH_SHORT).show();
            return;
        }
        Bitmap bitmap = QrCodeGenerator.getQrCodeImage(etContent.getText().toString(), imgQrcode.getWidth(), imgQrcode.getHeight());
        if (bitmap == null) {
            Toast.makeText(this, "生成二维码出错", Toast.LENGTH_SHORT).show();
            imgQrcode.setImageBitmap(null);
        } else {
            imgQrcode.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_qrcode:
                startQrCode();
                break;
            case R.id.btn_generate:
                generateQrCode();
                break;
            case R.id.insert1:
                insertdata();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫描结果回调
        if (requestCode == Constant.REQ_QR_CODE && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString(Constant.INTENT_EXTRA_KEY_QR_SCAN);
            //将扫描出的信息显示出来
            tvResult.setText(scanResult);
            resultString = scanResult;
        }
    }

    public void insertdata(){
        String test=resultString;
        String[] a=test.split("\\s+");

        String shape1=a[0];
        String company1=a[1];
        String productnum1=a[2];
        String usename1=a[3];
        String phonenum1=a[4];
        String userange1=a[5];



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

                Toast.makeText(addbyscan.this,volleyError.toString(),Toast.LENGTH_SHORT).show();

            }
        });

        ((RequestQueue) requestQueue).add(stringRequest);

    }





    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Constant.REQ_PERM_CAMERA:
                // 摄像头权限申请
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 获得授权
                    startQrCode();
                } else {
                    // 被禁止授权
                    Toast.makeText(addbyscan.this, "请至权限中心打开本应用的相机访问权限", Toast.LENGTH_LONG).show();
                }
                break;
            case Constant.REQ_PERM_EXTERNAL_STORAGE:
                // 文件读写权限申请
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 获得授权
                    startQrCode();
                } else {
                    // 被禁止授权
                    Toast.makeText(addbyscan.this, "请至权限中心打开本应用的文件读写权限", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

}
