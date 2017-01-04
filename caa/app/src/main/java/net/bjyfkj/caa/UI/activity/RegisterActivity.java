package net.bjyfkj.caa.UI.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.bjyfkj.caa.R;
import net.bjyfkj.caa.app.BaseActivity;
import net.bjyfkj.caa.mvp.presenter.RegisterPresenter;
import net.bjyfkj.caa.mvp.view.IRegisterView;
import net.bjyfkj.caa.util.SendSmsTimerUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements IRegisterView {

    @InjectView(R.id.register_right_return)
    LinearLayout registerRightReturn;
    @InjectView(R.id.register_et_number)
    EditText registerEtNumber;
    @InjectView(R.id.register_et_valida)
    EditText registerEtValida;
    @InjectView(R.id.register_tv_valida)
    TextView registerTvValida;
    @InjectView(R.id.register_et_pwd)
    EditText registerEtPwd;
    @InjectView(R.id.register_et_confirm_pwd)
    EditText registerEtConfirmPwd;
    @InjectView(R.id.register_btn_register)
    Button registerBtnRegister;
    private SendSmsTimerUtils mCountDownTimerUtils;
    private RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.inject(this);
        init();
    }

    public void init() {
        registerPresenter = new RegisterPresenter(this);
        mCountDownTimerUtils = new SendSmsTimerUtils(registerTvValida, 60000, 1000, R.color.writ, R.color.writ);
    }


    @OnClick({R.id.register_right_return, R.id.register_tv_valida, R.id.register_btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_right_return:
                this.finish();
                break;
            case R.id.register_tv_valida:
                registerPresenter.sendValida();
                break;
            case R.id.register_btn_register:
                registerPresenter.register();
                break;
        }
    }

    @Override
    public String getetNumber() {
        return registerEtNumber.getText().toString();
    }

    @Override
    public String getCode() {
        return registerEtValida.getText().toString();
    }

    @Override
    public String getetPwd() {
        return registerEtPwd.getText().toString();
    }

    @Override
    public String getConfirm_Pwd() {
        return registerEtConfirmPwd.getText().toString();
    }

    @Override
    public void registerSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registerError() {
        Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show();
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
}
