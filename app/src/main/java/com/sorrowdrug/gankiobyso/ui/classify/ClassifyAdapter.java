package com.sorrowdrug.gankiobyso.ui.classify;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sorrowdrug.gankiobyso.R;
import com.sorrowdrug.gankiobyso.activity.ArticleWebView;
import com.sorrowdrug.gankiobyso.ui.classify.bean.ClassifyBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chentaikang on 2017/6/8 21:20.
 */

public class ClassifyAdapter extends RecyclerView.Adapter<ClassifyAdapter.ClassifyHolder> {

    private final LayoutInflater inflater;
    private Context context;
    private ArrayList<ClassifyBean> datas;

    public static final int TYPE_IMAGE = 0;
    public static final int TYPE_NO_IMAGE = 1;

    public ClassifyAdapter(Context context, ArrayList<ClassifyBean> datas) {
        this.context = context;
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ClassifyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_IMAGE:
                view = inflater.inflate(R.layout.item_classify_image, parent, false);
                return new ClassifyImageHolder(context, view);
            case TYPE_NO_IMAGE:
                view = inflater.inflate(R.layout.item_classify_no_image, parent, false);
                return new ClassifyNoImageHolder(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        List<String> images = datas.get(position).getImages();
        if (images != null && datas.size() > 0) {
            return TYPE_IMAGE;
        } else {
            return TYPE_NO_IMAGE;
        }
    }

    @Override
    public void onBindViewHolder(ClassifyHolder holder, int position) {
        final ClassifyBean bean = datas.get(position);
        holder.fill(bean);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ArticleWebView.class);
                intent.putExtra("url",bean.getUrl());
                context.startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }


    private class ClassifyImageHolder extends ClassifyHolder {
        private Context context;
        private ImageView imageView;
        private TextView title;
        private TextView author;
        private TextView time;

        public ClassifyImageHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            imageView = (ImageView) itemView.findViewById(R.id.classify_image);
            title = (TextView) itemView.findViewById(R.id.classify_title);
            author = (TextView) itemView.findViewById(R.id.classify_author);
            time = (TextView) itemView.findViewById(R.id.classify_time);

        }

        @Override
        public void fill(ClassifyBean data) {

            title.setText(data.getDesc());
            author.setText(data.getWho());
            time.setText(data.getPublishedAt());
            String url = data.getImages().get(0);
            Glide.with(context).load(url).into(imageView);
        }
    }

    private class ClassifyNoImageHolder extends ClassifyHolder {

        private TextView title;
        private TextView author;
        private TextView time;

        public ClassifyNoImageHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.classify_title);
            author = (TextView) itemView.findViewById(R.id.classify_author);
            time = (TextView) itemView.findViewById(R.id.classify_time_no);
        }

        @Override
        public void fill(ClassifyBean data) {

            title.setText(data.getDesc());
            author.setText(data.getWho());
            time.setText(data.getPublishedAt());
        }
    }

    public static abstract class ClassifyHolder extends RecyclerView.ViewHolder {

        public ClassifyHolder(View itemView) {
            super(itemView);

        }

        public abstract void fill(ClassifyBean data);
    }
}
