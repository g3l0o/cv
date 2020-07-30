package com.roger.cv.view.academy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.roger.cv.R;
import com.roger.cv.databinding.AcademyItemBinding;
import com.roger.cv.model.AcademicStudies;

import java.util.ArrayList;
import java.util.List;

public class AcademyListAdapter extends RecyclerView.Adapter<AcademyListAdapter.AcademyViewHolder>{

    private ArrayList<AcademicStudies> academicStudiesList;

    public AcademyListAdapter(ArrayList<AcademicStudies> academicStudiesList) {
        this.academicStudiesList = academicStudiesList;
    }

    public void updateAcademicStudiesList(List<AcademicStudies> academicList){
        academicStudiesList.clear();
        academicStudiesList.addAll(academicList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AcademyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        AcademyItemBinding view = DataBindingUtil.inflate(inflater, R.layout.academy_item, parent, false);
        return new AcademyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AcademyViewHolder holder, int position) {
        AcademicStudies academicStudies = academicStudiesList.get(position);
        holder.itemView.setAcademy(academicStudies);
    }

    @Override
    public int getItemCount() {
        return academicStudiesList.size();
    }


    class AcademyViewHolder extends RecyclerView.ViewHolder{

        public AcademyItemBinding itemView;

        public AcademyViewHolder(@NonNull AcademyItemBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }
}
