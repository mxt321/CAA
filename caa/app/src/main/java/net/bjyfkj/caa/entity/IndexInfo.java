package net.bjyfkj.caa.entity;

/**
 * Created by YFKJ-1 on 2016/12/17.
 */

public class IndexInfo {

    /**
     * data : {"ads_id":null,"device_count":"0","status":null,"play_count":null,"get_count":null,"use_count":null}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * ads_id : null
         * device_count : 0
         * status : null
         * play_count : null
         * get_count : null
         * use_count : null
         */

        private String ads_id;
        private String device_count;
        private String status;
        private String play_count;
        private String get_count;
        private String use_count;

        public String getAds_id() {
            return ads_id;
        }

        public void setAds_id(String ads_id) {
            this.ads_id = ads_id;
        }

        public String getDevice_count() {
            return device_count;
        }

        public void setDevice_count(String device_count) {
            this.device_count = device_count;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPlay_count() {
            return play_count;
        }

        public void setPlay_count(String play_count) {
            this.play_count = play_count;
        }

        public String getGet_count() {
            return get_count;
        }

        public void setGet_count(String get_count) {
            this.get_count = get_count;
        }

        public String getUse_count() {
            return use_count;
        }

        public void setUse_count(String use_count) {
            this.use_count = use_count;
        }
    }
}
