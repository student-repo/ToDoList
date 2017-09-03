package com.example.ubuntu_master.image_gallery;

import android.app.Activity;
import android.icu.util.Calendar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class CustomList extends ArrayAdapter<String> {

    private final Activity context;
    private NoticeListFragment ilf;
    private final HashMap<Integer, NoticeDetails> noticeInfo;
    public CustomList(Activity context, NoticeListFragment ilf,
                      String[] noticeTitles, HashMap<Integer, NoticeDetails> noticeInfo) {
        super(context, R.layout.list_single, noticeTitles);
        this.context = context;
        this.noticeInfo = noticeInfo;
        this.ilf = ilf;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy MMM dd");

        View rowView= inflater.inflate(R.layout.list_single, null, true);


        final CheckBox bb = (CheckBox) rowView.findViewById(R.id.finishCheckBox);
        if(noticeInfo.get(position).getFinished()){
            bb.setChecked(true);
        }
        else{
            bb.setChecked(false);
        }
        bb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(noticeInfo.get(position).getDate() == null){
                    ilf.updateNoticeListFragment(noticeInfo.get(position).getId(),noticeInfo.get(position).getTitle(), noticeInfo.get(position).getDetails(),-1,-1,-1,-1,-1, !noticeInfo.get(position).getFinished());
                }
                else if(noticeInfo.get(position).getWithoutHours()){
                    ilf.updateNoticeListFragment(noticeInfo.get(position).getId(),noticeInfo.get(position).getTitle(), noticeInfo.get(position).getDetails(),
                            noticeInfo.get(position).getDate().get(java.util.Calendar.YEAR), noticeInfo.get(position).getDate().get(java.util.Calendar.MONTH),
                            noticeInfo.get(position).getDate().get(java.util.Calendar.DAY_OF_MONTH),-1,-1, !noticeInfo.get(position).getFinished());
                }
                else{
                    ilf.updateNoticeListFragment(noticeInfo.get(position).getId(),noticeInfo.get(position).getTitle(), noticeInfo.get(position).getDetails(),
                            noticeInfo.get(position).getDate().get(java.util.Calendar.YEAR), noticeInfo.get(position).getDate().get(java.util.Calendar.MONTH),
                            noticeInfo.get(position).getDate().get(java.util.Calendar.DAY_OF_MONTH),noticeInfo.get(position).getDate().get(java.util.Calendar.HOUR),
                            noticeInfo.get(position).getDate().get(java.util.Calendar.MINUTE), !noticeInfo.get(position).getFinished());
                }
            }
        });



        TextView txtTitle = (TextView) rowView.findViewById(R.id.image_title);
        TextView des = (TextView) rowView.findViewById(R.id.noticeDate);

        if(noticeInfo.get(position).getDate() != null && noticeInfo.get(position).getWithoutHours()){
            des.setText(sdf1.format(noticeInfo.get(position).getDate().getTime()));
        }
        else if(noticeInfo.get(position).getDate() != null){
            des.setText(sdf.format(noticeInfo.get(position).getDate().getTime()));
        }

        txtTitle.setText(noticeInfo.get(position).getTitle());


        return rowView;
    }

}

