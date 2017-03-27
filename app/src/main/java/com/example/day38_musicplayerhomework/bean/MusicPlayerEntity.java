package com.example.day38_musicplayerhomework.bean;

import java.util.List;

/**
 * Created by NYR on 2016/10/19.
 */
public class MusicPlayerEntity {

    /**
     * errorCode : 22000
     * data : {"xcode":"aaaafbc89de27f47a46aafc3339c575b","songList":[{"queryId":"44805341","songId":44805341,"songName":"安和桥","artistId":"59859914","artistName":"宋冬野","albumId":71265988,"albumName":"安和桥北","songPicSmall":"http://musicdata.baidu.com/data2/pic/71265951/71265951.jpg","songPicBig":"http://musicdata.baidu.com/data2/pic/71265948/71265948.jpg","songPicRadio":"http://musicdata.baidu.com/data2/pic/71265945/71265945.jpg","lrcLink":"http://musicdata.baidu.com/data2/lrc/241648644/241648644.lrc","version":"","copyType":0,"time":251,"linkCode":22000,"songLink":"http://yinyueshiting.baidu.com/data2/music/71272858/44805341104400128.mp3?xcode=aaaafbc89de27f47d7984b79751f9d39","showLink":"http://yinyueshiting.baidu.com/data2/music/71272858/44805341104400128.mp3?xcode=aaaafbc89de27f47d7984b79751f9d39","format":"mp3","rate":128,"size":4021056,"relateStatus":"0","resourceType":"0","source":"web"}]}
     */

    private int errorCode;
    /**
     * xcode : aaaafbc89de27f47a46aafc3339c575b
     * songList : [{"queryId":"44805341","songId":44805341,"songName":"安和桥","artistId":"59859914","artistName":"宋冬野","albumId":71265988,"albumName":"安和桥北","songPicSmall":"http://musicdata.baidu.com/data2/pic/71265951/71265951.jpg","songPicBig":"http://musicdata.baidu.com/data2/pic/71265948/71265948.jpg","songPicRadio":"http://musicdata.baidu.com/data2/pic/71265945/71265945.jpg","lrcLink":"http://musicdata.baidu.com/data2/lrc/241648644/241648644.lrc","version":"","copyType":0,"time":251,"linkCode":22000,"songLink":"http://yinyueshiting.baidu.com/data2/music/71272858/44805341104400128.mp3?xcode=aaaafbc89de27f47d7984b79751f9d39","showLink":"http://yinyueshiting.baidu.com/data2/music/71272858/44805341104400128.mp3?xcode=aaaafbc89de27f47d7984b79751f9d39","format":"mp3","rate":128,"size":4021056,"relateStatus":"0","resourceType":"0","source":"web"}]
     */

    private DataBean data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String xcode;
        /**
         * queryId : 44805341
         * songId : 44805341
         * songName : 安和桥
         * artistId : 59859914
         * artistName : 宋冬野
         * albumId : 71265988
         * albumName : 安和桥北
         * songPicSmall : http://musicdata.baidu.com/data2/pic/71265951/71265951.jpg
         * songPicBig : http://musicdata.baidu.com/data2/pic/71265948/71265948.jpg
         * songPicRadio : http://musicdata.baidu.com/data2/pic/71265945/71265945.jpg
         * lrcLink : http://musicdata.baidu.com/data2/lrc/241648644/241648644.lrc
         * version :
         * copyType : 0
         * time : 251
         * linkCode : 22000
         * songLink : http://yinyueshiting.baidu.com/data2/music/71272858/44805341104400128.mp3?xcode=aaaafbc89de27f47d7984b79751f9d39
         * showLink : http://yinyueshiting.baidu.com/data2/music/71272858/44805341104400128.mp3?xcode=aaaafbc89de27f47d7984b79751f9d39
         * format : mp3
         * rate : 128
         * size : 4021056
         * relateStatus : 0
         * resourceType : 0
         * source : web
         */

        private List<SongListBean> songList;

        public String getXcode() {
            return xcode;
        }

        public void setXcode(String xcode) {
            this.xcode = xcode;
        }

        public List<SongListBean> getSongList() {
            return songList;
        }

        public void setSongList(List<SongListBean> songList) {
            this.songList = songList;
        }

        public static class SongListBean {
            private String queryId;
            private int songId;
            private String songName;
            private String artistId;
            private String artistName;
            private int albumId;
            private String albumName;
            private String songPicSmall;
            private String songPicBig;
            private String songPicRadio;
            private String lrcLink;
            private String version;
            private int copyType;
            private int time;
            private int linkCode;
            private String songLink;
            private String showLink;
            private String format;
            private int rate;
            private int size;
            private String relateStatus;
            private String resourceType;
            private String source;

            public String getQueryId() {
                return queryId;
            }

            public void setQueryId(String queryId) {
                this.queryId = queryId;
            }

            public int getSongId() {
                return songId;
            }

            public void setSongId(int songId) {
                this.songId = songId;
            }

            public String getSongName() {
                return songName;
            }

            public void setSongName(String songName) {
                this.songName = songName;
            }

            public String getArtistId() {
                return artistId;
            }

            public void setArtistId(String artistId) {
                this.artistId = artistId;
            }

            public String getArtistName() {
                return artistName;
            }

            public void setArtistName(String artistName) {
                this.artistName = artistName;
            }

            public int getAlbumId() {
                return albumId;
            }

            public void setAlbumId(int albumId) {
                this.albumId = albumId;
            }

            public String getAlbumName() {
                return albumName;
            }

            public void setAlbumName(String albumName) {
                this.albumName = albumName;
            }

            public String getSongPicSmall() {
                return songPicSmall;
            }

            public void setSongPicSmall(String songPicSmall) {
                this.songPicSmall = songPicSmall;
            }

            public String getSongPicBig() {
                return songPicBig;
            }

            public void setSongPicBig(String songPicBig) {
                this.songPicBig = songPicBig;
            }

            public String getSongPicRadio() {
                return songPicRadio;
            }

            public void setSongPicRadio(String songPicRadio) {
                this.songPicRadio = songPicRadio;
            }

            public String getLrcLink() {
                return lrcLink;
            }

            public void setLrcLink(String lrcLink) {
                this.lrcLink = lrcLink;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public int getCopyType() {
                return copyType;
            }

            public void setCopyType(int copyType) {
                this.copyType = copyType;
            }

            public int getTime() {
                return time;
            }

            public void setTime(int time) {
                this.time = time;
            }

            public int getLinkCode() {
                return linkCode;
            }

            public void setLinkCode(int linkCode) {
                this.linkCode = linkCode;
            }

            public String getSongLink() {
                return songLink;
            }

            public void setSongLink(String songLink) {
                this.songLink = songLink;
            }

            public String getShowLink() {
                return showLink;
            }

            public void setShowLink(String showLink) {
                this.showLink = showLink;
            }

            public String getFormat() {
                return format;
            }

            public void setFormat(String format) {
                this.format = format;
            }

            public int getRate() {
                return rate;
            }

            public void setRate(int rate) {
                this.rate = rate;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public String getRelateStatus() {
                return relateStatus;
            }

            public void setRelateStatus(String relateStatus) {
                this.relateStatus = relateStatus;
            }

            public String getResourceType() {
                return resourceType;
            }

            public void setResourceType(String resourceType) {
                this.resourceType = resourceType;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }
        }
    }
}
