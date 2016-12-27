package net.bjyfkj.caa.mvp.model.datedevice;

/**
 * Created by YFKJ-1 on 2016/12/26.
 */

public interface OnGetDevicesByScheduleLinstener {
    void scheduleSuccess();

    void scheduleError();

    void timeout();
}
