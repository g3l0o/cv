package com.roger.cv.view.technical;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.roger.cv.R;
import com.roger.cv.databinding.TechnicalItemBinding;
import com.roger.cv.model.TechnicalSkill;

import java.util.ArrayList;
import java.util.List;

public class TechnicalListAdapter extends RecyclerView.Adapter<TechnicalListAdapter.TechnicalViewHolder>{

    private ArrayList<TechnicalSkill> technicalSkillList;

    public TechnicalListAdapter(ArrayList<TechnicalSkill> technicalList) {
        technicalSkillList = technicalList;
    }

    public void updateTechnicalList(List<TechnicalSkill> technicalList){
        technicalSkillList.clear();
        technicalSkillList.addAll(technicalList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TechnicalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        TechnicalItemBinding view = DataBindingUtil.inflate(inflater, R.layout.technical_item, parent, false);
        return new TechnicalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TechnicalViewHolder holder, int position) {
        TechnicalSkill technicalSkill = technicalSkillList.get(position);
        holder.itemView.setTechnicalSkill(technicalSkill);
    }

    @Override
    public int getItemCount() {
        return technicalSkillList.size();
    }

    class TechnicalViewHolder extends RecyclerView.ViewHolder{

        public TechnicalItemBinding itemView;

        public TechnicalViewHolder(@NonNull TechnicalItemBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }
}
