package com.example.jvillanueva.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.Date;

public class NewTaskFormActivity extends AppCompatActivity {

    TaskViewModel currentTask;
    String method;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.LoadSpinners();

        Bundle b = this.getIntent().getExtras();

        if( b != null){
            //Toast.makeText(getApplicationContext(), "Edit task", Toast.LENGTH_LONG).show();
            this.method = "edit";
            this.position = getIntent().getIntExtra("position", 0);
            currentTask = b.getParcelable("TaskViewModel");

            EditText taskNameET = (EditText) findViewById(R.id.etTaskName);
            taskNameET.setText(currentTask.tittle);

//            EditText dueDateET = (EditText) findViewById(R.id.etDueDate);
//            String dueData = dueDateET.getText().toString();

            EditText detailsET = (EditText) findViewById(R.id.etDetails);
            detailsET.setText(currentTask.description);

            Spinner prioritySp = (Spinner) findViewById(R.id.spPriority);
            int priorityIndex = 0;
            int statusIndex = 0;
            switch (currentTask.priority){
                case "HIGH":
                    priorityIndex = 0;
                    break;

                case "MEDIUM":
                    priorityIndex = 1;
                    break;

                case "LOW":
                    priorityIndex = 2;
                    break;
            }
            prioritySp.setSelection(priorityIndex);
            Spinner statusSp = (Spinner) findViewById(R.id.spStatus);
            switch (currentTask.status){
                case "TO-DO":
                    statusIndex = 0;
                    break;

                case "DONE":
                    statusIndex = 1;
                    break;
            }
            statusSp.setSelection(statusIndex);

        }else
        {
            this.method = "new";
            //Toast.makeText(getApplicationContext(), "new TaskName", Toast.LENGTH_LONG).show();
        }

    }

    private void LoadSpinners(){
        Spinner spinner = (Spinner) findViewById(R.id.spPriority);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.priority_items, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Spinner statutsSpinner = (Spinner) findViewById(R.id.spStatus);
        ArrayAdapter<CharSequence> adapterStatus = ArrayAdapter.createFromResource(this,
                R.array.status_items, android.R.layout.simple_spinner_item);

        adapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statutsSpinner.setAdapter(adapterStatus);
    }

    public void OnCancel(View view) {
        Intent data = new Intent();
        setResult(-1, data);
        finish();
    }

    public void OnSave(View view) {
        EditText taskNameET = (EditText) findViewById(R.id.etTaskName);
        String taskName = taskNameET.getText().toString();

        DatePicker datePicker = (DatePicker)findViewById(R.id.dpDate);
        Date date = getDateFromDatePicker(datePicker);

        EditText detailsET = (EditText) findViewById(R.id.etDetails);
        String deatils = detailsET.getText().toString();

        Spinner prioritySp = (Spinner) findViewById(R.id.spPriority);
        String priority = prioritySp.getSelectedItem().toString();

        Spinner statusSp = (Spinner) findViewById(R.id.spStatus);
        String status = statusSp.getSelectedItem().toString();

        TaskViewModel newTask = new TaskViewModel(taskName, deatils, priority, status, date);
        Bundle abstractData = new Bundle();
        abstractData.putParcelable("TaskViewModel", newTask);

        Intent data = new Intent();
        data.putExtras(abstractData);
        data.putExtra("method", this.method);
        data.putExtra("position", this.position);
        setResult(10, data);
        finish();
    }

    public static java.util.Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }
}
