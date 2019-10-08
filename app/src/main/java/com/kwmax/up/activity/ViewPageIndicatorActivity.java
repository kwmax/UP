package com.kwmax.up.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.widget.RadioGroup;

import com.kwmax.up.R;
import com.kwmax.up.fragment.MineTabFragment;
import com.kwmax.up.fragment.NoteTabFragment;
import com.kwmax.up.fragment.ScheduleTabFragment;
import com.kwmax.up.fragment.TomatoTabFragment;

/**
 * Created by keweimeng on 2019/10/3.
 */
public class ViewPageIndicatorActivity extends AppCompatActivity {

    private RadioGroup mTabRadioGroup;
    private SparseArray<Fragment> mFragmentSparseArray;

    private ScheduleTabFragment scheduleTabFragment;
    private TomatoTabFragment tomatoTabFragment;
    private NoteTabFragment noteTabFragment;
    private MineTabFragment mineFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//隐藏标题栏
        setContentView(R.layout.activity_viewpage_indicator);
        initView();
    }

    private void initView(){
        mTabRadioGroup = findViewById(R.id.tabs_rg);
        mFragmentSparseArray = new SparseArray<>();

        scheduleTabFragment = new ScheduleTabFragment();
        tomatoTabFragment = new TomatoTabFragment();
        noteTabFragment = new NoteTabFragment();
        mineFragment = new MineTabFragment();

        mFragmentSparseArray.append(R.id.tomato_tab, tomatoTabFragment);
        mFragmentSparseArray.append(R.id.todolist_tab, scheduleTabFragment);
        mFragmentSparseArray.append(R.id.note_tab, noteTabFragment);
        mFragmentSparseArray.append(R.id.settings_tab, mineFragment);

        mTabRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // 具体的fragment切换逻辑可以根据应用调整，例如使用show()/hide()
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        mFragmentSparseArray.get(checkedId)).commit();
            }
        });
        // 默认显示第一个
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,
                mFragmentSparseArray.get(R.id.tomato_tab)).commit();

    }
}
