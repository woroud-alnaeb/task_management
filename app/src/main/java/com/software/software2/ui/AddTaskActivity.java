package com.software.software2.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.software.software2.R;
import com.software.software2.db.AppDatabase;
import com.software.software2.db.Task;
import com.software.software2.db.TaskDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AddTaskActivity extends AppCompatActivity {
        private Button back;
    private Button add_task_btn;

    private EditText ed_title;
    private EditText ed_desc;
    private int year=2022;
    private int month=1;
    private int day=1;
    private TextView tv_date;
    private TaskDao taskDao;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "task_management_example").build();
        back = findViewById(R.id.clear);
        ed_title = findViewById(R.id.ed_title);
        ed_desc = findViewById(R.id.ed_desc);
        add_task_btn = findViewById(R.id.add_task);
        tv_date = findViewById(R.id.tv_date);

        back.setOnClickListener(view -> finish());
        taskDao = db.taskDao();
        tv_date.setOnClickListener(view -> {
            showDialog(999);
        });
        add_task_btn.setOnClickListener(view -> {
            List<Task> list = new ArrayList<>();
            list.add(new Task(new Random().nextInt(1000), "woroud", ed_title.getText().toString(), ed_desc.getText().toString(), false, year, month, day));
            // because Cannot access database on the main thread
            AsyncTask.execute(() -> taskDao.insertAll(list));
            finish();
        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private final DatePickerDialog.OnDateSetListener myDateListener = (view, yearArgument, monthArgument, dayArgument) -> {
        year = yearArgument;
        month = monthArgument;
        day = dayArgument;

        tv_date.setText(year + "/" + month + "/" + day);
    };
}