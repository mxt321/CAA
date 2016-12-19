package net.bjyfkj.caa.mvp.model.home;

import android.util.Log;

import net.bjyfkj.caa.entity.IndexInfo;
import net.bjyfkj.caa.util.GsonUtils;
import net.bjyfkj.caa.util.MD5Util;
import net.bjyfkj.caa.util.PropertiesUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by YFKJ-1 on 2016/12/17.
 */

public class HomeModel implements IHomeModel {

    @Override
    public void index(String token, final OnUserIndexLinstener onUserIndexLinstener) {
        String sign = MD5Util.encrypt("Ads" + MD5Util.encrypt("bjyfkj4006010136") + "index");
        RequestParams params = new RequestParams(PropertiesUtils.getpath("index"));
        params.addBodyParameter("sign", sign);
        params.addBodyParameter("token", token);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("index", result + "");
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int status = jsonObject.getInt("status");
                    if (status == 1) {
                        IndexInfo.DataBean index = GsonUtils.fromJson(jsonObject.getString("data"), IndexInfo.DataBean.class);
                        onUserIndexLinstener.indexSuccess(index);
                    } else if (status == -1) {
                        onUserIndexLinstener.timeout();
                    } else {
                        onUserIndexLinstener.indexError();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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
}
