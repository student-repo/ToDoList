package com.example.ubuntu_master.image_gallery;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomList extends ArrayAdapter<String> {

    private final Activity context;
    private final HashMap<Integer, ImageInfo> imagesInfo;
    public CustomList(Activity context,
                      String[] imagesTitles, HashMap<Integer, ImageInfo> imagesInfo) {
        super(context, R.layout.list_single, imagesTitles);
        this.context = context;
        this.imagesInfo = imagesInfo;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View rowView= inflater.inflate(R.layout.list_single, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.image_title);
        TextView des = (TextView) rowView.findViewById(R.id.image_description);


        des.setText(imagesInfo.get(position).getDescription());

        ImageView imageView = (ImageView) rowView.findViewById(R.id.list_image);
        txtTitle.setText(imagesInfo.get(position).getTitle());

        String mDrawableName = imagesInfo.get(position).getImage();
        int resID = context.getResources().getIdentifier(mDrawableName , "drawable", context.getPackageName());


        imageView.setImageResource(resID);


        return rowView;
    }

    private String[] createTitleArray(){
        ArrayList<String> s = new ArrayList<>();
        for(Integer i: imagesInfo.keySet()){
            s.add(i, imagesInfo.get(i).getTitle());
        }
        return (String[])s.toArray();
    }
}

