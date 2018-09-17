package com.example.jp.regform_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class Display_Data extends AppCompatActivity {

    ListView lv;
    Database d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_data);
        d=new Database(Display_Data.this);
        lv=(ListView)findViewById(R.id.lv);

        List<Data_Model>data_models=d.getAllDatabase();

        Base_Adapter base_adapter=new Base_Adapter(Display_Data.this,data_models);
        lv.setAdapter(base_adapter);

    }


}
