package net.bjyfkj.caa.mvp.model.datedevice;

/**
 * Created by YFKJ-1 on 2016/12/20.
 */

public interface IDateDeviceModel {
    void getSchedule(String token, OnGetScheduleLinstener onGetScheduleLinstener);

    void getDevicesBySchedule(String token, int timestamp, int type, int area_id, OnGetDevicesByScheduleLinstener onGetDevicesByScheduleLinstener);
}
