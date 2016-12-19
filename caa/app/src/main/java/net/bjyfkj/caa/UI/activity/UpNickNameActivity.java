package net.bjyfkj.caa.UI.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.autolayout.AutoLayoutActivity;

import net.bjyfkj.caa.R;
import net.bjyfkj.caa.constant.User;
import net.bjyfkj.caa.util.MD5Util;
import net.bjyfkj.caa.util.PropertiesUtils;
import net.bjyfkj.caa.util.SharedPreferencesUtils;
import net.bjyfkj.caa.util.VProgressDialog;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static net.bjyfkj.caa.R.id.et_nickname;

public class UpNickNameActivity extends AutoLayoutActivity implements View.OnClickListener {

    @InjectView(R.id.right_return)
    LinearLayout rightReturn;
    @InjectView(R.id.complete)
    TextView complete;
    @InjectView(et_nickname)
    EditText etNickname;
    private VProgressDialog vProgressDialog;
    private String nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_nick_name);
        ButterKnife.inject(this);
        init();
    }

    public void init() {
        vProgressDialog = new VProgressDialog(this);
        Intent intent = getIntent();
        nickname = intent.getStringExtra("nickname");
        etNickname.setText(nickname);
        complete.setOnClickListener(this);
        rightReturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.right_return:
                this.finish();
                break;
            case R.id.complete:
                if (!etNickname.getText().toString().equals("")) {
                    if (etNickname.getText().toString().equals(nickname)) {
                        Toast.makeText(x.app(), "您未作任何修改", Toast.LENGTH_SHORT).show();
                    } else {
                        vProgressDialog.showProgressDlg("正在保存...");
                        setNickName();
                    }
                }
                break;
        }
    }

    /***
     * 修改昵称
     */
    private void setNickName() {
        String sign = MD5Util.encrypt("Member" + MD5Util.encrypt("bjyfkj4006010136") + "setNickname");
        RequestParams params = new RequestParams(PropertiesUtils.getpath("setNickname"));
        params.addBodyParameter("sign", sign);
        params.addBodyParameter("token", SharedPreferencesUtils.getParam(x.app(), User.TOKEN, "").toString());
        params.addBodyParameter("nickname", etNickname.getText().toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Log.i("setNickName", s + "");
                try {
                    JSONObject json = new JSONObject(s);
                    int jsonInt = json.getInt("status");
                    String message = json.getString("message");
                    if (jsonInt == 1) {
                        vProgressDialog.dismissProgressDlg();
                        Toast.makeText(UpNickNameActivity.this, "昵称修改成功", Toast.LENGTH_SHORT).show();
                        SharedPreferencesUtils.setParam(x.app(), User.NICKNAME, etNickname.getText().toString());
                        UpNickNameActivity.this.finish();
                    } else if (jsonInt == 0) {
                        Toast.makeText(UpNickNameActivity.this, "昵称修改失败", Toast.LENGTH_SHORT).show();
                        vProgressDialog.dismissProgressDlg();
                    } else {
                        vProgressDialog.dismissProgressDlg();
                        Toast.makeText(UpNickNameActivity.this, message, Toast.LENGTH_SHORT).show();
                        SharedPreferencesUtils.setParam(x.app(), User.TOKEN, "");
                        Intent intent = new Intent(UpNickNameActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
