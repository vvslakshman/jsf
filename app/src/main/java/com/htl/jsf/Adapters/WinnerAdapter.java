package com.htl.jsf.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.htl.jsf.Models.WinneListResponse;
import com.htl.jsf.R;

import java.util.List;

public class WinnerAdapter extends RecyclerView.Adapter<WinnerAdapter.Viewholder> {
    Context context;
    //    private List<WinnerModel> winnerModelList;
    private List<WinneListResponse.WinnerData> winnerModelList;

//    public WinnerAdapter(Context context, List<WinnerModel> winnerModelList) {
//        this.context = context;
//        this.winnerModelList = winnerModelList;
//    }

    public WinnerAdapter(Context context, List<WinneListResponse.WinnerData> generalInfoObject) {
        this.context = context;
        this.winnerModelList = generalInfoObject;
    }

//    public WinnerAdapter(WinnerActivity context, List<WinneListResponse.WinnerData> data) {
//    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.winner_card_view_layout, viewGroup, false);
        return new Viewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
//        WinnerModel myModel = winnerModelList.get(position);
        WinneListResponse.WinnerData myModel = winnerModelList.get(position);
        holder.winner_coupon_code.setText(myModel.getCoupon_code());
        holder.winner_name.setText(myModel.getName());
        holder.winner_date.setText(myModel.getDate());
        holder.winner_time.setText(myModel.getTime());
        holder.shop_name.setText(myModel.getShop());
        holder.winner_position.setText(myModel.getPosition());
//        Glide.with(context).load(myModel.ge).placeholder(R.drawable.profile_img).dontAnimate().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.image);


    }

    @Override
    public int getItemCount() {
        return winnerModelList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private ImageView winner_image;
        private TextView winner_coupon_code;
        private TextView winner_name;
        private TextView winner_date;
        private TextView winner_time, shop_name;
        private TextView winner_position;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            winner_image = itemView.findViewById(R.id.winner_image);
            winner_coupon_code = itemView.findViewById(R.id.winner_coupon_code);
            winner_name = itemView.findViewById(R.id.winner_name);
            winner_date = itemView.findViewById(R.id.winner_date);
            winner_time = itemView.findViewById(R.id.winner_time);
            shop_name = itemView.findViewById(R.id.shop_name);
            winner_position = itemView.findViewById(R.id.winner_position);

        }
    }
}
