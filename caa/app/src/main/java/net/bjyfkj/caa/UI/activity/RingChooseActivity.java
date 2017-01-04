package net.bjyfkj.caa.UI.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import net.bjyfkj.caa.R;
import net.bjyfkj.caa.UI.adapter.ChooseCityGridAdapter;
import net.bjyfkj.caa.app.BaseActivity;
import net.bjyfkj.caa.constant.User;
import net.bjyfkj.caa.mvp.presenter.RingChoosePresenter;
import net.bjyfkj.caa.mvp.view.IRingChooseView;
import net.bjyfkj.caa.util.JPushUtil;
import net.bjyfkj.caa.util.SharedPreferencesUtils;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RingChooseActivity extends BaseActivity implements View.OnClickListener, IRingChooseView {

    @InjectView(R.id.forget_right_return)
    LinearLayout forgetRightReturn;
    @InjectView(R.id.btn_select_city)
    Button btnSelectCity;
    @InjectView(R.id.hot_city_grid)
    GridView hotCityGrid;
    @InjectView(R.id.ll_hot_city)
    LinearLayout llHotCity;
    @InjectView(R.id.hot_districts_grid)
    GridView hotDistrictsGrid;
    @InjectView(R.id.ll_hot_districts)
    LinearLayout llHotDistricts;
    @InjectView(R.id.hot_area_grid)
    GridView hotAreaGrid;
    @InjectView(R.id.ll_hot_area)
    LinearLayout llHotArea;

    static RingChooseActivity instance;
    private RingChoosePresenter presenter;
    private List<Map<String, String>> citys;
    private ChooseCityGridAdapter adapter;
    private ChooseCityGridAdapter adapter2;
    private ChooseCityGridAdapter adapter3;
    private String city;
    private String district;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring_choose);
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
        forgetRightReturn.setOnClickListener(this);
        presenter = new RingChoosePresenter(this);
        citys = new ArrayList<>();
        adapter = new ChooseCityGridAdapter(new ArrayList<Map<String, String>>());
        adapter2 = new ChooseCityGridAdapter(new ArrayList<Map<String, String>>());
        adapter3 = new ChooseCityGridAdapter(new ArrayList<Map<String, String>>());

    }

    public void initdata() {
        presenter.getCity();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forget_right_return:
                this.finish();
                break;
        }
    }

    @Override
    public String getToken() {
        return SharedPreferencesUtils.getParam(x.app(), User.TOKEN, "").toString();
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public String getStrict() {
        return district;
    }

    @Override
    public void hidell_hot_districts() {
        llHotDistricts.setVisibility(View.GONE);
    }

    @Override
    public void hidell_hot_area() {
        llHotArea.setVisibility(View.GONE);
    }

    @Override
    public void showll_hot_districts() {
        llHotDistricts.setVisibility(View.VISIBLE);
    }

    @Override
    public void showll_hot_area() {
        llHotArea.setVisibility(View.VISIBLE);
    }

    @Override
    public void Timeout() {
        Toast.makeText(x.app(), "登录超时", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RingChooseActivity.this, LoginActivity.class);
        SharedPreferencesUtils.setParam(x.app(), User.TOKEN, "");
        JPushUtil.setAlias(x.app(), "");
        startActivity(intent);
        instance.finish();
        HomeActivity.instance.finish();
        PerfectAdvertisingActivity.instance.finish();
    }

    @Override
    public void Error() {
        Toast.makeText(x.app(), "获取失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Success(final int status, final List<Map<String, String>> list) {
        // 0 -城市列表   1 -区域列表   2 -商圈列表
        if (status == 0) {
            adapter.setmData(list);
            hotCityGrid.setAdapter(adapter);
            hotCityGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    adapter.setSelectPosition(i);
                    adapter.notifyDataSetChanged();
                    city = adapter.getItem(i);
                    presenter.getDistricts();
                }
            });
        } else if (status == 1) {
            adapter2.setmData(list);
            hotDistrictsGrid.setAdapter(adapter2);
            hotDistrictsGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    adapter2.setSelectPosition(i);
                    adapter2.notifyDataSetChanged();
                    district = adapter2.getItem(i);
                    presenter.getArea();
                }
            });
        } else if (status == 2) {
            adapter3.setmData(list);
            hotAreaGrid.setAdapter(adapter3);
            hotAreaGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    adapter3.setSelectPosition(i);
                    adapter3.notifyDataSetChanged();
                    String area = list.get(i).get("id");
                    Intent intent = new Intent(RingChooseActivity.this, DateDeviceActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}
