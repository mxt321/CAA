package net.bjyfkj.caa.mvp.model.datedevice;

import android.util.Log;

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
}
