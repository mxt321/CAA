package net.bjyfkj.caa.UI.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.bjyfkj.caa.R;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/7.
 * 选择城市通用适配器
 */

public class ChooseCityGridAdapter extends BaseAdapter {
    private List<Map<String, String>> mData;

    private int selectPosition = -1;

    public ChooseCityGridAdapter(List<Map<String, String>> data) {
        mData = data;
    }

    public void setmData(List<Map<String, String>> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public String getItem(int position) {
        return mData.get(position).get("name");
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_city_item, null);
            holder = new ViewHolder();
            holder.mTextView = (TextView) convertView.findViewById(R.id.root);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (position == selectPosition) {
            holder.mTextView.setSelected(true);
        } else {
            holder.mTextView.setSelected(false);
        }
        holder.mTextView.setText(mData.get(position).get("name"));
        return convertView;
    }

    public class ViewHolder {
        TextView mTextView;
    }
}
