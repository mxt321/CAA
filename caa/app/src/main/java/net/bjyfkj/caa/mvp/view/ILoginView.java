package net.bjyfkj.caa.mvp.view;

import net.bjyfkj.caa.entity.UserInfo;

/**
 * Created by YFKJ-1 on 2016/12/13.
 */

public interface ILoginView {
    String getetNumber();

    String getetPwd();

    String getCode();

    int getStatus();

    void loginSuccess(UserInfo.DataBean ui);

    void loginError();

    void toError(String error);

    void toSendSuccess();

    void showProgressDlg();

    void dismissProgressDlg();
}
