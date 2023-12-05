package com.example.android_final_project.gui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.android_final_project.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddTask#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddTask extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText taskNameEt, taskDescriptionEt, pickDateEt;
    private RadioGroup taskTypeRG;
    private Button saveButton;

    public AddTask() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddTask.
     */
    // TODO: Rename and change types and number of parameters
    public static AddTask newInstance(String param1, String param2) {
        AddTask fragment = new AddTask();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);

        // Initialize EditTexts
        taskNameEt = view.findViewById(R.id.taskNameEt);
        taskDescriptionEt = view.findViewById(R.id.taskDescriptionEt);
        pickDateEt = view.findViewById(R.id.pickDateEt);

        // Initialize RadioGroup
        taskTypeRG = view.findViewById(R.id.taskTypeRG);

        // Initialize Button
        saveButton = view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(v -> saveTask(view));

        return view;
    }

    private void saveTask(View view) {
        String taskName = taskNameEt.getText().toString();
        String description = taskDescriptionEt.getText().toString();
        String dueDate = pickDateEt.getText().toString();

        // Get selected task type from RadioGroup
        int selectedId = taskTypeRG.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = view.findViewById(selectedId);
        String taskType = selectedRadioButton.getText().toString();

        // Validate inputs and proceed with saving task logic
        if (validateInput(taskName, description, dueDate)) {
            // Create a Task object and save it
            // Task task = new Task(taskName, description, dueDate, taskType);
            // taskViewModel.insert(task);
        } else {
            // Show error message
        }
    }

    private boolean validateInput(String taskName, String description, String dueDate) {
        if (taskName.isEmpty() || description.isEmpty() || dueDate.isEmpty()) {
            // Show error message
            return false;
        }
        return true;
    }
}