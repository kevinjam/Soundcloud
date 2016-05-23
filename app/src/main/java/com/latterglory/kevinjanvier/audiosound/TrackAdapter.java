package com.latterglory.kevinjanvier.audiosound;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by kevinjanvier on 19/05/2016.
 */
public class TrackAdapter extends BaseAdapter {

    private Context context;
    private List<Track> mtrack;

    public TrackAdapter(Context context, List<Track> mtrack){
        this.context = context;
        this.mtrack = mtrack;
    }
    @Override
    public int getCount() {
        return mtrack.size();
    }

    @Override
    public Track getItem(int position) {
        return mtrack.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Track track = getItem(position);

        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.track_list, parent, false);
            holder = new ViewHolder();
            holder.trackImageView = (ImageView) convertView.findViewById(R.id.track_image);
            holder.titleTextView = (TextView) convertView.findViewById(R.id.track_title);
            holder.username_txt = (TextView) convertView.findViewById(R.id.username_txt);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.titleTextView.setText(track.getTitle());
        holder.username_txt.setText(track.getMpermalink());
        Log.v("Username -----"  +track.getMlabel_name(), "");


        // Trigger the download of the URL asynchronously into the image view.
        Glide.with(context).load(track.getArtworkURL()).placeholder(R.drawable.ic_audiotrack_black_36dp).into(holder.trackImageView);

        return convertView;
    }
    static class ViewHolder {
        ImageView trackImageView;
        TextView titleTextView, username_txt;

    }
}
