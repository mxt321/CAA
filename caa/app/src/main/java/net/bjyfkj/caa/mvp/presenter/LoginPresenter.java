package net.bjyfkj.caa.mvp.presenter;

import net.bjyfkj.caa.entity.UserInfo;
import net.bjyfkj.caa.mvp.model.login.ILoginModel;
import net.bjyfkj.caa.mvp.model.login.LoginModel;
import net.bjyfkj.caa.mvp.model.login.OnUserLoginLinstener;
import net.bjyfkj.caa.mvp.model.OnValideLoginLinstener;
import net.bjyfkj.caa.mvp.view.ILoginView;

/**
 * Created by YFKJ-1 on 2016/12/13.
 */

public class LoginPresenter {

    private ILoginModel iLoginModel;
    private ILoginView iLoginView;

    public LoginPresenter(ILoginView iLoginView) {
        this.iLoginModel = new LoginModel();
        this.iLoginView = iLoginView;
    }

    /***
     * 登录
     */
    public void login() {
        if (!iLoginView.getetNumber().equals("")) {
            if (iLoginView.getStatus() == 0) {//密码登录
                if (!iLoginView.getetPwd().equals("")) {
                    iLoginView.showProgressDlg();
                    iLoginModel.login(iLoginView.getetNumber(), iLoginView.getetPwd(), iLoginView.getCode(), new OnUserLoginLinstener() {
                        @Override
                        public void loginSuccess(UserInfo.DataBean userInfo) {
                            iLoginView.loginSuccess(userInfo);
                            iLoginView.dismissProgressDlg();
                        }

                        @Override
                        public void loginError() {
                            iLoginView.loginError();
                            iLoginView.dismissProgressDlg();
                        }
                    });
                } else {
                    iLoginView.toError("密码不能为空");
                }
            } else if (iLoginView.getStatus() == 1) {//验证码登录
                if (!iLoginView.getCode().equals("")) {
                    iLoginView.showProgressDlg();
                    iLoginModel.login(iLoginView.getetNumber(), iLoginView.getetPwd(), iLoginView.getCode(), new OnUserLoginLinstener() {
                        @Override
                        public void loginSuccess(UserInfo.DataBean userInfo) {
                            iLoginView.loginSuccess(userInfo);
                            iLoginView.dismissProgressDlg();
                        }

                        @Override
                        public void loginError() {
                            iLoginView.loginError();
                            iLoginView.dismissProgressDlg();
                        }
                    });

                } else {
                    iLoginView.toError("验证码不能为空");
                }
            }
        } else {
            iLoginView.toError("手机号不能为空");
        }
    }

    /***
     * 获取验证码
     */
    public void sendValida() {
        if (!iLoginView.getetNumber().equals("")) {
            iLoginModel.sendValide(iLoginView.getetNumber(), new OnValideLoginLinstener() {
                @Override
                public void sendSuccess() {
                    iLoginView.toSendSuccess();
                }

                @Override
                public void sendError() {
                    iLoginView.toError("获取失败");
                }
            });
        } else {
            iLoginView.toError("手机号不能为空");
        }
    }


}
