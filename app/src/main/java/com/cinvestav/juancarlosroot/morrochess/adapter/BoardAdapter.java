package com.cinvestav.juancarlosroot.morrochess.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.cinvestav.juancarlosroot.morrochess.MainActivity;
import com.cinvestav.juancarlosroot.morrochess.R;

import java.util.ArrayList;

/**
 * Created by juancarlosroot on 12/5/16.
 */

public class BoardAdapter extends BaseAdapter {
    Context context;
    ArrayList<Square> list;
    Square lastSelected = null;

    public BoardAdapter(MainActivity context, ArrayList<Square> list)
    {
        this.context = context;
        this.list = list;
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

        final Square item = getItem(position);

        if(item.getPiece() != null)
        {
            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);

            Bitmap source = BitmapFactory.decodeResource(context.getResources(), R.drawable.sprites);
            Bitmap image = Bitmap.createBitmap(source, item.getPiece().getStart_x(),  item.getPiece().getStart_y(), item.getPiece().getWidth(), item.getPiece().getWidth(), null, false);

            imageView.setImageBitmap(Bitmap.createScaledBitmap(image, 100, 100, false));
        }

        convertView.setMinimumWidth(item.getWidth());
        convertView.setMinimumHeight(item.getWidth());
        convertView.setBackgroundColor(item.getColor());

        return convertView;
    }


}
