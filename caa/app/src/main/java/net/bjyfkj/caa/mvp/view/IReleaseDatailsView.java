package net.bjyfkj.caa.mvp.view;

import net.bjyfkj.caa.entity.AdsEntity;

/**
 * Created by YFKJ-1 on 2017/1/7.
 */

public interface IReleaseDatailsView {

    String getToken();

    String getAds_id();

    void getMyAdsListSuccess(AdsEntity adsEntity);

    void getMyAdsListError();

    void timeout();
}
