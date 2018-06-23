package com.example.test1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.okhttputils.callback.ResultCallback;
import com.example.okhttputils.request.OkHttpRequest;
import com.example.test1.listener.RefreshListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

import static android.content.ContentValues.TAG;

public class ListFragment extends Fragment implements RefreshListener{

    private View view;
    private WebView webView;
    protected ListView lv;
    protected List<NewsInfo> list;
    protected MyAadapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_lru_cache_demo, container, false);

        lv = (ListView) view.findViewById(R.id.lv_demo);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "第" + position + "条数据图片被移除了!", Toast.LENGTH_SHORT).show();
            }
        });

        //设置适配器.
        list = new ArrayList<>();

        request();
        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void refreshView(ItemInfo info) {

    }

    private void request() {
        String url = "http://litchiapi.jstv.com/api/GetFeeds?column=4&PageSize=20&pageIndex=1&val=100511D3BE5301280E0992C73A9DEC41";
        new OkHttpRequest.Builder().url(url).errResId(R.mipmap.ic_launcher).get(new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception exception) {
                Log.i(TAG, "onError: " + exception.getMessage());
            }

            @Override
            public void onResponse(String response) {
                Log.i(TAG, "onResponse: " + response);
                parseData(response);
                adapter = new MyAadapter(list);
                lv.setAdapter(adapter);
            }

        });
    }

    private void parseData(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject paramz = jsonObject.getJSONObject("paramz");
            JSONArray feeds = paramz.getJSONArray("feeds");
            for (int i = 0; i < feeds.length(); i++) {
                NewsInfo info = new NewsInfo();
                JSONObject object = feeds.getJSONObject(i);
                JSONObject data = object.getJSONObject("data");
                String cover = data.getString("cover");
                String subject = data.getString("subject");
                String summary = data.getString("summary");
                info.setCover(cover);
                info.setSummary(summary);
                info.setSubject(subject);
                list.add(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
