package com.example.day38_musicplayerhomework.uri;

/**
 * Created by NYR on 2016/10/19.
 */
public class AppInterfance {
    public static String CHANNEL_URL="http://tingapi.ting.baidu.com/v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.radio.getCategoryList&format=json";
    public static String LIST_URL="http://tingapi.ting.baidu.com/v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.radio.getChannelSong&format=json&pn=0&rn=10&channelname=%s";
    public static String MUSIC_URL="http://ting.baidu.com/data/music/links?songIds=%s";
}
