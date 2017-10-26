package com.gavin.com.stickydecoration.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gavin.com.library.PowerfulStickyDecoration;
import com.gavin.com.library.XinlangStickyDecoration;
import com.gavin.com.library.listener.PowerGroupListener;
import com.gavin.com.stickydecoration.R;
import com.gavin.com.stickydecoration.model.City;
import com.gavin.com.stickydecoration.util.CityUtil;
import com.gavin.com.stickydecoration.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 仿新浪个人中心的布局
 */
public class XinLangRecyclerViewActivity  extends AppCompatActivity {

    @BindView(R.id.rv)
    RecyclerView mRv;

    RecyclerView.Adapter mAdapter;
    List<City> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky_recycler_view);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        //模拟数据  啦啦啦啦
        dataList.add(new City("福州", "", R.mipmap.city1));
        dataList.add(new City("厦门", "福建", R.mipmap.city1));
        dataList.addAll(CityUtil.getCityList());
//        dataList.addAll(CityUtil.getCityList());

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRv.setLayoutManager(manager);
        XinlangStickyDecoration decoration = XinlangStickyDecoration.Builder
                .init(new PowerGroupListener() {
                    @Override
                    public String getGroupName(int position) {
                        //获取组名，用于判断是否是同一组
                        if (dataList.size() > position) {
                            return dataList.get(position).getProvince();
                        }
                        return null;
                    }

                    @Override
                    public View getGroupView(int position) {
                        //获取自定定义的组View
                        if (dataList.size() > position) {
                            //中间的view
                            View view = getLayoutInflater().inflate(R.layout.item_group, null, false);
                            ((TextView) view.findViewById(R.id.tv)).setText(dataList.get(position).getProvince());
                            return view;
                        } else {
                            return null;
                        }
                    }
                })
                .setGroupHeight(DensityUtil.dip2px(this, 40))       //设置高度
                .isAlignLeft(false)                                 //靠右边显示   默认左边
                .setGroupBackground(Color.parseColor("#48BDFF"))    //设置背景   默认透明
                .setDivideColor(Color.parseColor("#CCCCCC"))        //分割线颜色
                .setDivideHeight(DensityUtil.dip2px(this, 1))       //分割线高度
                .build();

        mRv.addItemDecoration(decoration);
        mAdapter = new RecyclerView.Adapter() {
            private final int HEAD_VIEW = 1;
            private final int BOTTOWN_VIEW = 2;
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view;
                if(viewType == HEAD_VIEW){
                    //头部的view headView
                    view= LayoutInflater.from(parent.getContext()).inflate(R.layout.head_view, parent, false);
                }else{
                    //尾部的view
                    view= LayoutInflater.from(parent.getContext()).inflate(R.layout.bottom_view, parent, false);
                }

                return new Holder(view);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
                Holder holder = (Holder) viewHolder;
            }

            @Override
            public int getItemCount() {
                return 2;
            }

            @Override
            public int getItemViewType(int position) {
                if(position == 0){
                    return HEAD_VIEW;
                }else{
                    return BOTTOWN_VIEW;
                }
            }
        };
        mRv.setAdapter(mAdapter);
    }

    static class Holder extends RecyclerView.ViewHolder {

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private void l(String str) {
        Log.i("TAG", str);
    }
}
