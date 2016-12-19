package net.bjyfkj.caa.mvp.view;

/**
 * Created by YFKJ-1 on 2016/12/16.
 */

public interface IMeView {
    String getToken();

    void logoutSuccess();

    void logoutError();

    String geturlPath();

    void timeout();

    void upHeadSuccess(String urlpath);

    void upHeadError();

    void showProgressDlg();

    void dismissProgressDlg();
}
