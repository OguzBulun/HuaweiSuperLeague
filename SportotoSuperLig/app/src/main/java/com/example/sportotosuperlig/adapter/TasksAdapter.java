package com.example.sportotosuperlig.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sportotosuperlig.MatchActivity;
import com.example.sportotosuperlig.R;
import com.example.sportotosuperlig.dbRoom.Team;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksViewHolder> {

    private Context mCtx;
    private List<Team> taskList;

    public TasksAdapter(Context mCtx, List<Team> taskList) {
        this.mCtx = mCtx;
        this.taskList = taskList;
    }

    @Override
    public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_tasks, parent, false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TasksViewHolder holder, int position) {
        Team t = taskList.get(position);
        holder.ilkTakim.setText(t.getFistTeam());
        holder.ikinciTakim.setText(t.getSecondTeam());
        holder.macDate.setText(t.getMacthDate());
        //holder.macWeek.setText(t.getMacthWeek());

        Picasso.get().load(t.fistTeamImg).into(holder.firstTeam);
        Picasso.get().load(t.secondTeamImg).into(holder.secondTeam);

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView ilkTakim, ikinciTakim, macDate, macWeek;
        ImageView secondTeam,firstTeam;

        public TasksViewHolder(View itemView) {
            super(itemView);

            ilkTakim = itemView.findViewById(R.id.ilkTakim);
            firstTeam = itemView.findViewById(R.id.firstTeam);
            secondTeam = itemView.findViewById(R.id.secondTeam);
            ikinciTakim = itemView.findViewById(R.id.ikinciTakim);
            macDate = itemView.findViewById(R.id.macDate);
            // macWeek = itemView.findViewById(R.id.macWeek);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Team task = taskList.get(getAdapterPosition());

            Intent intent = new Intent(mCtx, MatchActivity.class);
            intent.putExtra("matchDetails", task);
            mCtx.startActivity(intent);
        }
    }
}
