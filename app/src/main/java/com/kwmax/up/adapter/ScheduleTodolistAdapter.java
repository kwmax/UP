package com.kwmax.up.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.kwmax.up.OnItemClickListener;
import com.kwmax.up.R;
import com.kwmax.up.model.ScheduleTodo;

import java.util.List;

/**
 * Created by keweimeng on 2019/10/3.
 */
public class ScheduleTodolistAdapter extends RecyclerView.Adapter<ScheduleTodolistAdapter.ViewHolder> {

    private List<ScheduleTodo> scheduleTodoList;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public ScheduleTodolistAdapter(Context context, List<ScheduleTodo> scheduleTodoList) {
        this.scheduleTodoList = scheduleTodoList;
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.schedule_todolist_recycler_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int pos) {

        ScheduleTodo todo = scheduleTodoList.get(pos);
        viewHolder.selected.setVisibility(View.GONE);
        if (todo.getStar() != null && todo.getStar().equals("1")) {
            viewHolder.star.setImageDrawable(context.getDrawable(R.drawable.star));
        }

        viewHolder.time.setText(todo.getTime());
        viewHolder.title.setText(todo.getName());
        if (TextUtils.isEmpty(todo.getContent())) {
            viewHolder.content.setVisibility(View.GONE);
        } else {
            viewHolder.content.setText(todo.getContent());
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null){
                    onItemClickListener.OnItemClick(viewHolder.itemView,pos);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return scheduleTodoList.size();
    }

    public void refresh(List<ScheduleTodo> datas) {
        scheduleTodoList = datas;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView star;
        private ImageView selected;
        private TextView time;
        private TextView title;
        private TextView content;

        public ViewHolder(final View itemView) {
            super(itemView);
            star = itemView.findViewById(R.id.star);
            selected = itemView.findViewById(R.id.selected);
            time = itemView.findViewById(R.id.todo_time);
            title = itemView.findViewById(R.id.todo_title);
            content = itemView.findViewById(R.id.todo_content);
        }
    }
}
