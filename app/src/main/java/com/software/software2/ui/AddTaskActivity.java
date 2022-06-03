package com.software.software2.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
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

public class AddTaskActivity extends AppCompatActivity {
    private Button back;
    private Button add_task_btn;

    private EditText ed_title;
    private EditText ed_desc;
    private int year;
    private int month;
    private int day;
    private TextView tv_date;
    private TaskDao taskDao;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "task_management_example").build();
        back = (Button) findViewById(R.id.clear);
        ed_title = (EditText) findViewById(R.id.ed_title);
        ed_desc = (EditText) findViewById(R.id.ed_desc);
        add_task_btn = (Button) findViewById(R.id.add_task);
        tv_date = (TextView) findViewById(R.id.tv_date);

        back.setOnClickListener(view -> finish());
        taskDao = db.taskDao();
        tv_date.setOnClickListener(view -> {
            showDialog(999);
        });
        add_task_btn.setOnClickListener(view -> {
            List<Task> list = new ArrayList<>();
            list.add(new Task(1, "woroud", ed_title.getText().toString(), ed_desc.getText().toString(), false, year, month, day));
            taskDao.insertAll(list);
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