package com.example.test1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.test1.listener.RefreshListener;

import java.util.List;

/*
LayoutManager: 管理RecyclerView的结构.
Adapter: 处理每个Item的显示.
ItemDecoration: 添加每个Item的装饰.
ItemAnimator: 负责添加\移除\重排序时的动画效果.



 */
public class HomeFragment extends Fragment implements RefreshListener{

    private View view;
//    private List<DataInfo> mDatas = new ArrayList<>();
    private LinearLayoutManager manager;


    private RecyclerView recyclerView;
    private String TAG = this.getClass().getName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frament_home, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //1,初始化布局
        //2,获取数据
        //3,更新数据.
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true); // 设置固定大小

        //垂直列表布局.
//        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        // 错列网格布局
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        //添加了条目之间边线.
        recyclerView.addItemDecoration(new MyItemDecoration(getActivity()));
//        getData();
    }





    public AppCompatActivity activity;

    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void refreshView(ItemInfo info) {
        final List<DataInfo>  mDatas = info.getData();
        //创建适配器.
        HomeAdapter adapter = new HomeAdapter(mDatas);
        //设置适配器.
        recyclerView.setAdapter(adapter);
        //注册条目点击.
        adapter.setOnItemClickListener(new HomeAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(HomeFragment.this.getActivity(),mDatas.get(position).getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void getData() {
//
//        //参数:1,接口地址.2成功回调.3,失败回调.
//        Request request = new StringRequest(url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.i(TAG, "onResponse: --->"+response);
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    JSONArray card = jsonObject.getJSONArray("card");
//                    for (int i = 0; i < card.length(); i++) {
//                        JSONObject object = card.getJSONObject(i);
//                        //
//                        String name = object.getString("name");
//                        JSONArray value = object.getJSONArray("value");
//                        //
//                        String val =  value.getString(0);
//                        DataInfo dataInfo = new DataInfo();
//                        dataInfo.setName(name);
//                        dataInfo.setValue(val);
//                        mDatas.add(dataInfo);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                refreshView();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.i(TAG, "onErrorResponse: "+error);
//            }
//        });
//        requestQueue.add(request);
//    }

    /*public void refreshView(final List<DataInfo> mDatas) {
        //创建适配器.
        HomeAdapter adapter = new HomeAdapter(mDatas);
        //设置适配器.
        recyclerView.setAdapter(adapter);
        //注册条目点击.
        adapter.setOnItemClickListener(new HomeAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(HomeFragment.this.getActivity(),mDatas.get(position).getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }*/

    //详情,网页.
}
