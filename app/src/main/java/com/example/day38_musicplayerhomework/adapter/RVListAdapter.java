package com.example.day38_musicplayerhomework.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.day38_musicplayerhomework.R;
import com.example.day38_musicplayerhomework.bean.ListMusicEntity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by NYR on 2016/10/26.
 */
public class RVListAdapter extends RecyclerView.Adapter<RVListAdapter.ViewHolder> {

    @BindView(R.id.item_activity_music_list_thumb)
    ImageView thumb;
    @BindView(R.id.item_activity_music_list_title)
    TextView title;
    @BindView(R.id.item_activity_music_list_artist)
    TextView artist;
    private List<ListMusicEntity.ResultBean.SonglistBean> datas;
    private LayoutInflater inflater;
    private Context context;
    private itemOnClickListener listener;

    public List<ListMusicEntity.ResultBean.SonglistBean> getDatas() {
        return datas;
    }

    public void setListener(itemOnClickListener listener) {
        this.listener = listener;
    }

    public RVListAdapter(Context context) {
        this.datas = new ArrayList<>();
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_activity_music_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //设置文字
        if(datas.get(position).getTitle()!=null){
            holder.title.setText(datas.get(position).getTitle());
        }
        if(datas.get(position).getArtist()!=null) {
            holder.artist.setText(datas.get(position).getArtist());
        }

        //设置图片
        if (!datas.get(position).getThumb().equals("")){
            Picasso.with(context)
                    .load(datas.get(position).getThumb())
                    .into(holder.thumb);
        }

    }

    @Override
    public int getItemCount() {
        return datas.size()-1;
    }

    public void addAll(List<ListMusicEntity.ResultBean.SonglistBean> dd) {
        datas.addAll(dd);
        notifyDataSetChanged();
    }

     class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.item_activity_music_list_thumb)
        ImageView thumb;
        @BindView(R.id.item_activity_music_list_title)
        TextView title;
        @BindView(R.id.item_activity_music_list_artist)
        TextView artist;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            listener.onClickListener(view, getAdapterPosition());
        }
    }

    public interface itemOnClickListener {
        public void onClickListener(View itemView, int position);
    }

}
