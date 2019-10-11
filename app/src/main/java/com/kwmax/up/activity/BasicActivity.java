package com.kwmax.up.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kwmax.up.R;
import com.kwmax.up.util.UltimateBar;

/**
 * Created by kwmax on 2019/10/10.
 */
public class BasicActivity extends AppCompatActivity {

    private int layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setColorBar(getResources().getColor(R.color.blue_crm));
    }

}
