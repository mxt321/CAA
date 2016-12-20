package net.bjyfkj.caa.mvp.model.ringchoose;

import android.util.Log;

import net.bjyfkj.caa.util.EncodeUtils;
import net.bjyfkj.caa.util.MD5Util;
import net.bjyfkj.caa.util.PropertiesUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YFKJ-1 on 2016/12/19.
 */

public class RingChooseModel implements IRingChooseModel {
    @Override
    public void getCity(String token, final OnGetCityLinstener onGetCityLinstener) {
        String sign = MD5Util.encrypt("Ads" + MD5Util.encrypt("bjyfkj4006010136") + "getCities");
        RequestParams params = new RequestParams(PropertiesUtils.getpath("getCities"));
        params.addBodyParameter("sign", sign);
        params.addBodyParameter("token", token);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("getCity", result + "");
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int status = jsonObject.getInt("status");
                    if (status == 1) {
                        List<Map<String, String>> citys = new ArrayList<Map<String, String>>();
                        JSONArray jsonIn = jsonObject.getJSONArray("data");
                        for (int i = 0; i < jsonIn.length(); i++) {
                            jsonObject = jsonIn.getJSONObject(i);
                            Map map = new HashMap();
                            map.put("name", jsonObject.getString("city"));
                            citys.add(map);
                        }
                        Log.i("getCity", citys.size() + "");
                        onGetCityLinstener.Success(citys);
                    } else if (status == 0) {
                        onGetCityLinstener.Error();

                    } else {
                        onGetCityLinstener.Timeout();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                onGetCityLinstener.Error();
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
    public void getDistricts(String token, String city, final OnGetCityLinstener onGetCityLinstener) {
        String sign = MD5Util.encrypt("Ads" + MD5Util.encrypt("bjyfkj4006010136") + "getDistricts");
        RequestParams params = new RequestParams(PropertiesUtils.getpath("getDistricts"));
        params.addBodyParameter("sign", sign);
        params.addBodyParameter("token", token);
        params.addBodyParameter("city", EncodeUtils.urlDecode(city));
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("getDistricts", result + "");
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int status = jsonObject.getInt("status");
                    if (status == 1) {
                        List<Map<String, String>> districts = new ArrayList<Map<String, String>>();
                        JSONArray jsonIn = jsonObject.getJSONArray("data");
                        for (int i = 0; i < jsonIn.length(); i++) {
                            jsonObject = jsonIn.getJSONObject(i);
                            Map map = new HashMap();
                            map.put("name", jsonObject.getString("district"));
                            districts.add(map);
                        }
                        Log.i("getDistricts", districts.size() + "");
                        onGetCityLinstener.Success(districts);
                    } else if (status == 0) {
                        onGetCityLinstener.Error();
                    } else {
                        onGetCityLinstener.Timeout();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                onGetCityLinstener.Error();
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
    public void getArea(String token, String city, String district, final OnGetCityLinstener onGetCityLinstener) {
        String sign = MD5Util.encrypt("Ads" + MD5Util.encrypt("bjyfkj4006010136") + "getArea");
        RequestParams params = new RequestParams(PropertiesUtils.getpath("getArea"));
        params.addBodyParameter("sign", sign);
        params.addBodyParameter("token", token);
        params.addBodyParameter("city", EncodeUtils.urlDecode(city));
        params.addBodyParameter("district", EncodeUtils.urlDecode(district));
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("getArea", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int status = jsonObject.getInt("status");
                    if (status == 1) {
                        List<Map<String, String>> area = new ArrayList<Map<String, String>>();
                        JSONArray jsonIn = jsonObject.getJSONArray("data");
                        for (int i = 0; i < jsonIn.length(); i++) {
                            jsonObject = jsonIn.getJSONObject(i);
                            Map map = new HashMap();
                            map.put("id", jsonObject.getString("area_id"));
                            map.put("name", jsonObject.getString("name"));
                            area.add(map);
                        }
                        Log.i("getDistricts", area.size() + "");
                        onGetCityLinstener.Success(area);
                    } else if (status == 0) {
                        onGetCityLinstener.Error();
                    } else {
                        onGetCityLinstener.Timeout();
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
