package net.bjyfkj.caa.UI.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zhy.autolayout.AutoLinearLayout;

import net.bjyfkj.caa.R;
import net.bjyfkj.caa.UI.activity.LoginActivity;
import net.bjyfkj.caa.UI.activity.PerfectAdvertisingActivity;
import net.bjyfkj.caa.app.ActivityCollector;
import net.bjyfkj.caa.constant.User;
import net.bjyfkj.caa.entity.IndexInfo;
import net.bjyfkj.caa.mvp.presenter.HomePresenter;
import net.bjyfkj.caa.mvp.view.IHomeView;
import net.bjyfkj.caa.util.JPushUtil;
import net.bjyfkj.caa.util.SharedPreferencesUtils;

import org.xutils.x;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by YFKJ-1 on 2016/12/15.
 */

public class HomeFragment extends Fragment implements IHomeView, View.OnClickListener {
    @InjectView(R.id.ll_werben)
    AutoLinearLayout llWerben;
    @InjectView(R.id.ll_release_null)
    AutoLinearLayout llReleaseNull;
    @InjectView(R.id.ll_home_release)
    AutoLinearLayout llHomeRelease;
    @InjectView(R.id.tv_divice_count)
    TextView tvDiviceCount;
    @InjectView(R.id.tv_play_count)
    TextView tvPlayCount;
    @InjectView(R.id.tv_get_count)
    TextView tvGetCount;
    @InjectView(R.id.tv_use_count)
    TextView tvUseCount;
    @InjectView(R.id.ll_home_ring)
    AutoLinearLayout llHomeRing;
    @InjectView(R.id.btn_detail)
    Button btnDetail;
    private View view;
    private HomePresenter homePresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.inject(this, view);
        init();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    public void init() {
        homePresenter = new HomePresenter(this);
        btnDetail.setOnClickListener(this);
        llWerben.setOnClickListener(this);
    }

    public void initData() {
        homePresenter.index();
    }

    @Override
    public String getToken() {
        return SharedPreferencesUtils.getParam(x.app(), User.TOKEN, "").toString();
    }

    @Override
    public void indexError() {

    }

    @Override
    public void timeout() {
        SharedPreferencesUtils.setParam(x.app(), User.TOKEN, "");
        JPushUtil.setAlias(x.app(), "");
        ActivityCollector.finishAll();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);

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
        btnDetail.setClickable(false);
    }

    @Override
    public void showll_home_release() {
        llHomeRelease.setVisibility(View.VISIBLE);
        btnDetail.setClickable(false);
    }

    @Override
    public void showll_home_ring(IndexInfo.DataBean index) {
        llHomeRing.setVisibility(View.VISIBLE);
        tvDiviceCount.setText(index.getDevice_count());
        tvGetCount.setText(index.getGet_count());
        tvPlayCount.setText(index.getPlay_count());
        tvUseCount.setText(index.getUse_count());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_detail:

                break;
            case R.id.ll_werben:
                Intent intent = new Intent(getActivity(), PerfectAdvertisingActivity.class);
                startActivity(intent);
                break;
        }
    }
}
