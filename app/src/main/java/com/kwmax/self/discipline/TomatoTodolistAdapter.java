package com.kwmax.self.discipline;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kwmax.self.discipline.model.ScheduleTodo;
import com.kwmax.self.discipline.model.TomatoTodo;

import java.util.List;

/**
 * Created by kwmax on 2019/10/3.
 */
public class TomatoTodolistAdapter extends RecyclerView.Adapter<TomatoTodolistAdapter.ViewHolder> {

    private List<TomatoTodo> tomatoTodoList;
    private Context context;

    public TomatoTodolistAdapter(Context context, List<TomatoTodo> tomatoTodoList) {
        this.tomatoTodoList = tomatoTodoList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.tomato_todolist_recycler_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int pos) {

        TomatoTodo todo = tomatoTodoList.get(pos);
        viewHolder.dura.setText(TextUtils.isEmpty(todo.getDuration())?"":todo.getDuration());
        viewHolder.content.setText(TextUtils.isEmpty(todo.getContent())?"":todo.getContent()+"min");
    }

    @Override
    public int getItemCount() {
        return tomatoTodoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView selected;
        private TextView content;
        private TextView dura;

        public ViewHolder(View itemView) {
            super(itemView);
            selected = itemView.findViewById(R.id.tomato_selected);
            dura = itemView.findViewById(R.id.tomato_dura);
            content = itemView.findViewById(R.id.tomato_content);
        }
    }
}
