package net.bjyfkj.caa.mvp.model.datedevice;

import net.bjyfkj.caa.entity.TimeEntity;

import java.util.List;

/**
 * Created by YFKJ-1 on 2016/12/26.
 */

public interface OnGetScheduleLinstener {
    void scheduleSuccess(List<TimeEntity.DataBean> dataBeen);

    void scheduleError();

    void timeout();
}
