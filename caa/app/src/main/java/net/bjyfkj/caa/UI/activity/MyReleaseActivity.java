package net.bjyfkj.caa.UI.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import net.bjyfkj.caa.R;
import net.bjyfkj.caa.UI.adapter.StaggerdRecyclerViewAdapter;
import net.bjyfkj.caa.app.BaseActivity;
import net.bjyfkj.caa.constant.User;
import net.bjyfkj.caa.entity.MyReleaseEntity;
import net.bjyfkj.caa.mvp.presenter.MyReleasePresenter;
import net.bjyfkj.caa.mvp.view.IMyReleaseView;
import net.bjyfkj.caa.util.SharedPreferencesUtils;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MyReleaseActivity extends BaseActivity implements IMyReleaseView, PullLoadMoreRecyclerView.PullLoadMoreListener, View.OnClickListener {

    @InjectView(R.id.forget_right_return)
    LinearLayout forgetRightReturn;
    @InjectView(R.id.ll_release_null)
    LinearLayout llReleaseNull;
    @InjectView(R.id.ll_home_release)
    LinearLayout llHomeRelease;
    @InjectView(R.id.tv_divice_count)
    TextView tvDiviceCount;
    @InjectView(R.id.tv_play_count)
    TextView tvPlayCount;
    @InjectView(R.id.tv_get_count)
    TextView tvGetCount;
    @InjectView(R.id.noget)
    RelativeLayout noget;
    @InjectView(R.id.tv_use_count)
    TextView tvUseCount;
    @InjectView(R.id.get_use)
    RelativeLayout getUse;
    @InjectView(R.id.btn_detail)
    Button btnDetail;
    @InjectView(R.id.clear_release)
    Button clearRelease;
    @InjectView(R.id.ll_home_ring)
    LinearLayout llHomeRing;
    @InjectView(R.id.mPullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private StaggerdRecyclerViewAdapter adapter;

    private int from = 0;
    private MyReleasePresenter presenter;
    private List<MyReleaseEntity.DataBean.HistoryBean> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_release);
        ButterKnife.inject(this);
        init();
    }

    public void init() {
        presenter = new MyReleasePresenter(this);
        forgetRightReturn.setOnClickListener(this);
        btnDetail.setOnClickListener(this);
        clearRelease.setOnClickListener(this);
        adapter = new StaggerdRecyclerViewAdapter(this);
        mPullLoadMoreRecyclerView.setStaggeredGridLayout(2);
        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        presenter.myAds();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter = null;
        }
        if (adapter != null) {
            adapter = null;
        }
    }

    public void initData() {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forget_right_return:
                MyReleaseActivity.this.finish();
                break;
            case R.id.btn_detail:
                break;
            case R.id.clear_release:
                break;
        }
    }

    @Override
    public void onRefresh() {
        from = 0;
        presenter.myAds();
    }

    @Override
    public void onLoadMore() {
        presenter.myAds();
    }

    @Override
    public String getToken() {
        return SharedPreferencesUtils.getParam(x.app(), User.TOKEN, "").toString();
    }

    @Override
    public int getFrom() {
        return from;
    }

    @Override
    public void timeout() {
        Toast.makeText(x.app(), "登录超时，请重新登录", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void myAdsError() {
        Toast.makeText(x.app(), "获取失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hidell_release_null() {
        llReleaseNull.setVisibility(View.GONE);
    }

    @Override
    public void hidell_home_release() {
        llHomeRelease.setVisibility(View.GONE);
    }

    @Override
    public void hidell_home_ring() {
        llHomeRing.setVisibility(View.GONE);
    }

    @Override
    public void showll_release_null() {
        llReleaseNull.setVisibility(View.VISIBLE);
    }

    @Override
    public void showll_home_release() {
        llHomeRelease.setVisibility(View.VISIBLE);
    }

    @Override
    public void showll_home_ring(MyReleaseEntity.DataBean.InProgressBean inProgressBean) {
        llHomeRing.setVisibility(View.VISIBLE);
        tvDiviceCount.setText(inProgressBean.getDevice_count().toString());
        tvPlayCount.setText(inProgressBean.getPlay_count().toString());
        tvGetCount.setText(inProgressBean.getGet_count().toString());
        tvUseCount.setText(inProgressBean.getUse_count().toString());
    }

    @Override
    public void getHistory(List<MyReleaseEntity.DataBean.HistoryBean> historyBean) {
        from += 15;
        for (MyReleaseEntity.DataBean.HistoryBean bean : historyBean) {
            mData.add(bean);
        }
        adapter.setmData(mData);
        adapter.notifyDataSetChanged();
        mPullLoadMoreRecyclerView.setAdapter(adapter);
    }

}
