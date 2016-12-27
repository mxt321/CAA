package net.bjyfkj.caa.UI.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.autolayout.AutoLayoutActivity;

import net.bjyfkj.caa.R;
import net.bjyfkj.caa.UI.adapter.DateAdapter;
import net.bjyfkj.caa.constant.User;
import net.bjyfkj.caa.entity.TimeEntity;
import net.bjyfkj.caa.mvp.presenter.DateDevicePresenter;
import net.bjyfkj.caa.mvp.view.IDateDeviceView;
import net.bjyfkj.caa.util.SharedPreferencesUtils;

import org.xutils.x;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DateDeviceActivity extends AutoLayoutActivity implements IDateDeviceView {

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
    static DateDeviceActivity instance;
    private DateDevicePresenter dateDevicePresenter;
    private DateAdapter adapter;
    private int timestamp;
    private int type;
    private int area_id;

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
        instance = this;
        dateDevicePresenter = new DateDevicePresenter(this);
        adapter = new DateAdapter();
        forgetRightReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateDeviceActivity.this.finish();
            }
        });
    }

    public void initdata() {
        dateDevicePresenter.getSchedule();
    }

    @Override
    public String getToken() {
        return SharedPreferencesUtils.getParam(x.app(), User.TOKEN, "").toString();
    }

    @Override
    public void Timeout() {
        Toast.makeText(x.app(), "登录超时", Toast.LENGTH_SHORT).show();
        HomeActivity.instance.finish();
        PerfectAdvertisingActivity.instance.finish();
        RingChooseActivity.instance.finish();
        Intent intent = new Intent(DateDeviceActivity.this, LoginActivity.class);
        SharedPreferencesUtils.setParam(x.app(), User.TOKEN, "");
        startActivity(intent);
        instance.finish();
    }

    @Override
    public void getScheduleError() {
        Toast.makeText(x.app(), "获取失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getScheduleSuccess(final List<TimeEntity.DataBean> dataBeen) {
        adapter.setmData(dataBeen);
        selectTimeGrid.setAdapter(adapter);
        selectTimeGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.setSelectPosition(i);
                type = dataBeen.get(i).getType();
                timestamp = dataBeen.get(i).getTimestamp();
                area_id = 2;
                dateDevicePresenter.getDevicesBySchedule();
            }
        });
    }

    @Override
    public int getTimestamp() {
        return timestamp;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public int getArea_id() {
        return area_id;
    }

    @Override
    public void getDevicesByScheduleError() {
        Toast.makeText(x.app(), "获取失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getDevicesByScheduleSuccess() {

    }

}
