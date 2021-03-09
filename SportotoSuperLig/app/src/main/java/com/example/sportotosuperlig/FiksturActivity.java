package com.example.sportotosuperlig;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.sportotosuperlig.adapter.TasksAdapter;
import com.example.sportotosuperlig.dbRoom.DatabaseClient;
import com.example.sportotosuperlig.dbRoom.Team;

import java.util.List;

public class FiksturActivity extends AppCompatActivity {
    LinearLayout teamdetay;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fikstur);

        recyclerView = findViewById(R.id.recyclerview_tasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getTasks();

        /*
        teamdetay=findViewById(R.id.teamdetay);

        teamdetay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FiksturActivity.this,MatchActivity.class);
                startActivity(intent);
            }
        });

        */

    }


    private void getTasks() {
        class GetTasks extends AsyncTask<Void, Void, List<Team>> {

            @Override
            protected List<Team> doInBackground(Void... voids) {
                List<Team> taskList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .teamDao()
                        .getAll();
                return taskList;
            }

            @Override
            protected void onPostExecute(List<Team> tasks) {
                super.onPostExecute(tasks);
                TasksAdapter adapter = new TasksAdapter(FiksturActivity.this, tasks);
                recyclerView.setAdapter(adapter);
            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }

}
