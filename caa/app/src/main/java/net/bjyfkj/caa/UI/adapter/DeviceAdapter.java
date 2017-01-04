package net.bjyfkj.caa.UI.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.bjyfkj.caa.R;
import net.bjyfkj.caa.entity.CheckDeviceEntity;
import net.bjyfkj.caa.entity.DeviceEntity;

import org.xutils.x;

import java.util.List;

import static net.bjyfkj.caa.common.Instance.imageOptions;

/**
 * Created by YFKJ-1 on 2016/12/30.
 */

public class DeviceAdapter extends BaseAdapter {
    private Context mContext;
    private List<DeviceEntity.DataBean> mDatabeans;
    private ViewHolder mViewHolder;

    public DeviceAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setmDatabeans(List<DeviceEntity.DataBean> mDatabeans) {
        this.mDatabeans = mDatabeans;
    }

    public void setmCheckDevice(List<CheckDeviceEntity> mCheckDevice) {
        for (CheckDeviceEntity checkDeviceEntity : mCheckDevice) {
            for (DeviceEntity.DataBean mDatabean : mDatabeans) {

            }
        }
    }

    @Override
    public int getCount() {
        return mDatabeans.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatabeans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_device, null);
            mViewHolder = new ViewHolder();
            mViewHolder.device_name = (TextView) view.findViewById(R.id.device_name);
            mViewHolder.iv_device_check = (ImageView) view.findViewById(R.id.iv_device_check);
            mViewHolder.iv_device = (ImageView) view.findViewById(R.id.iv_device);
            mViewHolder.iv_device_select = (ImageView) view.findViewById(R.id.iv_device_select);
            view.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) view.getTag();
        }
        DeviceEntity.DataBean mDataBean = mDatabeans.get(i);
        mViewHolder.device_name.setText(mDataBean.getName());
        //判断是否屏满
        if (mDataBean.getCurrent_ads() == mDataBean.getMax_ads()) {
            mViewHolder.iv_device_select.setVisibility(View.VISIBLE);
        } else {
            mViewHolder.iv_device_select.setVisibility(View.GONE);
            if (mDataBean.isselected()) {
                mViewHolder.iv_device_check.setVisibility(View.VISIBLE);
                mViewHolder.iv_device_select.setVisibility(View.VISIBLE);
            } else {
                mViewHolder.iv_device_check.setVisibility(View.GONE);
                mViewHolder.iv_device_select.setVisibility(View.GONE);
            }
        }
        x.image().bind(mViewHolder.iv_device, String.valueOf(mDataBean.getLogo()), imageOptions);
//        Glide.with(mContext).load(mDataBean.getLogo()).bitmapTransform(new RoundedCornersTransformation(mContext, 30, 0, RoundedCornersTransformation.CornerType.ALL                                                   )).crossFade(1000).into(mViewHolder.iv_device);
        return view;
    }

    class ViewHolder {
        TextView device_name;
        ImageView iv_device;
        ImageView iv_device_select;
        ImageView iv_device_check;
    }

}
