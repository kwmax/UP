package com.kwmax.self.discipline.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kwmax.self.discipline.R;

/**
 * Created by keweimeng on 2019/3/7.
 * Desc:
 */
public class MineTabFragment extends BasicFragment {

    private RecyclerView recomlist;

    public MineTabFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mineView = inflater.inflate(R.layout.fragment_mine, container, false);

        return mineView;
    }


}
