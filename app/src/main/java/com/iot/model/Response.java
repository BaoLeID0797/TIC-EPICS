package com.iot.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Response {
    @SerializedName("channel")
    private Channel channel;
    @SerializedName("feeds")
    private List<Feeds> feeds;

    public Response() {
        this.feeds = new ArrayList<>();
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public List<Feeds> getFeeds() {
        return feeds;
    }

    public void setFeeds(List<Feeds> feeds) {
        this.feeds = feeds;
    }
}
