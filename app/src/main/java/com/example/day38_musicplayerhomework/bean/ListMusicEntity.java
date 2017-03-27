package com.example.day38_musicplayerhomework.bean;

import java.util.List;

/**
 * Created by NYR on 2016/10/19.
 */
public class ListMusicEntity {

    /**
     * error_code : 22000
     * result : {"channel":"秋日私语","channelid":null,"ch_name":"public_tuijian_autumn","artistid":null,"avatar":null,"count":null,"songlist":[{"songid":"14950794","title":"Star In You","artist":"曲婉婷","thumb":"http://qukufile2.qianqian.com/data2/pic/124528851/124528851.jpg","method":0,"flow":0,"artist_id":"10490649","all_artist_id":"10490649","resource_type":"0","havehigh":2,"charge":0,"all_rate":"24,64,128,192,256,320,flac"},{"songid":"7560139","title":"Sunday Morning","artist":"Nico,The Velvet Underground","thumb":"http://b.hiphotos.baidu.com/ting/abpic/item/1b4c510fd9f9d72ab2d05981d52a2834359bbbf2.jpg","method":0,"flow":0,"artist_id":"42585","all_artist_id":"42585,48197","resource_type":"0","havehigh":2,"charge":0,"all_rate":"24,64,128,192,256,320,flac"},{"songid":"1271193","title":"好久不见","artist":"陈奕迅","thumb":"http://qukufile2.qianqian.com/data2/pic/115994935/115994935.jpg","method":0,"flow":0,"artist_id":"87","all_artist_id":"87","resource_type":"0","havehigh":2,"charge":0,"all_rate":"24,64,128,160,192,256,320,flac"},{"songid":"293126","title":"九月","artist":"许巍","thumb":"http://qukufile2.qianqian.com/data2/pic/6b33ed4b2da831d4278b5557c2ced95a/262023146/262023146.jpg","method":0,"flow":0,"artist_id":"371","all_artist_id":"371","resource_type":"0","havehigh":2,"charge":0,"all_rate":"24,64,128,192,256,320,flac"},{"songid":"9069877","title":"被遗忘的","artist":"杨宗纬","thumb":"http://qukufile2.qianqian.com/data2/pic/115485699/115485699.jpg","method":0,"flow":0,"artist_id":"1009","all_artist_id":"1009","resource_type":"0","havehigh":2,"charge":0,"all_rate":"24,64,128,192,256,320,flac"},{"songid":"619936","title":"咖啡","artist":"张学友","thumb":"http://qukufile2.qianqian.com/data2/pic/117471783/117471783.jpg","method":0,"flow":0,"artist_id":"23","all_artist_id":"23","resource_type":"0","havehigh":2,"charge":0,"all_rate":"24,64,128,192,256,320,flac"},{"songid":"65598295","title":"浮光掠影","artist":"阿肆","thumb":"http://qukufile2.qianqian.com/data2/pic/115434823/115434823.jpg","method":0,"flow":0,"artist_id":"28307135","all_artist_id":"28307135","resource_type":"0","havehigh":0,"charge":0,"all_rate":"64,128,192,256"},{"songid":"238034","title":"消失","artist":"范晓萱","thumb":"http://qukufile2.qianqian.com/data2/pic/fb6e25a0b89dbcc5f5f59d18264eae53/88351809/88351809.jpg","method":0,"flow":0,"artist_id":"304","all_artist_id":"304","resource_type":"0","havehigh":2,"charge":0,"all_rate":"24,64,128,160,192,256,320,flac"},{"songid":"1154713","title":"Life For Rent","artist":"Dido","thumb":"http://qukufile2.qianqian.com/data2/pic/117822598/117822598.jpg","method":0,"flow":0,"artist_id":"649","all_artist_id":"649","resource_type":"0","havehigh":2,"charge":0,"all_rate":"24,64,128,192,256,320,flac"},{"songid":"7280177","title":"离人","artist":"林志炫","thumb":"http://qukufile2.qianqian.com/data2/pic/7743bfc677ef2e2d441c0549d4dc07b5/115994013/115994013.jpg","method":0,"flow":0,"artist_id":"313","all_artist_id":"313","resource_type":"0","havehigh":2,"charge":0,"all_rate":"24,64,128,192,256,320,flac"},{"songid":null,"title":null,"artist":null,"thumb":"","method":0,"flow":0,"artist_id":null,"all_artist_id":null,"resource_type":null,"havehigh":0,"charge":0,"all_rate":""}]}
     */

    private int error_code;
    /**
     * channel : 秋日私语
     * channelid : null
     * ch_name : public_tuijian_autumn
     * artistid : null
     * avatar : null
     * count : null
     * songlist : [{"songid":"14950794","title":"Star In You","artist":"曲婉婷","thumb":"http://qukufile2.qianqian.com/data2/pic/124528851/124528851.jpg","method":0,"flow":0,"artist_id":"10490649","all_artist_id":"10490649","resource_type":"0","havehigh":2,"charge":0,"all_rate":"24,64,128,192,256,320,flac"},{"songid":"7560139","title":"Sunday Morning","artist":"Nico,The Velvet Underground","thumb":"http://b.hiphotos.baidu.com/ting/abpic/item/1b4c510fd9f9d72ab2d05981d52a2834359bbbf2.jpg","method":0,"flow":0,"artist_id":"42585","all_artist_id":"42585,48197","resource_type":"0","havehigh":2,"charge":0,"all_rate":"24,64,128,192,256,320,flac"},{"songid":"1271193","title":"好久不见","artist":"陈奕迅","thumb":"http://qukufile2.qianqian.com/data2/pic/115994935/115994935.jpg","method":0,"flow":0,"artist_id":"87","all_artist_id":"87","resource_type":"0","havehigh":2,"charge":0,"all_rate":"24,64,128,160,192,256,320,flac"},{"songid":"293126","title":"九月","artist":"许巍","thumb":"http://qukufile2.qianqian.com/data2/pic/6b33ed4b2da831d4278b5557c2ced95a/262023146/262023146.jpg","method":0,"flow":0,"artist_id":"371","all_artist_id":"371","resource_type":"0","havehigh":2,"charge":0,"all_rate":"24,64,128,192,256,320,flac"},{"songid":"9069877","title":"被遗忘的","artist":"杨宗纬","thumb":"http://qukufile2.qianqian.com/data2/pic/115485699/115485699.jpg","method":0,"flow":0,"artist_id":"1009","all_artist_id":"1009","resource_type":"0","havehigh":2,"charge":0,"all_rate":"24,64,128,192,256,320,flac"},{"songid":"619936","title":"咖啡","artist":"张学友","thumb":"http://qukufile2.qianqian.com/data2/pic/117471783/117471783.jpg","method":0,"flow":0,"artist_id":"23","all_artist_id":"23","resource_type":"0","havehigh":2,"charge":0,"all_rate":"24,64,128,192,256,320,flac"},{"songid":"65598295","title":"浮光掠影","artist":"阿肆","thumb":"http://qukufile2.qianqian.com/data2/pic/115434823/115434823.jpg","method":0,"flow":0,"artist_id":"28307135","all_artist_id":"28307135","resource_type":"0","havehigh":0,"charge":0,"all_rate":"64,128,192,256"},{"songid":"238034","title":"消失","artist":"范晓萱","thumb":"http://qukufile2.qianqian.com/data2/pic/fb6e25a0b89dbcc5f5f59d18264eae53/88351809/88351809.jpg","method":0,"flow":0,"artist_id":"304","all_artist_id":"304","resource_type":"0","havehigh":2,"charge":0,"all_rate":"24,64,128,160,192,256,320,flac"},{"songid":"1154713","title":"Life For Rent","artist":"Dido","thumb":"http://qukufile2.qianqian.com/data2/pic/117822598/117822598.jpg","method":0,"flow":0,"artist_id":"649","all_artist_id":"649","resource_type":"0","havehigh":2,"charge":0,"all_rate":"24,64,128,192,256,320,flac"},{"songid":"7280177","title":"离人","artist":"林志炫","thumb":"http://qukufile2.qianqian.com/data2/pic/7743bfc677ef2e2d441c0549d4dc07b5/115994013/115994013.jpg","method":0,"flow":0,"artist_id":"313","all_artist_id":"313","resource_type":"0","havehigh":2,"charge":0,"all_rate":"24,64,128,192,256,320,flac"},{"songid":null,"title":null,"artist":null,"thumb":"","method":0,"flow":0,"artist_id":null,"all_artist_id":null,"resource_type":null,"havehigh":0,"charge":0,"all_rate":""}]
     */

    private ResultBean result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private String channel;
        private Object channelid;
        private String ch_name;
        private Object artistid;
        private Object avatar;
        private Object count;
        /**
         * songid : 14950794
         * title : Star In You
         * artist : 曲婉婷
         * thumb : http://qukufile2.qianqian.com/data2/pic/124528851/124528851.jpg
         * method : 0
         * flow : 0
         * artist_id : 10490649
         * all_artist_id : 10490649
         * resource_type : 0
         * havehigh : 2
         * charge : 0
         * all_rate : 24,64,128,192,256,320,flac
         */

        private List<SonglistBean> songlist;

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public Object getChannelid() {
            return channelid;
        }

        public void setChannelid(Object channelid) {
            this.channelid = channelid;
        }

        public String getCh_name() {
            return ch_name;
        }

        public void setCh_name(String ch_name) {
            this.ch_name = ch_name;
        }

        public Object getArtistid() {
            return artistid;
        }

        public void setArtistid(Object artistid) {
            this.artistid = artistid;
        }

        public Object getAvatar() {
            return avatar;
        }

        public void setAvatar(Object avatar) {
            this.avatar = avatar;
        }

        public Object getCount() {
            return count;
        }

        public void setCount(Object count) {
            this.count = count;
        }

        public List<SonglistBean> getSonglist() {
            return songlist;
        }

        public void setSonglist(List<SonglistBean> songlist) {
            this.songlist = songlist;
        }

        public static class SonglistBean {
            private String songid;
            private String title;
            private String artist;
            private String thumb;
            private int method;
            private int flow;
            private String artist_id;
            private String all_artist_id;
            private String resource_type;
            private int havehigh;
            private int charge;
            private String all_rate;

            public String getSongid() {
                return songid;
            }

            public void setSongid(String songid) {
                this.songid = songid;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getArtist() {
                return artist;
            }

            public void setArtist(String artist) {
                this.artist = artist;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public int getMethod() {
                return method;
            }

            public void setMethod(int method) {
                this.method = method;
            }

            public int getFlow() {
                return flow;
            }

            public void setFlow(int flow) {
                this.flow = flow;
            }

            public String getArtist_id() {
                return artist_id;
            }

            public void setArtist_id(String artist_id) {
                this.artist_id = artist_id;
            }

            public String getAll_artist_id() {
                return all_artist_id;
            }

            public void setAll_artist_id(String all_artist_id) {
                this.all_artist_id = all_artist_id;
            }

            public String getResource_type() {
                return resource_type;
            }

            public void setResource_type(String resource_type) {
                this.resource_type = resource_type;
            }

            public int getHavehigh() {
                return havehigh;
            }

            public void setHavehigh(int havehigh) {
                this.havehigh = havehigh;
            }

            public int getCharge() {
                return charge;
            }

            public void setCharge(int charge) {
                this.charge = charge;
            }

            public String getAll_rate() {
                return all_rate;
            }

            public void setAll_rate(String all_rate) {
                this.all_rate = all_rate;
            }
        }
    }
}
