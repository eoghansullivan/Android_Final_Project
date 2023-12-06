package com.example.android_final_project.gui.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.CheckBox;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_final_project.R;
import com.example.android_final_project.db.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private List<Task> tasks = new ArrayList<>();

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task currentTask = tasks.get(position);
        holder.textViewTaskName.setText(currentTask.getName());
        holder.textViewDueDate.setText(currentTask.getDueDate());
        holder.checkBoxCompleted.setChecked(currentTask.isCompleted());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTaskName;
        private TextView textViewDueDate;
        private CheckBox checkBoxCompleted;

        public TaskViewHolder(View itemView) {
            super(itemView);
            textViewTaskName = itemView.findViewById(R.id.textViewTaskName);
            textViewDueDate = itemView.findViewById(R.id.textViewDueDate);
            checkBoxCompleted = itemView.findViewById(R.id.checkBoxCompleted);
        }
    }
}