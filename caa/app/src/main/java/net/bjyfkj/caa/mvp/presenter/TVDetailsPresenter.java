package net.bjyfkj.caa.mvp.presenter;

import net.bjyfkj.caa.entity.AdsDetailEntity;
import net.bjyfkj.caa.mvp.model.tvdetails.ITVDetailsModel;
import net.bjyfkj.caa.mvp.model.tvdetails.OnTVDetailsLinstener;
import net.bjyfkj.caa.mvp.model.tvdetails.TVDetailsModel;
import net.bjyfkj.caa.mvp.view.ITVDetailsView;

/**
 * Created by YFKJ-1 on 2017/1/7.
 */

public class TVDetailsPresenter {
    private ITVDetailsView itvDetailsView;
    private ITVDetailsModel itvDetailsModel;

    public TVDetailsPresenter(ITVDetailsView itvDetailsView) {
        this.itvDetailsView = itvDetailsView;
        itvDetailsModel = new TVDetailsModel();
    }

    public void getAdsDetail() {
        itvDetailsModel.getAdsDetail(itvDetailsView.getToken(), itvDetailsView.getDevice_id(), new OnTVDetailsLinstener() {
            @Override
            public void getAdsDetailSuccess(AdsDetailEntity adsDetailEntity) {
                itvDetailsView.getAdsDetailSuccess(adsDetailEntity);
            }

            @Override
            public void getAdsDetailError() {
                itvDetailsView.getAdsDetailError();
            }

            @Override
            public void Timeout() {
                itvDetailsView.Timeout();
            }
        });
    }

}
