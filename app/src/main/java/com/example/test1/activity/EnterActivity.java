package com.example.test1.activity;

import com.example.demonstrate.DialogPage;
import com.example.demonstrate.FirstActivity;
import com.example.test1.listener.PageItemListener1;

public class EnterActivity extends FirstActivity {


    @Override
    protected void click0() {
        DialogPage
                .getInstance()
                .setOnOnDialogItemListener(new PageItemListener1(this));
    }
}
