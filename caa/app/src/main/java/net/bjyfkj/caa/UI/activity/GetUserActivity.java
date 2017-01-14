package net.bjyfkj.caa.UI.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import net.bjyfkj.caa.R;
import net.bjyfkj.caa.UI.adapter.GetUseAdapter;
import net.bjyfkj.caa.app.BaseActivity;
import net.bjyfkj.caa.constant.User;
import net.bjyfkj.caa.entity.WxUserEntity;
import net.bjyfkj.caa.mvp.presenter.GetUserPresenter;
import net.bjyfkj.caa.mvp.view.IGetUserView;
import net.bjyfkj.caa.util.SharedPreferencesUtils;

import org.xutils.x;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class GetUserActivity extends BaseActivity implements IGetUserView, PullLoadMoreRecyclerView.PullLoadMoreListener {

    @InjectView(R.id.forget_right_return)
    LinearLayout forgetRightReturn;
    @InjectView(R.id.tv_noGet)
    TextView tvNoGet;
    @InjectView(R.id.lin_noget)
    LinearLayout linNoget;
    @InjectView(R.id.tv_Use)
    TextView tvUse;
    @InjectView(R.id.lin_use)
    LinearLayout linUse;
    @InjectView(R.id.tv_expired)
    TextView tvExpired;
    @InjectView(R.id.lin_expired)
    LinearLayout linExpired;
    @InjectView(R.id.linearLayout)
    LinearLayout linearLayout;
    @InjectView(R.id.recyclerview)
    PullLoadMoreRecyclerView recyclerview;

    private int from = 0;
    private String ads_id;
    private String status;
    private GetUserPresenter presenter;
    private Intent intent;
    private RecyclerView mRecyclerView;
    private GetUseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_user);
        ButterKnife.inject(this);
        init();
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
        if (intent != null) {
            intent = null;
        }
        if (adapter != null) {
            adapter = null;
        }
    }

    public void init() {
        intent = getIntent();
        ads_id = intent.getStringExtra("ads_id");
        status = intent.getStringExtra("status");
        presenter = new GetUserPresenter(this);
        mRecyclerView = recyclerview.getRecyclerView();
        //设置scrollbar无效
        mRecyclerView.setVerticalScrollBarEnabled(true);
        recyclerview.setRefreshing(true);
        //设置上拉加载的文字
        recyclerview.setFooterViewText("loading");
        //设置上拉记载的文字颜色
        recyclerview.setFooterViewTextColor(R.color.white);
        //设置加载更多背景色
        recyclerview.setFooterViewBackgroundColor(R.color.colorBackground);
        recyclerview.setLinearLayout();
        //上拉下拉监听
        recyclerview.setOnPullLoadMoreListener(this);
        //设置空数据的页面
        recyclerview.setEmptyView(LayoutInflater.from(this.getApplicationContext()).inflate(R.layout.empty_view, null));
        adapter = new GetUseAdapter(this);
        recyclerview.setAdapter(adapter);
    }

    public void initData() {
        presenter.getWxUserPromotion();
    }


    @OnClick({R.id.forget_right_return, R.id.lin_noget, R.id.lin_use, R.id.lin_expired})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forget_right_return:
                this.finish();
                break;
            case R.id.lin_noget:
                if (!status.equals("0")) {
                    status = "0";
                    presenter.getWxUserPromotion();
                }
                break;
            case R.id.lin_use:
                if (!status.equals("1")) {
                    status = "1";
                    presenter.getWxUserPromotion();
                }
                break;
            case R.id.lin_expired:
                if (!status.equals("2")) {
                    status = "2";
                    presenter.getWxUserPromotion();
                }
                break;
        }
    }

    @Override
    public String getToken() {
        return SharedPreferencesUtils.getParam(x.app(), User.TOKEN, "").toString();
    }

    @Override
    public void getWxUserPromotionSuccess(WxUserEntity wxUserEntity) {
        adapter.addAllData(wxUserEntity.getData());
        recyclerview.setPullLoadMoreCompleted();
    }

    @Override
    public void getWxUserPromotionError() {
        Toast.makeText(x.app(), "获取失败", Toast.LENGTH_SHORT).show();
        recyclerview.setPullLoadMoreCompleted();
    }

    @Override
    public void timeout() {
        Toast.makeText(x.app(), "登录超时，请重新登录", Toast.LENGTH_SHORT).show();
        recyclerview.setPullLoadMoreCompleted();
    }

    @Override
    public int getFrom() {
        return from;
    }

    @Override
    public String getAdsId() {
        return ads_id;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void clearColor() {
        tvNoGet.setTextColor(getResources().getColor(R.color.colorGree));
        tvUse.setTextColor(getResources().getColor(R.color.colorGree));
        tvExpired.setTextColor(getResources().getColor(R.color.colorGree));
    }

    @Override
    public void tvNoGetColor() {
        tvNoGet.setTextColor(getResources().getColor(R.color.colorOrange));
    }

    @Override
    public void tvUseColor() {
        tvUse.setTextColor(getResources().getColor(R.color.colorOrange));
    }

    @Override
    public void tvExpiredColor() {
        tvExpired.setTextColor(getResources().getColor(R.color.colorOrange));
    }

    @Override
    public void onRefresh() {
        from += 15;

    }

    @Override
    public void onLoadMore() {
        from = 0;
    }
}
