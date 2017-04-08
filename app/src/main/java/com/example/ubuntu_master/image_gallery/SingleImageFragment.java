package com.example.ubuntu_master.image_gallery;

import android.os.Bundle;
import android.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class SingleImageFragment extends Fragment {

    private ImageInfo imageInfo;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_image, container, false);
        this.imageInfo = new ImageInfo(getArguments().getString("imageTitle"), getArguments().getString("imageDescription"),
                getArguments().getString("imageName"), Integer.parseInt(getArguments().getString("imageProgress")));

        TextView t = (TextView)view.findViewById(R.id.foo);
        LinearLayout l = (LinearLayout)view.findViewById(R.id.image_info_layout1);

//
        t.setText("awdawdwa wwdawd awdawda wdawd awd awd awdawdawd awdawaw daw dawdawd awd awda wdadda da da adwadwad ad awd awdawdawdaw daw a ad aw dawd ada dadad adada dadadadadawdadada dad ad adadada daadad ad ad ad awd aw");
//        t.setMovementMethod(new ScrollingMovementMethod());


        return view;
    }

}
