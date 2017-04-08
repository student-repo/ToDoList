package com.example.ubuntu_master.image_gallery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;


public class SingleImageFragment extends Fragment {

    public interface OnDataPass {
        public void onDataPass(String data);
    }

    OnDataPass dataPasser;

    @Override
    public void onAttach(Activity a) {
        super.onAttach(a);
        dataPasser = (OnDataPass) a;
    }

    public void passData(String data) {
        dataPasser.onDataPass(data);
    }

    private ImageInfo imageInfo;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_image, container, false);
        this.imageInfo = new ImageInfo(getArguments().getString("imageTitle"), getArguments().getString("imageDescription"),
                getArguments().getString("imageName"), Integer.parseInt(getArguments().getString("imageProgress")), Integer.parseInt(getArguments().getString("imageId")));

        TextView t = (TextView)view.findViewById(R.id.single_view_description);
        t.setText(imageInfo.getDescription());

        RatingBar r = (RatingBar)view.findViewById(R.id.single_rating_bar);
       r.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                String rateValue = String.valueOf(ratingBar.getRating());
                System.out.println("Rate for Module is"+rateValue);
                passData(rateValue);
            }
        });
        r.setProgress(imageInfo.getProgress() / 10);


        return view;
    }

}
