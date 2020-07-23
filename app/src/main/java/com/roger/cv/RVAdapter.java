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
import com.roger.cv.model.Job;

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
            companyLogo = (ImageButton) itemView.findViewById(R.id.imagebutton_company_logo);
            companyName = (TextView) itemView.findViewById(R.id.text_company_name);
            companyDescription = (TextView) itemView.findViewById(R.id.text_position);
        }
    }

    final long ONE_MEGABYTE = 1024 * 1024;
    List<Job> companies;
    ViewGroup parent;


    public RVAdapter(List<Job> companies){
        this.companies = companies;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public CompanyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_item, parent, false);
        CompanyHolder ch = new CompanyHolder(v);
        return ch;
    }

    @Override
    public void onBindViewHolder(final CompanyHolder holder, int position) {
        Job job = companies.get(position);
        holder.companyName.setText(job.getName());
        holder.companyDescription.setText(job.getDescription());

        if(!job.getLogo().isEmpty()){
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReferenceFromUrl("gs://roger-cv.appspot.com").child(job.getLogo());
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
