package com.bhxx.goldenstraw.activity;
/**
 * 身份认证界面3
 */

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.pc.ioc.event.EventBus;
import com.android.pc.ioc.inject.InjectAll;
import com.android.pc.ioc.inject.InjectBinder;
import com.android.pc.ioc.inject.InjectLayer;
import com.android.pc.ioc.view.listener.OnClick;
import com.bhxx.goldenstraw.R;
import com.bhxx.goldenstraw.application.App;
import com.bhxx.goldenstraw.entity.CheckStepEntity;
import com.bhxx.goldenstraw.fragment.VerifyidentityComputerCheckFragment;
import com.bhxx.goldenstraw.fragment.VerifyidentityHumanCheckFragment;
import com.bhxx.goldenstraw.fragment.VerifyidentityPersoninfoFragment;
import com.bhxx.goldenstraw.fragment.VerifyidentityProfessioninfoFragment;
import com.bhxx.goldenstraw.fragment.VerifyidentitySocialRelationsFragment;
import com.bhxx.goldenstraw.utils.ActivityCollector;

@InjectLayer(R.layout.activity_verify_identity3)
public class VerifyIdentity3Activity extends BasicActivity {

    @InjectAll
    private Views v;
    private Fragment[] mFragmentArray;

    private class Views {
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        ImageView titleBack, titleRightImg;
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        TextView titleTitle, verifyIdentity3_computerCheck, verifyIdentity3_humanCheck;
        ViewPager viewpager;
    }

    @Override
    protected void init() {
        ActivityCollector.addActivity(this);
        v.titleRightImg.setVisibility(View.VISIBLE);
        v.titleRightImg.setImageResource(R.mipmap.icon_meanu);
        v.titleTitle.setText("身份认证");

        v.viewpager.setOffscreenPageLimit(2);
        mFragmentArray = new Fragment[2];
        mFragmentArray[0] = Fragment.instantiate(this, VerifyidentityComputerCheckFragment.class.getName());
        mFragmentArray[1] = Fragment.instantiate(this, VerifyidentityHumanCheckFragment.class.getName());

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
        v.verifyIdentity3_computerCheck.setTextColor(Color.parseColor("#FF623E"));
        v.verifyIdentity3_computerCheck.setBackgroundResource(R.drawable.hollow_round_corner_app_left);
        v.verifyIdentity3_humanCheck.setTextColor(Color.parseColor("#FF623E"));
        v.verifyIdentity3_humanCheck.setBackgroundResource(R.drawable.hollow_round_corner_app_right);
    }


    private void setSelectedTab(int index) {
        if (index == 0) {
            ComputerCheck();
        } else if (index == 1) {
            HumanCheck();
        }
    }

    private void ComputerCheck() {
        clearColor();
        v.verifyIdentity3_computerCheck.setTextColor(Color.parseColor("#FFFFFF"));
        v.verifyIdentity3_computerCheck.setBackgroundResource(R.drawable.round_corner_app_left);
        v.viewpager.setCurrentItem(0);
    }

    private void HumanCheck() {
        clearColor();
        v.verifyIdentity3_humanCheck.setTextColor(Color.parseColor("#FFFFFF"));
        v.verifyIdentity3_humanCheck.setBackgroundResource(R.drawable.round_corner_app_right);
        v.viewpager.setCurrentItem(1);
    }

    @Override
    protected void click(View view) {
        switch (view.getId()) {
            case R.id.titleBack:
                finish();
                break;
            case R.id.verifyIdentity3_computerCheck://自动审核
                ComputerCheck();
                break;
            case R.id.verifyIdentity3_humanCheck://人工审核
                HumanCheck();
                break;
        }
    }

}
