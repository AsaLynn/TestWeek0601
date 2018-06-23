package com.example.test1;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.test1.factory.MoreFactory;
import com.example.test1.listener.RefreshListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 1,在我们的MainActivity当中访问网络,请求数据.
 * 2,请求数据成功后,在挂在fragment.
 * 3,fragment中调用getactivuty()方法获取,并强转成MainActivity.
 * 4,fragment拿取MainActivity实例中获取的数据,更新各自的页面.
 *
 *
 *
 */

public class MainActivity extends AppCompatActivity {

    String url = "http://baike.baidu.com/api/openapi/BaikeLemmaCardApi?scope=103&format=json&appid=379020&bk_key=%E7%A7%AF%E4%BA%91&BK_length=600";
    private String TAG = this.getClass().getName();
    private List<DataInfo> mDatas = new ArrayList<>();
    private HomeFragment homeFragment;
    private AppFragment appFragment;
    private RefreshListener listener1;
    private RefreshListener listener2;
    protected ListFragment listFragment;

    private void getData() {

        //参数:1,接口地址.2成功回调.3,失败回调.
        Request request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "onResponse: --->"+response);
                ItemInfo newsInfo = new ItemInfo();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String wapUrl = jsonObject.getString("wapUrl");
                    newsInfo.setWapUrl(wapUrl);
//                    appFragment.refreshView(wapUrl);
                    JSONArray card = jsonObject.getJSONArray("card");
                    for (int i = 0; i < card.length(); i++) {
                        JSONObject object = card.getJSONObject(i);
                        //
                        String name = object.getString("name");
                        JSONArray value = object.getJSONArray("value");
                        //
                        String val =  value.getString(0);
                        DataInfo dataInfo = new DataInfo();
                        dataInfo.setName(name);
                        dataInfo.setValue(val);
                        mDatas.add(dataInfo);
                    }
                    newsInfo.setData(mDatas);
                    listener1.refreshView(newsInfo);
                    listener2.refreshView(newsInfo);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                refreshView();
//                homeFragment.refreshView(mDatas);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "onErrorResponse: "+error);
            }
        });
        requestQueue.add(request);
    }

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建队列.
        requestQueue = Volley.newRequestQueue(this);
//        getData();





        //实例化控件.
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        //添加滑动选项.
        tabLayout.addTab(tabLayout.newTab().setText("详情"));
        tabLayout.addTab(tabLayout.newTab().setText("网页"));
        tabLayout.addTab(tabLayout.newTab().setText("新闻"));

        //第一个参数默认颜色
        //第二个参赛选中颜色
//        setTabTextColors(int normalColor, int selectedColor)
        tabLayout.setTabTextColors(getResources().getColor(R.color.link_text_dark),getResources().getColor(R.color.red));


        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);


        ActionBar actionBar = getSupportActionBar();
        //设置actionbar的标题
        actionBar.setTitle("这是标题");

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        //创建适配器.
//        Adapter adapter = new Adapter(getSupportFragmentManager());
        //设置适配器
//        viewPager.setAdapter(adapter);

        setupViewPager(viewPager);
        //viewpager绑定tablayout
        tabLayout.setupWithViewPager(viewPager);

        getData();
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());

//        RefreshListenerFactory factory = new RefreshListenerFactory();
        MoreFactory factory = new MoreFactory();
//        listener1 = factory.createRefreshListener(0);
//        listener2 = factory.createRefreshListener(1);
        listener1 = factory.createHomeListener();
        listener2 = factory.createAppListener();
        adapter.addFragment((Fragment) listener1,"详情");
        adapter.addFragment((Fragment) listener2,"网页");
//        adapter.addFragment((Fragment) listener2,"网页");

        /*FragmentFactory factory = new FragmentFactory();
        adapter.addFragment( factory.createRefreshListener(0), "详情");
        adapter.addFragment( factory.createRefreshListener(1), "网页");*/

//        homeFragment = new HomeFragment();
//        adapter.addFragment(homeFragment, "详情");
//        appFragment = new AppFragment();
//        adapter.addFragment(appFragment, "网页");
        listFragment = new ListFragment();
        adapter.addFragment(listFragment, "新闻");

        viewPager.setAdapter(adapter);
    }



    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
