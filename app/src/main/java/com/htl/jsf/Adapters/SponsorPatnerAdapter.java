package com.htl.jsf.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.htl.jsf.Activities.SponsorActivity;
import com.htl.jsf.Models.SponserPartnerReponse;
import com.htl.jsf.Models.SponsorModel;
import com.htl.jsf.R;

import java.util.List;

public class SponsorPatnerAdapter extends RecyclerView.Adapter<SponsorPatnerAdapter.Viewholder> {
    Context context;
    private List<SponserPartnerReponse.SponserData> sponsorModelList;

//    public SponsorPatnerAdapter(Context context, List<SponsorModel> sponsorModelList) {
//        this.context = context;
//        this.sponsorModelList = sponsorModelList;
//    }

    public SponsorPatnerAdapter(SponsorActivity context, List<SponserPartnerReponse.SponserData> data) {
        this.context=context;
        this.sponsorModelList=data;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.sponsor_card_view_layout, viewGroup, false);
        return new Viewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        SponserPartnerReponse.SponserData myModel = sponsorModelList.get(position);
        holder.sponsor_name.setText(myModel.getSponserName());
        holder.company_name.setText(myModel.getSponserCompany());
        holder.sponsor_mobile.setText(myModel.getSponserMobile());
        holder.sponsor_city.setText(myModel.getCity());
        Glide.with(context).load(myModel.getLogo()).placeholder(R.drawable.logo).dontAnimate().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.image);

    }


    @Override
    public int getItemCount() {
        return sponsorModelList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView sponsor_name;
        private TextView company_name;
        private TextView sponsor_mobile;
        private TextView sponsor_city;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            sponsor_name = itemView.findViewById(R.id.sponsor_name);
            company_name = itemView.findViewById(R.id.company_name);
            sponsor_mobile = itemView.findViewById(R.id.sponsor_mobile);
            sponsor_city = itemView.findViewById(R.id.sponsor_city);
            image=itemView.findViewById(R.id.image);


        }
    }
}
