package com.example.android_final_project.gui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.android_final_project.R;
import com.example.android_final_project.application.MainApplication;
import com.example.android_final_project.db.Task;
import com.example.android_final_project.db.TaskViewModel;
import com.example.android_final_project.db.TaskViewModelFactory;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddTaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddTaskFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText taskNameEt, taskDescriptionEt, pickDateEt;
    private RadioGroup taskTypeRG;
    private Button saveButton, pickDateButt, closeTab;

    private Calendar calendar;

    private String retreivedDateTimeString;

    private TaskViewModel taskViewModel;

    private int idOfCheckedRadio;

    private final int UNCHECKED_RADIOBUTTON = -1;

    public AddTaskFragment() {
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
    public static AddTaskFragment newInstance(String param1, String param2) {
        AddTaskFragment fragment = new AddTaskFragment();
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
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);

        // Initialize TaskViewModel
        if (getActivity() != null) {
            TaskViewModelFactory factory = new TaskViewModelFactory(getActivity().getApplication());
            taskViewModel = new ViewModelProvider(requireActivity(), factory).get(TaskViewModel.class);
        }

        calendar = Calendar.getInstance();

        // Initialize EditTexts
        taskNameEt = view.findViewById(R.id.taskNameEt);
        pickDateEt = view.findViewById(R.id.pickDateEt);
        taskDescriptionEt = view.findViewById(R.id.taskDescriptionEt);
        pickDateButt = view.findViewById(R.id.pickDateButton);

        // Initialize RadioGroup
        taskTypeRG = view.findViewById(R.id.taskTypeRG);

        // Initialize Button
        saveButton = view.findViewById(R.id.saveButton);
        closeTab = view.findViewById(R.id.closeTaskTab);
        saveButton.setOnClickListener(v -> saveTask(view));
        closeTab.setOnClickListener(v-> closeFragment());
        pickDateButt.setOnClickListener(v -> getDateAndTime());

        return view;
    }

    private void getDateAndTime() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), (view, selectedYear, selectedMonth, selectedDayOfMonth) -> {
            // Handle the selected date
            String selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDayOfMonth;

            // Create a TimePickerDialog to select the time
            TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), (view1, selectedHourOfDay, selectedMinute) -> {
                // Handle the selected time
                String selectedTime = selectedHourOfDay + ":" + selectedMinute;

                // Combine the date and time
                String selectedDateTime = selectedDate + " " + selectedTime;

                // Update your EditText or any other view with the selected date and time
                pickDateEt.setText(selectedDateTime);
            }, hourOfDay, minute, true // Set to true for 24-hour format, false for AM/PM format
            );

            // Show the TimePickerDialog after selecting the date
            timePickerDialog.show();
        }, year, month, dayOfMonth);
        retreivedDateTimeString = String.format("%04d-%02d-%02d %02d:%02d:00", year, month, dayOfMonth, hourOfDay, minute);
        datePickerDialog.show();
    }

    private void saveTask(View view) {

        String taskName = taskNameEt.getText().toString();
        String description = taskDescriptionEt.getText().toString();
        String dueDate = retreivedDateTimeString != null ? retreivedDateTimeString : Task.INITIAL_DATE_TIME;
        Task.TaskType taskType = Task.TaskType.UNDEFINED;

        Log.d(MainApplication.LOG_HEADER, "inputs");
        Log.d(MainApplication.LOG_HEADER, "taskName: " + taskName);
        Log.d(MainApplication.LOG_HEADER, "description: " + description);
        Log.d(MainApplication.LOG_HEADER, "dueDate: " + dueDate);

        // Get selected task type from RadioGroup
        int selectedId = taskTypeRG.getCheckedRadioButtonId();
        idOfCheckedRadio = selectedId;
        Log.d(MainApplication.LOG_HEADER, "radio button ID: " + selectedId);
        if (selectedId != UNCHECKED_RADIOBUTTON) {
            if (selectedId == R.id.personalRB) {
                Log.d(MainApplication.LOG_HEADER, "PERSONAL");
                taskType = Task.TaskType.PERSONAL;
            }
            if (selectedId == R.id.workRB) {
                Log.d(MainApplication.LOG_HEADER, "WORK");
                taskType = Task.TaskType.WORK;
            }
            if (selectedId == R.id.leisureRB) {
                Log.d(MainApplication.LOG_HEADER, "LEISURE");
                taskType = Task.TaskType.LEISURE;
            }
        }


        // Validate inputs and proceed with saving task logic
        if (validateInput(taskName, description, dueDate)) {
            // Create a Task object and save it
            Task task = new Task(taskName, description, taskType, dueDate);
            taskViewModel.insert(task);
            Toast.makeText(getActivity(), "Task added successfully", Toast.LENGTH_SHORT).show();
            Log.i(MainApplication.LOG_HEADER, "TASK ADDED SUCCESSFULLY");
            clearForm(view);
            closeFragment();
        } else {
            Toast.makeText(getActivity(), "Task failed to be added.", Toast.LENGTH_SHORT).show();
            Log.e(MainApplication.LOG_HEADER, "TASK FAILED TO BE ADDED.");
        }
    }

    private void clearForm(View view) {
        taskNameEt.setText("");
        taskDescriptionEt.setText("");
        retreivedDateTimeString = "0000-00-00 00:00:00";
        if (idOfCheckedRadio != UNCHECKED_RADIOBUTTON) {
            RadioButton selectedButton = view.findViewById(idOfCheckedRadio);
            selectedButton.setChecked(false);
        }
    }

    private boolean validateInput(String taskName, String description, String dueDate) {
        if (taskName.isEmpty() || description.isEmpty() || dueDate.isEmpty()) {
            // Show error message
            return false;
        }
        return true;
    }

    public void closeFragment() {
        if (getParentFragmentManager() != null) {
            getParentFragmentManager().popBackStack();
        }
    }
}