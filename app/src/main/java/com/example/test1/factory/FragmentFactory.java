package com.example.test1.factory;


import android.support.v4.app.Fragment;

import com.example.test1.AppFragment;
import com.example.test1.HomeFragment;

/**
 *
 */

public class FragmentFactory {

    //直接创建该类型的对象.
    public Fragment createFragment(int index){
        Fragment fragment = null;
        switch (index){
            case 0:
                fragment =  new HomeFragment();
                break;
            case 1:
                fragment = new AppFragment();
                break;
        }
        return fragment;
    };
}
