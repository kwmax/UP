package com.kwmax.self.discipline;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kwmax.self.discipline.widget.TomatoView;

/**
 * Created by keweimeng on 2019/10/4.
 */
public class CountDownActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView quickStart;
    private ImageView tomatoPause;
    private ImageView tomatoStop;
    private ImageView tomatoBack;

    private TomatoView tomatoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);
        initView();
    }

    private void initView() {
        quickStart = findViewById(R.id.tomato_quickstart);
        tomatoPause = findViewById(R.id.tomato_pause);
        tomatoStop = findViewById(R.id.tomato_stop);
        tomatoView = findViewById(R.id.tomato_view);
        tomatoBack = findViewById(R.id.tomato_back);

        tomatoBack.setOnClickListener(this);
        quickStart.setOnClickListener(this);
        tomatoPause.setOnClickListener(this);
        tomatoStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.tomato_quickstart:
                tomatoView.start();
                break;
            case R.id.tomato_pause:
                break;
            case R.id.tomato_stop:
                break;
            case R.id.tomato_back:
                break;
            default:
                break;
        }
    }


}
