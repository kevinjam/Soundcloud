package com.latterglory.kevinjanvier.audiosound;

import android.app.ProgressDialog;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private List<Track> mListItems;
    private TrackAdapter mtrackadapter;
    private TextView mSelectedTrackTitle;
    private ImageView mSelectedTrackImage;
    private MediaPlayer mMediaPlayer;
    private ImageView mPlayerControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v("ArrayList", " List");
        mListItems = new ArrayList<Track>();
        final ListView listView = (ListView) findViewById(R.id.track_list_view);
        mtrackadapter = new TrackAdapter(this, mListItems);
        Log.v("List----" + mListItems, "List Item");
        listView.setAdapter(mtrackadapter);


//Media Play at the Bottom
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
//                When the Media is Playing I cna still pause it
               // mMediaPlayer.start();
                togglePlayPause();

            }
        });
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mPlayerControl.setImageResource(R.drawable.ic_fast_rewind_black_24dp);
            }
        });
//
        mSelectedTrackImage = (ImageView) findViewById(R.id.selected_track_image);
        mSelectedTrackTitle = (TextView) findViewById(R.id.selected_track_title);
        mPlayerControl = (ImageView) findViewById(R.id.player_control);
//        Once a user Click
        mPlayerControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePlayPause();

            }
        });
//        OnClick the Item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Track track = mListItems.get(position);
                mSelectedTrackTitle.setText(track.getTitle());
                Log.v("Title=== " +track.getTitle(), "The Title");
                Glide.with(MainActivity.this).load(track.getArtworkURL()).placeholder(R.drawable.ic_audiotrack_white_36dp).into(mSelectedTrackImage);

                if (mMediaPlayer.isPlaying()){
                    mMediaPlayer.stop();
                    mMediaPlayer.reset();

                }
                try {
                    Log.v("Media" +Config.CLIENT_ID.toString(), "ClientId");
                    mMediaPlayer.setDataSource(track.getStreamURL() + "?client_id=" +Config.CLIENT_ID);
                    Log.v("Prepare on the Crash", " ");
                    //mMediaPlayer.prepare();
                    mMediaPlayer.prepareAsync();
                }catch (IOException e){
                    e.printStackTrace();

                }
            }
        });

        final ProgressDialog progressDialog;
        progressDialog = ProgressDialog.show(MainActivity.this, "Fect","Wait", false, false);
        Log.v("Start----","=======");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TrackInterface trackInterface = retrofit.create(TrackInterface.class);
        Call<List<Track>> call = trackInterface.getRecentTracks(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));

        call.enqueue(new Callback<List<Track>>() {
            @Override
            public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {
                progressDialog.dismiss();
                int trackplay = response.code();
                Log.v("Succss", "OK");
                List list = response.body();
                // Log.v("User TiTle ---" + response.body().get(1).getTitle(), "Title");
                //   Log.v("----ID----" +response.body().get(0).getID(), "ID");
                loadTracks(list);
            }

            @Override
            public void onFailure(Call<List<Track>> call, Throwable t) {
                Log.v("Error", t.getMessage());

            }
        });
    }
//    On Pause

    private void togglePlayPause() {

        if (mMediaPlayer.isPlaying()) {
            Log.v("Start", "Read");
            //mMediaPlayer.start();
            Log.v("Pause","Just Pause");
            mMediaPlayer.pause();

            mPlayerControl.setImageResource(R.drawable.ic_fast_rewind_black_24dp);
        }else {
            mMediaPlayer.start();
            mPlayerControl.setImageResource(R.drawable.ic_pause_black_24dp);
        }
    }
//Load the tracks
    private void loadTracks(List list) {
        mListItems.clear();
        mListItems.addAll(list);
        mtrackadapter.notifyDataSetChanged();
    }
//OnDestroy

    @Override
    protected void onDestroy() {
        Log.v("On Destroy===", "Destroy==");
        super.onDestroy();
        if (mMediaPlayer != null)
            if (mMediaPlayer.isPlaying())
                mMediaPlayer.stop();
    }

}
