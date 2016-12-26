package net.bjyfkj.caa.UI.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.bjyfkj.caa.R;
import net.bjyfkj.caa.entity.TimeEntity;

import java.util.List;


public class DateAdapter extends BaseAdapter {
    private List<TimeEntity.DataBean> mData;

    private int selectPosition = -1;

    public DateAdapter() {
    }

    public void setmData(List<TimeEntity.DataBean> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public TimeEntity.DataBean getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_date, null);
            holder = new ViewHolder();
            holder.mTextView = (TextView) convertView.findViewById(R.id.date);
            holder.mTextView2 = (TextView) convertView.findViewById(R.id.type);
            holder.mLinearLayout = (LinearLayout) convertView.findViewById(R.id.ll_date_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (position == selectPosition) {
            holder.mTextView.setSelected(true);
            holder.mTextView2.setSelected(true);
            holder.mLinearLayout.setSelected(true);

        } else {
            holder.mTextView.setSelected(false);
            holder.mTextView2.setSelected(false);
            holder.mLinearLayout.setSelected(false);
        }

        if (mData.get(position).getType() == 0) {
            holder.mTextView2.setText("中午");
        } else {
            holder.mTextView2.setText("晚上");
        }
        holder.mTextView.setText(mData.get(position).getDate());
        return convertView;
    }

    public class ViewHolder {
        TextView mTextView;
        TextView mTextView2;
        LinearLayout mLinearLayout;
    }
}
