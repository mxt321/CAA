package net.bjyfkj.caa.mvp.model.releasedatails;

import net.bjyfkj.caa.entity.AdsEntity;

/**
 * Created by YFKJ-1 on 2017/1/7.
 */

public interface OnGetMyAdsListLinstener {

    void getMyAdsListSuccess(AdsEntity adsEntity);

    void getMyAdsListError();

    void timeout();

}
