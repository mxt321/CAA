package net.bjyfkj.caa.mvp.model.releasedatails;

/**
 * Created by YFKJ-1 on 2017/1/7.
 */

public interface IReleaseDatailsModel {

    void getMyAdsList(String token, String ads_id, OnGetMyAdsListLinstener onGetMyAdsListLinstener);

}
