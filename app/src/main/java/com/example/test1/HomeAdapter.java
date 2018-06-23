package com.example.test1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<MyViewHolder> {


    private List<DataInfo> mDatas;
    private List<Integer> mHeights;


    public HomeAdapter(List<DataInfo> mDatas) {
        this.mDatas = mDatas;
        //存放控件高度值.
        mHeights = new ArrayList<>();
    }

    //定义接口,对外暴漏条目点击.
    public interface onItemClickListener{
        //对外未知逻辑.
        void onItemClick(View view, int position);
    }

    public onItemClickListener onItemClickListener;


    //对外暴漏方法,设置条目监听.
    public void setOnItemClickListener(onItemClickListener listener){
        this.onItemClickListener = listener;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home,parent,false);

        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        // 随机高度, 模拟瀑布效果.
        if (mHeights.size() <= position) {
            mHeights.add((int) (100 + Math.random() * 300));
        }
        //代码中获取布局参数.
        ViewGroup.LayoutParams lp = holder.name.getLayoutParams();
        lp.height = mHeights.get(position);

        holder.name.setLayoutParams(lp);
        holder.text.setText(mDatas.get(position).getValue());
        holder.name.setText(mDatas.get(position).getName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(null != onItemClickListener){
                    onItemClickListener.onItemClick(holder.view,position);
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return mDatas.size();
    }


}

class MyViewHolder extends RecyclerView.ViewHolder{

    TextView text,name;
    View view;//整个条目.
    public MyViewHolder(View itemView) {
        super(itemView);
        this.view = itemView;
        text = (TextView) itemView.findViewById(R.id.text);
        name = (TextView) itemView.findViewById(R.id.name);
    }
}
