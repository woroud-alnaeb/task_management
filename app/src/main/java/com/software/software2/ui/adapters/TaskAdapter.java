package com.software.software2.ui.adapters;


import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.software.software2.R;
import com.software.software2.db.AppDatabase;
import com.software.software2.db.Task;

import java.util.ArrayList;

public  class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Task> items;
    private AppDatabase dbHelper;

    public TaskAdapter(Context context, ArrayList<Task> SubjectArrayList) {
        this.context = context;
        this.items = SubjectArrayList;
    }

    @Override
    public TaskAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.task_view, parent, false);
        dbHelper = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "task_management_example").build();

        return new MyViewHolder(view);

    }


    @Override
    public void onBindViewHolder(TaskAdapter.MyViewHolder holder, int position) {
        Task s = items.get(holder.getAdapterPosition());
        holder.tv_date.setText(s.year + "/" + s.month + "/" + s.day);
        holder.tv_desc.setText(s.desc);
        holder.tv_title.setText(s.title);

    }

    private void removeAt(int position) {
        items.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, items.size());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title, tv_desc, tv_date;
        private ImageView img_delete;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_task_title);
            tv_desc = itemView.findViewById(R.id.tv_task_desc);
            tv_date = itemView.findViewById(R.id.tv_task_date);
            img_delete = itemView.findViewById(R.id.delete_task);

            img_delete.setOnClickListener(view -> {
                Task deleted= items.get(getAdapterPosition());
                removeAt(getAdapterPosition());
                AsyncTask.execute(() -> dbHelper.taskDao().delete(deleted));

            });
        }
    }
}




