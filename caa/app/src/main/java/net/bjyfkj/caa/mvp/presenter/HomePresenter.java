package net.bjyfkj.caa.mvp.presenter;

import net.bjyfkj.caa.entity.IndexInfo;
import net.bjyfkj.caa.mvp.model.home.HomeModel;
import net.bjyfkj.caa.mvp.model.home.IHomeModel;
import net.bjyfkj.caa.mvp.model.home.OnUserIndexLinstener;
import net.bjyfkj.caa.mvp.view.IHomeView;

/**
 * Created by YFKJ-1 on 2016/12/17.
 */

public class HomePresenter {
    private IHomeModel iHomeModel;
    private IHomeView iHomeView;

    public HomePresenter(IHomeView iHomeView) {
        this.iHomeModel = new HomeModel();
        this.iHomeView = iHomeView;
    }

    public void index() {
        iHomeModel.index(iHomeView.getToken(), new OnUserIndexLinstener() {
            @Override
            public void indexSuccess(IndexInfo.DataBean index) {
                iHomeView.hidell_home_ring();
                iHomeView.hidell_release_null();
                iHomeView.hidell_home_release();
                if (index.getStatus() == null) {
                    iHomeView.showll_release_null();
                } else if (index.getStatus().equals("0")) {
                    iHomeView.showll_home_release();
                } else if (index.getStatus().equals("1")) {
                    iHomeView.showll_home_ring(index);
                }
            }

            @Override
            public void indexError() {
                iHomeView.indexError();
            }

            @Override
            public void timeout() {
                iHomeView.timeout();
            }
        });
    }

}
