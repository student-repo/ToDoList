package com.example.ubuntu_master.image_gallery;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.test.espresso.core.deps.guava.primitives.Ints;
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
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;

public class MainActivity extends AppCompatActivity implements ImagesListFragment.updateImagesListFragment {

    private ImagesListFragment portraitFragment;

    private HashMap<Integer, ImageInfo> imagesInfo = new HashMap<Integer, ImageInfo>(){{
        put(0, new ImageInfo("Google Plus1", "ala ma kota ale nie ma psa 11111 22222 333333 44444", "nature1", 0, 0));
        put(1, new ImageInfo("Twitter1", "ala ma kota ale nie ma psa 11111 22222 333333 44444", "nature2", 0, 1));
        put(2, new ImageInfo("Google Plus2", "ala ma kota ale nie ma psa 11111 22222 333333 44444", "nature3", 0, 2));
        put(3, new ImageInfo("Twitter2", "ala ma kota ale nie ma psa 11111 22222 333333 44444", "nature4", 0, 3));
        put(4, new ImageInfo("Google Plus3", "ala ma kota ale nie ma psa 11111 22222 333333 44444", "nature5", 0, 4));
        put(5, new ImageInfo("Twitter3", "ala ma kota ale nie ma psa 11111 22222 333333 44444", "nature1", 0, 5));
        put(6, new ImageInfo("Google Plus4", "ala ma kota ale nie ma psa 11111 22222 333333 44444", "nature2", 0, 6));
        put(7, new ImageInfo("Twitter4", "ala ma kota ale nie ma psa 11111 22222 333333 44444", "nature3", 0, 7));
        put(8, new ImageInfo("Google Plus5", "ala ma kota ale nie ma psa 11111 22222 333333 44444", "nature4", 0, 8));
        put(9, new ImageInfo("Twitter5", "ala ma kota ale nie ma psa 11111 22222 333333 44444", "nature5", 0, 9));
    }};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Image Gallery");  // provide compatibility to all the versions

        this.getIntent().addFlags(FLAG_ACTIVITY_SINGLE_TOP);
        commitFragment();
    }

    @Override
    public void updateImagesListFragment(int id, int progress) {
        ImageInfo ii = imagesInfo.get(id);
        ii.setProgress(progress);
        imagesInfo.put(id, ii);
        commitFragment();
    }

    private void commitFragment(){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        // get the display mode
        int displaymode = getResources().getConfiguration().orientation;
        if (displaymode == 1) { // it portrait mode
            portraitFragment = new ImagesListFragment();
            Bundle b = new Bundle();
            b.putStringArray("titleArray", getTitleArray());
            b.putStringArray("descriptionArray", getDescriptionArray());
            b.putIntArray("progressArray", getProgressArray());
            b.putStringArray("imageArray", getImageArray());
            b.putIntArray("idArray", getIdArray());

            portraitFragment.setArguments(b);
            fragmentTransaction.replace(android.R.id.content, portraitFragment);
        }
//        else {// its landscape
//            Fragment2 f2 = new Fragment2();
//            fragmentTransaction.replace(android.R.id.content, f2);
        portraitFragment = new ImagesListFragment();
        Bundle b = new Bundle();
        b.putStringArray("titleArray", getTitleArray());
        b.putStringArray("descriptionArray", getDescriptionArray());
        b.putIntArray("progressArray", getProgressArray());
        b.putStringArray("imageArray", getImageArray());
        b.putIntArray("idArray", getIdArray());

        portraitFragment.setArguments(b);
        fragmentTransaction.replace(android.R.id.content, portraitFragment);
//        }
        fragmentTransaction.commit();
    }


    private String[] getTitleArray(){
        ArrayList<String> s = new ArrayList<>();
        for(Integer i: imagesInfo.keySet()){
            s.add(imagesInfo.get(i).getTitle());
        }
        return s.toArray(new String[0]);
    }

    private String[] getDescriptionArray(){
        ArrayList<String> s = new ArrayList<>();
        for(Integer i: imagesInfo.keySet()){
            s.add(imagesInfo.get(i).getDescription());
        }
        return s.toArray(new String[0]);
    }

    private int[] getProgressArray(){
        List<Integer> s = new ArrayList<>();
        for(Integer i: imagesInfo.keySet()){
            s.add(imagesInfo.get(i).getProgress());
        }
        return Ints.toArray(s);
    }

    private String[] getImageArray(){
        ArrayList<String> s = new ArrayList<>();
        for(Integer i: imagesInfo.keySet()){
            s.add(imagesInfo.get(i).getImage());
        }
        return s.toArray(new String[0]);
    }

    private int[] getIdArray(){
        List<Integer> s = new ArrayList<>();
        for(Integer i: imagesInfo.keySet()){
            s.add(imagesInfo.get(i).getId());
        }
        return Ints.toArray(s);
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
