package com.example.jp.regform_app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by JP on 11/27/2017.
 */
public class Base_Adapter extends BaseAdapter {
    Button update;
    Button delete;
    Context context;
    List<Data_Model> data_models;
    LayoutInflater inflater;
    Database d;


    public Base_Adapter(Context context,List<Data_Model> data_models){
        this.context=context;
        this.data_models=data_models;
        inflater=LayoutInflater.from(context);
        d=new Database(context);

    }

    @Override
    public int getCount() {
        return data_models.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convetrview, ViewGroup present) {
        convetrview=inflater.inflate(R.layout.data_item,null);
        TextView name=(TextView)convetrview .findViewById(R.id.txt1);
        TextView address=(TextView)convetrview .findViewById(R.id.txt2);
        TextView mo=(TextView)convetrview .findViewById(R.id.txt3);

        ImageView iv1=(ImageView)convetrview.findViewById(R.id.iv1);
            update=(Button)convetrview.findViewById(R.id.btnupdate);
            delete=(Button)convetrview.findViewById(R.id.btndelete);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context,UpdateDatabase.class);
                i.putExtra("id",data_models.get(position).getId());
                i.putExtra("name",data_models.get(position).getName());
                i.putExtra("address",data_models.get(position).getAddress());
                i.putExtra("mobile",data_models.get(position).getMobile());
                i.putExtra("image",data_models.get(position).getImbyte());

                context.startActivity(i);



            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.deletdata(data_models.get(position).getId());
                Intent in=new Intent(context,Display_Data.class);
                context.startActivity(in);
                Toast.makeText(context,"Data Deleted",Toast.LENGTH_SHORT).show();
            }
        });

        Data_Model dm=data_models.get(position);
        name.setText(dm.getName());
        address.setText(dm.getAddress());
        mo.setText(dm.getMobile());
       byte[] bimage= dm.getImbyte();

                Bitmap bb= BitmapFactory.decodeByteArray(bimage,0,bimage.length);

                iv1.setImageBitmap(bb);
//String nm = pref.getString(nmekey,);

        return convetrview;
    }
}
