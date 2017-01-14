package net.bjyfkj.caa.mvp.model.getuser;

import net.bjyfkj.caa.entity.WxUserEntity;

/**
 * Created by YFKJ-1 on 2017/1/13.
 */

public interface OnGetWxUserPromotionLinstener {
    void getWxUserPromotionSuccess(WxUserEntity wxUserEntity);

    void getWxUserPromotionError();

    void timeout();
}
