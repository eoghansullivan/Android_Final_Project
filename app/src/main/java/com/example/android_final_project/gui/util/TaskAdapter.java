package com.example.android_final_project.gui.util;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_final_project.R;
import com.example.android_final_project.application.util.CustomLogger;
import com.example.android_final_project.db.Task;
import com.example.android_final_project.gui.AddTaskFragment;
import com.example.android_final_project.gui.TaskListFragment;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private List<Task> tasks = new ArrayList<>();
    private final Fragment parentFragment;

    public interface TaskAdapterListener {
        void onDeleteTask(Task task);
    }

    private final TaskAdapterListener listener;

    public TaskAdapter(TaskListFragment parentFragmentListener) {
        this.parentFragment = parentFragmentListener;
        this.listener = parentFragmentListener;
    }


    public void deleteItem(int position) {
        Task taskToDelete = tasks.get(position);

        new AlertDialog.Builder(parentFragment.getContext())
                .setTitle("Delete Task")
                .setMessage("Are you sure you want to delete this task?")
                .setPositiveButton("Delete", (dialog, which) -> {
                    listener.onDeleteTask(taskToDelete);
                    tasks.remove(position);
                    notifyItemRemoved(position);
                    Toast.makeText(parentFragment.getContext(), "Task deleted", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancel", (dialog, which) -> notifyItemChanged(position))
                .show();
    }


    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task currentTask = tasks.get(position);
        holder.taskId = currentTask.getId();
        holder.taskName = currentTask.getName();
        holder.taskTypeString = currentTask.getTaskType().toString();
        holder.taskDueDate = currentTask.getDueDate();
        holder.taskDescription = currentTask.getDescription();
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
        private String taskName, taskDescription, taskTypeString, taskDueDate;
        private int taskId;
        private final TextView textViewTaskName;
        private final TextView textViewDueDate;
        private final CheckBox checkBoxCompleted;


        public TaskViewHolder(View itemView) {
            super(itemView);
            textViewTaskName = itemView.findViewById(R.id.textViewTaskName);
            textViewDueDate = itemView.findViewById(R.id.textViewDueDate);
            checkBoxCompleted = itemView.findViewById(R.id.checkBoxCompleted);
            Button editButt = itemView.findViewById(R.id.editButton);
            editButt.setOnClickListener(v -> editEntry());
        }

        private void editEntry() {
            FragmentManager fragmentManager = parentFragment.getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            try {
                Task taskToEdit = new Task(taskName, taskDescription, Task.TaskType.fromDescription(taskTypeString), taskDueDate, taskId);
                Fragment formFragment = new AddTaskFragment();
                Bundle args = new Bundle();
                args.putSerializable("task", taskToEdit);
                formFragment.setArguments(args);
                fragmentTransaction.replace(R.id.fragmentFrame, formFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            } catch (Exception e) {
                CustomLogger.e("Error during fragment transaction: ", e.getMessage());
            }
        }
    }
}