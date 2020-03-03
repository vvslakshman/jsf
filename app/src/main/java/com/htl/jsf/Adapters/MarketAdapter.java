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
import com.htl.jsf.Models.ShopsListResponse;
import com.htl.jsf.R;

import java.util.List;

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.Viewholder> {
    private Context context;
    private List<ShopsListResponse.ShapsDataResponse> marketModelList;


    public MarketAdapter(List<ShopsListResponse.ShapsDataResponse> data, Context shopsMarketActivity) {
        this.context = shopsMarketActivity;
        this.marketModelList = data;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.market_card_view_layout, viewGroup, false);
        return new Viewholder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        ShopsListResponse.ShapsDataResponse marketModel = marketModelList.get(position);
        holder.shop_name.setText(marketModel.getShopName());
        holder.owner_name.setText(marketModel.getShopOwner());
        holder.shop_number.setText(marketModel.getShopOwnerMobile());
        holder.shop_location.setText(marketModel.getShopAddress());
        Glide.with(context).load(marketModel.getShopImage()).placeholder(R.drawable.logo).dontAnimate().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.image);


    }


    @Override
    public int getItemCount() {
        return marketModelList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView shop_name;
        private TextView owner_name;
        private TextView shop_number;
        private TextView shop_location;
        private ImageView image;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            shop_name = itemView.findViewById(R.id.shop_name);
            owner_name = itemView.findViewById(R.id.owner_name);
            shop_number = itemView.findViewById(R.id.shop_number);
            shop_location = itemView.findViewById(R.id.shop_location);
            image=itemView.findViewById(R.id.image);

        }
    }


}
