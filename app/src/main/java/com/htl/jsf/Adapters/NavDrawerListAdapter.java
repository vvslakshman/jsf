package com.htl.jsf.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htl.jsf.Models.SideNavPojo;
import com.htl.jsf.R;

import java.util.List;


public class NavDrawerListAdapter extends BaseAdapter {
    Context context;
    List<SideNavPojo> drawerItemListing;

    public NavDrawerListAdapter(Context context, List<SideNavPojo> drawerItemListing) {
        this.context = context;
        this.drawerItemListing = drawerItemListing;
    }

    @Override
    public int getCount() {
        return drawerItemListing.size();
    }

    @Override
    public Object getItem(int position) {
        return drawerItemListing.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    class ViewHolder {
        TextView itemTextView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_menu_action_view, parent, false);
            viewHolder.itemTextView = convertView.findViewById(R.id.tv_items);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Drawable back_icon= context.getResources().getDrawable( R.drawable.ic_keyboard_arrow_right_black_24dp);

        viewHolder.itemTextView.setText(drawerItemListing.get(position).getTitle());
        viewHolder.itemTextView.setCompoundDrawablesWithIntrinsicBounds(drawerItemListing.get(position).getLogo(),0,R.drawable.ic_keyboard_arrow_right_black_24dp,0);
//        viewHolder.itemTextView.setCompoundDrawablesWithIntrinsicBounds(0,0,back_icon,null);
//        button.setCompoundDrawablesWithIntrinsicBounds( icon, null, null, null );
        return convertView;
    }
}
