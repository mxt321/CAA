package net.bjyfkj.caa.mvp.view;

import net.bjyfkj.caa.entity.DeviceEntity;
import net.bjyfkj.caa.entity.TimeEntity;

import java.util.List;

/**
 * Created by YFKJ-1 on 2016/12/20.
 */

public interface IDateDeviceView {

    String getToken();

    void Timeout();

    void getScheduleError();

    void getScheduleSuccess(List<TimeEntity.DataBean> dataBeen);

    int getTimestamp();

    int getType();

    int getArea_id();

    void getDevicesByScheduleError();

    void getDevicesByScheduleSuccess(List<DeviceEntity.DataBean> mDataBean);

}
