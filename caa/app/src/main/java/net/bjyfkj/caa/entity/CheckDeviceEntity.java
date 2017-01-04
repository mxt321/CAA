package net.bjyfkj.caa.entity;

import java.util.List;

/**
 * Created by YFKJ-1 on 2017/1/4.
 */

public class CheckDeviceEntity {

    /**
     * date :
     * type : 0
     * device_id : [1,2,3]
     */

    private String date;
    private int type;
    private List<Integer> device_id;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Integer> getDevice_id() {
        return device_id;
    }

    public void setDevice_id(List<Integer> device_id) {
        this.device_id = device_id;
    }
}
