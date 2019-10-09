package com.kwmax.up;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.kwmax.up.model.DateValue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by keweimeng on 2018/12/17.
 * Desc:
 */
public class DatePicker {

    private ResultHandler handler;
    private Context context;

    private Dialog dialog;
    private TextView timeBoard;
    private DatePickerView year_pv, month_pv, day_pv, hour_pv, minute_pv;

    private static final int MAX_MINUTE = 59;
    private static final int MAX_HOUR = 23;
    private static final int MAX_MONTH = 12;

    private DateValue dateValue;
    private String format = "";

    private List<String> year, month, day, hour, minute;
    private Calendar selectedCalender;
    private TextView tv_cancle, tv_select;

    public DatePicker(Context context, String startDate, ResultHandler resultHandler) {
        this.context = context;
        this.handler = resultHandler;
        selectedCalender = Calendar.getInstance();

        format = DateValue.DefaultShowMinuteFormat;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            selectedCalender.setTime(sdf.parse(startDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        initDialog();
        initView();
    }

    public void setDateParms(DateValue dateValue) {
        this.dateValue = dateValue;
        refreshBoardText(dateValue);
    }

    private void initDialog() {
        try {
            if (dialog == null) {
                dialog = new Dialog(context, R.style.time_dialog);
                dialog.setContentView(R.layout.new_daterange_dialog);
                dialog.setCancelable(false);
                Window window = dialog.getWindow();
                window.setGravity(Gravity.CENTER);
                dialog.setCanceledOnTouchOutside(true);
                WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                DisplayMetrics dm = new DisplayMetrics();
                manager.getDefaultDisplay().getMetrics(dm);
                WindowManager.LayoutParams lp = window.getAttributes();
                lp.width = (int) (dm.widthPixels * 0.8);
                window.setAttributes(lp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        year_pv = (DatePickerView) dialog.findViewById(R.id.year_pv);
        month_pv = (DatePickerView) dialog.findViewById(R.id.month_pv);
        day_pv = (DatePickerView) dialog.findViewById(R.id.day_pv);
        hour_pv = (DatePickerView) dialog.findViewById(R.id.hour_pv);
        minute_pv = (DatePickerView) dialog.findViewById(R.id.minute_pv);

        timeBoard = dialog.findViewById(R.id.selected_time);

        timeBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshBoardText(dateValue);
                setSelectedTime(dateValue.getText());
            }
        });

        tv_cancle = (TextView) dialog.findViewById(R.id.tv_cancle);
        tv_select = (TextView) dialog.findViewById(R.id.tv_select);

        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        tv_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                invalidatePickerTime();
                handler.handle(dateValue);
                dialog.dismiss();
            }
        });
    }

    public void invalidatePickerTime() {
        dateValue.setValue(selectedCalender.getTime());
        refreshBoardText(dateValue);
    }


    public void refreshBoardText(DateValue dateValues) {
        timeBoard.setText(dateValues.getTextValue(format));
        timeBoard.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
    }

    private void initTimer() {
        initArrayList();

        for (int i = 0; i < 50; i++) {
            year.add(String.valueOf(selectedCalender.get(Calendar.YEAR) - 25 + i));
        }
        for (int i = 0; i <= 12; i++) {
            month.add(formatTimeUnit(i));
        }
        for (int i = 1; i <= selectedCalender.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            day.add(formatTimeUnit(i));
        }

        for (int i = 0; i <= MAX_HOUR; i++) {
            hour.add(formatTimeUnit(i));
        }

        for (int i = 0; i <= MAX_MINUTE; i++) {
            minute.add(formatTimeUnit(i));
        }

        loadComponent();
    }

    /**
     * 将“0-9”转换为“00-09”
     */
    private String formatTimeUnit(int unit) {
        return unit < 10 ? "0" + String.valueOf(unit) : String.valueOf(unit);
    }

    private void initArrayList() {
        if (year == null) year = new ArrayList<>();
        if (month == null) month = new ArrayList<>();
        if (day == null) day = new ArrayList<>();
        if (hour == null) hour = new ArrayList<>();
        if (minute == null) minute = new ArrayList<>();
        year.clear();
        month.clear();
        day.clear();
        hour.clear();
        minute.clear();
    }

    private void loadComponent() {
        year_pv.setData(year);
        month_pv.setData(month);
        day_pv.setData(day);
        hour_pv.setData(hour);
        minute_pv.setData(minute);
        executeScroll();
    }

    private void addListener() {
        year_pv.setOnSelectListener(new DatePickerView.onSelectListener() {
            @Override
            public void onSelect(String year) {
                selectedCalender.set(Calendar.YEAR, Integer.parseInt(year));
                invalidatePickerTime();
            }
        });

        month_pv.setOnSelectListener(new DatePickerView.onSelectListener() {
            @Override
            public void onSelect(String month) {
                int hour = selectedCalender.get(Calendar.HOUR_OF_DAY);
                int minute = selectedCalender.get(Calendar.MINUTE);
                int dayMonth = Integer.parseInt(month) - 1;
                int selectedDay = selectedCalender.get(Calendar.DAY_OF_MONTH);
                int year = selectedCalender.get(Calendar.YEAR);
                selectedCalender.clear();
                selectedCalender.set(Calendar.YEAR, year);
                selectedCalender.set(Calendar.MONTH, dayMonth);
                selectedCalender.set(Calendar.HOUR,hour);
                selectedCalender.set(Calendar.MINUTE,minute);
                day.clear();
                for (int i = 1; i <= selectedCalender.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
                    day.add(formatTimeUnit(i));
                }
                if (selectedDay > selectedCalender.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                    selectedDay = selectedCalender.getActualMaximum(Calendar.DAY_OF_MONTH);
                }
                day_pv.setData(day);
                day_pv.setSelected(String.valueOf(formatTimeUnit(selectedDay)));
                selectedCalender.set(Calendar.DAY_OF_MONTH, Integer.parseInt(formatTimeUnit(selectedDay)));
                invalidatePickerTime();
            }
        });

        day_pv.setOnSelectListener(new DatePickerView.onSelectListener() {
            @Override
            public void onSelect(String day) {
                selectedCalender.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
                invalidatePickerTime();
            }
        });

        hour_pv.setOnSelectListener(new DatePickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                selectedCalender.set(Calendar.HOUR_OF_DAY, Integer.parseInt(text));
                invalidatePickerTime();
            }
        });

        minute_pv.setOnSelectListener(new DatePickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                selectedCalender.set(Calendar.MINUTE, Integer.parseInt(text));
                invalidatePickerTime();
            }
        });
    }

    private void executeScroll() {
        year_pv.setCanScroll(year.size() > 1);
        month_pv.setCanScroll(month.size() > 1);
        day_pv.setCanScroll(day.size() > 1);
        hour_pv.setCanScroll(hour.size() > 1);
        minute_pv.setCanScroll(minute.size() > 1);
    }

    public void show() {
        initTimer();
        //滑动监听
        addListener();
        setSelectedTime(dateValue.getText());
        dialog.show();
    }

    /**
     * 设置日期控件默认选中的时间
     */
    public void setSelectedTime(String time) {

        String[] str = time.split(" ");
        String data = str[0];
        year_pv.setSelected(data.substring(0, 4));
        selectedCalender.set(Calendar.YEAR, Integer.parseInt(data.substring(0, 4)));

        if (data.length() > 4) {
            month.clear();
            for (int i = 1; i <= MAX_MONTH; i++) {
                month.add(formatTimeUnit(i));
            }
            month_pv.setData(month);
            month_pv.setSelected(data.substring(5, 7));
            selectedCalender.set(Calendar.MONTH, Integer.parseInt(data.substring(5, 7)) - 1);
        }

        if (data.length() > 8) {
            day.clear();
            for (int i = 1; i <= selectedCalender.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
                day.add(formatTimeUnit(i));
            }
            day_pv.setData(day);
            day_pv.setSelected(data.substring(8, data.length()));
            selectedCalender.set(Calendar.DAY_OF_MONTH, Integer.parseInt(data.substring(8, data.length())));
        }

        if (str.length == 2) {
            String[] timeStr = str[1].split(":");

            hour.clear();
            for (int i = 0; i <= MAX_HOUR; i++) {
                hour.add(formatTimeUnit(i));
            }
            hour_pv.setData(hour);
            hour_pv.setSelected(timeStr[0]);
            selectedCalender.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeStr[0]));

            if (timeStr.length > 1) {
                minute.clear();
                for (int i = 0; i <= MAX_MINUTE; i++) {
                    minute.add(formatTimeUnit(i));
                }
                minute_pv.setData(minute);
                minute_pv.setSelected(timeStr[1]);
                selectedCalender.set(Calendar.MINUTE, Integer.parseInt(timeStr[1]));
            }
        }
        executeScroll();

    }

    /**
     * 定义结果回调接口
     */
    public interface ResultHandler {
        void handle(DateValue dateValue);
    }
}
