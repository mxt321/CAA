package net.bjyfkj.caa.UI.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import net.bjyfkj.caa.R;
import net.bjyfkj.caa.UI.adapter.TVPlayListAdapter;
import net.bjyfkj.caa.UI.adapter.TVTagsGridAdapter;
import net.bjyfkj.caa.app.BaseActivity;
import net.bjyfkj.caa.constant.User;
import net.bjyfkj.caa.entity.AdsDetailEntity;
import net.bjyfkj.caa.mvp.presenter.TVDetailsPresenter;
import net.bjyfkj.caa.mvp.view.ITVDetailsView;
import net.bjyfkj.caa.util.SharedPreferencesUtils;

import org.xutils.x;

import butterknife.ButterKnife;
import butterknife.InjectView;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class TVDetailsActitvity extends BaseActivity implements ITVDetailsView {
    @InjectView(R.id.forget_right_return)
    LinearLayout forgetRightReturn;
    @InjectView(R.id.ll_business_photo)
    ImageView llBusinessPhoto;
    @InjectView(R.id.tv_business_name)
    TextView tvBusinessName;
    @InjectView(R.id.gv_business_label)
    GridView gvBusinessLabel;
    @InjectView(R.id.lv_playing_ad)
    ListView lvPlayingAd;
    private Intent intent;
    private String device_id;
    private TVDetailsPresenter presenter;
    private TVPlayListAdapter tvPlayListAdapter;
    private TVTagsGridAdapter tvTagsGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvdetails_actitvity);
        ButterKnife.inject(this);
        init();
    }

    private void init() {
        intent = getIntent();
        device_id = intent.getStringExtra("device_id");
        presenter = new TVDetailsPresenter(this);
        tvPlayListAdapter = new TVPlayListAdapter(this);
        tvTagsGridAdapter = new TVTagsGridAdapter(this);
        forgetRightReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TVDetailsActitvity.this.finish();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        presenter.getAdsDetail();
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
        if (tvPlayListAdapter != null) {
            tvPlayListAdapter = null;
        }
        if (tvTagsGridAdapter != null) {
            tvTagsGridAdapter = null;
        }
    }

    @Override
    public String getToken() {
        return SharedPreferencesUtils.getParam(x.app(), User.TOKEN, "").toString();
    }

    @Override
    public String getDevice_id() {
        return device_id;
    }

    @Override
    public void getAdsDetailSuccess(AdsDetailEntity adsDetailEntity) {
        tvBusinessName.setText(adsDetailEntity.getData().getName().toString());
        Glide.with(this).load(adsDetailEntity.getData().getPhoto())
                .placeholder(R.mipmap.epu_loading_pic)
                .bitmapTransform(new RoundedCornersTransformation(this, 25, 0))
                .into(llBusinessPhoto);
        tvTagsGridAdapter.setmData(adsDetailEntity.getData().getTags());
        gvBusinessLabel.setAdapter(tvTagsGridAdapter);
        tvPlayListAdapter.setmData(adsDetailEntity.getData().getPlaylist());
        lvPlayingAd.setAdapter(tvPlayListAdapter);
    }

    @Override
    public void getAdsDetailError() {
        Toast.makeText(x.app(), "获取失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Timeout() {
        Toast.makeText(x.app(), "登录超时，请重新登录!", Toast.LENGTH_SHORT).show();
    }
}
