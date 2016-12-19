package net.bjyfkj.caa.mvp.model.forget;

import net.bjyfkj.caa.mvp.model.OnUserCheckCodeLinstener;
import net.bjyfkj.caa.mvp.model.OnValideLoginLinstener;

/**
 * Created by YFKJ-1 on 2016/12/14.
 */

public interface IForgetModel {

    //验证 验证码
    void checkCode(String account, String code, OnUserCheckCodeLinstener onUserCheckCodeLinstener);

    //重置密码
    void resetPWD(String account, String pwd, OnUserForgetLinstener onUserForgetLinstener);

    //获取验证码
    void sendValide(String account, OnValideLoginLinstener onValideLoginLinstener);
}
