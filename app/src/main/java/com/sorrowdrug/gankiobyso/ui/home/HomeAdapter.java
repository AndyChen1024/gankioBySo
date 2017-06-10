package com.sorrowdrug.gankiobyso.ui.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sorrowdrug.gankiobyso.R;
import com.sorrowdrug.gankiobyso.activity.ArticleWebView;
import com.sorrowdrug.gankiobyso.activity.PhotoActivity;
import com.sorrowdrug.gankiobyso.ui.home.bean.HomeBean;
import com.sorrowdrug.gankiobyso.ui.home.bean.ItemType;

import java.util.ArrayList;

/**
 * Created by chentaikang on 2017/5/28 00:52.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeHolder> {

    private final LayoutInflater inflater;
    private Context context;
    private ArrayList<ItemType> datas;

    public HomeAdapter(Context context, ArrayList<ItemType> datas) {

        this.context = context;
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        return datas.get(position).getType();
    }

    @Override
    public HomeHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType){
            case ItemType.TYPE_TITLE:
                View title = inflater.inflate(R.layout.item_home_title,parent,false);
                return new TitleHolder(title);
            case ItemType.TYPE_IMAGE:
                View image = inflater.inflate(R.layout.item_home_image,parent,false);
                return new ImageHolder(context,image);
            case ItemType.TYPE_SUB_TITLE:
                View subTitle = inflater.inflate(R.layout.item_home_subtitle,parent,false);
                return new SubTitleHolder(subTitle);
            case ItemType.TYPE_CONTENT:
                View content = inflater.inflate(R.layout.item_home_content,parent,false);
                return new ContentHolder(content);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(HomeHolder holder, final int position) {
        holder.fill(datas.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                if(getItemViewType(position)==ItemType.TYPE_IMAGE){
                    intent=new Intent(context, PhotoActivity.class);
                    Toast.makeText(context,"点击了图片",Toast.LENGTH_SHORT).show();
                }else {
                    intent = new Intent(context, ArticleWebView.class);
                    Toast.makeText(context,"跳转到详情界面",Toast.LENGTH_SHORT).show();
                }
                HomeBean bean = (HomeBean) datas.get(position).getData();
                intent.putExtra("url",bean.getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas!=null?datas.size():0;
    }

    public static abstract class HomeHolder extends RecyclerView.ViewHolder{
        public HomeHolder(View itemView) {
            super(itemView);
        }
        public abstract void fill(ItemType data);
    }

    private class TitleHolder extends HomeHolder {

        private TextView title;
        public TitleHolder(View itemView) {

            super(itemView);
            title = (TextView) itemView.findViewById(R.id.home_title);
        }

        @Override
        public void fill(ItemType data) {
            title.setText((String)data.getData());
        }
    }

    private class ImageHolder extends HomeHolder {
        private ImageView imageView;
        private Context context;
        public ImageHolder(Context context,View itemView) {
            super(itemView);
            this.context = context;
            imageView = (ImageView) itemView.findViewById(R.id.home_image);
        }

        @Override
        public void fill(ItemType data) {
            HomeBean bean = (HomeBean) data.getData();
            String images = bean.getUrl();
            Glide.with(context).load(images).into(imageView);
        }
    }

    private class SubTitleHolder extends HomeHolder {
        private TextView subTitle;
        public SubTitleHolder(View itemView) {
            super(itemView);
            subTitle = (TextView) itemView.findViewById(R.id.home_subtitle);
        }

        @Override
        public void fill(ItemType data) {
            subTitle.setText((String)data.getData());
        }
    }

    private class ContentHolder extends HomeHolder {
        private TextView content;
        public ContentHolder(View itemView) {
            super(itemView);
            content = (TextView) itemView.findViewById(R.id.home_content);
        }

        @Override
        public void fill(ItemType data) {
            HomeBean bean = (HomeBean) data.getData();
            content.setText(bean.getDesc());
        }
    }
}
