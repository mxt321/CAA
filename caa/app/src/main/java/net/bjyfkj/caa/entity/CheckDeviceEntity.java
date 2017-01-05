package net.bjyfkj.caa.entity;

import java.util.ArrayList;
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

    private int date;
    private int type;
    private List<Integer> device_id = new ArrayList<>();

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
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

    public static List<CheckDeviceEntity> addCheckDevice(List<CheckDeviceEntity> checkDeviceEntities, CheckDeviceEntity checkDeviceEntity) {
        for (int i = 0; i < checkDeviceEntities.size(); i++) {
            if (checkDeviceEntities.get(i).getDate() == checkDeviceEntity.getDate() && checkDeviceEntities.get(i).getType() == checkDeviceEntity.getType()) {
                checkDeviceEntities.remove(i);
            }
        }
        if (checkDeviceEntity.getDevice_id().size() > 0) {
            checkDeviceEntities.add(checkDeviceEntity);
        }
        return checkDeviceEntities;
    }
}
