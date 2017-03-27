package com.example.day38_musicplayerhomework.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.day38_musicplayerhomework.R;
import com.example.day38_musicplayerhomework.bean.GridMusicEntity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by NYR on 2016/10/26.
 */
public class RVadapter extends RecyclerView.Adapter<RVadapter.ViewHolder> {

    private List<GridMusicEntity.ResultBean.ChannellistBean> datas;
    private LayoutInflater inflater;
    private Context context;
    private itemOnClickListener listener;

    public List<GridMusicEntity.ResultBean.ChannellistBean> getDatas() {
        return datas;
    }

    public void setListener(itemOnClickListener listener) {
        this.listener = listener;
    }

    public RVadapter(Context context) {
        this.datas = new ArrayList<>();
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_fragment_main_grid, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //设置文字
        if(datas.get(position).getName()!=null){
            holder.name.setText(datas.get(position).getName());
        }
        else {
            holder.name.setText("xxx");
        }

        //设置图片
        if (datas.get(position).getThumb()!=null){
            Picasso.with(context)
                    .load(datas.get(position).getThumb())
                    .transform(new CropCircleTransformation())
                    .into(holder.thumb);
        }
        /*else if (datas.get(position).getAvatar()!=null){
            Picasso.with(context)
                    .load(datas.get(position).getAvatar())
                    .transform(new CropCircleTransformation())
                    .into(holder.thumb);
        }*/else {
            holder.thumb.setBackgroundResource(R.mipmap.ic_launcher);
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void addAll(List<GridMusicEntity.ResultBean.ChannellistBean> dd) {
        datas.addAll(dd);
        notifyDataSetChanged();
    }

     class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.item_fragment_main_grid_thumb)
        ImageView thumb;
        @BindView(R.id.item_fragment_main_grid_name)
        TextView name;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClickListener(view,getAdapterPosition());
        }
    }
    public interface itemOnClickListener{
        public void onClickListener(View itemView ,int position);
    }
}
