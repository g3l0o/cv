package com.roger.cv;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by roger on 20/03/17.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CompanyHolder>{

    public static class CompanyHolder extends RecyclerView.ViewHolder{

        CardView cv;
        ImageButton companyLogo;
        TextView companyName;
        TextView companyDescription;

        CompanyHolder(View itemView){
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.company_card_view);
            companyLogo = (ImageButton) itemView.findViewById(R.id.card_view_company_logo);
            companyName = (TextView) itemView.findViewById(R.id.card_view_company_name);
            companyDescription = (TextView) itemView.findViewById(R.id.card_view_company_description);
        }
    }

    List<Company> companies;

    RVAdapter(List<Company> companies){
        this.companies = companies;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public CompanyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.company_item, parent, false);
        CompanyHolder ch = new CompanyHolder(v);
        return ch;
    }

    @Override
    public void onBindViewHolder(CompanyHolder holder, int position) {
        holder.companyName.setText(companies.get(position).getName());
        holder.companyDescription.setText(companies.get(position).getDescription());
    }


    @Override
    public int getItemCount() {
        return companies.size();
    }


}
