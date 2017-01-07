package net.bjyfkj.caa.mvp.presenter;

import net.bjyfkj.caa.entity.AdsEntity;
import net.bjyfkj.caa.mvp.model.releasedatails.IReleaseDatailsModel;
import net.bjyfkj.caa.mvp.model.releasedatails.OnGetMyAdsListLinstener;
import net.bjyfkj.caa.mvp.model.releasedatails.ReleaseDatailsModel;
import net.bjyfkj.caa.mvp.view.IReleaseDatailsView;

/**
 * Created by YFKJ-1 on 2017/1/7.
 */

public class ReleaseDatailsPresenter {
    private IReleaseDatailsView iReleaseDatailsView;
    private IReleaseDatailsModel iReleaseDatailsModel;

    public ReleaseDatailsPresenter(IReleaseDatailsView iReleaseDatailsView) {
        this.iReleaseDatailsView = iReleaseDatailsView;
        iReleaseDatailsModel = new ReleaseDatailsModel();
    }


    /***
     * 获取我发布的广告列表
     */
    public void getMyAdsList() {
        iReleaseDatailsModel.getMyAdsList(iReleaseDatailsView.getToken(), iReleaseDatailsView.getAds_id(), new OnGetMyAdsListLinstener() {
            @Override
            public void getMyAdsListSuccess(AdsEntity adsEntity) {
                iReleaseDatailsView.getMyAdsListSuccess(adsEntity);
            }

            @Override
            public void getMyAdsListError() {
                iReleaseDatailsView.getMyAdsListError();
            }

            @Override
            public void timeout() {
                iReleaseDatailsView.timeout();
            }
        });
    }

}
