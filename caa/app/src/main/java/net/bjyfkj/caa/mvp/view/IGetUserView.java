package net.bjyfkj.caa.mvp.view;

import net.bjyfkj.caa.entity.WxUserEntity;

/**
 * Created by YFKJ-1 on 2017/1/13.
 */

public interface IGetUserView {

    String getToken();

    void getWxUserPromotionSuccess(WxUserEntity wxUserEntity);

    void getWxUserPromotionError();

    void timeout();

    int getFrom();

    String getAdsId();

    String getStatus();

    void clearColor();

    void tvNoGetColor();

    void tvUseColor();

    void tvExpiredColor();


}
