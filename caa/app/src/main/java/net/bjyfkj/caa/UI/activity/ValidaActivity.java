package net.bjyfkj.caa.UI.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.autolayout.AutoLayoutActivity;

import net.bjyfkj.caa.R;
import net.bjyfkj.caa.constant.User;
import net.bjyfkj.caa.entity.UserInfo;
import net.bjyfkj.caa.mvp.presenter.LoginPresenter;
import net.bjyfkj.caa.mvp.view.ILoginView;
import net.bjyfkj.caa.util.JPushUtil;
import net.bjyfkj.caa.util.SendSmsTimerUtils;
import net.bjyfkj.caa.util.SharedPreferencesUtils;
import net.bjyfkj.caa.util.VProgressDialog;

import org.xutils.x;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static net.bjyfkj.caa.R.id.tv_valida_valida;

public class ValidaActivity extends AutoLayoutActivity implements View.OnClickListener, ILoginView {

    @InjectView(R.id.et_number)
    EditText etNumber;
    @InjectView(R.id.et_valida)
    EditText etValida;
    @InjectView(tv_valida_valida)
    TextView tvValidaValida;
    @InjectView(R.id.tv_pas_login)
    TextView tvPasLogin;
    @InjectView(R.id.btn_valida_login)
    Button btnValidaLogin;
    @InjectView(R.id.tv_valida_register)
    TextView tvValidaRegister;
    @InjectView(R.id.right_return)
    LinearLayout rightReturn;
    private SendSmsTimerUtils mCountDownTimerUtils;
    private LoginPresenter loginPresenter;
    private VProgressDialog vProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valida);
        ButterKnife.inject(this);
        init();
    }

    public void init() {
        vProgressDialog = new VProgressDialog(this);
        loginPresenter = new LoginPresenter(this);
        mCountDownTimerUtils = new SendSmsTimerUtils(tvValidaValida, 60000, 1000, R.color.writ, R.color.writ);
        etNumber.setOnClickListener(this);
        etValida.setOnClickListener(this);
        tvValidaValida.setOnClickListener(this);
        tvPasLogin.setOnClickListener(this);
        btnValidaLogin.setOnClickListener(this);
        tvValidaRegister.setOnClickListener(this);
        rightReturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.right_return://返回上一个界面
                this.finish();
                break;
            case R.id.tv_valida_valida://获取验证码
                loginPresenter.sendValida();
                break;
            case R.id.tv_pas_login://返回到密码登录
                this.finish();
                break;
            case R.id.btn_valida_login://验证码登录
                loginPresenter.login();
                break;
            case R.id.tv_valida_register://注册界面
                Intent intent = new Intent(ValidaActivity.this, RegisterActivity.class);
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
        return "";
    }

    @Override
    public String getCode() {
        return etValida.getText().toString();
    }

    @Override
    public int getStatus() {
        return 1;
    }

    @Override
    public void loginSuccess(UserInfo.DataBean ui) {
        SharedPreferencesUtils.setParam(x.app(), User.PHONE, etNumber.getText().toString());
        SharedPreferencesUtils.setParam(x.app(), User.USER_ID, ui.getUser_id());
        SharedPreferencesUtils.setParam(x.app(), User.NICKNAME, ui.getNickname());
        SharedPreferencesUtils.setParam(x.app(), User.HEADIMG, ui.getHeadimg());
        SharedPreferencesUtils.setParam(x.app(), User.TOKEN, ui.getToken());
        JPushUtil.setAlias(x.app(), ui.getUser_id());
        Intent intent = new Intent(ValidaActivity.this, HomeActivity.class);
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
        mCountDownTimerUtils.start();
        Toast.makeText(this, "发送成功", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showProgressDlg() {
        vProgressDialog.showProgressDlg("正在登录...");
    }

    @Override
    public void dismissProgressDlg() {
        vProgressDialog.dismissProgressDlg();
    }
}
