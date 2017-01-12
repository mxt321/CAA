package net.bjyfkj.caa.entity;

import java.util.List;

/**
 * Created by YFKJ-2 on 2016/11/28.
 */

public class AdsDetailEntity {

    /**
     * status : 1
     * message : ok
     * data : {"photo":"http://admin.bjyfkj.net/Public/Uploads/2016-11-05/581d977e128f0.gif","name":"测试设备-北京","description":"暂无介绍","tags":["口味佳","环境佳"],"playlist":[{"title":"呵呵"},{"title":"呵呵"},{"title":null},{"title":"美食"},{"title":null},{"title":"咚咚"}]}
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
         * photo : http://admin.bjyfkj.net/Public/Uploads/2016-11-05/581d977e128f0.gif
         * name : 测试设备-北京
         * description : 暂无介绍
         * tags : ["口味佳","环境佳"]
         * playlist : [{"title":"呵呵"},{"title":"呵呵"},{"title":null},{"title":"美食"},{"title":null},{"title":"咚咚"}]
         */

        private String photo;
        private String name;
        private String description;
        private List<String> tags;
        private List<PlaylistBean> playlist;

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public List<PlaylistBean> getPlaylist() {
            return playlist;
        }

        public void setPlaylist(List<PlaylistBean> playlist) {
            this.playlist = playlist;
        }

        public static class PlaylistBean {
            /**
             * title : 呵呵
             */

            private String title;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
