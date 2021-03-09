package com.example.sportotosuperlig;

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
    Button fikstur,macEkle;
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
        macEkle=findViewById(R.id.macEkle);

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

        macEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTask();
                macEkle.setEnabled(false);
            }
        });


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

                String evSahibi[] = {"Başakşehir","Sivasspor","Rizespor","Alanyaspor","Kayserispor","Ankaragücü","BB Erzurumspor","Gaziantep FK","Fenerbahçe","Kasımpaşa"};
                String konukTakim[] = {"Beşiktaş","Karagümrük","Hatayspor","Antalyaspor","Galatasaray","Göztepe","Trabzonspor","Denizlispor","Gençlerbirliği","Konyaspor"};
                String macTarihi[] = {"12.03.2021 | 19:00","13.03.2021 | 13:30","13.03.2021 | 16:00","13.03.2021 | 16:00","13.03.2021 | 19:00",
                        "14.03.2021 | 13:30","14.03.2021 | 16:00","14.03.2021 | 16:00","14.03.2021 | 19:00","15.03.2021 | 19:00"};
                String macStadium[] = {"Fatih Terim Stadyumu","Yeni 4 Eylül Stadyumu","Çaykur Didi Stadyumu","Bahçeşehir Okulları Stadyumu","Kadir Has",
                        "Ankara Ondokuz Mayıs Stadyumu","Kazım Karabekir Stadyumu","Gaziantep Kalyon Stadyumu","Ülker Stadyumu","Recep Tayyip Erdoğan Stadyumu"};

                String evSahibiImg[] = {"https://secure.cache.images.core.optasports.com/soccer/teams/150x150/uuid_47njg6cmlx5q3fvdsupd2n6qu.png",
                        "https://secure.cache.images.core.optasports.com/soccer/teams/150x150/uuid_f432akygffyamal3h6poig65t.png",
                        "https://secure.cache.images.core.optasports.com/soccer/teams/150x150/uuid_1lbrlj3uu8wi2h9j79snuoae4.png",
                         "https://secure.cache.images.core.optasports.com/soccer/teams/150x150/uuid_84fpe0iynjdghwysyo5tizdkk.png",
                        "https://secure.cache.images.core.optasports.com/soccer/teams/150x150/uuid_c8ns6z3u8kxldv1zh1evu10rv.png",
                        "https://secure.cache.images.core.optasports.com/soccer/teams/150x150/uuid_a7jkz1m32wntcvegrwjbr85t5.png",
                        "https://secure.cache.images.core.optasports.com/soccer/teams/150x150/uuid_ea2gyhkv6vwmxbxevdb4u3796.png",
                        "https://secure.cache.images.core.optasports.com/soccer/teams/150x150/uuid_2agzb2h4ppg7lfz9hn7eg1rqo.png",
                        "https://secure.cache.images.core.optasports.com/soccer/teams/150x150/uuid_8lroq0cbhdxj8124qtxwrhvmm.png",
                        "https://secure.cache.images.core.optasports.com/soccer/teams/150x150/uuid_4idg23egrrvtrbgrg7p5x7bwf.png"};

                String konukTakimImg[] = {"https://secure.cache.images.core.optasports.com/soccer/teams/150x150/uuid_2ez9cvam9lp9jyhng3eh3znb4.png",
                        "https://secure.cache.images.core.optasports.com/soccer/teams/150x150/uuid_c3txoz57mu7w9y1jprvnv2flr.png",
                        "https://secure.cache.images.core.optasports.com/soccer/teams/150x150/uuid_efy1nhpn5l6hc27znhsptegba.png",
                        "https://secure.cache.images.core.optasports.com/soccer/teams/150x150/uuid_9irsyv431fpuqhqtfq9iwxf2u.png",
                        "https://secure.cache.images.core.optasports.com/soccer/teams/150x150/uuid_esa748l653sss1wurz5ps3228.png",
                        "https://secure.cache.images.core.optasports.com/soccer/teams/150x150/uuid_cjbaf8s09qoa1n11r33gc560x.png",
                        "https://secure.cache.images.core.optasports.com/soccer/teams/150x150/uuid_2yab38jdfl0gk2tei1mq40o06.png",
                        "https://secure.cache.images.core.optasports.com/soccer/teams/150x150/uuid_atzw1r6gsxsegd0omo1f0yxkl.png",
                        "https://secure.cache.images.core.optasports.com/soccer/teams/150x150/uuid_embqktr41hfzczc8uav1scmcn.png",
                         "https://secure.cache.images.core.optasports.com/soccer/teams/150x150/uuid_cw4lbdzlqqdvbkdkz00c9ye49.png"};

            for(int i=0;i<=evSahibi.length-1;i++)
            {
                //creating a task
                com.example.sportotosuperlig.dbRoom.Team task = new com.example.sportotosuperlig.dbRoom.Team();
                //task.setId(1);
                task.setFistTeam(evSahibi[i]);
                task.setSecondTeam(konukTakim[i]);
                task.setMacthDate(macTarihi[i]);
                task.setMacthWeek("30. Hafta");
                task.setMacthStadium(macStadium[i]);
                task.setFistTeamImg(evSahibiImg[i]);
                task.setSecondTeamImg(konukTakimImg[i]);

                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .teamDao()
                        .insert(task);


            }
                String a = "as";
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                //finish();
                startActivity(new Intent(getApplicationContext(), FiksturActivity.class));
                Toast.makeText(getApplicationContext(), "Fikstür Oluşturuldu", Toast.LENGTH_LONG).show();

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
