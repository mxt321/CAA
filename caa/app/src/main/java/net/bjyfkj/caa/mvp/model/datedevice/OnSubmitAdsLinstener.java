package net.bjyfkj.caa.mvp.model.datedevice;

/**
 * Created by YFKJ-1 on 2017/1/5.
 */

public interface OnSubmitAdsLinstener {
    void submitAdsSuccess();

    void submitAdsError();

    void timeout();
}
