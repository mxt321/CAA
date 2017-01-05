package net.bjyfkj.caa.mvp.model.datedevice;

import android.util.Log;

import net.bjyfkj.caa.entity.AdvertisingEntity;
import net.bjyfkj.caa.entity.CheckDeviceEntity;
import net.bjyfkj.caa.entity.DeviceEntity;
import net.bjyfkj.caa.entity.TimeEntity;
import net.bjyfkj.caa.util.GsonUtils;
import net.bjyfkj.caa.util.MD5Util;
import net.bjyfkj.caa.util.PropertiesUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by YFKJ-1 on 2016/12/20.
 */

public class DateDeviceModel implements IDateDeviceModel {
    @Override
    public void getSchedule(String token, final OnGetScheduleLinstener onGetScheduleLinstener) {
        String sign = MD5Util.encrypt("Ads" + MD5Util.encrypt("bjyfkj4006010136") + "getSchedule");
        RequestParams params = new RequestParams(PropertiesUtils.getpath("getSchedule"));
        params.addBodyParameter("sign", sign);
        params.addBodyParameter("token", token);
        x.http().get(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.i("getSchedule", result + "");
                        TimeEntity entity = GsonUtils.fromJson(result, TimeEntity.class);
                        if (entity.getStatus() == 1) {
                            List<TimeEntity.DataBean> dataBeen = entity.getData();
                            onGetScheduleLinstener.scheduleSuccess(dataBeen);
                        } else if (entity.getStatus() == 0) {
                            onGetScheduleLinstener.scheduleError();
                        } else {
                            onGetScheduleLinstener.timeout();
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                }

        );
    }

    @Override
    public void getDevicesBySchedule(String token, int timestamp, int type, String area_id, final OnGetDevicesByScheduleLinstener onGetDevicesByScheduleLinstener) {
        String sign = MD5Util.encrypt("Ads" + MD5Util.encrypt("bjyfkj4006010136") + "getDevicesBySchedule");
        RequestParams params = new RequestParams(PropertiesUtils.getpath("getDevicesBySchedule"));
        params.addBodyParameter("sign", sign);
        params.addBodyParameter("token", token);
        params.addBodyParameter("timestamp", timestamp + "");
        params.addBodyParameter("type", type + "");
        params.addBodyParameter("area_id", area_id);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("getDevicesBySchedule", result + "");
                DeviceEntity deviceEntity = GsonUtils.fromJson(result, DeviceEntity.class);
                if (deviceEntity.getStatus() == 1) {
                    List<DeviceEntity.DataBean> mDataBean = deviceEntity.getData();
                    onGetDevicesByScheduleLinstener.scheduleSuccess(mDataBean);
                } else if (deviceEntity.getStatus() == -1) {
                    onGetDevicesByScheduleLinstener.timeout();
                } else {
                    onGetDevicesByScheduleLinstener.scheduleError();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void submitAds(String token, AdvertisingEntity advertisingEntity, List<CheckDeviceEntity> checkDeviceEntities, OnSubmitAdsLinstener onSubmitAdsLinstener) {
        String sign = MD5Util.encrypt("Ads" + MD5Util.encrypt("bjyfkj4006010136") + "submitAds");
        RequestParams params = new RequestParams(PropertiesUtils.getpath("submitAds"));
        params.addBodyParameter("sign", sign);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
