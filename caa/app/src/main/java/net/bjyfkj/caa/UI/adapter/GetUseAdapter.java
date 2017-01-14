package net.bjyfkj.caa.UI.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.bjyfkj.caa.R;
import net.bjyfkj.caa.entity.WxUserEntity;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by YFKJ-1 on 2017/1/13.
 */

public class GetUseAdapter extends RecyclerView.Adapter<GetUseAdapter.ViewHolder> {
    private Context mContext;
    private List<WxUserEntity.DataBean> dataList;


    public void addAllData(List<WxUserEntity.DataBean> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void clearData() {
        this.dataList.clear();
    }

    public GetUseAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nouse, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_nouse.setText(dataList.get(position).getNickname().toString());
        holder.tv_getTime.setText(dataList.get(position).getCreate_time().toString());
        Glide.with(mContext).load(dataList.get(position).getHeadimgurl())
                .bitmapTransform(new RoundedCornersTransformation(mContext, 25, 0))
                .into(holder.iv_use);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_nouse;
        private TextView tv_getTime;
        private ImageView iv_use;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_nouse = (TextView) itemView.findViewById(R.id.tv_nouse);
            tv_getTime = (TextView) itemView.findViewById(R.id.tv_getTime);
            iv_use = (ImageView) itemView.findViewById(R.id.iv_use);
        }
    }
}
