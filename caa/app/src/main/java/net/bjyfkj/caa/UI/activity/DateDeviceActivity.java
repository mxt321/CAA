package net.bjyfkj.caa.UI.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhy.autolayout.AutoLayoutActivity;

import net.bjyfkj.caa.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DateDeviceActivity extends AutoLayoutActivity {

    @InjectView(R.id.forget_right_return)
    LinearLayout forgetRightReturn;
    @InjectView(R.id.select_time_grid)
    GridView selectTimeGrid;
    @InjectView(R.id.checkBox)
    CheckBox checkBox;
    @InjectView(R.id.select_device_grid)
    GridView selectDeviceGrid;
    @InjectView(R.id.tv_device_sumcount)
    TextView tvDeviceSumcount;
    @InjectView(R.id.btn_release)
    Button btnRelease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_device);
        ButterKnife.inject(this);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initdata();
    }

    public void init() {
    }

    public void initdata() {
    }
}
