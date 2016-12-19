package net.bjyfkj.caa.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jaiky.imagespickers.ImageLoader;

import net.bjyfkj.caa.R;

/**
 * Created by YFKJ-1 on 2016/12/19.
 * 图片加载器
 */
public class GlideLoader implements ImageLoader {
    @Override
    public void displayImage(Context context, String path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .placeholder(R.mipmap.imageselector_photo)
                .centerCrop()
                .into(imageView);
    }
}
