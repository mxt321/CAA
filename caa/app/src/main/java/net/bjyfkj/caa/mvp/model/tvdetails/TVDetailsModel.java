package net.bjyfkj.caa.mvp.model.tvdetails;

import android.util.Log;

import net.bjyfkj.caa.entity.AdsDetailEntity;
import net.bjyfkj.caa.util.GsonUtils;
import net.bjyfkj.caa.util.MD5Util;
import net.bjyfkj.caa.util.PropertiesUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by YFKJ-1 on 2017/1/7.
 */

public class TVDetailsModel implements ITVDetailsModel {
    @Override
    public void getAdsDetail(String token, String device_id, final OnTVDetailsLinstener onTVDetailsLinstener) {
        String sign = MD5Util.encrypt("Member" + MD5Util.encrypt("bjyfkj4006010136") + "getAdsDetail");
        RequestParams params = new RequestParams(PropertiesUtils.getpath("getAdsDetail"));
        params.addBodyParameter("token", token);
        params.addBodyParameter("device_id", device_id);
        params.addBodyParameter("sign", sign);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("getAdsDetail", result);
                AdsDetailEntity adsDetailEntity = GsonUtils.fromJson(result, AdsDetailEntity.class);
                if (adsDetailEntity.getStatus() == 0) {
                    onTVDetailsLinstener.getAdsDetailError();
                } else if (adsDetailEntity.getStatus() == 1) {
                    onTVDetailsLinstener.getAdsDetailSuccess(adsDetailEntity);
                } else if (adsDetailEntity.getStatus() == -1) {
                    onTVDetailsLinstener.Timeout();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                onTVDetailsLinstener.getAdsDetailError();
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
