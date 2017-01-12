package net.bjyfkj.caa.mvp.model.tvdetails;

import net.bjyfkj.caa.entity.AdsDetailEntity;

/**
 * Created by YFKJ-1 on 2017/1/7.
 */

public interface OnTVDetailsLinstener {
    void getAdsDetailSuccess(AdsDetailEntity adsDetailEntity);

    void getAdsDetailError();

    void Timeout();
}
