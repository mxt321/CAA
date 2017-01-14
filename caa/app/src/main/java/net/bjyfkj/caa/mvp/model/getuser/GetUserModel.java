package net.bjyfkj.caa.mvp.model.getuser;

import android.util.Log;

import net.bjyfkj.caa.entity.WxUserEntity;
import net.bjyfkj.caa.util.GsonUtils;
import net.bjyfkj.caa.util.MD5Util;
import net.bjyfkj.caa.util.PropertiesUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by YFKJ-1 on 2017/1/13.
 */

public class GetUserModel implements IGetUserModel {

    @Override
    public void getWxUserPromotion(String token, String ads_id, int from, final OnGetWxUserPromotionLinstener onGetWxUserPromotionLinstener) {
        String sign = MD5Util.encrypt("Ads" + MD5Util.encrypt("bjyfkj4006010136") + "getWxUserPromotion");
        RequestParams params = new RequestParams(PropertiesUtils.getpath("getWxUserPromotion"));
        params.addBodyParameter("sign", sign);
        params.addBodyParameter("token", token);
        params.addBodyParameter("ads_id", ads_id);
        params.addBodyParameter("from", from + "");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("getWxUserPromotion", result);
                WxUserEntity wxUserEntity = GsonUtils.fromJson(result, WxUserEntity.class);
                int status = wxUserEntity.getStatus();
                if (status == 0) {
                    onGetWxUserPromotionLinstener.getWxUserPromotionError();
                } else if (status == 1) {
                    onGetWxUserPromotionLinstener.getWxUserPromotionSuccess(wxUserEntity);
                } else if (status == -1) {
                    onGetWxUserPromotionLinstener.timeout();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                onGetWxUserPromotionLinstener.getWxUserPromotionError();
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
