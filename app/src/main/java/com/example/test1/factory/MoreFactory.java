package com.example.test1.factory;

import com.example.test1.AppFragment;
import com.example.test1.HomeFragment;
import com.example.test1.listener.RefreshListener;

/**
 * Created by think on 2017/9/19.
 */

public class MoreFactory {

    public RefreshListener createHomeListener(){
        return new HomeFragment();
    }

    public RefreshListener createAppListener(){
        return new AppFragment();
    }
}
