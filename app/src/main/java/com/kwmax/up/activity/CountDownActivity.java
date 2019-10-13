package com.kwmax.up.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kwmax.up.R;
import com.kwmax.up.TomatoGiveUpDialog;
import com.kwmax.up.operateTomatoListener;
import com.kwmax.up.widget.TomatoView;

/**
 * Created by keweimeng on 2019/10/4.
 */
public class CountDownActivity extends BasicActivity implements View.OnClickListener {

    private TextView quickStart;
    private ImageView tomatoPause;
    private ImageView tomatoStop;
    private ImageView tomatoBack;

    private TomatoView tomatoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);
        if (Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
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
                showGiveUpDialog();
                break;
            case R.id.tomato_back:
                showGiveUpDialog();
                break;
            default:
                break;
        }
    }

    private void showGiveUpDialog(){
        final TomatoGiveUpDialog dialogFragment = TomatoGiveUpDialog.getInstance("确定放弃番茄任务？");
        dialogFragment.show(getSupportFragmentManager(), new operateTomatoListener() {
            @Override
            public void addTomato(String tomato, String minCount) {

            }

            @Override
            public void cancal() {

            }

            @Override
            public void giveup(String reason) {

            }
        });
    }

}
