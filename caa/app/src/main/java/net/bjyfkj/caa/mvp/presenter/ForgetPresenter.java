package net.bjyfkj.caa.mvp.presenter;

import net.bjyfkj.caa.mvp.model.forget.ForgetModel;
import net.bjyfkj.caa.mvp.model.forget.IForgetModel;
import net.bjyfkj.caa.mvp.model.OnUserCheckCodeLinstener;
import net.bjyfkj.caa.mvp.model.forget.OnUserForgetLinstener;
import net.bjyfkj.caa.mvp.model.OnValideLoginLinstener;
import net.bjyfkj.caa.mvp.view.IForgetView;

/**
 * Created by YFKJ-1 on 2016/12/14.
 */

public class ForgetPresenter {
    private IForgetModel iForgetModel;
    private IForgetView iForgetView;

    public ForgetPresenter(IForgetView iForgetView) {
        this.iForgetModel = new ForgetModel();
        this.iForgetView = iForgetView;
    }

    /***
     * 重置密码
     */
    public void resetPWD() {
        if (!iForgetView.getetNumber().equals("")) {//手机号不能为空
            if (!iForgetView.getCode().equals("")) {//验证码不能为空
                if (!iForgetView.getetPwd().equals("")) {//密码不能为空
                    if (!iForgetView.getConfirm_Pwd().equals("")) {//第二次输入密码不能为空
                        if (iForgetView.getetPwd().equals(iForgetView.getConfirm_Pwd())) {//两次密码要相同
                            iForgetModel.checkCode(iForgetView.getetNumber(), iForgetView.getCode(), new OnUserCheckCodeLinstener() {
                                @Override
                                public void checkSuccess() {
                                    iForgetModel.resetPWD(iForgetView.getetNumber(), iForgetView.getConfirm_Pwd(), new OnUserForgetLinstener() {
                                        @Override
                                        public void resetSuccess() {
                                            iForgetView.resetSuccess();
                                        }

                                        @Override
                                        public void resetError() {
                                            iForgetView.resetError();
                                        }
                                    });
                                }

                                @Override
                                public void checkError() {
                                    iForgetView.toError("验证码错误");
                                }
                            });
                        } else {
                            iForgetView.toError("两次密码不一样");
                        }
                    } else {
                        iForgetView.toError("再次输入密码不能为空");
                    }
                } else {
                    iForgetView.toError("密码不能为空");
                }
            } else {
                iForgetView.toError("验证码不能为空");
            }
        } else {
            iForgetView.toError("手机号不能为空");
        }
    }

    /***
     * 获取验证码
     */
    public void sendValida() {
        if (!iForgetView.getetNumber().equals("")) {
            iForgetModel.sendValide(iForgetView.getetNumber(), new OnValideLoginLinstener() {
                @Override
                public void sendSuccess() {
                    iForgetView.toSendSuccess();
                }

                @Override
                public void sendError() {
                    iForgetView.toError("获取失败");
                }
            });
        } else {
            iForgetView.toError("手机号不能为空");
        }
    }

}
