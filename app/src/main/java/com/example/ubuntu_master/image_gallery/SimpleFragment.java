package com.example.ubuntu_master.image_gallery;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

public class SimpleFragment extends Fragment {

    public interface foo {
        void foo( int progress, int id);
    }

    foo dataPasser;

    @Override
    public void onAttach(Activity a) {
        super.onAttach(a);
        dataPasser = (foo) a;
    }

    void foo(int progress, int id){
        dataPasser.foo(progress, id);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.simple_fragment, container, false);

        RatingBar r = (RatingBar)view.findViewById(R.id.landscape_rating_bar);
        r.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                String rateValue = String.valueOf(ratingBar.getRating() * 2); // * 2 becouse values are weird (depend if we get from xml or not)
                System.out.println("Rate for Module is"+rateValue);
                TextView tt = (TextView)view.findViewById(R.id.landscape_image_title);
                try{
                    foo((int) (ratingBar.getRating() * 2 * 10), Integer.parseInt(tt.getText().toString()));
                }
                catch (Exception e){
                    System.out.println("first bug"  + e);
                }
//                passData(rateValue);
            }
        });
//        r.setProgress(imageInfo.getProgress() / 10);


        return view;
    }

}
