package net.bjyfkj.caa.entity;

import java.util.List;

/**
 * Created by YFKJ-2 on 2016/11/9.
 */

public class TimeEntity {


    /**
     * status : 1
     * message : ok
     * data : [{"date":"2016-11-04","timestamp":1478188800,"type":0},{"date":"2016-11-04","timestamp":1478188800,"type":1},"......"]
     */

    private int status;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * date : 2016-11-04
         * timestamp : 1478188800
         * type : 0
         */


        private String date;
        private int timestamp;
        private int type;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
