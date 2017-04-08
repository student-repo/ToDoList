package com.example.ubuntu_master.image_gallery;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;


public class ImagesListFragment extends Fragment {

    private HashMap<Integer, ImageInfo> imagesInfo = new HashMap<Integer, ImageInfo>(){{
       put(1, new ImageInfo("Google Plus", "ala ma kota ale nie ma psa 11111 22222 333333 44444", "nature1", 30));
        put(1, new ImageInfo("Twitter", "ala ma kota ale nie ma psa 11111 22222 333333 44444", "nature2", 30));
        put(1, new ImageInfo("Google Plus", "ala ma kota ale nie ma psa 11111 22222 333333 44444", "nature3", 30));
        put(1, new ImageInfo("Twitter", "ala ma kota ale nie ma psa 11111 22222 333333 44444", "nature4", 30));
        put(1, new ImageInfo("Google Plus", "ala ma kota ale nie ma psa 11111 22222 333333 44444", "nature5", 30));
        put(1, new ImageInfo("Twitter", "ala ma kota ale nie ma psa 11111 22222 333333 44444", "nature1", 30));
        put(1, new ImageInfo("Google Plus", "ala ma kota ale nie ma psa 11111 22222 333333 44444", "nature2", 30));
        put(1, new ImageInfo("Twitter", "ala ma kota ale nie ma psa 11111 22222 333333 44444", "nature3", 30));
        put(1, new ImageInfo("Google Plus", "ala ma kota ale nie ma psa 11111 22222 333333 44444", "nature4", 30));
        put(1, new ImageInfo("Twitter", "ala ma kota ale nie ma psa 11111 22222 333333 44444", "nature5", 30));
    }};

    private String[] web = {
            "Google Plus",
            "Twitter",
            "Twitter",
            "Google Plus",
            "Twitter",
            "Twitter",
            "Google Plus",
            "Twitter",
            "Google Plus",
            "Twitter",
            "Twitter"

    } ;
    private Integer[] imageId = {
            R.drawable.nature1,
            R.drawable.nature2,
            R.drawable.nature3,
            R.drawable.nature1,
            R.drawable.nature4,
            R.drawable.nature5,
            R.drawable.nature1,
            R.drawable.nature2,
            R.drawable.nature3,
            R.drawable.nature1,
            R.drawable.nature4


    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_images_list, container, false);
        CustomList adapter = new
                CustomList(getActivity(), web, imageId);
        ListView list = (ListView)view.findViewById(R.id.listview);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getActivity(), "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getActivity(), SingleImageActivity.class);
                i.putExtra("imageInfo", web[+ position]);
                startActivity(i);

            }
        });
        return view;
    }
}


