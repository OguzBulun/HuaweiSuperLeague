package com.example.sportotosuperlig;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sportotosuperlig.dbRoom.Team;
import com.squareup.picasso.Picasso;

public class MatchActivity extends AppCompatActivity {
    TextView MatchFirstTeam, MatchSecondTeam, MatchDateWeek, MatchDateDetails, firstName, secondName,firstPlayer, secondPlayer;
    ImageView firstImg, secondImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        MatchFirstTeam = findViewById(R.id.MatchFirstTeam);
        MatchSecondTeam = findViewById(R.id.MatchSecondTeam);
        MatchDateWeek = findViewById(R.id.MatchDateWeek);
        MatchDateDetails = findViewById(R.id.MatchDateDetails);
        firstImg = findViewById(R.id.firstImg);
        secondImg = findViewById(R.id.secondImg);

        firstName = findViewById(R.id.firstName);
        secondName = findViewById(R.id.secondName);
        firstPlayer = findViewById(R.id.firstPlayer);
        secondPlayer = findViewById(R.id.secondPlayer);

        final Team task = (Team) getIntent().getSerializableExtra("matchDetails");
        loadTask(task);
    }

    private void loadTask(Team task) {
        MatchFirstTeam.setText(task.getFistTeam());
        MatchSecondTeam.setText(task.getSecondTeam());
        MatchDateDetails.setText("30. Hafta | "+task.getMacthDate());
        MatchDateWeek.setText(task.getMacthWeek());

        Picasso.get().load(task.fistTeamImg).into(firstImg);
        Picasso.get().load(task.secondTeamImg).into(secondImg);

        firstName.setText(task.getFistTeam());
        secondName.setText(task.getSecondTeam());
        firstPlayer.setText(task.getFistTeam()+" İlk 11");
        secondPlayer.setText(task.getSecondTeam()+" İlk 11");



    }

}
