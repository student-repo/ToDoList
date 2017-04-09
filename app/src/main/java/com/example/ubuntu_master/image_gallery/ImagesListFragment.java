package com.example.ubuntu_master.image_gallery;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import static android.app.Activity.RESULT_OK;


public class ImagesListFragment extends Fragment {

    public interface updateImagesListFragment {
        void updateImagesListFragment(int id, int progress);
        void handleLandscapeListClick(String s, int progress, int id);
    }

    updateImagesListFragment dataPasser;

    @Override
    public void onAttach(Activity a) {
        super.onAttach(a);
        dataPasser = (updateImagesListFragment) a;
    }

    public void updateImagesListFragment(int id, int progress) {
        dataPasser.updateImagesListFragment(id, progress);
    }

    public void fpp(String s, int progress, int id){
        dataPasser.handleLandscapeListClick(s, progress, id);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 999 && resultCode == RESULT_OK){
            try{
                int id = Integer.parseInt(data.getStringExtra("imageId"));
                int progress = (int) (Float.parseFloat(data.getStringExtra("ratingBarValue")) * 10);

                updateImagesListFragment(id, progress);
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final String[] titleArray = getArguments().getStringArray("titleArray");
        final String[] descriptionArray = getArguments().getStringArray("descriptionArray");
        final int[] progressArray = getArguments().getIntArray("progressArray");
        final int[] idArray = getArguments().getIntArray("idArray");
        final String[] imageArray = getArguments().getStringArray("imageArray");

        View view = inflater.inflate(R.layout.fragment_images_list, container, false);

        CustomList adapter = new
                CustomList(getActivity(), titleArray, buildImagesInfoMap(titleArray, descriptionArray, progressArray, idArray, imageArray));


        ListView list = (ListView)view.findViewById(R.id.listview);

        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getActivity(), "You Clicked at " + titleArray[position], Toast.LENGTH_SHORT).show();

                int displaymode = getResources().getConfiguration().orientation;

                if(displaymode == 1){ // it portrait mode
                    Intent i = new Intent(getActivity(), SingleImageActivity.class);
                    i.putExtra("imageTitle", titleArray[position]);
                    i.putExtra("imageDescription", descriptionArray[position]);
                    i.putExtra("imageName", imageArray[position]);
                    i.putExtra("imageProgress", String.valueOf(progressArray[position]));
                    i.putExtra("imageId", String.valueOf(idArray[position]));
                    startActivityForResult(i, 999);
                }
                else {
//                    TextView tt = (TextView)view.findViewById(R.id.landscape_image_title);
//                    tt.setText("adasdad");
                    fpp(titleArray[position], progressArray[position], idArray[position]);
                }

            }
        });
        return view;
    }

    private HashMap<Integer, ImageInfo> buildImagesInfoMap(String[] titleArray, String[] descriptionArray, int[] progressArray, int[] idArray, String[] imageArray){
        HashMap<Integer, ImageInfo> h = new HashMap<>();
        for(Integer i: idArray){
            h.put(i, new ImageInfo(titleArray[i], descriptionArray[i], imageArray[i], progressArray[i], i));
        }
        return h;
    }
}


