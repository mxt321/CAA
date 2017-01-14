package net.bjyfkj.caa.UI.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.bjyfkj.caa.R;
import net.bjyfkj.caa.entity.MyReleaseEntity;

import java.util.List;

/**
 * Created by YFKJ-1 on 2016/11/4.
 */
public class StaggerdRecyclerViewAdapter extends RecyclerView.Adapter<StaggerdRecyclerViewAdapter.ViewHolder> {
    private Context mContext;
    private List<MyReleaseEntity.DataBean.HistoryBean> mData;

    public StaggerdRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setmData(List<MyReleaseEntity.DataBean.HistoryBean> mData) {
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_date, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mData.get(position).getType().equals("0")) {
            holder.type.setText("中午");
        } else {
            holder.type.setText("晚上");
        }
        holder.date.setText(mData.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView date;
        private TextView type;

        public ViewHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.date);
            type = (TextView) itemView.findViewById(R.id.type);
        }
    }


}
