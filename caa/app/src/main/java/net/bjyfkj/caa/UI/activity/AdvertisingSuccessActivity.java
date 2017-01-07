package net.bjyfkj.caa.UI.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import net.bjyfkj.caa.R;
import net.bjyfkj.caa.app.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class AdvertisingSuccessActivity extends BaseActivity implements View.OnClickListener {

    @InjectView(R.id.right_return)
    LinearLayout registerRightReturn;
    @InjectView(R.id.btn_my_publish)
    Button btnMyPublish;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertising_success);
        ButterKnife.inject(this);
    }


    public void init() {
        registerRightReturn.setOnClickListener(this);
        btnMyPublish.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.right_return:
                break;
            case R.id.btn_my_publish:
                break;
        }
    }
}
