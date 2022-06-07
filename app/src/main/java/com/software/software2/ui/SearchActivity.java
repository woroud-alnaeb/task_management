package com.software.software2.ui;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.software.software2.R;
import com.software.software2.db.AppDatabase;
import com.software.software2.db.Task;
import com.software.software2.db.TaskDao;
import com.software.software2.ui.adapters.TaskAdapter;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;


public class SearchActivity extends AppCompatActivity {

    private ArrayList<Task> itemArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TaskAdapter itemAdapter;
    private Button back;

    private LinearLayoutManager linearLayoutManager;
    private TaskDao taskDao;
    AppDatabase db;
    Context con;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "task_management_example").build();
        taskDao = db.taskDao();
        back = findViewById(R.id.clear);
        back.setOnClickListener(view -> finish());

        // because Cannot access database on the main thread
        con=this;
        CompletableFuture<ArrayList> completableFuture = CompletableFuture.supplyAsync(() -> itemArrayList = (ArrayList<Task>) taskDao.getAll());
        while (!completableFuture.isDone()) {
            System.out.println("CompletableFuture is not finished yet...");
        }

        AsyncTask.execute(() -> {
            itemArrayList = (ArrayList<Task>) taskDao.getAll();
            recyclerView = findViewById(R.id.recyclerView);
            linearLayoutManager = new LinearLayoutManager(con);
            itemAdapter = new TaskAdapter(con, itemArrayList);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(itemAdapter);
        });


    }

}