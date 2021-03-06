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
import net.bjyfkj.caa.app.ActivityCollector;
import net.bjyfkj.caa.app.BaseActivity;
import net.bjyfkj.caa.constant.User;
import net.bjyfkj.caa.entity.AdvertisingEntity;
import net.bjyfkj.caa.entity.CheckDeviceEntity;
import net.bjyfkj.caa.entity.DeviceEntity;
import net.bjyfkj.caa.entity.TimeEntity;
import net.bjyfkj.caa.mvp.presenter.DateDevicePresenter;
import net.bjyfkj.caa.mvp.view.IDateDeviceView;
import net.bjyfkj.caa.util.JPushUtil;
import net.bjyfkj.caa.util.SharedPreferencesUtils;
import net.bjyfkj.caa.util.VProgressDialog;

import org.xutils.x;

import java.util.ArrayList;
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
    private DateDevicePresenter dateDevicePresenter;
    private DateAdapter adapter;//时间表的adapter
    private int timestamp;
    private int type;
    private int area_id;
    private DeviceAdapter mDeviceAdapter;//设备列表的adapter
    private List<DeviceEntity.DataBean> DataBean;//当前正在显示的设备数据
    private List<CheckDeviceEntity> checklist = new ArrayList<>();
    private int deviceCount;
    private AdvertisingEntity advertisingEntity;
    private VProgressDialog vProgressDialog;


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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (DataBean != null) {
            DataBean = null;
        }
        if (checklist != null) {
            checklist = null;
        }
        if (advertisingEntity != null) {
            advertisingEntity = null;
        }
        if (dateDevicePresenter != null) {
            dateDevicePresenter = null;
        }
    }

    public void init() {
        Intent intent = getIntent();
        advertisingEntity = (AdvertisingEntity) intent.getSerializableExtra("advertisingEntity");
        dateDevicePresenter = new DateDevicePresenter(this);
        vProgressDialog = new VProgressDialog(this);
        Drawable drawable = getResources().getDrawable(R.drawable.checkbox_selector);
        drawable.setBounds(0, 0, 32, 32);
        checkBox.setCompoundDrawables(drawable, null, null, null);
        adapter = new DateAdapter();
        mDeviceAdapter = new DeviceAdapter(this);
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
                checklist = checkDevice();
                device_count();
                mDeviceAdapter.setmDatabeans(DataBean);
                mDeviceAdapter.notifyDataSetChanged();
            }
        });
        //发布广告
        btnRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (deviceCount > 0) {
                    dateDevicePresenter.submitAds();
                } else {
                    Toast.makeText(x.app(), "您还未选择设备", Toast.LENGTH_SHORT).show();
                }
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
        SharedPreferencesUtils.setParam(x.app(), User.TOKEN, "");
        JPushUtil.setAlias(x.app(), "");
        ActivityCollector.finishAll();
        Intent intent = new Intent(DateDeviceActivity.this, LoginActivity.class);
        startActivity(intent);
        this.finish();
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
    public String getArea_id() {
        return advertisingEntity.getArea_id();
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
        initDevice();
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
                    checklist = checkDevice();
                    device_count();
                    mDeviceAdapter.setmDatabeans(DataBean);
                    mDeviceAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public AdvertisingEntity getAdvertisingEntity() {
        return advertisingEntity;
    }

    @Override
    public List<CheckDeviceEntity> getCheckDeviceEntity() {
        return checklist;
    }

    @Override
    public void submitAdsSuccess() {
        ActivityCollector.finishAll();
        Intent intent = new Intent(DateDeviceActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void submitAdsError() {

    }

    @Override
    public void showProgressDlg() {
        vProgressDialog.showProgressDlg("正在发布");
    }

    @Override
    public void dismissProgressDlg() {
        vProgressDialog.dismissProgressDlg();
    }

    /***
     * 根据记录的设备同时显示出来
     */
    public void initDevice() {
        for (int i = 0; i < checklist.size(); i++) {
            if (checklist.get(i).getType() == type && checklist.get(i).getDate() == timestamp) {
                for (int i1 = 0; i1 < checklist.get(i).getDevice_id().size(); i1++) {
                    for (int i2 = 0; i2 < DataBean.size(); i2++) {
                        if (checklist.get(i).getDevice_id().get(i1) == DataBean.get(i2).getDevice_id()) {
                            DataBean.get(i2).setIsselected(true);
                        }
                    }
                }
            }
        }
    }


    /***
     * 保存选中的设备
     *
     * @return
     */
    public List<CheckDeviceEntity> checkDevice() {
        CheckDeviceEntity checkDeviceEntity = new CheckDeviceEntity();
        checkDeviceEntity.setDate(timestamp);
        checkDeviceEntity.setType(type);
        for (int i1 = 0; i1 < DataBean.size(); i1++) {
            if (DataBean.get(i1).isselected()) {
                checkDeviceEntity.getDevice_id().add(DataBean.get(i1).getDevice_id());
            }
        }
        return CheckDeviceEntity.addCheckDevice(checklist, checkDeviceEntity);
    }

    /***
     * 统计被选中的广告屏总数量
     */
    public void device_count() {
        deviceCount = 0;
        for (int i = 0; i < checklist.size(); i++) {
            deviceCount += checklist.get(i).getDevice_id().size();
        }
        tvDeviceSumcount.setText(deviceCount + "屏");
    }


}