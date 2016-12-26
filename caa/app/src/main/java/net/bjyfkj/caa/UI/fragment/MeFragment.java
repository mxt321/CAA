package net.bjyfkj.caa.UI.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import net.bjyfkj.caa.R;
import net.bjyfkj.caa.UI.activity.LoginActivity;
import net.bjyfkj.caa.UI.activity.UpNickNameActivity;
import net.bjyfkj.caa.constant.User;
import net.bjyfkj.caa.mvp.presenter.MePresenter;
import net.bjyfkj.caa.mvp.view.IMeView;
import net.bjyfkj.caa.util.FileUtil;
import net.bjyfkj.caa.util.JPushUtil;
import net.bjyfkj.caa.util.SelectPicPopupWindow;
import net.bjyfkj.caa.util.SharedPreferencesUtils;
import net.bjyfkj.caa.util.VProgressDialog;

import org.xutils.x;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;
import jp.wasabeef.glide.transformations.CropCircleTransformation;


/**
 * Created by YFKJ-1 on 2016/12/15.
 */

public class MeFragment extends Fragment implements View.OnClickListener, IMeView {
    @InjectView(R.id.iv_me)
    ImageView ivMe;
    @InjectView(R.id.tv_nickname)
    TextView tvNickname;
    @InjectView(R.id.btn_me_publish)
    Button btnMePublish;
    @InjectView(R.id.userlogout)
    TextView userlogout;
    private View v;
    private VProgressDialog vProgressDialog;
    private MePresenter mePresenter;
    private String urlpath; // 图片本地路径

    private SelectPicPopupWindow menuWindow; // 自定义的头像编辑弹出框
    private static final String IMAGE_FILE_NAME = "avatarImage.jpg";// 头像文件名称
    private static final int REQUESTCODE_PICK = 0; // 相册选图标记
    private static final int REQUESTCODE_TAKE = 1; // 相机拍照标记
    private static final int REQUESTCODE_CUTTING = 2;
    private File file;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_me, container, false);
        ButterKnife.inject(this, v);
        init();
        return v;
    }


    private void init() {
        mePresenter = new MePresenter(this);
        vProgressDialog = new VProgressDialog(getContext());
        userlogout.setOnClickListener(this);
        tvNickname.setOnClickListener(this);
        ivMe.setOnClickListener(this);
        menuWindow = new SelectPicPopupWindow(getContext(), itemsOnClick);
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    public void initData() {
        Glide.with(this).load(SharedPreferencesUtils.getParam(x.app(), User.HEADIMG, "")).bitmapTransform(new CropCircleTransformation(x.app())).into(ivMe);
        tvNickname.setText(SharedPreferencesUtils.getParam(x.app(), User.NICKNAME, "").toString());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.userlogout:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("你确定要退出吗？");
                builder.setTitle("温馨提示：");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        vProgressDialog.showProgressDlg("退出中...");
                        mePresenter.logout();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
                break;
            case R.id.iv_me:
                View viewById = v.findViewById(R.id.fragment_me);
                menuWindow.showAtLocation(viewById,
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.tv_nickname:
                Intent intent = new Intent(getActivity(), UpNickNameActivity.class);
                intent.putExtra("nickname", tvNickname.getText().toString());
                startActivity(intent);
                break;
        }
    }

    @Override
    public String getToken() {
        return SharedPreferencesUtils.getParam(x.app(), User.TOKEN, "").toString();
    }

    @Override
    public void logoutSuccess() {
        JPushUtil.setAlias(x.app(), "");
        vProgressDialog.dismissProgressDlg();
        SharedPreferencesUtils.setParam(x.app(), User.TOKEN, "");
        SharedPreferencesUtils.setParam(x.app(), User.NICKNAME, "");
        SharedPreferencesUtils.setParam(x.app(), User.HEADIMG, "");
        SharedPreferencesUtils.setParam(x.app(), User.USER_ID, "");
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void logoutError() {
        vProgressDialog.dismissProgressDlg();
        Log.i("logoutError", "退出登录失败");
    }

    @Override
    public String geturlPath() {
        return urlpath;
    }

    @Override
    public void timeout() {
        SharedPreferencesUtils.setParam(x.app(), User.TOKEN, "");
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void upHeadSuccess(String urlPath) {
        if (file.exists()) {
            file.delete();
        }
        Toast.makeText(x.app(), "上传成功", Toast.LENGTH_SHORT).show();
        SharedPreferencesUtils.setParam(x.app(), User.HEADIMG, urlPath);
        Glide.with(this).load(SharedPreferencesUtils.getParam(x.app(), User.HEADIMG, "")).bitmapTransform(new CropCircleTransformation(x.app())).into(ivMe);
    }

    @Override
    public void upHeadError() {
        Log.i("upHeadError", "上传失败");
        Toast.makeText(x.app(), "上传失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressDlg() {
        vProgressDialog.showProgressDlg("正在上传");
    }

    @Override
    public void dismissProgressDlg() {
        vProgressDialog.dismissProgressDlg();
    }

    View.OnClickListener itemsOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            menuWindow.dismiss();
            switch (v.getId()) {
                // 拍照
                case R.id.takePhotoBtn:
                    Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    // 下面这句指定调用相机拍照后的照片存储的路径
                    takeIntent
                            .putExtra(MediaStore.EXTRA_OUTPUT, Uri
                                    .fromFile(new File(Environment
                                            .getExternalStorageDirectory(),
                                            IMAGE_FILE_NAME)));
                    startActivityForResult(takeIntent, REQUESTCODE_TAKE);
                    break;
                // 相册选择图片
                case R.id.pickPhotoBtn:
                    Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
                    pickIntent
                            .setDataAndType(
                                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                    "image/*");
                    startActivityForResult(pickIntent, REQUESTCODE_PICK);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUESTCODE_PICK:// 直接从相册获取
                try {
                    if (data != null) {
                        startPhotoZoom(data.getData());
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();// 用户点击取消操作
                }
                break;
            case REQUESTCODE_TAKE:// 调用相机拍照
                file = new File(Environment.getExternalStorageDirectory()
                        + "/" + IMAGE_FILE_NAME);
                if (file.exists()) {
                    startPhotoZoom(Uri.fromFile(file));
                }

                break;
            case REQUESTCODE_CUTTING:// 取得裁剪后的图片
                if (data != null) {
                    setPicToView(data);
                }
                break;
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        if (uri != null) {
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(uri, "image/*");
            // crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
            intent.putExtra("crop", "true");
            // aspectX aspectY 是宽高的比例
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            // outputX outputY 是裁剪图片宽高
            intent.putExtra("outputX", 300);
            intent.putExtra("outputY", 300);
            intent.putExtra("return-data", true);
            startActivityForResult(intent, REQUESTCODE_CUTTING);
        }
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param picdata
     */
    private void setPicToView(Intent picdata) {
        Bundle extras = picdata.getExtras();
        if (extras != null) {
            // 取得SDCard图片路径做显示
            Bitmap photo = extras.getParcelable("data");
            if (photo != null) {
                Drawable drawable = new BitmapDrawable(null, photo);
                urlpath = FileUtil.saveFile(x.app(), "head.png", photo);
                mePresenter.upHead();
            }
        }
    }

}
