package com.example.android_final_project.gui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.android_final_project.R;
import com.example.android_final_project.db.Task;
import com.example.android_final_project.db.TaskViewModel;
import com.example.android_final_project.db.TaskViewModelFactory;
import com.example.android_final_project.gui.util.TaskAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaskListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskListFragment extends Fragment implements TaskAdapter.TaskAdapterListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TaskViewModel taskViewModel;
    private RecyclerView tasksRecyclerView;
    private TaskAdapter adapter;

    private Button closeListButt;


    public TaskListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TaskListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TaskListFragment newInstance(String param1, String param2) {
        TaskListFragment fragment = new TaskListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        closeListButt = view.findViewById(R.id.closeListButt);
        tasksRecyclerView = view.findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TaskAdapter(this);
        tasksRecyclerView.setAdapter(adapter);

        if (getActivity() != null) {
            TaskViewModelFactory factory = new TaskViewModelFactory(getActivity().getApplication());
            taskViewModel = new ViewModelProvider(requireActivity(), factory).get(TaskViewModel.class);
        }

        // Observe the LiveData from the ViewModel
        taskViewModel.getAllTasks().observe(getViewLifecycleOwner(), tasks -> adapter.setTasks(tasks));

        closeListButt.setOnClickListener(v -> closeFragment());

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                adapter.deleteItem(position);
            }
        });
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);
        taskViewModel.getAllTasks().observe(getViewLifecycleOwner(), tasks -> adapter.setTasks(tasks));

        return view;
    }

    public void closeFragment() {
        getParentFragmentManager().popBackStack();
    }

    @Override
    public void onDeleteTask(Task task) {
        taskViewModel.delete(task);
    }
}