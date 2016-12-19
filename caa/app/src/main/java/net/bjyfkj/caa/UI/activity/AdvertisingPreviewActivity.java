package net.bjyfkj.caa.UI.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhy.autolayout.AutoLayoutActivity;

import net.bjyfkj.caa.R;

import org.xutils.x;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class AdvertisingPreviewActivity extends AutoLayoutActivity implements View.OnClickListener {

    @InjectView(R.id.forget_right_return)
    LinearLayout forgetRightReturn;
    @InjectView(R.id.tv_detail_shop_address)
    TextView tvDetailShopAddress;
    @InjectView(R.id.tv_preview_content)
    TextView tvPreviewContent;
    @InjectView(R.id.iv_preview)
    ImageView ivPreview;
    @InjectView(R.id.iv_detail)
    ImageView ivDetail;
    @InjectView(R.id.tv_datail_text)
    TextView tvDatailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertising_preview);
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
        Intent intent = getIntent();
        String content = intent.getStringExtra("content");
        String description = intent.getStringExtra("description");
        String shop_address = intent.getStringExtra("shop_address");
        String itempath = intent.getStringExtra("item_img");
        String path = intent.getStringExtra("path");
        tvPreviewContent.setText(content);
        tvDetailShopAddress.setText(shop_address);
        tvDatailText.setText(description);
        Glide.with(x.app()).load(path).into(ivPreview);
        Glide.with(x.app()).load(itempath).into(ivDetail);

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
