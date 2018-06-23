package com.example.test1.listener;

import android.app.Activity;

import com.example.demonstrate.DialogPage;
import com.example.test1.MainActivity;
import com.example.test1.activity.Test1Activity;

/**
 * Created by think on 2018/3/9.
 */

public class PageItemListener1 implements DialogPage.OnDialogItemListener {

    private final Activity mActivity;

    public PageItemListener1(Activity activity) {
        mActivity = activity;
    }

    @Override
    public Activity getActivity() {
        return mActivity;
    }

    @Override
    public String getTitle() {
        return "核心原理周1技能1";
    }

    @Override
    public Class<?> getStartActivity(int which) {
        if (which == 0) {
            return MainActivity.class;
        } else if (which == 1) {
            return Test1Activity.class;
        }
        return null;
    }
}
