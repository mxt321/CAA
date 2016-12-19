package net.bjyfkj.caa.mvp.model.login;


import net.bjyfkj.caa.entity.UserInfo;

/**
 * Created by 37315 on 2016/8/24.
 */
public interface OnUserLoginLinstener {
    void loginSuccess(UserInfo.DataBean userInfo);

    void loginError();
}
