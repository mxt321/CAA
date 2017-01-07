package net.bjyfkj.caa.entity;

import java.util.List;

/**
 * Created by YFKJ-2 on 2016/11/26.
 */

public class AdsEntity {
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

    public static class DataBean{
        private String device_id;
        private String name;
        private String logo;
        private String play_count;
        private String get_count;
        private String use_count;

        public String getDevice_id() {
            return device_id;
        }

        public void setDevice_id(String device_id) {
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
