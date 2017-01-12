package net.bjyfkj.caa.UI.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.bjyfkj.caa.R;
import net.bjyfkj.caa.entity.AdsDetailEntity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by YFKJ-2 on 2016/11/28.
 */

public class TVPlayListAdapter extends BaseAdapter {

    private List<AdsDetailEntity.DataBean.PlaylistBean> mData;
    private Context mContext;

    public TVPlayListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setmData(List<AdsDetailEntity.DataBean.PlaylistBean> mData) {
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public AdsDetailEntity.DataBean.PlaylistBean getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.tv_play_list_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvPlay.setText(mData.get(position).getTitle());
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.tv_play)
        TextView tvPlay;
        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
