package net.bjyfkj.caa.UI.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.bjyfkj.caa.R;
import net.bjyfkj.caa.app.BaseActivity;
import net.bjyfkj.caa.constant.User;
import net.bjyfkj.caa.entity.UserInfo;
import net.bjyfkj.caa.mvp.presenter.LoginPresenter;
import net.bjyfkj.caa.mvp.view.ILoginView;
import net.bjyfkj.caa.util.JPushUtil;
import net.bjyfkj.caa.util.SharedPreferencesUtils;
import net.bjyfkj.caa.util.VProgressDialog;

import org.xutils.x;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LoginActivity extends BaseActivity implements View.OnClickListener, ILoginView {

    @InjectView(R.id.tv_logo)
    TextView tvLogo;
    @InjectView(R.id.et_number)
    EditText etNumber;
    @InjectView(R.id.et_pwd)
    EditText etPwd;
    @InjectView(R.id.tv_forget)
    TextView tvForget;
    @InjectView(R.id.tv_validation)
    TextView tvValidation;
    @InjectView(R.id.btn_login)
    Button btnLogin;
    @InjectView(R.id.tv_register)
    TextView tvRegister;
    private LoginPresenter loginPresenter;
    private Intent intent;
    private long exitTime = 0;
    private VProgressDialog vProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        init();
    }


    public void init() {
        if (!SharedPreferencesUtils.getParam(x.app(), User.TOKEN, "").toString().equals("")) {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            this.finish();
        }
        vProgressDialog = new VProgressDialog(this);
        loginPresenter = new LoginPresenter(this);
        tvForget.setOnClickListener(this);
        tvValidation.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!SharedPreferencesUtils.getParam(x.app(), User.PHONE, "").toString().equals("")) {
            etNumber.setText(SharedPreferencesUtils.getParam(x.app(), User.PHONE, "").toString());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_validation://验证码登录
                intent = new Intent(LoginActivity.this, ValidaActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login://登录
                loginPresenter.login();
                break;
            case R.id.tv_forget://忘记密码
                intent = new Intent(LoginActivity.this, ForgetActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_register://注册界面
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;

        }
    }

    @Override
    public String getetNumber() {
        return etNumber.getText().toString();
    }

    @Override
    public String getetPwd() {
        return etPwd.getText().toString();
    }

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public int getStatus() {
        return 0;
    }

    @Override
    public void loginSuccess(UserInfo.DataBean ui) {
        SharedPreferencesUtils.setParam(x.app(), User.PHONE, etNumber.getText().toString());
        SharedPreferencesUtils.setParam(x.app(), User.USER_ID, ui.getUser_id());
        SharedPreferencesUtils.setParam(x.app(), User.NICKNAME, ui.getNickname());
        SharedPreferencesUtils.setParam(x.app(), User.HEADIMG, ui.getHeadimg());
        SharedPreferencesUtils.setParam(x.app(), User.TOKEN, ui.getToken());
        JPushUtil.setAlias(x.app(), ui.getUser_id());
        intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void loginError() {
        Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toError(String error) {
        Toast.makeText(this, error + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toSendSuccess() {

    }

    @Override
    public void showProgressDlg() {
        vProgressDialog.showProgressDlg("正在登录...");
    }

    @Override
    public void dismissProgressDlg() {
        vProgressDialog.dismissProgressDlg();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }


    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

}
