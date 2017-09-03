package com.example.ubuntu_master.image_gallery;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.GregorianCalendar;
import java.util.HashMap;

import static android.app.Activity.RESULT_OK;


public class NoticeListFragment extends Fragment {

    public interface updateNoticeListFragment {
        void updateNoticeListFragment(int id, String title, String details,int year, int month, int day, int hour, int minute, boolean finished);
        void handleLandscapeListClick(int id, String title, String details,int year, int month, int day, int hour, int minute, boolean finished);
    }

    updateNoticeListFragment dataPasser;

    @Override
    public void onAttach(Activity a) {
        super.onAttach(a);
        dataPasser = (updateNoticeListFragment) a;
    }

    public void updateNoticeListFragment(int id, String title, String details, int year, int month, int day, int hour, int minute, Boolean finished) {
        dataPasser.updateNoticeListFragment(id, title, details, year, month, day, hour, minute, finished);
    }

    public void fpp(int id, String title, String details, int year, int month, int day, int hour, int minute, boolean finished){
        dataPasser.handleLandscapeListClick(id, title, details, year, month, day, hour, minute, finished);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 999 && resultCode == RESULT_OK){
            try{
                int id = Integer.parseInt(data.getStringExtra("noticeId"));
                int year = Integer.parseInt(data.getStringExtra("noticeYear"));
                int month = Integer.parseInt(data.getStringExtra("noticeMonth"));
                int day = Integer.parseInt(data.getStringExtra("noticeDay"));
                int hour = Integer.parseInt(data.getStringExtra("noticeHour"));
                int minute = Integer.parseInt(data.getStringExtra("noticeMinute"));
                String title = data.getStringExtra("noticeTitle");
                String noticeDetails = data.getStringExtra("noticeDetails");

                updateNoticeListFragment(id, title, noticeDetails, year, month, day, hour, minute, false);
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
        final String[] detailsArray = getArguments().getStringArray("detailsArray");
        final int[] idArray = getArguments().getIntArray("idArray");
        final int[] dateYearArray = getArguments().getIntArray("dateYear");
        final int[] dateMonthArray = getArguments().getIntArray("dateMonth");
        final int[] dateDayArray = getArguments().getIntArray("dateDay");
        final int[] dateHourArray = getArguments().getIntArray("dateHour");
        final int[] dateMinuteArray = getArguments().getIntArray("dateMinute");
        final int mode = getArguments().getInt("mode");

        View view = inflater.inflate(R.layout.fragment_images_list, container, false);

        CustomList adapter = new
                CustomList(getActivity(), this, titleArray, buildNoticeInfoMap(titleArray, detailsArray, dateYearArray, dateMonthArray, dateDayArray,
                dateHourArray, dateMinuteArray, idArray, mode));

        Button b = (Button)view.findViewById(R.id.quickTaskButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ee = (EditText)getActivity().findViewById(R.id.addQuickTask);
                String dd = ee.getText().toString();
                if(!dd.equals("")){

                    updateNoticeListFragment(-1, dd, "", -1, -1, -1, -1, -1, false);
                }
            }
        });


        Button bb = (Button)view.findViewById(R.id.addTaskButton);
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int displaymode = getResources().getConfiguration().orientation;

                if(displaymode == 1){ // it portrait mode
                    Intent i = new Intent(getActivity(), SingleNoticeActivity.class);
                    i.putExtra("noticeTitle", "");
                    i.putExtra("noticeDetails", "");
                    i.putExtra("noticeYear", "-1");
                    i.putExtra("noticeMonth", "-1");
                    i.putExtra("noticeDay", "-1");
                    i.putExtra("noticeHour", "-1");
                    i.putExtra("noticeMinute", "-1");
                    i.putExtra("noticeId", "-1");
                    startActivityForResult(i, 999);
                }
                else {
                    fpp(-1, "", "", -1, -1, -1, -1, -1, false);
                }
            }
        });


        ListView list = (ListView)view.findViewById(R.id.listview);

        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getActivity(), "You Clicked at " + titleArray[position], Toast.LENGTH_SHORT).show();

                int displaymode = getResources().getConfiguration().orientation;

                if(displaymode == 1){ // it portrait mode
                    Intent i = new Intent(getActivity(), SingleNoticeActivity.class);
                    i.putExtra("noticeTitle", titleArray[position]);
                    i.putExtra("noticeDetails", detailsArray[position]);
                    i.putExtra("noticeYear", String.valueOf(dateYearArray[position]));
                    i.putExtra("noticeMonth", String.valueOf(dateMonthArray[position]));
                    i.putExtra("noticeDay", String.valueOf(dateDayArray[position]));
                    i.putExtra("noticeHour", String.valueOf(dateHourArray[position]));
                    i.putExtra("noticeMinute", String.valueOf(dateMinuteArray[position]));
                    i.putExtra("noticeId", String.valueOf(idArray[position]));
                    startActivityForResult(i, 999);
                }
                else {
                    if(mode == 0){
                        fpp(idArray[position], titleArray[position], detailsArray[position], dateYearArray[position], dateMonthArray[position],
                                dateYearArray[position], dateHourArray[position], dateMinuteArray[position], true);
                    }
                    else {
                        fpp(idArray[position], titleArray[position], detailsArray[position], dateYearArray[position], dateMonthArray[position],
                                dateYearArray[position], dateHourArray[position], dateMinuteArray[position], false);
                    }

                }

            }
        });
        return view;
    }

    private HashMap<Integer, NoticeDetails> buildNoticeInfoMap(String[] titleArray, String[] detailsArray, int[] dateYear, int[] dateMonth,
                                                               int[] dateDay, int[] dateHour, int[] dateMinute, int[] idArray, int mode){
        HashMap<Integer, NoticeDetails> h = new HashMap<>();
        for(int i = 0; i < idArray.length; i++){

            if(dateYear[i] < 0 || dateMonth[i] < 0  || dateDay[i] < 0){
                if(mode == 0){
                    h.put(i, new NoticeDetails(titleArray[i], detailsArray[i], null, idArray[i], false, true));
                }
                else{
                    h.put(i, new NoticeDetails(titleArray[i], detailsArray[i], null, idArray[i]));
                }
            }
            else if(dateHour[i] < 0){
                if(mode == 0){
                    h.put(i, new NoticeDetails(titleArray[i], detailsArray[i], new GregorianCalendar(dateYear[i], dateMonth[i], dateDay[i]), idArray[i], true, true));
                }
                else {
                    h.put(i, new NoticeDetails(titleArray[i], detailsArray[i], new GregorianCalendar(dateYear[i], dateMonth[i], dateDay[i]), idArray[i], true));
                }
            }
            else {
                if(mode == 0){
                    h.put(i, new NoticeDetails(titleArray[i], detailsArray[i], new GregorianCalendar(dateYear[i], dateMonth[i], dateDay[i], dateHour[i],
                            dateHour[i], dateMinute[i]), idArray[i], false, true));
                }
                else{
                    h.put(i, new NoticeDetails(titleArray[i], detailsArray[i], new GregorianCalendar(dateYear[i], dateMonth[i], dateDay[i], dateHour[i],
                            dateHour[i], dateMinute[i]), idArray[i]));
                }
            }
        }
        return h;
    }
}


