package com.htl.jsf.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.htl.jsf.Models.CityDataResponse;
import com.htl.jsf.R;

import java.util.List;

public class CityListAdatper extends RecyclerView.Adapter<CityListAdatper.ViewHolder> {

    private Context context;
    private List<CityDataResponse> list;
    private onCityItemClick onCityItemClick;

    public CityListAdatper(Context eventActivity, List<CityDataResponse> city_list, CityListAdatper.onCityItemClick onCityItemClick) {
        this.context = eventActivity;
        this.list = city_list;
        this.onCityItemClick = onCityItemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewholder = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cityitem_row, parent, false);
        return new ViewHolder(viewholder);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.rdb_city_name.setText(list.get(position).getCity());
        holder.rdb_city_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCityItemClick.cityItem(position, list.get(position).getCity_id(), list.get(position).getCity());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private RadioButton rdb_city_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rdb_city_name = itemView.findViewById(R.id.rdb_city_name);
        }
    }

    public interface onCityItemClick {
        void cityItem(int posi, String city_id, String city_name);
    }
}
