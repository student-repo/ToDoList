package com.example.ubuntu_master.image_gallery;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SingleNoticeActivity extends AppCompatActivity {//implements SingleImageFragment.OnDataPass {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_image);




        Bundle imageInfo = getIntent().getExtras();
        getSupportActionBar().setTitle(imageInfo.getString("noticeTitle"));



        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        // get the display mode
        int displaymode = getResources().getConfiguration().orientation;
        if (displaymode == 1) { // it portrait mode
            SingleNoticeFragment f1 = new SingleNoticeFragment();
            f1.setArguments(imageInfo);
            fragmentTransaction.replace(android.R.id.content, f1);
        }
        else {// its landscape
            SingleNoticeFragment f1 = new SingleNoticeFragment();
            f1.setArguments(imageInfo);
            fragmentTransaction.replace(android.R.id.content, f1);
        }
        fragmentTransaction.commit();
    }

}
