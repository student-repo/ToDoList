package com.example.ubuntu_master.image_gallery;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class SingleImageActivity extends AppCompatActivity implements SingleImageFragment.OnDataPass {


    private ImageInfo imageInfo;
    private String data = "";

    @Override
    public void onBackPressed() {
        Intent i = new Intent();
        i.putExtra("ratingBarValue", data);
        i.putExtra("imageId", String.valueOf(imageInfo.getId()));
        setResult(RESULT_OK, i);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_image);




        Bundle imageInfo = getIntent().getExtras();
        this.imageInfo = new ImageInfo(imageInfo.getString("imageTitle"), imageInfo.getString("imageDescription"),
                imageInfo.getString("imageName"), Integer.parseInt(imageInfo.getString("imageProgress")), Integer.parseInt(imageInfo.getString("imageId")));

        getSupportActionBar().setTitle(this.imageInfo.getTitle());



        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        // get the display mode
        int displaymode = getResources().getConfiguration().orientation;
        if (displaymode == 1) { // it portrait mode
            SingleImageFragment f1 = new SingleImageFragment();
            f1.setArguments(imageInfo);
            fragmentTransaction.replace(android.R.id.content, f1);
        }
        else {// its landscape
            SingleImageFragment f1 = new SingleImageFragment();
            f1.setArguments(imageInfo);
            fragmentTransaction.replace(android.R.id.content, f1);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onDataPass(String data) {
        this.data = data;
    }
}
