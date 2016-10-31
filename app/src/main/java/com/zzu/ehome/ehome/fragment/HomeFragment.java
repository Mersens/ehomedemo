package com.zzu.ehome.ehome.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.zzu.ehome.ehome.R;
import com.zzu.ehome.ehome.adapter.HosptialAdapter;
import com.zzu.ehome.ehome.entity.HosptialBean;
import com.zzu.ehome.ehome.service.ServiceStore;
import com.zzu.ehome.ehome.utils.Node;
import com.zzu.ehome.ehome.utils.RequestManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class HomeFragment extends BaseFragment{
    private View mView;
    private ListView mListView;
    private HosptialAdapter mAdapter;
    private RequestManager manager;
    private List<HosptialBean> mList;
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean isRefresh=false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_home,null);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mView=view;
        init();

    }

    @Override
    public void init() {
        manager=RequestManager.getInstance();

        swipeRefreshLayout=(SwipeRefreshLayout)mView.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefresh=true;
                initData();
            }
        });

        mListView=(ListView)mView.findViewById(R.id.listView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), mList.get(i).getHospitalName(), Toast.LENGTH_SHORT).show();
            }
        });

        initData();

    }

    public void initData(){
        String result= Node.getResult("HospitalInquiryByTopmd",null);
        ServiceStore service= manager.create(ServiceStore.class);
        Call<ResponseBody> call= service.getHospitalInfo(result);
        manager.execute(call, new RequestManager.RequestCallBack() {
            @Override
            public void onSueecss(String msg) {
                try {
                    JSONObject jsonObject=new JSONObject(msg);
                    JSONArray array = jsonObject
                            .getJSONArray("HospitalInquiryByTopmd");
                    if(!array.getJSONObject(0).has("MessageCode")){
                        mList=new ArrayList<HosptialBean>();
                        for(int i=0;i<array.length();i++){
                            JSONObject object=array.getJSONObject(i);
                            HosptialBean hosptialBean=new HosptialBean();
                            hosptialBean.setHospitalID(object.getString("HospitalID"));
                            hosptialBean.setHospitalName(object.getString("HospitalName"));
                            hosptialBean.setDescription(object.getString("Description"));
                            hosptialBean.setCity(object.getString("City"));
                            mList.add(hosptialBean);
                        }
                        setData();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }finally {
                    stopRefresh();
                }
            }

            @Override
            public void onError(String msg) {
                stopRefresh();
                Log.e("onError",msg);


            }

            @Override
            public void onStart() {
                Log.e("onStart","onStart");
            }

            @Override
            public void onFinish() {
                Log.e("onFinish","onFinish");

            }
        });
    }

    public void setData(){
        if(isRefresh){
            mAdapter.setList(mList);
            mAdapter.notifyDataSetChanged();
        }else{
            mAdapter=new HosptialAdapter(getActivity(),mList);
            mListView.setAdapter(mAdapter);
        }
    }

    public void stopRefresh(){
        if(swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    public static Fragment getInstance(){
        return new HomeFragment();
    }
}
