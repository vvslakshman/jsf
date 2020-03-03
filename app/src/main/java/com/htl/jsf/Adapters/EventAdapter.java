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
import com.htl.jsf.Models.EventModel;
import com.htl.jsf.Models.MasterReponse;
import com.htl.jsf.R;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.Viewholder> {
    Context context;
    List<EventModel> eventModelList;
    private List<MasterReponse.EvenListResponse> list;

    //    public EventAdapter(Context context, List<EventModel> eventModelList) {
//        this.context = context;
//        this.eventModelList = eventModelList;
//    }
    public EventAdapter(List<MasterReponse.EvenListResponse> eventModelList, Context context) {
        this.context = context;
        this.list = eventModelList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.event_card_view_layout, viewGroup, false);
        return new Viewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        MasterReponse.EvenListResponse myModel = list.get(position);
        holder.txt_music_night.setText(myModel.getEventName());
        holder.event_location.setText(myModel.getEventPlace());
        holder.name_artist.setText(myModel.getEventPerformer());
        holder.real_time.setText(myModel.getEventTime());
        holder.real_contact.setText(myModel.getEventManageMobile());
//        holder.about_event.setText(myModel.getEventName());
        holder.music_night_with.setText("Musical Night With " + myModel.getEventPerformer());
        Glide.with(context).load(myModel.getEventImage()).placeholder(R.drawable.event_banner_ic).dontAnimate().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.show_image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        private TextView txt_music_night;
        private TextView event_location;
        private TextView name_artist;
        private TextView real_time;
        private TextView real_contact;
        private TextView about_event;
        private TextView music_night_with;
        private ImageView show_image;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            txt_music_night = itemView.findViewById(R.id.txt_music_night);
            event_location = itemView.findViewById(R.id.event_location);
            name_artist = itemView.findViewById(R.id.name_artist);
            real_time = itemView.findViewById(R.id.real_time);
            real_contact = itemView.findViewById(R.id.real_contact);
            about_event = itemView.findViewById(R.id.about_event);
            music_night_with = itemView.findViewById(R.id.music_night_with);
            show_image = itemView.findViewById(R.id.show_image);

        }
    }
}
