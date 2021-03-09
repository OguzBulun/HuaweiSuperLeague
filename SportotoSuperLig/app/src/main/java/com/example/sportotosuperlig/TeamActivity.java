package com.example.sportotosuperlig;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sportotosuperlig.adapter.AdapterPlayerList;
import com.example.sportotosuperlig.api_rest.ApiClient;
import com.example.sportotosuperlig.api_rest.URL;
import com.example.sportotosuperlig.models.Player;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class TeamActivity extends AppCompatActivity {
    LinearLayout teamplayer;
    AdapterPlayerList customAdapter=null;
    List<Player> players = null;
    ImageView teamIm;
    TextView teamDetailName,teamDetailStadium,teamDetailDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        teamplayer=findViewById(R.id.teamplayer);

        String teamName=getIntent().getStringExtra("teamName");
        String teamImg=getIntent().getStringExtra("teamImg");
        String teamStadium=getIntent().getStringExtra("teamStadium");
        String teamCoach=getIntent().getStringExtra("teamCoach");
        String teamCapacity=getIntent().getStringExtra("teamCapacity");

        teamIm = findViewById(R.id.teamIm);
        teamDetailName = findViewById(R.id.teamDetailName);
        teamDetailStadium = findViewById(R.id.teamDetailStadium);
        teamDetailDesc = findViewById(R.id.teamDetailDesc);

        Picasso.get().load(teamImg).into(teamIm);
        teamDetailName.setText(teamName);
        teamDetailStadium.setText("Ülke : Türkiye | Stadyum : "+teamStadium);
        teamDetailDesc.setText("Kapasite : "+teamCapacity+" | Teknik Direktör : "+teamCoach);


        ApiClient.getJSONClient(URL.SERVER1).playerList().enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, retrofit2.Response<List<Player>> response) {
                if (response.isSuccessful()) {

                    players = response.body();

                    ListView listViewPlayerList = (ListView) findViewById(R.id.listViewPlayerList);
                    listViewPlayerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        }
                    });
                    customAdapter = new AdapterPlayerList(getApplicationContext(), players);
                    listViewPlayerList.setAdapter(customAdapter);

                }


            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {

            }

        });



    }
}
