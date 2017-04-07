package com.example.ubuntu_master.image_gallery;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] web;
    private final Integer[] imageId;
    public CustomList(Activity context,
                      String[] web, Integer[] imageId) {
        super(context, R.layout.list_single, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.image_title);
        TextView des = (TextView) rowView.findViewById(R.id.image_description);

        des.setText("ala nie ma kota 111111 222222 3333 4444 5555  6666666 7777777 8888888 999999 101010101 1111111  ");

        ImageView imageView = (ImageView) rowView.findViewById(R.id.list_image);
        txtTitle.setText(web[position]);

        String mDrawableName = "nature" + (position % 5 + 1);
        int resID = context.getResources().getIdentifier(mDrawableName , "drawable", context.getPackageName());

//        imageView.setImageResource(imageId[position]);
        imageView.setImageResource(resID);
        return rowView;
    }
}

