package com.brownbox.mvvm.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.brownbox.mvvm.R;
import com.brownbox.mvvm.databinding.ItemRowBinding;
import com.brownbox.mvvm.model.TeamDetail;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {

    private Context context;
    private List<TeamDetail> listTeam;
    private LayoutInflater layoutInflater;


    public RVAdapter(Context context, List<TeamDetail> listTeam) {
        this.context = context;
        this.listTeam = listTeam;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(layoutInflater == null){
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        ItemRowBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_row,
                viewGroup, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.binding.setTeamDetailVM(listTeam.get(i));
    }

    @Override
    public int getItemCount() {
        return listTeam.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemRowBinding binding;

        public ViewHolder(@NonNull ItemRowBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.binding = itemRowBinding;
        }
    }
}
