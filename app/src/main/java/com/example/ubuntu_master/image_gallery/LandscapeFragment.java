package com.example.ubuntu_master.image_gallery;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class LandscapeFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final String[] titleArray = getArguments().getStringArray("titleArray");
        final String[] detailsArray = getArguments().getStringArray("detailsArray");
        final int[] idArray = getArguments().getIntArray("idArray");
        final int[] dateYearArray = getArguments().getIntArray("dateYear");
        final int[] dateMonthArray = getArguments().getIntArray("dateMonth");
        final int[] dateDayArray = getArguments().getIntArray("dateDay");
        final int[] dateHourArray = getArguments().getIntArray("dateHour");
        final int[] dateMinuteArray = getArguments().getIntArray("dateMinute");
        final int mode = getArguments().getInt("mode");
        final int landscapeDisplayID = getArguments().getInt("landscapeDisplayID");

        Bundle b = new Bundle();
        b.putStringArray("titleArray", titleArray);
        b.putStringArray("detailsArray", detailsArray);
        b.putIntArray("dateYear", dateYearArray);
        b.putIntArray("dateMonth", dateMonthArray);
        b.putIntArray("dateDay", dateDayArray);
        b.putIntArray("dateHour", dateHourArray);
        b.putIntArray("dateMinute", dateMinuteArray);
        b.putIntArray("idArray", idArray);
        b.putInt("mode", mode);
        b.putInt("landscapeDisplayID", landscapeDisplayID);

        Bundle bbb = new Bundle();
        if(landscapeDisplayID < 0){
            bbb.putString("title", "");
            bbb.putString("details", "");
            bbb.putInt("dateYear", -1);
            bbb.putInt("dateMonth", -1);
            bbb.putInt("dateDay", -1);
            bbb.putInt("dateHour", -1);
            bbb.putInt("dateMinute", -1);
            bbb.putInt("id", -1);
            bbb.putInt("mode", mode);
        }
        else {
            bbb.putString("title", titleArray[findLandscapeIndex(idArray, landscapeDisplayID)]);
            bbb.putString("details", detailsArray[findLandscapeIndex(idArray, landscapeDisplayID)]);
            bbb.putInt("dateYear", dateYearArray[findLandscapeIndex(idArray, landscapeDisplayID)]);
            bbb.putInt("dateMonth", dateMonthArray[findLandscapeIndex(idArray, landscapeDisplayID)]);
            bbb.putInt("dateDay", dateDayArray[findLandscapeIndex(idArray, landscapeDisplayID)]);
            bbb.putInt("dateHour", dateHourArray[findLandscapeIndex(idArray, landscapeDisplayID)]);
            bbb.putInt("dateMinute", dateMinuteArray[findLandscapeIndex(idArray, landscapeDisplayID)]);
            bbb.putInt("id", idArray[findLandscapeIndex(idArray, landscapeDisplayID)]);
            bbb.putInt("mode", mode);
        }

        View view = inflater.inflate(R.layout.landscape_fragment, container, false);


        NoticeListFragment ff = new NoticeListFragment();
        ff.setArguments(b);
        getChildFragmentManager().beginTransaction().add(R.id.fragment1, ff).commit();

        SimpleFragment fff = new SimpleFragment();
        fff.setArguments(bbb);
        getChildFragmentManager().beginTransaction().add(R.id.fragment2, fff).commit();


        return view;
    }
    public int findLandscapeIndex(int[] indexes, int x){
        for(int i = 0; i < indexes.length; i++){
            if(indexes[i] == x){
                return i;
            }
        }
        return 0;
    }

}
