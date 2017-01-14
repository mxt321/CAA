package net.bjyfkj.caa.entity;

import java.util.List;

/**
 * Created by YFKJ-2 on 2016/11/29.
 */

public class MyReleaseEntity {

    /**
     * status : 1
     * message : ok
     * data : {"in_progress":{"ads_id":"76","status":"1","play_count":"306","get_count":"157","use_count":"149","device_count":"1"},"history":[{"status":"4","ads_id":"74","time":"2016-11-27","type":"0"},{"status":"4","ads_id":"74","time":"2016-11-27","type":"1"},{"status":"4","ads_id":"74","time":"2016-11-27","type":"1"}]}
     */

    private int status;
    private String message;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * in_progress : {"ads_id":"76","status":"1","play_count":"306","get_count":"157","use_count":"149","device_count":"1"}
         * history : [{"status":"4","ads_id":"74","time":"2016-11-27","type":"0"},{"status":"4","ads_id":"74","time":"2016-11-27","type":"1"},{"status":"4","ads_id":"74","time":"2016-11-27","type":"1"}]
         */

        private InProgressBean in_progress;
        private List<HistoryBean> history;

        public InProgressBean getIn_progress() {
            return in_progress;
        }

        public void setIn_progress(InProgressBean in_progress) {
            this.in_progress = in_progress;
        }

        public List<HistoryBean> getHistory() {
            return history;
        }

        public void setHistory(List<HistoryBean> history) {
            this.history = history;
        }

        public static class InProgressBean {
            /**
             * ads_id : 76
             * status : 1
             * play_count : 306
             * get_count : 157
             * use_count : 149
             * device_count : 1
             */

            private String ads_id;
            private String status;
            private String play_count;
            private String get_count;
            private String use_count;
            private String device_count;

            public String getAds_id() {
                return ads_id;
            }

            public void setAds_id(String ads_id) {
                this.ads_id = ads_id;
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

            public String getDevice_count() {
                return device_count;
            }

            public void setDevice_count(String device_count) {
                this.device_count = device_count;
            }
        }

        public static class HistoryBean {
            /**
             * status : 4
             * ads_id : 74
             * time : 2016-11-27
             * type : 0
             */

            private String status;
            private String ads_id;
            private String time;
            private String type;

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getAds_id() {
                return ads_id;
            }

            public void setAds_id(String ads_id) {
                this.ads_id = ads_id;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }


}
