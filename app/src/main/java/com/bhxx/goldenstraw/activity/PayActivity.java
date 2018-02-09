package com.bhxx.goldenstraw.activity;
/**
 * 还款
 */

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.pc.ioc.inject.InjectAll;
import com.android.pc.ioc.inject.InjectBinder;
import com.android.pc.ioc.inject.InjectLayer;
import com.android.pc.ioc.view.listener.OnClick;
import com.bhxx.goldenstraw.R;
import com.bhxx.goldenstraw.fragment.Pay2Fragment;
import com.bhxx.goldenstraw.fragment.PayFragment;
import com.bhxx.goldenstraw.fragment.XuQiFragment;
import com.bhxx.goldenstraw.utils.ActivityCollector;

@InjectLayer(R.layout.activity_pay)
public class PayActivity extends BasicActivity {

    @InjectAll
    private Views v;
    private Fragment[] mFragmentArray;

    private class Views {
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        ImageView titleBack;
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        TextView titleTitle, pay_pay, pay_xuqi;
        ViewPager viewpager;
    }

    @Override
    protected void init() {
        ActivityCollector.addActivity(this);
        v.titleTitle.setText("还款");

        v.viewpager.setOffscreenPageLimit(2);
        mFragmentArray = new Fragment[2];
        //先判断是否是逾期未还款
        boolean ispay = true;
        if (ispay) {
            mFragmentArray[0] = Fragment.instantiate(this, PayFragment.class.getName());
        } else {
            mFragmentArray[0] = Fragment.instantiate(this, Pay2Fragment.class.getName());
        }

        mFragmentArray[1] = Fragment.instantiate(this, XuQiFragment.class.getName());

        v.viewpager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mFragmentArray[i];
            }

            @Override
            public int getCount() {
                return mFragmentArray.length;
            }
        });

        v.viewpager.setCurrentItem(0);

        v.viewpager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setSelectedTab(position);
            }
        });
    }

    /**
     * 清除颜色
     */
    private void clearColor() {
        v.pay_pay.setTextColor(Color.parseColor("#FF623E"));
        v.pay_pay.setBackgroundResource(R.drawable.hollow_round_corner_app_left);
        v.pay_xuqi.setTextColor(Color.parseColor("#FF623E"));
        v.pay_xuqi.setBackgroundResource(R.drawable.hollow_round_corner_app_right);
    }


    private void setSelectedTab(int index) {
        if (index == 0) {
            pay();//还款
        } else if (index == 1) {
            xuqi();//续期
        }
    }

    private void pay() {
        clearColor();
        v.pay_pay.setTextColor(Color.parseColor("#FFFFFF"));
        v.pay_pay.setBackgroundResource(R.drawable.round_corner_app_left);
        v.viewpager.setCurrentItem(0);
    }

    private void xuqi() {
        clearColor();
        v.pay_xuqi.setTextColor(Color.parseColor("#FFFFFF"));
        v.pay_xuqi.setBackgroundResource(R.drawable.round_corner_app_right);
        v.viewpager.setCurrentItem(1);
    }

    @Override
    protected void click(View view) {
        switch (view.getId()) {
            case R.id.titleBack:
                finish();
                break;
            case R.id.pay_pay://还款
                pay();
                break;
            case R.id.pay_xuqi://续期
                xuqi();
                break;
        }
    }

}
