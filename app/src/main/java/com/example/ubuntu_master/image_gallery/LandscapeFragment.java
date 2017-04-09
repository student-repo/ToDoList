package com.example.ubuntu_master.image_gallery;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class LandscapeFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final String[] titleArray = getArguments().getStringArray("titleArray");
        final String[] descriptionArray = getArguments().getStringArray("descriptionArray");
        final int[] progressArray = getArguments().getIntArray("progressArray");
        final int[] idArray = getArguments().getIntArray("idArray");
        final String[] imageArray = getArguments().getStringArray("imageArray");

        Bundle b = new Bundle();
        b.putStringArray("titleArray", titleArray);
        b.putStringArray("descriptionArray", descriptionArray);
        b.putIntArray("progressArray", progressArray);
        b.putStringArray("imageArray", imageArray);
        b.putIntArray("idArray", idArray);

        View view = inflater.inflate(R.layout.landscape_fragment, container, false);

        ImageView iv = (ImageView)view.findViewById(R.id.list_image);

        ImagesListFragment ff = new ImagesListFragment();
        ff.setArguments(b);
        getChildFragmentManager().beginTransaction().add(R.id.fragment1, ff).commit();

        SimpleFragment fff = new SimpleFragment();

        getChildFragmentManager().beginTransaction().add(R.id.fragment2, fff).commit();


        return view;
    }

}
