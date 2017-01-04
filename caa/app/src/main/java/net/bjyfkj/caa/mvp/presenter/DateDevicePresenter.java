package net.bjyfkj.caa.mvp.presenter;

import net.bjyfkj.caa.entity.DeviceEntity;
import net.bjyfkj.caa.entity.TimeEntity;
import net.bjyfkj.caa.mvp.model.datedevice.DateDeviceModel;
import net.bjyfkj.caa.mvp.model.datedevice.IDateDeviceModel;
import net.bjyfkj.caa.mvp.model.datedevice.OnGetDevicesByScheduleLinstener;
import net.bjyfkj.caa.mvp.model.datedevice.OnGetScheduleLinstener;
import net.bjyfkj.caa.mvp.view.IDateDeviceView;

import java.util.List;

/**
 * Created by YFKJ-1 on 2016/12/20.
 */

public class DateDevicePresenter {
    private IDateDeviceModel iDateDeviceModel;
    private IDateDeviceView iDateDeviceView;

    public DateDevicePresenter(IDateDeviceView iDateDeviceView) {
        this.iDateDeviceModel = new DateDeviceModel();
        this.iDateDeviceView = iDateDeviceView;
    }

    /***
     * 获取可选的时刻表
     */
    public void getSchedule() {
        iDateDeviceModel.getSchedule(iDateDeviceView.getToken(), new OnGetScheduleLinstener() {
            @Override
            public void scheduleSuccess(List<TimeEntity.DataBean> dataBeen) {
                iDateDeviceView.getScheduleSuccess(dataBeen);
            }

            @Override
            public void scheduleError() {
                iDateDeviceView.getScheduleError();
            }

            @Override
            public void timeout() {
                iDateDeviceView.Timeout();
            }
        });
    }

    /**
     * 根据时刻表获取设备
     */
    public void getDevicesBySchedule() {
        iDateDeviceModel.getDevicesBySchedule(iDateDeviceView.getToken(), iDateDeviceView.getTimestamp(), iDateDeviceView.getType(), iDateDeviceView.getArea_id(), new OnGetDevicesByScheduleLinstener() {
            @Override
            public void scheduleSuccess(List<DeviceEntity.DataBean> mDataBean) {
                iDateDeviceView.getDevicesByScheduleSuccess(mDataBean);
            }

            @Override
            public void scheduleError() {
                iDateDeviceView.getDevicesByScheduleError();
            }

            @Override
            public void timeout() {
                iDateDeviceView.Timeout();
            }
        });
    }

}
