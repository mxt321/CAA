package net.bjyfkj.caa.mvp.model.forget;

import android.util.Log;

import net.bjyfkj.caa.mvp.model.OnUserCheckCodeLinstener;
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

public class ForgetModel implements IForgetModel {
    @Override
    public void checkCode(String account, String code, final OnUserCheckCodeLinstener onUserCheckCodeLinstener) {
        String sign = MD5Util.encrypt("User" + MD5Util.encrypt("bjyfkj4006010136") + "checkCode");
        RequestParams params = new RequestParams(PropertiesUtils.getpath("checkCode"));
        params.addBodyParameter("sign", sign);
        params.addBodyParameter("phone", account);
        params.addBodyParameter("code", code);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Log.i("checkCode", s + "");
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    int status = jsonObject.getInt("status");
                    if (status > 0) {
                        onUserCheckCodeLinstener.checkSuccess();
                    } else {
                        onUserCheckCodeLinstener.checkError();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                onUserCheckCodeLinstener.checkError();
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
    public void resetPWD(String account, String pwd, String code, final OnUserForgetLinstener onUserForgetLinstener) {
        String sign = MD5Util.encrypt("User" + MD5Util.encrypt("bjyfkj4006010136") + "resetPWD");
        RequestParams params = new RequestParams(PropertiesUtils.getpath("resetPWD"));
        params.addBodyParameter("sign", sign);
        params.addBodyParameter("phone", account);
        params.addBodyParameter("password", MD5Util.encrypt(pwd));
//        params.addBodyParameter("code", code);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("resetPWD", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int status = jsonObject.getInt("status");
                    if (status > 0) {
                        onUserForgetLinstener.resetSuccess();
                    } else {
                        onUserForgetLinstener.resetError();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                onUserForgetLinstener.resetError();
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
    public void sendValide(String account, final OnValideLoginLinstener onValideLoginLinstener) {
        String sign = MD5Util.encrypt("User" + MD5Util.encrypt("bjyfkj4006010136") + "sendCode");
        RequestParams params = new RequestParams(PropertiesUtils.getpath("sendCode"));
        params.addBodyParameter("sign", sign);
        params.addBodyParameter("phone", account);
        params.addBodyParameter("authPhone", "0");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Log.i("ForgetModel", s + "");
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
