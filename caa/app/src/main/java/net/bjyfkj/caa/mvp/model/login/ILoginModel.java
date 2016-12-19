package net.bjyfkj.caa.mvp.model.login;

import net.bjyfkj.caa.mvp.model.OnValideLoginLinstener;

/**
 * Created by YFKJ-1 on 2016/12/13.
 */

public interface ILoginModel {
    //登录----包括（密码登录和验证码登录）
    void login(String account, String password, String code, OnUserLoginLinstener linsterenr);

    //验证码登录----获取验证码
    void sendValide(String account, OnValideLoginLinstener onValideLoginLinstener);
}
