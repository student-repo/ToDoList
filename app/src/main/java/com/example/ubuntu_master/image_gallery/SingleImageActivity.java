package com.example.ubuntu_master.image_gallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class SingleImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_image);

        Bundle imageInfo = getIntent().getExtras();
        System.out.println(imageInfo.getString("imageInfo"));




    }



}
