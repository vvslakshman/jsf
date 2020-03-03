package com.htl.jsf.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.htl.jsf.Models.WinneListResponse;
import com.htl.jsf.R;
import com.htl.jsf.utils.Utils;

import java.util.List;

public class TodayWinerAdapter extends RecyclerView.Adapter<TodayWinerAdapter.Viewholder> {
    private Context context;
    private List<WinneListResponse.WinnerData> modelList;


    public TodayWinerAdapter(List<WinneListResponse.WinnerData> modelList, Context context) {
        this.context = context;
        this.modelList = modelList;
        Utils.loge(1, "TodayWinerAdapter", "" + modelList.size());
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_today_winner_layout, viewGroup, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        WinneListResponse.WinnerData myModel = modelList.get(position);
        holder.winner_today.setText(myModel.getName());
        holder.winner_today_place.setText(myModel.getCity());
        holder.winner_today_prize.setText(myModel.getPosition());

    }

    @Override
    public int getItemCount() {

////        if (modelList != null) {
//            if (modelList.size() > 3)
//                return 3;
////        }
//        return 0;
        return modelList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView winner_today;
        private TextView winner_today_place;
        private TextView winner_today_prize;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            winner_today = itemView.findViewById(R.id.winner_today);
            winner_today_place = itemView.findViewById(R.id.winner_today_place);
            winner_today_prize = itemView.findViewById(R.id.winner_today_prize);


        }
    }
}
