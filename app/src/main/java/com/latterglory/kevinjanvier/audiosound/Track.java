package com.latterglory.kevinjanvier.audiosound;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kevinjanvier on 18/05/2016.
 */
public class Track {
    @SerializedName("title")
    private String mTitle;

    @SerializedName("id")
    private int mID;

    @SerializedName("stream_url")
    private String mStreamURL;

    @SerializedName("artwork_url")
    private String mArtworkURL;


    @SerializedName("comment_count")
    private String comment_count;


    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    @SerializedName("user_id")
    public String mUser_id;

    @SerializedName("label_name")
    public String mlabel_name;

    @SerializedName("permalink")
    public String mpermalink;

    @SerializedName("username")
    public String mUsername;

//    Avatar
    @SerializedName("avatar_url")
    public String mAvatar_url;


    public String getmAvatar_url() {
        return mAvatar_url;
    }

    public void setmAvatar_url(String mAvatar_url) {
        this.mAvatar_url = mAvatar_url;
    }

    public String getmUsername() {
        return mUsername;
    }

    public void setmUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getMpermalink() {
        return mpermalink;
    }

    public void setMpermalink(String mpermalink) {
        this.mpermalink = mpermalink;
    }

    public String getMlabel_name() {
        return mlabel_name;
    }

    public void setMlabel_name(String mlabel_name) {
        this.mlabel_name = mlabel_name;
    }

    public String getmUser_id() {
        return mUser_id;
    }

    public void setmUser_id(String mUser_id) {
        this.mUser_id = mUser_id;
    }

    public String getTitle() {
        return mTitle;
    }

    public int getID() {
        return mID;
    }

    public String getStreamURL() {
        return mStreamURL;
    }

    public String getArtworkURL() {
        return mArtworkURL;
    }


}
