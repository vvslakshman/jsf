package com.htl.jsf.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.htl.jsf.Models.RefferalResponse;
import com.htl.jsf.R;

import java.util.List;

public class RefferalAdapter extends RecyclerView.Adapter<RefferalAdapter.Viewholder> {

    Context context;
    private List<RefferalResponse.RefferalData> refferalModelList;

    public RefferalAdapter(Context context, List<RefferalResponse.RefferalData> refferalModelList) {
        this.context = context;
        this.refferalModelList = refferalModelList;
    }


    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.refferal_card_view_layout, viewGroup, false);
        return new Viewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        RefferalResponse.RefferalData model = refferalModelList.get(position);
        holder.reffer_code.setText(/*"Coupon Code: " + " "+*/model.getCouponCode());
        holder.tv_city.setText("City: " + model.getCity());
        holder.tv_shop.setText("Shop: " + model.getShop());
        holder.reffer_applied_code_time.setText(" "+model.getDatetime());
//        Glide.with(context).load(model.get).placeholder(R.drawable.profile_img).dontAnimate().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.image);

    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return refferalModelList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        private TextView reffer_code;
        private TextView tv_city, tv_shop;
        private TextView reffer_applied_code_time;
        private ImageView winner_image;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            reffer_code = itemView.findViewById(R.id.reffer_code);
            tv_city = itemView.findViewById(R.id.tv_city);
            tv_shop = itemView.findViewById(R.id.tv_shop);
            reffer_applied_code_time = itemView.findViewById(R.id.reffer_applied_code_time);
            winner_image = itemView.findViewById(R.id.winner_image);

        }
    }
}
