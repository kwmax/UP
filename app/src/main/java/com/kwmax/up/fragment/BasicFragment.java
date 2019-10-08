package com.kwmax.up.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kwmax.up.R;


/**
 * Created by keweimeng on 2019/3/3.
 */
public class BasicFragment extends Fragment {

    private static final String ARG_TITLE_TEXT = "title";
    private static final String ARG_LAYOUT_ID  = "layout";
    private String titleText;
    private int layoutId;

    public BasicFragment() {}

    public static BasicFragment newInstance(String title, int layout) {
        BasicFragment fragment = new BasicFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE_TEXT, title);
        args.putInt(String.valueOf(ARG_LAYOUT_ID), layout);
        fragment.setArguments(args);
        return fragment;
    }
    public static BasicFragment newInstance(String title) {
        BasicFragment fragment = new BasicFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE_TEXT, title);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            titleText = getArguments().getString(ARG_TITLE_TEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_basic, container, false);
        TextView toptitle = rootView.findViewById(R.id.top_text);
        toptitle.setText(titleText);
        return rootView;
    }

}
