package com.roger.cv;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayInputStream;
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

    final long ONE_MEGABYTE = 1024 * 1024;
    List<Company> companies;
    ViewGroup parent;


    public RVAdapter(List<Company> companies){
        this.companies = companies;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public CompanyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.company_item, parent, false);
        CompanyHolder ch = new CompanyHolder(v);
        return ch;
    }

    @Override
    public void onBindViewHolder(final CompanyHolder holder, int position) {
        Company company = companies.get(position);
        holder.companyName.setText(company.getName());
        holder.companyDescription.setText(company.getDescription());

        if(!company.getLogo().isEmpty()){
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReferenceFromUrl("gs://roger-cv.appspot.com").child(company.getLogo());
            storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    ByteArrayInputStream imageStream = new ByteArrayInputStream(bytes);
                    Bitmap bm = BitmapFactory.decodeStream(imageStream);
                    RoundedBitmapDrawable rid = RoundedBitmapDrawableFactory.create(parent.getContext().getResources(), bm);
                    rid.setCircular(true);
                    holder.companyLogo.setImageDrawable(rid);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return companies.size();
    }


}
