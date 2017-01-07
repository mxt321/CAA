package net.bjyfkj.caa.mvp.view;

/**
 * Created by YFKJ-1 on 2017/1/7.
 */

public interface TVDetailsView {
    String getToken();

    String getDevice_id();

    void getAdsDetailSuccess();

    void getAdsDetailError();

    void Timeout();
}
