package com.example.jp.regform_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

/**
 * Created by JP on 11/27/2017.
 */
public class UpdateDatabase extends AppCompatActivity {
    ImageView imageView;
    Button update;
    Database d;
    private static final int camara_request = 123;
    byte[] imgbyte;
    int id;
                String name;
                String address;
                String mobile;


                @Override
                public void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.update_item);
                    d = new Database(UpdateDatabase.this);

                    update = (Button) findViewById(R.id.update);

                    final EditText nam = (EditText) findViewById(R.id.upedtname);
                    final EditText add = (EditText) findViewById(R.id.upedtaddress);
                    final EditText mo = (EditText) findViewById(R.id.upedtmobile);
                    imageView = (ImageView) findViewById(R.id.upimage);
                    Button img=(Button) findViewById(R.id.upbtnimg);

                    id = getIntent().getIntExtra("id", 0);
                    name = getIntent().getStringExtra("name");
                    address = getIntent().getStringExtra("address");
                    mobile = getIntent().getStringExtra("mobile");
                    imgbyte = getIntent().getByteArrayExtra("image");

                    nam.setText(name);
                    add.setText(address);
                    mo.setText(mobile);
                    Bitmap dd = BitmapFactory.decodeByteArray(imgbyte, 0, imgbyte.length);
                    imageView.setImageBitmap(dd);

                    img.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, camara_request);
                        }
                    });


                    update.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                Data_Model dm = new Data_Model();
                dm.setId(id);
                dm.setName(nam.getText().toString());
                dm.setAddress(add.getText().toString());
                dm.setMobile(mo.getText().toString());
                dm.setImbyte(imgbyte);
                d.update(dm);

                Intent inte = new Intent(UpdateDatabase.this, Display_Data.class);
                startActivity(inte);
                Toast.makeText(UpdateDatabase.this, "Data Update Successfully", Toast.LENGTH_SHORT).show();

            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent image) {
        super.onActivityResult(requestCode,resultCode,image);
        if (requestCode==camara_request){
            Bitmap photo=(Bitmap)image.getExtras().get("data");
            imageView.setImageBitmap(photo);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
            imgbyte = stream.toByteArray();
        }
    }
}

