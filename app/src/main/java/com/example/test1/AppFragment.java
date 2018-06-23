package com.example.test1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.test1.listener.RefreshListener;

public class AppFragment extends Fragment implements RefreshListener{

    private View view;
    private WebView webView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.text_input_layout, container, false);
        webView = view.findViewById(R.id.web);

        //屏蔽系统浏览器打开网址.
        webView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        return view;
    }

    /*public void refreshView(String wapUrl) {
        //加载网址.
        webView.loadUrl("http://wapbaike.baidu.com/item/%E7%A7%AF%E4%BA%91/31863");
    }*/

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void refreshView(ItemInfo info) {
//        webView.loadUrl(info.getWapUrl());
//        webView.loadUrl("https://c.m.163.com/news/a/D4010MDI0001875P.html?spss=newsapp");
        webView
                .loadUrl("https://baike.baidu.com/item/%E5%8C%97%E4%BA%AC%E5%85%AB%E7%BB%B4%E7%A0%94%E4%BF%AE%E5%AD%A6%E9%99%A2/8651018");
    }
}
