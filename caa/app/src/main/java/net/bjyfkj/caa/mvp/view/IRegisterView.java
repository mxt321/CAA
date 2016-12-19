package net.bjyfkj.caa.mvp.view;

/**
 * Created by YFKJ-1 on 2016/12/14.
 */

public interface IRegisterView {
    String getetNumber();

    String getCode();

    String getetPwd();

    String getConfirm_Pwd();

    void registerSuccess();

    void registerError();

    void toError(String error);

    void toSendSuccess();
}
