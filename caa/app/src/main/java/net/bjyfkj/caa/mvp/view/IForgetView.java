package net.bjyfkj.caa.mvp.view;

/**
 * Created by YFKJ-1 on 2016/12/14.
 */

public interface IForgetView {
    String getetNumber();

    String getetPwd();

    String getCode();

    String getConfirm_Pwd();

    void resetSuccess();

    void resetError();

    void toError(String error);

    void toSendSuccess();
}

