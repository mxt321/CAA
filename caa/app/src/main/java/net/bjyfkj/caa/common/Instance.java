package net.bjyfkj.caa.common;

import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.bjyfkj.caa.R;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;

/**
 * Created by YFKJ-2 on 2016/11/9.
 */

public class Instance {
    public static Gson gson = new GsonBuilder().create();

    public static ImageOptions imageOptions = new ImageOptions.Builder()
            .setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))
            .setRadius(DensityUtil.dip2px(10))
            .setUseMemCache(true)
            // 如果ImageView的大小不是定义为wrap_content, 不要crop.
            .setCrop(true) // 很多时候设置了合适的scaleType也不需要它.
            // 加载中或错误图片的ScaleType
            //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
            .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
            .setLoadingDrawableId(R.mipmap.epu_loading_pic)
            .setFailureDrawableId(R.mipmap.epu_loading_pic)
            .build();

    public static ImageOptions getImageOptionsptions = new ImageOptions.Builder()
            .setSize(DensityUtil.dip2px(150), DensityUtil.dip2px(150))
            .setRadius(DensityUtil.dip2px(360))
            .setUseMemCache(true)
            // 如果ImageView的大小不是定义为wrap_content, 不要crop.
            .setCrop(true) // 很多时候设置了合适的scaleType也不需要它.
            // 加载中或错误图片的ScaleType
            //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
            .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
            .setLoadingDrawableId(R.mipmap.epu_loading_pic)
            .setFailureDrawableId(R.mipmap.epu_loading_pic)
            .build();


    public static ImageOptions TVimageOptions = new ImageOptions.Builder()
            .setSize(DensityUtil.dip2px(320), DensityUtil.dip2px(210))
            .setRadius(DensityUtil.dip2px(10))
            .setUseMemCache(true)
            // 如果ImageView的大小不是定义为wrap_content, 不要crop.
//           .setCrop(true) // 很多时候设置了合适的scaleType也不需要它.
            // 加载中或错误图片的ScaleType
            .setIgnoreGif(true)
            .setPlaceholderScaleType(ImageView.ScaleType.CENTER_CROP)
            .setImageScaleType(ImageView.ScaleType.FIT_CENTER)
            .setLoadingDrawableId(R.mipmap.epu_loading_pic)
            .setFailureDrawableId(R.mipmap.epu_loading_pic)
            .build();
}
