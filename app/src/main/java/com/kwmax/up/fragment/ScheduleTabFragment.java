package com.kwmax.up.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kwmax.up.OnItemClickListener;
import com.kwmax.up.R;
import com.kwmax.up.activity.ScheduleEditActivity;
import com.kwmax.up.adapter.ScheduleTodolistAdapter;
import com.kwmax.up.db.ScheduleTodoOperation;
import com.kwmax.up.model.ScheduleTodo;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kwmax on 2019/3/7.
 * Desc:
 */
public class ScheduleTabFragment extends BasicFragment {

    private RecyclerView todoRecycler;
    private ScheduleTodolistAdapter adapter;
    private List<ScheduleTodo> scheduledatas;

    public ScheduleTabFragment() {
        scheduledatas = new ArrayList<>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View schedule = inflater.inflate(R.layout.fragment_schedule, container, false);
        initView(schedule);
        return schedule;
    }

    private void initView(View view){
        TextView title = view.findViewById(R.id.top_text);
        title.setText("待办");

        todoRecycler = view.findViewById(R.id.recyclerview);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        todoRecycler.setLayoutManager(manager);
        adapter = new ScheduleTodolistAdapter(getContext(), scheduledatas);
        todoRecycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Toast.makeText(getActivity(),"点击了列表",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), ScheduleEditActivity.class);
                startActivity(intent);
            }
        });

        initData();
    }

    private void initData(){

        List<ScheduleTodo> todoList = ScheduleTodoOperation.queryAll(getActivity());
        if (null != todoList && todoList.size() > 0 ){
            adapter.refresh(todoList);
            return;
        }
        ScheduleTodo todo1 = new ScheduleTodo();
        todo1.setTime("18:30");
        todo1.setName("图书馆学习");
        todo1.setStar("1");

        ScheduleTodo todo2 = new ScheduleTodo();
        todo2.setTime("23:30");
        todo2.setName("关灯睡觉");

        ScheduleTodoOperation.insertData(getActivity(),todo1);
        ScheduleTodoOperation.insertData(getActivity(),todo2);
    }

}
