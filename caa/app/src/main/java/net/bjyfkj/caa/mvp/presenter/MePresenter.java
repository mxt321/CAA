package net.bjyfkj.caa.mvp.presenter;

import net.bjyfkj.caa.mvp.model.me.IMeModel;
import net.bjyfkj.caa.mvp.model.me.MeModel;
import net.bjyfkj.caa.mvp.model.me.OnLogoutLinstener;
import net.bjyfkj.caa.mvp.model.me.OnUpHeadlinstener;
import net.bjyfkj.caa.mvp.view.IMeView;

/**
 * Created by YFKJ-1 on 2016/12/16.
 */

public class MePresenter {
    private IMeModel iMeModel;
    private IMeView iMeView;

    public MePresenter(IMeView iMeView) {
        this.iMeModel = new MeModel();
        this.iMeView = iMeView;
    }

    /**
     * 退出登录
     */
    public void logout() {
        iMeModel.logout(iMeView.getToken(), new OnLogoutLinstener() {
            @Override
            public void logoutSuccess() {
                iMeView.logoutSuccess();
            }

            @Override
            public void logoutError() {
                iMeView.logoutError();
            }
        });
    }

    /**
     * 修改头像
     */
    public void upHead() {
        iMeView.showProgressDlg();
        iMeModel.upHead(iMeView.getToken(), iMeView.geturlPath(), new OnUpHeadlinstener() {
            @Override
            public void upHeanSuccess(String urlPath) {
                iMeView.upHeadSuccess(urlPath);
                iMeView.dismissProgressDlg();
            }

            @Override
            public void upHeadError() {
                iMeView.upHeadError();
                iMeView.dismissProgressDlg();
            }

            @Override
            public void upHeadTimeout() {
                iMeView.timeout();
                iMeView.dismissProgressDlg();
            }
        });
    }

}
