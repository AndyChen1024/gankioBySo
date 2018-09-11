package com.sorrowdrug.gankiobyso.ui.mine;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import com.sorrowdrug.gankiobyso.R;
import com.sorrowdrug.gankiobyso.ui.base.BaseFragment;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by chentaikang on 2017/5/21 18:09.
 */

public class MineFragment extends BaseFragment {
  private Switch swMine;
  private TextView tvMine;
  private Button btnTest, btnText, btn_dialog;
  private TextView tvTest;
  private EditText etTest;

  public MineFragment() {
  }

  public static MineFragment newInstance() {
    MineFragment fragment = new MineFragment();
    return fragment;
  }

  @Override
  protected int getLayoutResource() {
    return R.layout.fragment_mine;
  }

  @Override
  protected void onInitView(View view, Bundle savedInstanceState) {
    swMine = view.findViewById(R.id.sw_mine);
    tvMine = view.findViewById(R.id.tv_mine);
    btnText = view.findViewById(R.id.btn_text);
    btnTest = view.findViewById(R.id.btn_jump);
    tvTest = view.findViewById(R.id.tv_text);
    etTest = view.findViewById(R.id.et_text);
    btn_dialog = view.findViewById(R.id.btn_dialog);
    swMine.setChecked(false);
    swMine.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
          tvMine.setText("开始自闭");
        } else {
          tvMine.setText("活泼开朗");
        }
      }
    });
    btnTest.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        turnTest();
      }
    });
    btnTest.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        jump();
      }
    });
    btn_dialog.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        dialog();
      }
    });
  }

  private void dialog() {

  }

  private void jump() {
    String a = "";
    String b = "";
    if (a==b) {

    }
    ComponentName
        chatActivity =
        new ComponentName("com.anrongcheck", "com.anrongcheck.activity.AboutActivity");
    String sfzh = "62010219721124241X";
    Intent intent = new Intent();
    intent.setComponent(chatActivity);
    intent.putExtra("sfzh", sfzh);
    intent.putExtra("isSelf", false);
    startActivity(intent);
  }

  private void turnTest() {

    //String str = "测试字符转换 hello word"; //默认环境，已是UTF-8编码
    String str = etTest.getText().toString(); //默认环境，已是UTF-8编码
    try {
      //String strGBK = URLEncoder.encode(str, "GBK");
      //System.out.println(strGBK);
      //String strUTF8 = URLDecoder.decode(str, "UTF-8");
      //System.out.println(strUTF8);
      tvTest.setText(URLEncoder.encode(str, "GBK"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void onInitData() {

  }
}
