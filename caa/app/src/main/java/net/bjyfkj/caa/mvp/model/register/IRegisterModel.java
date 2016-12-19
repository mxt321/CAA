package net.bjyfkj.caa.mvp.model.register;

import net.bjyfkj.caa.mvp.model.OnValideLoginLinstener;

/**
 * Created by YFKJ-1 on 2016/12/14.
 */

public interface IRegisterModel {

    //获取验证码
    void sendValide(String account, OnValideLoginLinstener onValideLoginLinstener);

    void userRegister(String phone, String pwd, String code, OnUserRegisterLinstener onUserRegisterLinstener);

}
