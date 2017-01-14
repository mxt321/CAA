package net.bjyfkj.caa.mvp.model.getuser;

/**
 * Created by YFKJ-1 on 2017/1/13.
 */

public interface IGetUserModel {
    void getWxUserPromotion(String token, String ads_id, int from, OnGetWxUserPromotionLinstener onGetWxUserPromotionLinstener);
}
