package net.bjyfkj.caa.mvp.view;

import net.bjyfkj.caa.entity.AdsDetailEntity;

/**
 * Created by YFKJ-1 on 2017/1/7.
 */

public interface ITVDetailsView {
    String getToken();

    String getDevice_id();

    void getAdsDetailSuccess(AdsDetailEntity adsDetailEntity);

    void getAdsDetailError();

    void Timeout();
}
