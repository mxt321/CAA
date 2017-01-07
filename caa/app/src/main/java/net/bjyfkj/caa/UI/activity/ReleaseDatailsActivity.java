package net.bjyfkj.caa.UI.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import net.bjyfkj.caa.R;
import net.bjyfkj.caa.UI.adapter.ReleaseDatailsAdapter;
import net.bjyfkj.caa.app.ActivityCollector;
import net.bjyfkj.caa.app.BaseActivity;
import net.bjyfkj.caa.entity.AdsEntity;
import net.bjyfkj.caa.mvp.presenter.ReleaseDatailsPresenter;
import net.bjyfkj.caa.mvp.view.IReleaseDatailsView;
import net.bjyfkj.caa.util.SharedPreferencesUtils;

import org.xutils.x;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static net.bjyfkj.caa.R.id.lv_release_detail;

public class ReleaseDatailsActivity extends BaseActivity implements IReleaseDatailsView, SwipeRefreshLayout.OnRefreshListener {

    @InjectView(R.id.forget_right_return)
    LinearLayout forgetRightReturn;
    @InjectView(R.id.releasing)
    TextView releasing;
    @InjectView(lv_release_detail)
    ListView lvReleaseDetail;
    @InjectView(R.id.id_swipe_ly)
    SwipeRefreshLayout idSwipeLy;

    private Intent intent;
    private String ads_id = "";
    private ReleaseDatailsPresenter presenter;
    private ReleaseDatailsAdapter adapter;
    private List<AdsEntity.DataBean> mData;

    private static final int REFRESH_COMPLETE = 0X110;
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    presenter.getMyAdsList();
                    adapter.notifyDataSetChanged();
                    idSwipeLy.setRefreshing(false);
            }
        }
    };

    @Override
    public void onRefresh() {
        mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_datails);
        ButterKnife.inject(this);
        init();
    }

    private void init() {
        presenter = new ReleaseDatailsPresenter(this);
        intent = getIntent();
        ads_id = intent.getStringExtra("ads_id");
        adapter = new ReleaseDatailsAdapter(this);
        forgetRightReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReleaseDatailsActivity.this.finish();
            }
        });
        idSwipeLy.setOnRefreshListener(this);
        lvReleaseDetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String deivice_id = mData.get(i).getDevice_id();

            }
        });
    }

    public void initData() {
        presenter.getMyAdsList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (intent != null) {
            intent = null;
        }
        if (presenter != null) {
            presenter = null;
        }
        if (adapter != null) {
            adapter = null;
        }
    }

    @Override
    public String getToken() {
        return SharedPreferencesUtils.getParam(x.app(), "token", "").toString();
    }

    @Override
    public String getAds_id() {
        return ads_id;
    }

    @Override
    public void getMyAdsListSuccess(AdsEntity adsEntity) {
        mData = adsEntity.getData();
        adapter.setmData(mData);
        lvReleaseDetail.setAdapter(adapter);
        releasing.setText(mData.size() + "屏");
    }

    @Override
    public void getMyAdsListError() {
        Toast.makeText(x.app(), "获取失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void timeout() {
        ActivityCollector.finishAll();
        Intent intent = new Intent(ReleaseDatailsActivity.this, LoginActivity.class);
        startActivity(intent);
    }

}
