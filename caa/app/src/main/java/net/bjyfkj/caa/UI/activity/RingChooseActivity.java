package net.bjyfkj.caa.UI.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;

import net.bjyfkj.caa.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RingChooseActivity extends AppCompatActivity implements View.OnClickListener {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring_choose);
        ButterKnife.inject(this);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initdata();
    }

    public void init() {
        forgetRightReturn.setOnClickListener(this);
    }

    public void initdata() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forget_right_return:
                this.finish();
                break;
        }
    }
}
