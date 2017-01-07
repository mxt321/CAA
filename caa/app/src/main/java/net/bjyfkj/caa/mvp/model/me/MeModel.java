package net.bjyfkj.caa.mvp.model.me;

import android.util.Log;

import net.bjyfkj.caa.constant.User;
import net.bjyfkj.caa.util.MD5Util;
import net.bjyfkj.caa.util.PropertiesUtils;
import net.bjyfkj.caa.util.SharedPreferencesUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

/**
 * Created by YFKJ-1 on 2016/12/16.
 */

public class MeModel implements IMeModel {

    @Override
    public void logout(String token, final OnLogoutLinstener onLogoutLinstener) {
        String sign = MD5Util.encrypt("Member" + MD5Util.encrypt("bjyfkj4006010136") + "userLogout");
        RequestParams params = new RequestParams(PropertiesUtils.getpath("userLogout"));
        params.addBodyParameter("sign", sign);
        params.addBodyParameter("token", SharedPreferencesUtils.getParam(x.app(), User.TOKEN, "").toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int status = jsonObject.getInt("status");
                    if (status == 1 || status == -1) {
                        onLogoutLinstener.logoutSuccess();
                    } else {
                        onLogoutLinstener.logoutError();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                onLogoutLinstener.logoutError();
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
    public void upHead(String token, String photo, final OnUpHeadlinstener onUpHeadlinstener) {
        String sign = MD5Util.encrypt("Member" + MD5Util.encrypt("bjyfkj4006010136") + "setHeadIMG");
        RequestParams params = new RequestParams(PropertiesUtils.getpath("setHeadIMG"));
        params.addBodyParameter("sign", sign);
        params.addBodyParameter("token", SharedPreferencesUtils.getParam(x.app(), User.TOKEN, "").toString());
        File headFile = new File(photo);
        params.addBodyParameter("photo", headFile);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("upHead", result + "");
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int status = jsonObject.getInt("status");
                    if (status == 1) {
                        String urlPath = jsonObject.getString("data");
                        onUpHeadlinstener.upHeanSuccess(urlPath);
                    } else if (status == -1) {
                        onUpHeadlinstener.upHeadTimeout();
                    } else if (status == 0) {
                        onUpHeadlinstener.upHeadError();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                onUpHeadlinstener.upHeadError();
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