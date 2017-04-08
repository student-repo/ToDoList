package com.example.ubuntu_master.image_gallery;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class MainActivity extends AppCompatActivity {

    private ImagesListFragment portraitFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Image Gallery");  // provide compatibility to all the versions

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        // get the display mode
        int displaymode = getResources().getConfiguration().orientation;
        if (displaymode == 1) { // it portrait mode
            portraitFragment = new ImagesListFragment();
            fragmentTransaction.replace(android.R.id.content, portraitFragment);
        }
//        else {// its landscape
//            Fragment2 f2 = new Fragment2();
//            fragmentTransaction.replace(android.R.id.content, f2);
//        }
        fragmentTransaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            Bundle imageInfo = getIntent().getExtras();
            System.out.println("we gare it");
            System.out.println(imageInfo.get("ratingBarValue"));
            System.out.println(imageInfo.get("imageId"));

//            Bundle b = new Bundle();
//            b.putString("ratingBarValue", imageInfo.get("ratingBarValue").toString());
//            b.putString("imageId", imageInfo.get("imageId").toString());
//            portraitFragment.setArguments(b);
            FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();

            portraitFragment = new ImagesListFragment();
            fragmentTransaction.replace(android.R.id.content, portraitFragment);

            fragmentTransaction.commit();
        }
        catch (Exception e){
            System.out.println("nie udalo sie");
        }
    }





































    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void jsonContent(){
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONObject oo = (JSONObject)obj.getJSONObject("images2").get("2");
            System.out.println(oo.get("name"));
            HashMap<String, JSONObject> h = new HashMap<>();
            System.out.println(oo.keys().getClass());

//            HashMap<String,Object> result =
//                    new ObjectMapper().readValue(loadJSONFromAsset(), HashMap.class);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
