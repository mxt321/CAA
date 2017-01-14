package net.bjyfkj.caa.mvp.view;

import net.bjyfkj.caa.entity.MyReleaseEntity;

import java.util.List;

/**
 * Created by YFKJ-1 on 2017/1/14.
 */

public interface IMyReleaseView {

    String getToken();

    int getFrom();

    void timeout();

    void myAdsError();

    void hidell_release_null();

    void hidell_home_release();

    void hidell_home_ring();

    void showll_release_null();

    void showll_home_release();

    void showll_home_ring(MyReleaseEntity.DataBean.InProgressBean inProgressBean);

    void getHistory(List<MyReleaseEntity.DataBean.HistoryBean> historyBean);
}
