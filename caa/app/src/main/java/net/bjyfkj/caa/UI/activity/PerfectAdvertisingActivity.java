package net.bjyfkj.caa.UI.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jaiky.imagespickers.ImageConfig;
import com.jaiky.imagespickers.ImageSelector;
import com.jaiky.imagespickers.ImageSelectorActivity;
import com.zhy.autolayout.AutoLayoutActivity;

import net.bjyfkj.caa.R;
import net.bjyfkj.caa.util.GlideLoader;

import org.xutils.x;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static net.bjyfkj.caa.R.id.et_end_time;
import static net.bjyfkj.caa.R.id.et_start_time;
import static net.bjyfkj.caa.R.id.promotion_get_type;
import static net.bjyfkj.caa.R.id.shop_address;

public class PerfectAdvertisingActivity extends AutoLayoutActivity implements View.OnClickListener, View.OnTouchListener {

    @InjectView(R.id.forget_right_return)
    LinearLayout forgetRightReturn;
    @InjectView(R.id.et_home_title)
    EditText etHomeTitle;
    @InjectView(R.id.content)
    EditText content;
    @InjectView(R.id.btn_home_addphoto)
    Button btnHomeAddphoto;
    @InjectView(R.id.ll_add_photo)
    LinearLayout llAddPhoto;
    @InjectView(R.id.item_img)
    ImageView itemImg;
    @InjectView(R.id.description)
    EditText description;
    @InjectView(R.id.shop_name)
    EditText shopName;
    @InjectView(shop_address)
    EditText shopAddress;
    @InjectView(R.id.promotion_count)
    EditText promotionCount;
    @InjectView(R.id.promotion_content)
    EditText promotionContent;
    @InjectView(R.id.et_valida_date)
    EditText etValidaDate;
    @InjectView(et_start_time)
    EditText etStartTime;
    @InjectView(et_end_time)
    EditText etEndTime;
    @InjectView(R.id.direction_for_use)
    EditText directionForUse;
    @InjectView(promotion_get_type)
    TextView promotionGetType;
    @InjectView(R.id.ll_add_Text)
    LinearLayout llAddText;
    @InjectView(R.id.scrollView1)
    ScrollView scrollView1;
    @InjectView(R.id.btn_infor_preview)
    Button btnInforPreview;
    @InjectView(R.id.btn_infor_next)
    Button btnInforNext;
    @InjectView(R.id.home_detail)
    LinearLayout homeDetail;
    @InjectView(R.id.llContainer)
    LinearLayout llContainer;
    static PerfectAdvertisingActivity instance;
    public static final int REQUEST_CODES = 123;//多选
    public static final int REQUEST_CODE = 456;//单选
    private ArrayList<String> path = new ArrayList<>();//多图片选择路径集合
    private ImageConfig imageConfig;
    private String item_path = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfect);
        ButterKnife.inject(this);
        init();
    }

    public void init() {
        instance = this;
        forgetRightReturn.setOnClickListener(this);
        btnHomeAddphoto.setOnClickListener(this);
        itemImg.setOnClickListener(this);
        etStartTime.setOnTouchListener(this);
        etEndTime.setOnTouchListener(this);
        promotionGetType.setOnClickListener(this);
        btnInforPreview.setOnClickListener(this);
        btnInforNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forget_right_return://返回上一界面
                this.finish();
                break;
            case R.id.btn_home_addphoto://选择轮播图片 （多选）
                imageConfig = new ImageConfig.Builder(
                        new GlideLoader())
                        .steepToolBarColor(getResources().getColor(R.color.colorOrange))
                        .titleBgColor(getResources().getColor(R.color.colorOrange))
                        .titleSubmitTextColor(getResources().getColor(R.color.white))
                        .titleTextColor(getResources().getColor(R.color.white))
                        // 开启多选   （默认为多选）
                        .mutiSelect()
                        // 多选时的最大数量   （默认 9 张）
                        .mutiSelectMaxSize(9)
                        //设置图片显示容器，参数：、（容器，每行显示数量，是否可删除）
                        .setContainer(llContainer, 4, true)
                        // 已选择的图片路径
                        .pathList(path)
                        // 拍照后存放的图片路径（默认 /temp/picture）
                        .filePath("/temp")
                        // 开启拍照功能 （默认关闭）
                        .showCamera()
                        .requestCode(REQUEST_CODES)
                        .build();
                ImageSelector.open(PerfectAdvertisingActivity.this, imageConfig);
                break;
            case R.id.item_img://选择商家图片
                imageConfig = new ImageConfig.Builder(
                        new GlideLoader())
                        .steepToolBarColor(getResources().getColor(R.color.colorOrange))
                        .titleBgColor(getResources().getColor(R.color.colorOrange))
                        .titleSubmitTextColor(getResources().getColor(R.color.white))
                        .titleTextColor(getResources().getColor(R.color.white))
                        // 开启单选   （默认为多选）
                        .singleSelect()
                        // 裁剪 (只有单选可裁剪)
                        //.crop()
                        // 开启拍照功能 （默认关闭）
                        .showCamera()
                        //设置显示容器
                        .requestCode(REQUEST_CODE)
                        .build();
                ImageSelector.open(PerfectAdvertisingActivity.this, imageConfig);
                break;
            case R.id.promotion_get_type://选择领取类型
                final String[] items = {"活动内限领一次", "使用后再次领取"};
                new AlertDialog.Builder(PerfectAdvertisingActivity.this)
                        .setItems(items, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 0) {
                                    promotionGetType.setText("活动内限领一次");
                                } else if (which == 1) {
                                    promotionGetType.setText("使用后再次领取");
                                }
                            }
                        }).show();
                break;
            case R.id.btn_infor_preview://预览
                if (isnull()) {
                    Intent intent = new Intent(PerfectAdvertisingActivity.this, AdvertisingPreviewActivity.class);
                    intent.putExtra("content", content.getText().toString());//活动内容
                    intent.putExtra("description", description.getText().toString());//活动描述
                    intent.putExtra("item_img", item_path);//商品图片
                    intent.putExtra("shop_address", shopAddress.getText().toString());//店铺地址
                    intent.putExtra("path", path.get(0));
                    startActivity(intent);
                }
                break;
            case R.id.btn_infor_next://下一步
                if (isnull()) {
                    Intent intent = new Intent(PerfectAdvertisingActivity.this, RingChooseActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }


    /***
     * 非空判断
     *
     * @return
     */
    public boolean isnull() {
        if (etHomeTitle.getText().toString().equals("")) {
            Toast.makeText(x.app(), "请输入标题", Toast.LENGTH_SHORT).show();
            return false;
        } else if (content.getText().toString().equals("")) {
            Toast.makeText(x.app(), "请输入活动内容", Toast.LENGTH_SHORT).show();
            return false;
        } else if (path.size() <= 0) {
            Toast.makeText(x.app(), "请添加海报图片", Toast.LENGTH_SHORT).show();
            return false;
        } else if (item_path.equals("")) {
            Toast.makeText(x.app(), "请添加商品图片", Toast.LENGTH_SHORT).show();
            return false;
        } else if (description.getText().toString().equals("")) {
            Toast.makeText(x.app(), "请输入活动描述", Toast.LENGTH_SHORT).show();
            return false;
        } else if (shopName.getText().toString().equals("")) {
            Toast.makeText(x.app(), "请输入店铺名称", Toast.LENGTH_SHORT).show();
            return false;
        } else if (shopAddress.getText().toString().equals("")) {
            Toast.makeText(x.app(), "请输入店铺地址", Toast.LENGTH_SHORT).show();
            return false;
        } else if (promotionCount.getText().toString().equals("")) {
            Toast.makeText(x.app(), "请输入红包个数", Toast.LENGTH_SHORT).show();
            return false;
        } else if (promotionContent.getText().toString().equals("")) {
            Toast.makeText(x.app(), "请输入红包内容", Toast.LENGTH_SHORT).show();
            return false;
        } else if (etValidaDate.getText().toString().equals("")) {
            Toast.makeText(x.app(), "请输入活动的有效期", Toast.LENGTH_SHORT).show();
            return false;
        } else if (etStartTime.getText().toString().equals("")) {
            Toast.makeText(x.app(), "请选择活动开始时间", Toast.LENGTH_SHORT).show();
            return false;
        } else if (etEndTime.getText().toString().equals("")) {
            Toast.makeText(x.app(), "请选择活动结束时间", Toast.LENGTH_SHORT).show();
            return false;
        } else if (directionForUse.getText().toString().equals("")) {
            Toast.makeText(x.app(), "请输入使用说明", Toast.LENGTH_SHORT).show();
            return false;
        } else if (promotionGetType.getText().toString().equals("")) {
            Toast.makeText(x.app(), "请输入领取类型", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODES && resultCode == RESULT_OK && data != null) {//多选
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
            for (String path : pathList) {
                Log.i("REQUEST_CODES", path + "");
            }
            path.clear();
            path.addAll(pathList);
        } else if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {//单选
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
            for (String path : pathList) {
                Log.i("REQUEST_CODE", path + "");
                item_path = path;
                Glide.with(x.app()).load(path).into(itemImg);
            }
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = View.inflate(this, R.layout.date_time_dialog, null);
            final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
            builder.setView(view);
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(System.currentTimeMillis());
            datePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), null);

            if (v.getId() == et_start_time) {
                final int inType = etStartTime.getInputType();
                etStartTime.setInputType(InputType.TYPE_NULL);
                etStartTime.onTouchEvent(event);
                etStartTime.setInputType(inType);
                etStartTime.setSelection(etStartTime.getText().length());
                builder.setTitle("选取起始时间");
                builder.setPositiveButton("确  定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuffer sb = new StringBuffer();
                        sb.append(String.format("%d-%02d-%02d", datePicker.getYear(), datePicker.getMonth() + 1, datePicker.getDayOfMonth()));
                        sb.append("");
                        etStartTime.setText(sb);
                        etEndTime.requestFocus();
                        dialog.cancel();
                    }
                });
                builder.setNegativeButton("取  消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
            } else if (v.getId() == et_end_time) {
                int inType = etEndTime.getInputType();
                etEndTime.setInputType(InputType.TYPE_NULL);
                etEndTime.onTouchEvent(event);
                etEndTime.setInputType(inType);
                etEndTime.setSelection(etEndTime.getText().length());
                builder.setTitle("选取结束时间");
                builder.setPositiveButton("确  定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        StringBuffer sb = new StringBuffer();
                        sb.append(String.format("%d-%02d-%02d",
                                datePicker.getYear(),
                                datePicker.getMonth() + 1,
                                datePicker.getDayOfMonth()));
                        sb.append("");
                        etEndTime.setText(sb);
                        dialog.cancel();
                    }
                });
                builder.setNegativeButton("取  消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
            }
            Dialog dialog = builder.create();
            dialog.show();
        }
        return true;
    }
}
