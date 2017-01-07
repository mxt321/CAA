package net.bjyfkj.caa.mvp.model.releasedatails;

import android.util.Log;

import net.bjyfkj.caa.entity.AdsEntity;
import net.bjyfkj.caa.util.GsonUtils;
import net.bjyfkj.caa.util.MD5Util;
import net.bjyfkj.caa.util.PropertiesUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by YFKJ-1 on 2017/1/7.
 */

public class ReleaseDatailsModel implements IReleaseDatailsModel {
    @Override
    public void getMyAdsList(String token, String ads_id, final OnGetMyAdsListLinstener onGetMyAdsListLinstener) {
        String sign = MD5Util.encrypt("Member" + MD5Util.encrypt("bjyfkj4006010136") + "getMyAdsList");
        RequestParams params = new RequestParams(PropertiesUtils.getpath("getMyAdsList"));
        params.addBodyParameter("sign", sign);
        params.addBodyParameter("token", token);
        params.addBodyParameter("ads_id", ads_id);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("getMyAdsList", result + "");
                AdsEntity adsEntity = GsonUtils.fromJson(result, AdsEntity.class);
                if (adsEntity.getStatus() == 1) {
                    onGetMyAdsListLinstener.getMyAdsListSuccess(adsEntity);
                } else if (adsEntity.getStatus() == 0) {
                    onGetMyAdsListLinstener.getMyAdsListError();
                } else if (adsEntity.getStatus() == -1) {
                    onGetMyAdsListLinstener.timeout();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                onGetMyAdsListLinstener.getMyAdsListError();
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
