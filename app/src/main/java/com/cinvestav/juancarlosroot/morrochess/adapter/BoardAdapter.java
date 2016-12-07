package com.cinvestav.juancarlosroot.morrochess.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.cinvestav.juancarlosroot.morrochess.MainActivity;
import com.cinvestav.juancarlosroot.morrochess.R;
import com.cinvestav.juancarlosroot.morrochess.general.General;

import java.util.ArrayList;

/**
 * Created by juancarlosroot on 12/5/16.
 */

public class BoardAdapter extends BaseAdapter {
    Context context;
    ArrayList<Square> list;
    Square lastSelected = null;
    int device_width;

    public BoardAdapter(MainActivity context, ArrayList<Square> list, int device_width)
    {
        this.context = context;
        this.list = list;
        this.device_width = device_width;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Square getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.square_layout, parent, false);
        }

        convertView.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT, device_width/8));
        final View finalConvertView = convertView;

        final Square item = getItem(position);
        item.setView(convertView);

        AsyncTask asyncTask =  new AsyncTask() {
            @Override
            protected Bitmap doInBackground(Object[] params) {


                Bitmap image = null;
                if(item.getPiece() != null) {
                    Bitmap source = BitmapFactory.decodeResource(context.getResources(), R.drawable.sprites);
                    image = Bitmap.createBitmap(source, item.getPiece().getStart_x(), item.getPiece().getStart_y(), item.getPiece().getWidth(), item.getPiece().getWidth(), null, false);
                }
                return image;
            }


            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                ImageView imageView = (ImageView) finalConvertView.findViewById(R.id.imageView);
                Bitmap resized = Bitmap.createScaledBitmap((Bitmap)o, item.getWidth(), item.getWidth(), false);
                item.getPiece().setImage(resized);
                imageView.setImageBitmap(resized);
            }
        };

        if(item.getPiece() != null)
            asyncTask.execute();

        convertView.setBackgroundColor(item.getColor());

        return convertView;
    }


}
