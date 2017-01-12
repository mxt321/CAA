package net.bjyfkj.caa.mvp.model.tvdetails;

/**
 * Created by YFKJ-1 on 2017/1/7.
 */

public interface ITVDetailsModel {
    void getAdsDetail(String token, String device_id, OnTVDetailsLinstener onTVDetailsLinstener);
}
