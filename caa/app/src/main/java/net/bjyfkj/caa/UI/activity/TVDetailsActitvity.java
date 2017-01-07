package net.bjyfkj.caa.UI.activity;

import android.content.Intent;
import android.os.Bundle;

import net.bjyfkj.caa.R;
import net.bjyfkj.caa.app.BaseActivity;

public class TVDetailsActitvity extends BaseActivity {
    private Intent intent;
    private String device_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvdetails_actitvity);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (intent != null) {
            intent = null;
        }
    }
}
