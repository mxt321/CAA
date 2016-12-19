package net.bjyfkj.caa.UI.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.autolayout.AutoLayoutActivity;

import net.bjyfkj.caa.R;
import net.bjyfkj.caa.mvp.presenter.ForgetPresenter;
import net.bjyfkj.caa.mvp.view.IForgetView;
import net.bjyfkj.caa.util.SendSmsTimerUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ForgetActivity extends AutoLayoutActivity implements View.OnClickListener, IForgetView {

    @InjectView(R.id.forget_right_return)
    LinearLayout forgetRightReturn;
    @InjectView(R.id.forget_et_number)
    EditText forgetEtNumber;
    @InjectView(R.id.forget_et_valida)
    EditText forgetEtValida;
    @InjectView(R.id.forget_tv_valida)
    TextView forgetTvValida;
    @InjectView(R.id.forget_et_pwd)
    EditText forgetEtPwd;
    @InjectView(R.id.forget_et_confirm_pwd)
    EditText forgetEtConfirmPwd;
    @InjectView(R.id.forget_btn_con)
    Button forgetBtnCon;
    private ForgetPresenter forgetPresenter;
    private SendSmsTimerUtils mCountDownTimerUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        ButterKnife.inject(this);
        init();
    }

    public void init() {
        forgetPresenter = new ForgetPresenter(this);
        mCountDownTimerUtils = new SendSmsTimerUtils(forgetTvValida, 60000, 1000, R.color.writ, R.color.writ);
        forgetRightReturn.setOnClickListener(this);
        forgetTvValida.setOnClickListener(this);
        forgetBtnCon.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {//返回上一个界面
            case R.id.forget_right_return:
                this.finish();
                break;
            case R.id.forget_tv_valida://获取验证码
                forgetPresenter.sendValida();
                break;
            case R.id.forget_btn_con://重置密码
                forgetPresenter.resetPWD();
                break;
        }

    }

    @Override
    public String getetNumber() {
        return forgetEtNumber.getText().toString();
    }

    @Override
    public String getetPwd() {
        return forgetEtPwd.getText().toString();
    }

    @Override
    public String getCode() {
        return forgetEtValida.getText().toString();
    }

    @Override
    public String getConfirm_Pwd() {
        return forgetEtConfirmPwd.getText().toString();
    }

    @Override
    public void resetSuccess() {
        Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void resetError() {
        Toast.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
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
