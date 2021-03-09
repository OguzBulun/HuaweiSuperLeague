package com.example.sportotosuperlig;
nurrayy
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.sportotosuperlig.adapter.AdapterTeamList;
import com.example.sportotosuperlig.api_rest.ApiClient;
import com.example.sportotosuperlig.api_rest.URL;
import com.example.sportotosuperlig.dbRoom.DatabaseClient;
import com.example.sportotosuperlig.models.Team;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class Main2Activity extends AppCompatActivity {
    LinearLayout team;
    Button fikstur;
    AdapterTeamList customAdapter=null;
    List<Team> teams = null;
    Switch switch1;

    public static final String MyPREFERENCES = "nightModePrefs";
    public static final String KEY_ISNIGHTMODE = "isNightMode";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        team=findViewById(R.id.team);
        fikstur=findViewById(R.id.fikstur);
        switch1=findViewById(R.id.switch1);

        checkNightModeActivated();

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    saveNightModeState(true);
                    recreate();
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    saveNightModeState(false);
                    recreate();
                }
            }
        });

/*
        team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,TeamActivity.class);
                startActivity(intent);
            }
        });
*/


        fikstur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // saveTask();
               // getTasks();

                Intent intent = new Intent(Main2Activity.this,FiksturActivity.class);
                startActivity(intent);
            }
        });

        ApiClient.getJSONClient(URL.SERVER1).teamList().enqueue(new Callback<List<Team>>() {
            @Override
            public void onResponse(Call<List<Team>> call, retrofit2.Response<List<Team>> response) {
                if (response.isSuccessful()) {

                    teams = response.body();

                    ListView listViewTeamList = (ListView) findViewById(R.id.listViewTeamList);
                    listViewTeamList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            Team m = teams.get(position);

                            Intent ıntent=new Intent(Main2Activity.this,TeamActivity.class);
                            ıntent.putExtra("teamName",String.valueOf(m.getName()));
                            ıntent.putExtra("teamImg",String.valueOf(m.getImagePath()));
                            ıntent.putExtra("teamStadium",String.valueOf(m.getStadium()));
                            ıntent.putExtra("teamCoach",String.valueOf(m.getCoach()));
                            ıntent.putExtra("teamCapacity",String.valueOf(m.getCapacity()));
                            startActivity(ıntent);

                        }
                    });
                    customAdapter = new AdapterTeamList(getApplicationContext(), teams);
                    listViewTeamList.setAdapter(customAdapter);

                }


            }

            @Override
            public void onFailure(Call<List<Team>> call, Throwable t) {

            }

        });
    }


    private void saveNightModeState(boolean nightMode){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_ISNIGHTMODE,nightMode);
        editor.apply();
    }

    public void checkNightModeActivated(){
        if(sharedPreferences.getBoolean(KEY_ISNIGHTMODE,false))
        {
            switch1.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            switch1.setChecked(false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }


    private void saveTask() {

        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task
                com.example.sportotosuperlig.dbRoom.Team task = new com.example.sportotosuperlig.dbRoom.Team();
                //task.setId(1);
                task.setFistTeam("Kasımpaşa");
                task.setSecondTeam("Konyaspor");
                task.setMacthDate("15.03.2021 | 19:00");
                task.setMacthWeek("30. Hafta");
                task.setMacthStadium("Recep Tayyip Erdoğan Stadyumu");
                task.setFistTeamImg("https://secure.cache.images.core.optasports.com/soccer/teams/150x150/uuid_4idg23egrrvtrbgrg7p5x7bwf.png");
                task.setSecondTeamImg("https://secure.cache.images.core.optasports.com/soccer/teams/150x150/uuid_cw4lbdzlqqdvbkdkz00c9ye49.png");

                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .teamDao()
                        .insert(task);

                String a = "as";
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), FiksturActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();

                String a = "as";
            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }

    private void getTasks() {
        class GetTasks extends AsyncTask<Void, Void, List<com.example.sportotosuperlig.dbRoom.Team>> {

            @Override
            protected List<com.example.sportotosuperlig.dbRoom.Team> doInBackground(Void... voids) {
                List<com.example.sportotosuperlig.dbRoom.Team> taskList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .teamDao()
                        .getAll();
                return taskList;

            }

            @Override
            protected void onPostExecute(List<com.example.sportotosuperlig.dbRoom.Team> tasks) {
                super.onPostExecute(tasks);
            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }

}
