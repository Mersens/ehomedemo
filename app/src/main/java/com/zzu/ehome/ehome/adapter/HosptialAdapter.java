package com.zzu.ehome.ehome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zzu.ehome.ehome.R;
import com.zzu.ehome.ehome.entity.HosptialBean;

import java.util.List;

/**
 * Created by Mersens on 2016/10/28.
 */

public class HosptialAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context mContext;
    private List<HosptialBean> mList;

    public HosptialAdapter(Context context, List<HosptialBean> mList) {
        this.mContext = context;
        this.mList = mList;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setList(List<HosptialBean> mList) {
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.hosptial_item, null);
            holder.tv_name = (TextView) view.findViewById(R.id.tv_name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tv_name.setText(mList.get(i).getHospitalName());

        return view;
    }

    static class ViewHolder {

        private TextView tv_name;
    }
}
