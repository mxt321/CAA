package net.bjyfkj.caa.mvp.model.register;

import android.util.Log;

import net.bjyfkj.caa.mvp.model.OnValideLoginLinstener;
import net.bjyfkj.caa.util.MD5Util;
import net.bjyfkj.caa.util.PropertiesUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;


/**
 * Created by YFKJ-1 on 2016/12/14.
 */

public class RegisterModel implements IRegisterModel {

    @Override
    public void sendValide(String account, final OnValideLoginLinstener onValideLoginLinstener) {
        String sign = MD5Util.encrypt("User" + MD5Util.encrypt("bjyfkj4006010136") + "sendCode");
        RequestParams params = new RequestParams(PropertiesUtils.getpath("sendCode"));
        params.addBodyParameter("sign", sign);
        params.addBodyParameter("phone", account);
        params.addBodyParameter("authPhone", "1");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("ForgetModel", result + "");
                try {
                    JSONObject jsonObject = new JSONObject(result);
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
    public void userRegister(String phone, String pwd, String code, final OnUserRegisterLinstener onUserRegisterLinstener) {
        String sign = MD5Util.encrypt("User" + MD5Util.encrypt("bjyfkj4006010136") + "addUserByPhone");
        RequestParams params = new RequestParams(PropertiesUtils.getpath("addUserByPhone"));
        params.addBodyParameter("sign", sign);
        params.addBodyParameter("phone", phone);
        params.addBodyParameter("password", MD5Util.encrypt(pwd));
        params.addBodyParameter("code", code);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("userRegister", result + "");
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int status = jsonObject.getInt("status");
                    if (status > 0) {
                        onUserRegisterLinstener.registerSuccess();
                    } else {
                        onUserRegisterLinstener.registerError();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                onUserRegisterLinstener.registerError();
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
