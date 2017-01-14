package net.bjyfkj.caa.mvp.model.myrelease;

import android.util.Log;

import net.bjyfkj.caa.entity.MyReleaseEntity;
import net.bjyfkj.caa.util.GsonUtils;
import net.bjyfkj.caa.util.MD5Util;
import net.bjyfkj.caa.util.PropertiesUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by YFKJ-1 on 2017/1/14.
 */

public class MyReleaseModel implements IMyReleaseModel {
    @Override
    public void myAds(String token, int from, final MyReleaseLinstener myReleaseLinstener) {
        String sign = MD5Util.encrypt("Ads" + MD5Util.encrypt("bjyfkj4006010136") + "myAds");
        RequestParams params = new RequestParams(PropertiesUtils.getpath("myAds"));
        params.addBodyParameter("sign", sign);
        params.addBodyParameter("token", token);
        params.addBodyParameter("from", from + "");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("myAds", result);
                MyReleaseEntity entity = GsonUtils.fromJson(result, MyReleaseEntity.class);
                int status = entity.getStatus();
                if (status == 1) {
                    myReleaseLinstener.MyReleaseSuccess(entity);
                } else if (status == 0) {
                    myReleaseLinstener.MyReleaseError();
                } else if (status == -1) {
                    myReleaseLinstener.timeout();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                myReleaseLinstener.MyReleaseError();
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
