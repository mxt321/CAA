package net.bjyfkj.caa.mvp.presenter;

import net.bjyfkj.caa.mvp.model.OnValideLoginLinstener;
import net.bjyfkj.caa.mvp.model.register.IRegisterModel;
import net.bjyfkj.caa.mvp.model.register.OnUserRegisterLinstener;
import net.bjyfkj.caa.mvp.model.register.RegisterModel;
import net.bjyfkj.caa.mvp.view.IRegisterView;

/**
 * Created by YFKJ-1 on 2016/12/14.
 */

public class RegisterPresenter {
    private IRegisterModel iRegisterModel;
    private IRegisterView iRegisterView;

    public RegisterPresenter(IRegisterView iRegisterView) {
        this.iRegisterModel = new RegisterModel();
        this.iRegisterView = iRegisterView;
    }

    /***
     * 注册用户
     */
    public void register() {
        if (iRegisterView.getetNumber().equals("")) {
            iRegisterView.toError("手机号不能为空");
        } else if (iRegisterView.getCode().equals("")) {
            iRegisterView.toError("验证码不能为空");
        } else if (!iRegisterView.getetPwd().equals("")) {
            iRegisterView.toError("密码不能为空");
        } else if (iRegisterView.getConfirm_Pwd().equals("")) {
            iRegisterView.toError("再次输入密码不能为空");
        } else if (!iRegisterView.getetPwd().equals(iRegisterView.getConfirm_Pwd())) {
            iRegisterView.toError("两次密码不一样");
        } else {
            iRegisterModel.userRegister(iRegisterView.getetNumber(), iRegisterView.getetPwd(), iRegisterView.getCode(), new OnUserRegisterLinstener() {
                @Override
                public void registerSuccess() {
                    iRegisterView.registerSuccess();
                }

                @Override
                public void registerError() {
                    iRegisterView.registerError();
                }
            });
        }
    }

    /***
     * 获取验证码
     */
    public void sendValida() {
        if (!iRegisterView.getetNumber().equals("")) {
            iRegisterModel.sendValide(iRegisterView.getetNumber(), new OnValideLoginLinstener() {
                @Override
                public void sendSuccess() {
                    iRegisterView.toSendSuccess();
                }

                @Override
                public void sendError() {
                    iRegisterView.toError("获取失败");
                }
            });
        } else {
            iRegisterView.toError("手机号不能为空");
        }
    }

}
