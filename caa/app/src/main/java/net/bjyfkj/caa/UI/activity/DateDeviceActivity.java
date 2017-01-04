package net.bjyfkj.caa.UI.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.bjyfkj.caa.R;
import net.bjyfkj.caa.UI.adapter.DateAdapter;
import net.bjyfkj.caa.UI.adapter.DeviceAdapter;
import net.bjyfkj.caa.app.BaseActivity;
import net.bjyfkj.caa.constant.User;
import net.bjyfkj.caa.entity.CheckDeviceEntity;
import net.bjyfkj.caa.entity.DeviceEntity;
import net.bjyfkj.caa.entity.TimeEntity;
import net.bjyfkj.caa.mvp.presenter.DateDevicePresenter;
import net.bjyfkj.caa.mvp.view.IDateDeviceView;
import net.bjyfkj.caa.util.JPushUtil;
import net.bjyfkj.caa.util.SharedPreferencesUtils;

import org.xutils.x;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DateDeviceActivity extends BaseActivity implements IDateDeviceView {

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
    private DateAdapter adapter;//时间表的adapter
    private int timestamp;
    private int type;
    private int area_id;
    private DeviceAdapter mDeviceAdapter;//设备列表的adapter
    private List<DeviceEntity.DataBean> DataBean;//当前正在显示的设备数据
    private List<CheckDeviceEntity> checklist;

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
        Drawable drawable = getResources().getDrawable(R.drawable.checkbox_selector);
        drawable.setBounds(0, 0, 32, 32);
        checkBox.setCompoundDrawables(drawable, null, null, null);
        adapter = new DateAdapter();
        mDeviceAdapter = new DeviceAdapter(instance);
        forgetRightReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateDeviceActivity.this.finish();
            }
        });
        //全选 or 全不选
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DataBean != null && DataBean.size() > 0) {
                    if (checkBox.isChecked()) {
                        for (DeviceEntity.DataBean dataBean : DataBean) {
                            if (!dataBean.isselected()) {
                                dataBean.setIsselected(true);
                            }
                        }
                    } else {
                        for (DeviceEntity.DataBean dataBean : DataBean) {
                            if (dataBean.isselected()) {
                                dataBean.setIsselected(false);
                            }
                        }
                    }
                }
                mDeviceAdapter.setmDatabeans(DataBean);
                mDeviceAdapter.notifyDataSetChanged();
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
        Intent intent = new Intent(DateDeviceActivity.this, LoginActivity.class);
        SharedPreferencesUtils.setParam(x.app(), User.TOKEN, "");
        JPushUtil.setAlias(x.app(), "");
        startActivity(intent);
        HomeActivity.instance.finish();
        PerfectAdvertisingActivity.instance.finish();
        RingChooseActivity.instance.finish();
        instance.finish();
    }

    @Override
    public void getScheduleError() {
        Toast.makeText(x.app(), "获取失败", Toast.LENGTH_SHORT).show();
    }

    /***
     * 获取可选的时刻表
     *
     * @param dataBeen
     */
    @Override
    public void getScheduleSuccess(final List<TimeEntity.DataBean> dataBeen) {
        adapter.setmData(dataBeen);
        selectTimeGrid.setAdapter(adapter);
        selectTimeGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                checkBox.setChecked(false);
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

    /***
     * 根据时刻表获取设备
     *
     * @param mDataBean
     */
    @Override
    public void getDevicesByScheduleSuccess(List<DeviceEntity.DataBean> mDataBean) {
        DataBean = mDataBean;
        mDeviceAdapter.setmDatabeans(DataBean);
        selectDeviceGrid.setAdapter(mDeviceAdapter);
        selectDeviceGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (DataBean.get(i).getCurrent_ads() != DataBean.get(i).getMax_ads()) {//判断当前设备是否可被选中
                    if (DataBean.get(i).isselected()) {//判断当前设备是否已经选择了  如果没有则选中
                        DataBean.get(i).setIsselected(false);

                    } else {
                        DataBean.get(i).setIsselected(true);

                    }
                    mDeviceAdapter.setmDatabeans(DataBean);
                    mDeviceAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DataBean.clear();
    }
}