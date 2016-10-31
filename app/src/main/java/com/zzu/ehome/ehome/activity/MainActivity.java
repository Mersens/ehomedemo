package com.zzu.ehome.ehome.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zzu.ehome.ehome.R;
import com.zzu.ehome.ehome.fragment.HealthFragment;
import com.zzu.ehome.ehome.fragment.HomeFragment;
import com.zzu.ehome.ehome.fragment.MsgFragment;
import com.zzu.ehome.ehome.fragment.UserCenterFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout layout_home;
    private RelativeLayout layout_health;
    private RelativeLayout layout_msg;
    private RelativeLayout layout_usercenter;

    private ImageView img_home;
    private ImageView img_health;
    private ImageView img_msg;
    private ImageView img_usercenter;

    private TextView tv_home;
    private TextView tv_health;
    private TextView tv_msg;
    private TextView tv_usercenter;

    private Fragment[] fragments;
    private int currentIndex;
    private int index;

    private int selectColor;
    private int unSelectColor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    public void init() {
        initViews();
        initEvent();
        initData();

    }

    public void initViews(){
        layout_health=(RelativeLayout)findViewById(R.id.layout_health);
        layout_home=(RelativeLayout)findViewById(R.id.layout_home);
        layout_msg=(RelativeLayout)findViewById(R.id.layout_msg);
        layout_usercenter=(RelativeLayout)findViewById(R.id.layout_usercenter);

        img_home=(ImageView)findViewById(R.id.img_home);
        img_health=(ImageView)findViewById(R.id.img_health);
        img_msg=(ImageView)findViewById(R.id.img_msg);
        img_usercenter=(ImageView)findViewById(R.id.img_usercenter);

        tv_home=(TextView)findViewById(R.id.tv_home);
        tv_health=(TextView)findViewById(R.id.tv_health);
        tv_msg=(TextView)findViewById(R.id.tv_msg);
        tv_usercenter=(TextView)findViewById(R.id.tv_usercenter);
    }

    public void initEvent(){
        layout_home.setOnClickListener(this);
        layout_health.setOnClickListener(this);
        layout_msg.setOnClickListener(this);
        layout_usercenter.setOnClickListener(this);
    }

    public void initData(){
        selectColor=getResources().getColor(R.color.bottom_text_color_pressed);
        unSelectColor=getResources().getColor(R.color.bottom_text_color_normal);

        fragments=new Fragment[4];
        fragments[0]= HomeFragment.getInstance();
        fragments[1]= HealthFragment.getInstance();
        fragments[2]= MsgFragment.getInstance();
        fragments[3]= UserCenterFragment.getInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_content,fragments[0]).commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.layout_home:
                index=0;
                setTabs(index);
                break;
            case R.id.layout_health:
                index=1;
                setTabs(index);
                break;
            case R.id.layout_msg:
                index=2;
                setTabs(index);
                break;
            case R.id.layout_usercenter:
                index=3;
                setTabs(index);
                break;
        }
        if(currentIndex!=index){
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.hide(fragments[currentIndex]);
            if(!fragments[index].isAdded()){
                ft.add(R.id.frame_content,fragments[index]);
            }
            ft.show(fragments[index]).commit();
        }
        currentIndex=index;
    }

    public void setTabs(int pos){
        resetColor();
        switch (pos){
            case 0:
                img_home.setImageResource(R.mipmap.icon_home_pressed);
                tv_home.setTextColor(selectColor);
                break;
            case 1:
                img_health.setImageResource(R.mipmap.icon_health_pressed);
                tv_health.setTextColor(selectColor);
                break;
            case 2:
                img_msg.setImageResource(R.mipmap.icon_msg_pressed);
                tv_msg.setTextColor(selectColor);
                break;
            case 3:
                img_usercenter.setImageResource(R.mipmap.icon_user_pressed);
                tv_usercenter.setTextColor(selectColor);
                break;
        }

    }
    public void resetColor(){
        img_home.setImageResource(R.mipmap.icon_home_normal);
        img_health.setImageResource(R.mipmap.icon_health_normal);
        img_msg.setImageResource(R.mipmap.icon_msg_normal);
        img_usercenter.setImageResource(R.mipmap.icon_user_normal);

        tv_home.setTextColor(unSelectColor);
        tv_health.setTextColor(unSelectColor);
        tv_msg.setTextColor(unSelectColor);
        tv_usercenter.setTextColor(unSelectColor);

    }

}
