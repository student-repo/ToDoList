package com.example.ubuntu_master.image_gallery;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class SimpleFragment extends Fragment {

    public interface updateNoticeInfoData {
        void updateNoticeInfoData(int id, String title, String details, int year, int month, int day, int hour, int minute, boolean finished);
    }

    updateNoticeInfoData dataPasser;

    @Override
    public void onAttach(Activity a) {
        super.onAttach(a);
        dataPasser = (updateNoticeInfoData) a;
    }

    void foo(int id, String title, String details, int year, int month, int day, int hour, int minute, boolean finished){
        dataPasser.updateNoticeInfoData(id, title, details, year, month, day, hour, minute, finished);
    }

    public void setYear(int year) {
        this.year = year;
    }

    private int year = -1;

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    private int month = -1;
    private int day = -1;
    private int hour = -1;
    private int minute = -1;
    private int id = -1;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    private String title = "";
    private String details = "";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_single_image, container, false);


        setTitle(getArguments().getString("title"));
        setDetails(getArguments().getString("details"));
        setYear(getArguments().getInt("dateYear"));
        setMonth(getArguments().getInt("dateMonth"));
        setDay(getArguments().getInt("dateDay"));
        setHour(getArguments().getInt("dateHour"));
        setMinute(getArguments().getInt("dateMinute"));
        setId(getArguments().getInt("id"));
        EditText eTitle = (EditText)view.findViewById(R.id.noticeTitle);
        eTitle.setText(title);

        EditText eDetails = (EditText)view.findViewById(R.id.noticeDetails);
        eDetails.setText(details);

        if(year != -1 && month != -1 && day != -1) {
            EditText eDate = (EditText)view.findViewById(R.id.noticeCalendar);
            eDate.setText(year + "/" + month + "/" + day);
        }

        if(hour != -1 && minute != -1){
            EditText eTime = (EditText)view.findViewById(R.id.noticeCalendarTime);
            eTime.setText(hour + " : " + minute);
        }

        TextView t = (TextView)view.findViewById(R.id.single_view_description);

        t.setText(getArguments().getString("noticeDate"));



        final EditText ee = (EditText)view.findViewById(R.id.noticeCalendar);
        final EditText eee = (EditText)view.findViewById(R.id.noticeCalendarTime);

        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                setYear(year);
                setMonth(monthOfYear);
                setDay(dayOfMonth);
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                ee.setText(sdf.format(myCalendar.getTime()));
            }

        };

        ee.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        final TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                myCalendar.set(Calendar.HOUR, hourOfDay);
                myCalendar.set(Calendar.MINUTE, minute);
                String myFormat = "HH:mm"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                setHour(hourOfDay);
                setMinute(minute);

                eee.setText(sdf.format(myCalendar.getTime()));
            }
        };

        eee.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new TimePickerDialog(getActivity(), time, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MINUTE),
                        true).show();
            }
        });


        Button bb = (Button)view.findViewById(R.id.saveTaskButton);

        bb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText ee = (EditText)getActivity().findViewById(R.id.noticeCalendar);
                EditText e = (EditText)getActivity().findViewById(R.id.noticeCalendarTime);
                EditText eeee = (EditText)getActivity().findViewById(R.id.noticeDetails);
                EditText eee = (EditText)getActivity().findViewById(R.id.noticeTitle);
                String noticeTitle = eee.getText().toString();
                String noticeDetails = eeee.getText().toString();
                if (!noticeTitle.equals("")){
                    if(ee.getText().toString().equals("")){
                        setYear(-1);
                        setMonth(-1);
                        setDay(-1);
                        setHour(-1);
                        setMinute(-1);
                    }
                    else if(e.getText().toString().equals("")){
                        setHour(-1);
                        setMinute(-1);
                    }

                    foo(id, noticeTitle, noticeDetails, year, month, day, hour, minute, false);

                }
            }
        });
        return view;
    }

}
