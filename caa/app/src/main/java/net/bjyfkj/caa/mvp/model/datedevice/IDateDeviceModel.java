package net.bjyfkj.caa.mvp.model.datedevice;

import net.bjyfkj.caa.entity.AdvertisingEntity;
import net.bjyfkj.caa.entity.CheckDeviceEntity;

import java.util.List;

/**
 * Created by YFKJ-1 on 2016/12/20.
 */

public interface IDateDeviceModel {
    void getSchedule(String token, OnGetScheduleLinstener onGetScheduleLinstener);

    void getDevicesBySchedule(String token, int timestamp, int type, String area_id, OnGetDevicesByScheduleLinstener onGetDevicesByScheduleLinstener);

    void submitAds(String token, AdvertisingEntity advertisingEntity, List<CheckDeviceEntity> checkDeviceEntities, OnSubmitAdsLinstener onSubmitAdsLinstener);
}
