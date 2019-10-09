package com.kwmax.up.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kwmax.up.DatePicker;
import com.kwmax.up.DateUtils;
import com.kwmax.up.R;
import com.kwmax.up.model.DateValue;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by keweimeng on 2019/6/3.
 */
public class ScheduleEditActivity extends Activity implements View.OnClickListener{

    private Button back,save;
    private EditText et_name;
    private EditText et_content;

    private TextView beginTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_edit);

        beginTime = findViewById(R.id.tx_begintime);
        initView();
    }

    private void initView(){
        back = findViewById(R.id.btn_back);
        save = findViewById(R.id.btn_save);
        et_name = findViewById(R.id.edt_title);
        et_content = findViewById(R.id.edt_content);

        String currenTime = DateUtils.getCurrentDateWithFormat(DateValue.DefaultShowMinuteFormat);
        beginTime.setText(currenTime);

        back.setOnClickListener(this);
        save.setOnClickListener(this);
        beginTime.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_save:
                //数据是使用Intent返回
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("beanTitle","");
                bundle.putString("beanTime","");
                bundle.putString("beanContent","");
                intent.putExtras(bundle);
                //设置返回数据
                this.setResult(RESULT_OK, intent);
                this.finish();
                break;
            case R.id.tx_begintime:
                selecTime(beginTime);
                break;
        }
    }

    private void selecTime(final TextView view){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String now = sdf.format(new Date());
        DatePicker picker = new DatePicker(ScheduleEditActivity.this, now, new DatePicker.ResultHandler() {
            @Override
            public void handle(DateValue date) {
                view.setText(date.getText());
            }
        });

        picker.setDateParms(new DateValue(new Date()));//初始化both设置起始两个时间
        picker.show();
    }
}
