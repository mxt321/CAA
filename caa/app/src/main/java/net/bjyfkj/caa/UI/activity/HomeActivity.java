package net.bjyfkj.caa.UI.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import net.bjyfkj.caa.R;
import net.bjyfkj.caa.UI.fragment.HomeFragment;
import net.bjyfkj.caa.UI.fragment.MeFragment;
import net.bjyfkj.caa.app.BaseActivity;
import net.bjyfkj.caa.util.DataUtil;

import org.xutils.x;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    @InjectView(R.id.home_home)
    RadioButton homeHome;
    @InjectView(R.id.home_me)
    RadioButton homeMe;
    static HomeActivity instance;
    private FragmentManager fm;
    private HomeFragment homeFragment;
    private MeFragment meFragment;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.inject(this);
        init();
    }

    public void init() {
        instance = this;
        selected_home();
        selected_me();
        homeHome.setOnClickListener(this);
        homeMe.setOnClickListener(this);
        fm = getSupportFragmentManager();
        homeFragment = new HomeFragment();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.content, homeFragment, "home").commit();
    }

    /**
     * 显示底部导航栏图标
     */
    public void selected_home() {
        Drawable DYDrawable = getResources().getDrawable(R.drawable.rdo_2_selector);
        DYDrawable.setBounds(0, DataUtil.dip2px(getApplicationContext(), 6), DataUtil.dip2px(getApplicationContext(), 25), DataUtil.dip2px(getApplicationContext(), 30));
        homeHome.setCompoundDrawables(null, DYDrawable, null, null);
        homeHome.setCompoundDrawablePadding(DataUtil.dip2px(getApplicationContext(), 3));
    }

    /**
     * 显示底部导航栏图标
     */
    public void selected_me() {
        Drawable DYDrawable = getResources().getDrawable(R.drawable.rdo_1_selector);
        DYDrawable.setBounds(0, DataUtil.dip2px(getApplicationContext(), 6), DataUtil.dip2px(getApplicationContext(), 25), DataUtil.dip2px(getApplicationContext(), 30));
        homeMe.setCompoundDrawables(null, DYDrawable, null, null);
        homeMe.setCompoundDrawablePadding(DataUtil.dip2px(getApplicationContext(), 3));
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction ft = fm.beginTransaction();
        hideAllFragment(ft);
        switch (view.getId()) {
            case R.id.home_home:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    ft.add(R.id.content, homeFragment, "home");
                } else {
                    ft.show(homeFragment);
                }
                break;
            case R.id.home_me:
                if (meFragment == null) {
                    meFragment = new MeFragment();
                    ft.add(R.id.content, meFragment, "me");
                } else {
                    ft.show(meFragment);
                }
                break;
        }
        ft.commit();
    }

    /**
     * 隐藏fragment
     *
     * @param ft
     */
    private void hideAllFragment(FragmentTransaction ft) {
        if (homeFragment != null) {
            ft.hide(homeFragment);
        }
        if (meFragment != null) {
            ft.hide(meFragment);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }


    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(x.app(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}
