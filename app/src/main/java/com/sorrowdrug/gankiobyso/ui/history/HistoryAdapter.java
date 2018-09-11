package com.sorrowdrug.gankiobyso.ui.history;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.sorrowdrug.gankiobyso.R;

import com.sorrowdrug.gankiobyso.activity.HistoryInfoActivity;
import com.sorrowdrug.gankiobyso.activity.MainActivity;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by chentaikang on 2017/6/12 08:52.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder> {

  private final LayoutInflater inflater;
  private Activity context;
  private ArrayList<HistoryBean> datas;
  private String img_url;
  private String baseUrl;
  private View iv;
  //    private List<Integer> heightList;

  public HistoryAdapter(Activity context, ArrayList<HistoryBean> datas) {
    this.context = context;
    this.datas = datas;
    inflater = LayoutInflater.from(context);
    baseUrl = "?imageView2/0/w/200";
  }

  @Override
  public HistoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = inflater.inflate(R.layout.item_history, parent, false);
    return new HistoryItemHolder(context, view);
  }

  @Override
  public void onBindViewHolder(HistoryHolder holder, int position) {
    final HistoryBean bean = datas.get(position);
    holder.fill(bean, position);
    //        heightList = new ArrayList<>();
    //        for (int i = 0; i < datas.size(); i++) {
    //            int height = new Random().nextInt(200) + 400;//[400,600)的随机高度
    //            heightList.add(height);
    //        }
    //        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
    //        params.height = heightList.get(position);
    //        holder.itemView.setLayoutParams(params);

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        iv = v.findViewById(R.id.iv_history);
        Intent intent = new Intent();
        intent.setClass(context, HistoryInfoActivity.class);
        intent.putExtra("img_url", bean.getUrl()+baseUrl);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
          iv.setTransitionName("share");
          ActivityOptions options =ActivityOptions.makeSceneTransitionAnimation(context,
              iv, "share");
          context.startActivity(intent,options.toBundle());
        }

        //startActivityForResult(intent, 1, options.toBundle());

        //context.startActivity(intent,
        //    ActivityOptions.makeSceneTransitionAnimation(context, iv, "share").toBundle());

        Snackbar.make(v, "准备跳转到当天详情界面", Snackbar.LENGTH_SHORT).show();
      }
    });
  }

  @Override
  public int getItemCount() {
    return datas == null ? 0 : datas.size();
  }

  private class HistoryItemHolder extends HistoryHolder {

    private Context context;
    private ImageView imageView;
    private TextView textView;

    public HistoryItemHolder(Context context, View view) {
      super(view);
      this.context = context;
      imageView = (ImageView) view.findViewById(R.id.iv_history);
      textView = (TextView) view.findViewById(R.id.tv_history);
    }

    @Override
    public void fill(HistoryBean data, int position) {
      img_url = data.getUrl() + baseUrl;//加载更小的图片,防止OOM
      RequestOptions options = new RequestOptions();
      options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
      Glide.with(context).load(img_url).apply(options).into(imageView);

      //            heightList = new ArrayList<>();
      //            for (int i = 0; i < datas.size(); i++) {
      //                int height = new Random().nextInt(200) + 400;//[400,600)的随机高度
      //                heightList.add(height);
      //            }
      //            ViewGroup.LayoutParams params_iv = imageView.getLayoutParams();
      //            params_iv.height = heightList.get(position);

      //            imageView.setLayoutParams(params_iv);

      textView.setText(data.getDesc());
      Log.i(TAG, "fill: " + data.getDesc());
    }
  }

  public static abstract class HistoryHolder extends RecyclerView.ViewHolder {

    public HistoryHolder(View itemView) {
      super(itemView);
    }

    public abstract void fill(HistoryBean data, int position);
  }
}
