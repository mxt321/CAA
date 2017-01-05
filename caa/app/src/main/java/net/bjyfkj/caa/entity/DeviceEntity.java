package net.bjyfkj.caa.entity;

import java.util.List;

/**
 * Created by YFKJ-2 on 2016/11/10.
 */

public class DeviceEntity {

    /**
     * status : 1
     * message : ok
     * data : [{"device_id":"2","name":"三星min","logo":"2016-11-03/581aa33eb15c8.jpg","current_ads":"0","max_ads":"3"}]
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
         * device_id : 2
         * name : 三星min
         * logo : 2016-11-03/581aa33eb15c8.jpg
         * current_ads : 0
         * max_ads : 3
         */


        private int device_id;
        private String name;
        private String logo;
        private String current_ads;
        private String max_ads;
        private boolean isselected;

        public boolean isselected() {
            return isselected;
        }

        public void setIsselected(boolean isselected) {
            this.isselected = isselected;
        }

        public int getDevice_id() {
            return device_id;
        }

        public void setDevice_id(int device_id) {
            this.device_id = device_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getCurrent_ads() {
            return current_ads;
        }

        public void setCurrent_ads(String current_ads) {
            this.current_ads = current_ads;
        }

        public String getMax_ads() {
            return max_ads;
        }

        public void setMax_ads(String max_ads) {
            this.max_ads = max_ads;
        }
    }
}
