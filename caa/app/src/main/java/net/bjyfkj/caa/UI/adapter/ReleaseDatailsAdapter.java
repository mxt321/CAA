package net.bjyfkj.caa.UI.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.bjyfkj.caa.R;
import net.bjyfkj.caa.entity.AdsEntity;

import org.xutils.x;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static net.bjyfkj.caa.common.Instance.imageOptions;

/**
 * Created by YFKJ-1 on 2017/1/7.
 */

public class ReleaseDatailsAdapter extends BaseAdapter {
    private List<AdsEntity.DataBean> mData;
    private LayoutInflater mInflater;
    private Context mContent;

    public ReleaseDatailsAdapter(Context mContent) {
        this.mContent = mContent;
    }

    public void setmData(List<AdsEntity.DataBean> mData) {
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(mContent).inflate(R.layout.list_release_details, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvPlayCount.setText(mData.get(i).getPlay_count().toString() + "次");
        holder.tvReceiveCount.setText(mData.get(i).getGet_count().toString() + "次");
        holder.tvShopMan.setText(mData.get(i).getUse_count().toString() + "人");
        holder.tvScreenName.setText(mData.get(i).getName().toString());
        x.image().bind(holder.imgLvReleaseDetails, mData.get(i).getLogo(), imageOptions);
        return view;
    }

    static class ViewHolder {
        @InjectView(R.id.img_lv_release_details)
        ImageView imgLvReleaseDetails;
        @InjectView(R.id.tv_play_count)
        TextView tvPlayCount;
        @InjectView(R.id.tv_receive_count)
        TextView tvReceiveCount;
        @InjectView(R.id.tv_shop_man)
        TextView tvShopMan;
        @InjectView(R.id.tv_screen_name)
        TextView tvScreenName;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
