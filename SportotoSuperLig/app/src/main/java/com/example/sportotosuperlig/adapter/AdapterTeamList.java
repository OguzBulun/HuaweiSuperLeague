package com.example.sportotosuperlig.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sportotosuperlig.R;
import com.example.sportotosuperlig.models.Team;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterTeamList extends BaseAdapter {

    private List<Team> items;
    private Gson gson;
    private Context context;
    private LayoutInflater inflater;


    public AdapterTeamList(Context context, List<Team> items) {

        this.context = context;
        this.items = items;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.gson = new Gson();

    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View row;
        if (convertView == null) {
            row = inflater.inflate(R.layout.row_teams_list, parent, false);
        } else {
            row = convertView;
        }


        Team teams = (Team) getItem(position);

        ImageView teamImg = (ImageView) row.findViewById(R.id.teamImg);
        Picasso.get().load(teams.getImagePath()).into(teamImg);

        TextView teamName = (TextView) row.findViewById(R.id.teamName);
        teamName.setText(teams.getName());

        TextView teamCoachName = (TextView) row.findViewById(R.id.teamCoachName);
        teamCoachName.setText(teams.getCoach());


        return row;


    }


}