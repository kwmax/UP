package com.kwmax.self.discipline.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.kwmax.self.discipline.R;
import com.kwmax.self.discipline.ScheduleTodolistAdapter;
import com.kwmax.self.discipline.model.ScheduleTodo;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kwmax on 2019/3/7.
 * Desc:
 */
public class ScheduleTabFragment extends BasicFragment {

    private RecyclerView todoRecycler;
    private List<ScheduleTodo> scheduledatas;

    public ScheduleTabFragment() {

        scheduledatas = new ArrayList<>();

        ScheduleTodo todo1 = new ScheduleTodo();
        todo1.setTime("18:30");
        todo1.setTitle("图书馆学习");
        todo1.setStar("1");

        ScheduleTodo todo2 = new ScheduleTodo();
        todo2.setTime("23:30");
        todo2.setTitle("关灯睡觉");

        scheduledatas.add(todo1);
        scheduledatas.add(todo2);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View schedule = inflater.inflate(R.layout.fragment_schedule, container, false);

        TextView title = schedule.findViewById(R.id.top_text);
        title.setText("待办");

        todoRecycler = schedule.findViewById(R.id.recyclerview);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        todoRecycler.setLayoutManager(manager);
        ScheduleTodolistAdapter adapter = new ScheduleTodolistAdapter(getContext(), scheduledatas);
        todoRecycler.setAdapter(adapter);

        return schedule;
    }


}
