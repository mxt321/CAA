package net.bjyfkj.caa.mvp.model.ringchoose;

/**
 * Created by YFKJ-1 on 2016/12/19.
 */

public interface IRingChooseModel {
    void getCity(String token, OnGetCityLinstener onGetCityLinstener);

    void getDistricts(String token, String city, OnGetCityLinstener onGetCityLinstener);

    void getArea(String token, String city, String district, OnGetCityLinstener onGetCityLinstener);
}
