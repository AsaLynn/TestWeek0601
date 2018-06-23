package com.example.test1.factory;

import com.example.test1.AppFragment;
import com.example.test1.HomeFragment;
import com.example.test1.listener.RefreshListener;

/**
 * Created by think on 2017/9/19.
 */

public class RefreshListenerFactory {

    public RefreshListener createRefreshListener(int index){
        RefreshListener listener = null;
        switch (index){
            case 0:
                listener =  new HomeFragment();
                break;
            case 1:
                listener = new AppFragment();
                break;
        }
        return listener;
    };
}
