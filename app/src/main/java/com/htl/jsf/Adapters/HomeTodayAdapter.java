package com.htl.jsf.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.htl.jsf.Models.HomeTodayModel;
import com.htl.jsf.R;

import java.util.List;

public class HomeTodayAdapter extends RecyclerView.Adapter<HomeTodayAdapter.Viewholder> {
    private Context context;
    private List<HomeTodayModel> modelList;



    public HomeTodayAdapter(Context context, List<HomeTodayModel> modelList) {
        this.context = context;
        this.modelList = modelList;
    }


    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_today_winner_layout, viewGroup, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        HomeTodayModel myModel = modelList.get(position);
        holder.winner_today.setText(myModel.getWinnername());
        holder.winner_today_place.setText(myModel.getWinnerlocation());
        holder.winner_today_prize.setText(myModel.getWinnerprize());

    }

    @Override
    public int getItemCount() {

        if (modelList != null) {
            if (modelList.size() > 3)
                return 3;
        }
        return 0;
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
