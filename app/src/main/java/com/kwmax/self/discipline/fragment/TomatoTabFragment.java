package com.kwmax.self.discipline.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kwmax.self.discipline.R;

/**
 * Created by keweimeng on 2019/3/10.
 */
public class TomatoTabFragment extends BasicFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View messageView = inflater.inflate(R.layout.fragment_tomato, container, false);

        return messageView;
    }
}
