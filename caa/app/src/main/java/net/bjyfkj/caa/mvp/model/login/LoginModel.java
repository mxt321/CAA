package net.bjyfkj.caa.mvp.model.login;

import android.util.Log;

import net.bjyfkj.caa.entity.UserInfo;
import net.bjyfkj.caa.mvp.model.OnValideLoginLinstener;
import net.bjyfkj.caa.util.GsonUtils;
import net.bjyfkj.caa.util.MD5Util;
import net.bjyfkj.caa.util.PropertiesUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by YFKJ-1 on 2016/12/13.
 */

public class LoginModel implements ILoginModel {
    @Override
    public void login(String account, String password, String code, final OnUserLoginLinstener linsterenr) {
        String sign = MD5Util.encrypt("User" + MD5Util.encrypt("bjyfkj4006010136") + "userLogin");
        RequestParams params = new RequestParams(PropertiesUtils.getpath("userLogin"));
        params.addBodyParameter("sign", sign);
        params.addBodyParameter("phone", account);
        params.addBodyParameter("password", MD5Util.encrypt(password));
        params.addBodyParameter("code", code);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Log.i("login", s + "");
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    int status = jsonObject.getInt("status");
                    if (status == 1) {
                        UserInfo.DataBean userInfo = GsonUtils.fromJson(jsonObject.getString("data"), UserInfo.DataBean.class);
                        linsterenr.loginSuccess(userInfo);
                    } else {
                        linsterenr.loginError();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                linsterenr.loginError();
            }

            @Override
            public void onCancelled(CancelledException e) {
            }

            @Override
            public void onFinished() {
            }
        });

    }

    @Override
    public void sendValide(String account, final OnValideLoginLinstener onValideLoginLinstener) {
        String sign = MD5Util.encrypt("User" + MD5Util.encrypt("bjyfkj4006010136") + "sendCode");
        RequestParams params = new RequestParams(PropertiesUtils.getpath("sendCode"));
        params.addBodyParameter("sign", sign);
        params.addBodyParameter("phone", account);
        params.addBodyParameter("authPhone", "0");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Log.i("sendValide", s + "");
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    int status = jsonObject.getInt("status");
                    if (status > 0) {
                        onValideLoginLinstener.sendSuccess();
                    } else {
                        onValideLoginLinstener.sendError();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
