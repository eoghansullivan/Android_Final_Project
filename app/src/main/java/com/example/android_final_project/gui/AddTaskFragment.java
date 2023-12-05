package com.example.android_final_project.gui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.android_final_project.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddTaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddTaskFragment extends Fragment {

    public static final String LOG_HEADER = "A00287845";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText taskNameEt, taskDescriptionEt, pickDateEt;
    private RadioGroup taskTypeRG;
    private Button saveButton, pickDateButt;

    private Calendar calendar;

    private String retreivedDateTimeString;

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
        saveButton.setOnClickListener(v -> saveTask(view));

        pickDateButt.setOnClickListener (v -> getDateAndTime());



        return view;
    }

    private void getDateAndTime() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                (view, selectedYear, selectedMonth, selectedDayOfMonth) -> {
                    // Handle the selected date
                    String selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDayOfMonth;

                    // Create a TimePickerDialog to select the time
                    TimePickerDialog timePickerDialog = new TimePickerDialog(
                            getContext(),
                            (view1, selectedHourOfDay, selectedMinute) -> {
                                // Handle the selected time
                                String selectedTime = selectedHourOfDay + ":" + selectedMinute;

                                // Combine the date and time
                                String selectedDateTime = selectedDate + " " + selectedTime;

                                // Update your EditText or any other view with the selected date and time
                                pickDateEt.setText(selectedDateTime);
                            },
                            hourOfDay,
                            minute,
                            true // Set to true for 24-hour format, false for AM/PM format
                    );

                    // Show the TimePickerDialog after selecting the date
                    timePickerDialog.show();
                },
                year,
                month,
                dayOfMonth
        );
        retreivedDateTimeString = String.format("%04d-%02d-%02d %02d:%02d:00", year, month, dayOfMonth, hourOfDay, minute);
        datePickerDialog.show();
    }

    private void saveTask(View view) {

        String taskName = taskNameEt.getText().toString();
        String description = taskDescriptionEt.getText().toString();
        String dueDate = retreivedDateTimeString!= null ? retreivedDateTimeString : "0000-00-00 00:00:00";
        
        Log.d(LOG_HEADER, "inputs");
        Log.d(LOG_HEADER, "taskName: " + taskName);
        Log.d(LOG_HEADER, "description: " + description);
        Log.d(LOG_HEADER, "dueDate: " + dueDate);

        // Get selected task type from RadioGroup
        int selectedId = taskTypeRG.getCheckedRadioButtonId();
        if (selectedId != -1) {
            RadioButton selectedRadioButton = view.findViewById(selectedId);
            String taskType = selectedRadioButton.getText().toString();
        }


        // Validate inputs and proceed with saving task logic
        if (validateInput(taskName, description, dueDate)) {
            // Create a Task object and save it
            //Task task = new Task(taskName, description, dueDate, taskType);
            //taskViewModel.insert(task);
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