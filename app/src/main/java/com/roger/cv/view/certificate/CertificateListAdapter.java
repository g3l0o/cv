package com.roger.cv.view.certificate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.roger.cv.R;
import com.roger.cv.databinding.CertificateItemBinding;
import com.roger.cv.model.Certificate;

import java.util.ArrayList;
import java.util.List;

public class CertificateListAdapter extends RecyclerView.Adapter<CertificateListAdapter.CertificateViewHolder>{

    private ArrayList<Certificate> certificateList;

    public CertificateListAdapter(ArrayList<Certificate> certificateList) {
        this.certificateList = certificateList;
    }

    public void updateCertificateList(List<Certificate> certificates){
        certificateList.clear();
        certificateList.addAll(certificates);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CertificateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CertificateItemBinding view = DataBindingUtil.inflate(inflater, R.layout.certificate_item, parent, false);
        return new CertificateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CertificateViewHolder holder, int position) {
        Certificate certificate = certificateList.get(position);
        holder.itemView.setCertificate(certificate);
        holder.itemView.textCertificateNumber.setVisibility(certificate.getCertificateNumber().isEmpty() ? View.GONE : View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return certificateList.size();
    }

    class CertificateViewHolder extends RecyclerView.ViewHolder{

        public CertificateItemBinding itemView;

        public CertificateViewHolder(@NonNull CertificateItemBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }

}
