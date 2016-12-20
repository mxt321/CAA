package net.bjyfkj.caa.entity;

import java.io.Serializable;

/**
 * Created by YFKJ-1 on 2016/12/13.
 */

public class UserInfo implements Serializable{

    /**
     * data : {"user_id":"29","nickname":"15010035699","headimg":"http://admin.bjyfkj.net//Public/Uploads/avatar/avatar.png","token":"75bd66b0a2957f9d35a1b7a10cb09807"}
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
         * user_id : 29
         * nickname : 15010035699
         * headimg : http://admin.bjyfkj.net//Public/Uploads/avatar/avatar.png
         * token : 75bd66b0a2957f9d35a1b7a10cb09807
         */

        private String user_id;
        private String nickname;
        private String headimg;
        private String token;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
