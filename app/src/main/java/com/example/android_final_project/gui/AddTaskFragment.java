package com.example.android_final_project.gui;

import android.annotation.SuppressLint;
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
import android.widget.CheckBox;
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

    private Calendar calendar;

    private CheckBox setAlarmCb;

    private String retreivedDateTimeString;

    private TaskViewModel taskViewModel;

    private int idOfCheckedRadio;

    private final int UNCHECKED_RADIOBUTTON = -1;

    private RadioButton checkedRadioButton;

    private boolean isEditing;

    private Task currentTask;

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
        isEditing = false;


        if (getActivity() != null) {
            TaskViewModelFactory factory = new TaskViewModelFactory(getActivity().getApplication());
            taskViewModel = new ViewModelProvider(requireActivity(), factory).get(TaskViewModel.class);
        }


        calendar = Calendar.getInstance();
        taskNameEt = view.findViewById(R.id.taskNameEt);
        pickDateEt = view.findViewById(R.id.pickDateEt);
        taskDescriptionEt = view.findViewById(R.id.taskDescriptionEt);
        Button pickDateButt = view.findViewById(R.id.pickDateButton);
        taskTypeRG = view.findViewById(R.id.taskTypeRG);
        Button saveButton = view.findViewById(R.id.saveButton);
        Button closeTab = view.findViewById(R.id.closeTaskTab);
        setAlarmCb = view.findViewById(R.id.checkBoxAlarm);


        saveButton.setOnClickListener(v -> saveTask(view, null));
        closeTab.setOnClickListener(v -> closeFragment());
        pickDateButt.setOnClickListener(v -> getDateAndTime());


        if (getArguments() != null && getArguments().containsKey("task")) {
            isEditing = true;
            currentTask = (Task) getArguments().getSerializable("task");
            taskNameEt.setText(currentTask.getName());
            pickDateEt.setText(currentTask.getDueDate());
            taskDescriptionEt.setText(currentTask.getDescription());
            String taskType = currentTask.getTaskType().toString();
            setAlarmCb.setChecked(currentTask.isAlarmOn());

            if (taskType.equalsIgnoreCase("LEISURE")) {
                checkedRadioButton = view.findViewById(R.id.leisureRB);
            }

            if (taskType.equalsIgnoreCase("PERSONAL")) {
                checkedRadioButton = view.findViewById(R.id.personalRB);

            }

            if (taskType.equalsIgnoreCase("WORK")) {
                checkedRadioButton = view.findViewById(R.id.workRB);
            }

            if (checkedRadioButton != null) {
                checkedRadioButton.setChecked(true);
            }
        }

        if (getArguments() != null && getArguments().containsKey("updatingCheckbox")) {
            isEditing = true;
            currentTask = (Task) getArguments().getSerializable("updatingCheckbox");
            saveTask(view, currentTask);
            closeFragment();
        }

        return view;
    }

    @SuppressLint("DefaultLocale")
    private void getDateAndTime() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), (view, selectedYear, selectedMonth, selectedDayOfMonth) -> {

            String selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDayOfMonth;
            TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), (view1, selectedHourOfDay, selectedMinute) -> {
                String selectedTime = selectedHourOfDay + ":" + selectedMinute;
                String selectedDateTime = selectedDate + " " + selectedTime;
                pickDateEt.setText(selectedDateTime);
            }, hourOfDay, minute, true // true for 24-hour, false for AM/PM
            );

            timePickerDialog.show();
        }, year, month, dayOfMonth);
        retreivedDateTimeString = String.format("%04d-%02d-%02d %02d:%02d:00", year, month, dayOfMonth, hourOfDay, minute);
        datePickerDialog.show();
    }

    private void saveTask(View view, Task updateCheckTask) {
        String taskName = taskNameEt.getText().toString();
        String description = taskDescriptionEt.getText().toString();
        String dueDate = retreivedDateTimeString != null ? retreivedDateTimeString : Task.INITIAL_DATE_TIME;
        Task.TaskType taskType = Task.TaskType.UNDEFINED;
        boolean alarmOn = setAlarmCb.isChecked();

        Log.d(MainApplication.LOG_HEADER, "inputs");
        Log.d(MainApplication.LOG_HEADER, "taskName: " + taskName);
        Log.d(MainApplication.LOG_HEADER, "description: " + description);
        Log.d(MainApplication.LOG_HEADER, "dueDate: " + dueDate);

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

        if (updateCheckTask != null) {
            taskViewModel.update(updateCheckTask);
            return;
        }

        if (validateInput(taskName, description, dueDate)) {
            if (!isEditing) {
                Task task = new Task(taskName, description, taskType, dueDate, alarmOn);
                Toast.makeText(getActivity(), "checking " + dueDate, Toast.LENGTH_SHORT).show();
                taskViewModel.insert(task);
            } else {
                Task task = new Task(taskName, description, taskType, dueDate, alarmOn, currentTask.getId());
                taskViewModel.update(task);
            }

            Toast.makeText(getContext(), isEditing ? "Task updated" : "Task created", Toast.LENGTH_SHORT).show();
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
        return !taskName.isEmpty() && !description.isEmpty() && !dueDate.isEmpty();
    }

    public void closeFragment() {
        getParentFragmentManager().popBackStack();
    }
}