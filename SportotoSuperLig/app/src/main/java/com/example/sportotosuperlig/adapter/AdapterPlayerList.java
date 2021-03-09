package com.example.sportotosuperlig.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sportotosuperlig.R;
import com.example.sportotosuperlig.models.Player;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterPlayerList extends BaseAdapter {
    private List<Player> items;
    private Gson gson;
    private Context context;
    private LayoutInflater inflater;

    public AdapterPlayerList(Context context, List<Player> items) {

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
            row = inflater.inflate(R.layout.row_team_player_list, parent, false);
        } else {
            row = convertView;
        }

        Player player = (Player) getItem(position);

        TextView player1 = (TextView) row.findViewById(R.id.playerName1);
        player1.setText(player.getPlayer1());

        TextView player2 = (TextView) row.findViewById(R.id.playerName2);
        player2.setText(player.getPlayer2());

        TextView player3 = (TextView) row.findViewById(R.id.playerName3);
        player3.setText(player.getPlayer3());

        TextView player4 = (TextView) row.findViewById(R.id.playerName4);
        player4.setText(player.getPlayer4());

        TextView player5 = (TextView) row.findViewById(R.id.playerName5);
        player5.setText(player.getPlayer5());

        TextView player6 = (TextView) row.findViewById(R.id.playerName6);
        player6.setText(player.getPlayer6());

        TextView player7 = (TextView) row.findViewById(R.id.playerName7);
        player7.setText(player.getPlayer7());

        TextView player8 = (TextView) row.findViewById(R.id.playerName8);
        player8.setText(player.getPlayer8());

        TextView player9 = (TextView) row.findViewById(R.id.playerName9);
        player9.setText(player.getPlayer9());

        TextView player10= (TextView) row.findViewById(R.id.playerName10);
        player10.setText(player.getPlayer10());

        TextView player11 = (TextView) row.findViewById(R.id.playerName11);
        player11.setText(player.getPlayer11());

        return row;

    }


    }
