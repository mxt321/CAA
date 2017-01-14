package net.bjyfkj.caa.mvp.presenter;

import net.bjyfkj.caa.entity.MyReleaseEntity;
import net.bjyfkj.caa.mvp.model.myrelease.IMyReleaseModel;
import net.bjyfkj.caa.mvp.model.myrelease.MyReleaseLinstener;
import net.bjyfkj.caa.mvp.model.myrelease.MyReleaseModel;
import net.bjyfkj.caa.mvp.view.IMyReleaseView;

/**
 * Created by YFKJ-1 on 2017/1/14.
 */

public class MyReleasePresenter {

    private IMyReleaseView iMyReleaseView;
    private IMyReleaseModel iMyReleaseModel;

    public MyReleasePresenter(IMyReleaseView iMyReleaseView) {
        this.iMyReleaseView = iMyReleaseView;
        iMyReleaseModel = new MyReleaseModel();
    }

    /**
     * 我的发布
     */
    public void myAds() {
        iMyReleaseModel.myAds(iMyReleaseView.getToken(), iMyReleaseView.getFrom(), new MyReleaseLinstener() {
            @Override
            public void MyReleaseSuccess(MyReleaseEntity entity) {
                iMyReleaseView.hidell_release_null();
                iMyReleaseView.hidell_home_release();//18701243410
                iMyReleaseView.hidell_home_ring();
                if (entity.getData().getIn_progress().getStatus() == null) {
                    iMyReleaseView.showll_release_null();
                } else if (entity.getData().getIn_progress().getStatus().equals("1")) {
                    iMyReleaseView.showll_home_ring(entity.getData().getIn_progress());
                } else if (entity.getData().getIn_progress().getStatus().equals("0")) {
                    iMyReleaseView.showll_home_release();
                }
                if (entity.getData().getHistory().size() > 0) {
                    iMyReleaseView.getHistory(entity.getData().getHistory());
                }
            }

            @Override
            public void MyReleaseError() {
                iMyReleaseView.myAdsError();
            }

            @Override
            public void timeout() {
                iMyReleaseView.timeout();
            }
        });
    }

}
