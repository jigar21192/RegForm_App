package com.example.jp.regform_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class RegForm_App extends AppCompatActivity {
    EditText name,address,mobile;
    Button alldata;
    Button submit;
    Button image;
    ImageView iv;
    String i;
    Database  db;
    private static final int camara_request=123;
    byte[] imgbyte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_form_app);

        name=(EditText)findViewById(R.id.edtname);
        address=(EditText)findViewById(R.id.edtaddress);
        mobile=(EditText)findViewById(R.id.edtmobile);


        submit=(Button)findViewById(R.id.btnsubmit);
        alldata=(Button)findViewById(R.id.btnalldata);
        image=(Button)findViewById(R.id.btnimage);
        iv=(ImageView)findViewById(R.id.iv);
        db=new Database(RegForm_App.this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Data_Model dm=new Data_Model();
                dm.setName(name.getText().toString());
                dm.setAddress(address.getText().toString());
                dm.setMobile(mobile.getText().toString());
                dm.setImbyte(imgbyte);
                name.setText("");
                address.setText("");
                mobile.setText("");
                iv.setImageResource(R.mipmap.ic_launcher);




               int i=db.insertdata(dm);
                Log.e("Row",">>>>"+i);


                Toast.makeText(getApplicationContext(),"Data Submited Successfully",Toast.LENGTH_SHORT).show();

            }
        });
        alldata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegForm_App.this,Display_Data.class);
                startActivity(intent);
            }
        });


        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent image=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(image,camara_request);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent image) {
        super.onActivityResult(requestCode,resultCode,image);
        if (requestCode==camara_request){
            Bitmap photo=(Bitmap)image.getExtras().get("data");
            iv.setImageBitmap(photo);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
            imgbyte = stream.toByteArray();
        }
    }
}
