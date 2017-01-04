package net.bjyfkj.caa.mvp.model.datedevice;

import net.bjyfkj.caa.entity.DeviceEntity;

import java.util.List;

/**
 * Created by YFKJ-1 on 2016/12/26.
 */

public interface OnGetDevicesByScheduleLinstener {
    void scheduleSuccess(List<DeviceEntity.DataBean> mDataBean);

    void scheduleError();

    void timeout();
}
