package com.lin.AppTab;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener{

    private ViewPager mViewPager;
    private PagerAdapter mAdapter;
    private List<View> mViews=new ArrayList<View>();

    private LinearLayout mtabweixin;
    private LinearLayout mtabaddress;
    private LinearLayout mtabfriend;
    private LinearLayout mtabsetting;

    private ImageButton mWeixinimg;
    private ImageButton mFriendimg;
    private ImageButton mAddressimg;
    private ImageButton msettingimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//无android默认标题栏
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //设置界面无系统标题栏即全屏显示但是android自带标题栏还在 需要在setContentView之前
        setContentView(R.layout.activity_main);

        initView();
        initEvents();
    }

    public void play(View v){ //对各个tab按钮点击进行监听
        switch (v.getId()){
            case R.id.bt1:
                Toast.makeText(MainActivity.this,"bit1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt2:
                Toast.makeText(MainActivity.this,"bit2",Toast.LENGTH_SHORT).show();
        }

    }

    private void initEvents() {
        mWeixinimg.setOnClickListener(this);
        mFriendimg.setOnClickListener(this);
        mAddressimg .setOnClickListener(this);
        msettingimg.setOnClickListener(this);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {  //滑动监听
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //设置当手动滑动到对应界面是图标变为亮色
                int currentItem=mViewPager.getCurrentItem();
                resetImg();//先设置为暗色图标
                switch (currentItem){
                    case 0: {
                        mWeixinimg.setImageResource(R.drawable.weixin_lang);
                        break;
                    }
                    case 1: {
                        mFriendimg.setImageResource(R.drawable.friend_liang);
                        break;
                    }
                    case 2: {
                        mAddressimg.setImageResource(R.drawable.zhibo_liang);
                        break;
                    }
                    case 3: {
                        msettingimg.setImageResource(R.drawable.shezhi_liang);
                        break;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });  //pagechange监听事件结束

    }

    private void initView() {

        mViewPager= (ViewPager) findViewById(R.id.view_pager);

        mtabfriend= (LinearLayout) findViewById(R.id.tab_friend);
        mtabaddress= (LinearLayout) findViewById(R.id.tab_address);
        mtabsetting= (LinearLayout) findViewById(R.id.tab_setting);
        mtabweixin= (LinearLayout) findViewById(R.id.tab_weixin);

        mWeixinimg= (ImageButton) findViewById(R.id.tab_weixin_img);
        msettingimg= (ImageButton) findViewById(R.id.tab_setting_img);
        mAddressimg= (ImageButton) findViewById(R.id.tab_address_img);
        mFriendimg= (ImageButton) findViewById(R.id.tab_friend_img);


        LayoutInflater mInflater=LayoutInflater.from(this);//获得tab界面
        View tab01=mInflater.inflate(R.layout.tab01,null);
        View tab02=mInflater.inflate(R.layout.tab02,null);
        View tab03=mInflater.inflate(R.layout.tab03,null);
        View tab04=mInflater.inflate(R.layout.tab04,null);

        mViews.add(tab01);
        mViews.add(tab02);
        mViews.add(tab03);
        mViews.add(tab04);  //装tab到List<View>中

       // mViewPager.setAdapter(mAdapter);

        mAdapter=new PagerAdapter() {
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mViews.get(position));//摧毁
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {  //初始化Item
                View view=mViews.get(position);//获得View
                container.addView(view);
                return view;
            }

            @Override
            public int getCount() {
                return mViews.size();//View包含的个数
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object; //检查是否来自同一个对象
            }
        };

        mViewPager.setAdapter(mAdapter);   //设置ViewPager

    }


//imageButton的点击事件
    @Override
    public void onClick(View view) {
        resetImg();
        switch (view.getId()){
            case R.id.tab_weixin_img:
            {
                mViewPager.setCurrentItem(0);
                mWeixinimg.setImageResource(R.drawable.weixin_lang); //设置回亮色
                break;
            }
            case R.id.tab_friend_img:
            {
                mViewPager.setCurrentItem(1);
                mFriendimg.setImageResource(R.drawable.friend_liang);
                break;
            }
            case R.id.tab_address_img:
            {
                mViewPager.setCurrentItem(2);
                mAddressimg.setImageResource(R.drawable.zhibo_liang);
                break;
            }
            case R.id.tab_setting_img:
            {
                mViewPager.setCurrentItem(3);
                msettingimg.setImageResource(R.drawable.shezhi_liang);
                break;
            }
        }

    }

    //将所有图片切换为暗色
    private void resetImg() {
        mWeixinimg.setImageResource(R.drawable.weixin_anse);//这里是切换成别的暗色图片，
        mFriendimg.setImageResource(R.drawable.friend_anse);
        mAddressimg.setImageResource(R.drawable.zhibo_anse);
        msettingimg.setImageResource(R.drawable.shezhi_anse);
    }
}

