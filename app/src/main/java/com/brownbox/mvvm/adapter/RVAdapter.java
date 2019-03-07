package com.brownbox.mvvm.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.brownbox.mvvm.R;
import com.brownbox.mvvm.model.TeamDetail;
import com.bumptech.glide.Glide;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {

    private Context context;
    private List<TeamDetail> listTeam;

    public RVAdapter(Context context, List<TeamDetail> listTeam) {
        this.context = context;
        this.listTeam = listTeam;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_row, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.tvName.setText(listTeam.get(i).getTeamName());
        Glide.with(context)
                .load(listTeam.get(i).getTeamLogo())
                .into(viewHolder.imgLogo);

    }

    @Override
    public int getItemCount() {
        return listTeam.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgLogo;
        private TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgLogo = (ImageView) itemView.findViewById(R.id.iv_logo);
            tvName = (TextView) itemView.findViewById(R.id.tv_teamName);
        }
    }
}
