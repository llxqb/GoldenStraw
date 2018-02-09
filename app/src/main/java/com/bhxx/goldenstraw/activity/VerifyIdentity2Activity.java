package com.bhxx.goldenstraw.activity;
/**
 * 身份认证界面2
 */

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.pc.ioc.event.EventBus;
import com.android.pc.ioc.inject.InjectAll;
import com.android.pc.ioc.inject.InjectBinder;
import com.android.pc.ioc.inject.InjectLayer;
import com.android.pc.ioc.view.listener.OnClick;
import com.bhxx.goldenstraw.R;
import com.bhxx.goldenstraw.fragment.VerifyidentityPersoninfoFragment;
import com.bhxx.goldenstraw.fragment.VerifyidentityProfessioninfoFragment;
import com.bhxx.goldenstraw.fragment.VerifyidentitySocialRelationsFragment;
import com.bhxx.goldenstraw.utils.ActivityCollector;
import com.bhxx.goldenstraw.utils.LogUtils;

@InjectLayer(R.layout.activity_verify_identity2)
public class VerifyIdentity2Activity extends BasicActivity {
    @InjectAll
    private Views v;
    private Fragment[] mFragmentArray;

    @Override
    protected void init() {
        ActivityCollector.addActivity(this);
        EventBus.getDefault().register(this);
        v.titleTitle.setText("身份认证");
        v.viewpager.setOffscreenPageLimit(3);
        mFragmentArray = new Fragment[3];
        mFragmentArray[0] = Fragment.instantiate(this, VerifyidentityPersoninfoFragment.class.getName());
        mFragmentArray[1] = Fragment.instantiate(this, VerifyidentityProfessioninfoFragment.class.getName());
        mFragmentArray[2] = Fragment.instantiate(this, VerifyidentitySocialRelationsFragment.class.getName());

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

    @Override
    protected void click(View view) {
        switch (view.getId()) {
            case R.id.titleBack:
                finish();
                break;
            case R.id.verifyIdentity2_personinfo://个人信息
                checkPersonInfo();
                break;
            case R.id.verifyIdentity2_professioninfo://职业信息
                checkProfessionInfo();
                break;
            case R.id.verifyIdentity2_socialRelations://社会关系
                checkSocialRelations();
                break;
        }
    }

    private class Views {
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        ImageView titleBack;
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        TextView titleTitle, verifyIdentity2_personinfo, verifyIdentity2_professioninfo, verifyIdentity2_socialRelations;
        ViewPager viewpager;
    }

    protected void onEventMainThread(Integer integer) {
        LogUtils.i("integer=" + integer);
        switch (integer) {
            case 0:
                checkPersonInfo();
                break;
            case 1:
                checkProfessionInfo();
                break;
            case 2:
                checkSocialRelations();
                break;
        }
    }

    /**
     * 清除颜色
     */
    private void clearColor() {
        v.verifyIdentity2_personinfo.setTextColor(Color.parseColor("#FFFFFF"));
        v.verifyIdentity2_personinfo.setBackgroundResource(R.drawable.round_corner_app_left);
        v.verifyIdentity2_professioninfo.setTextColor(Color.parseColor("#FFFFFF"));
        v.verifyIdentity2_professioninfo.setBackgroundResource(R.color.app_grey);
        v.verifyIdentity2_socialRelations.setTextColor(Color.parseColor("#FFFFFF"));
        v.verifyIdentity2_socialRelations.setBackgroundResource(R.drawable.round_corner_app_right);
    }

    private void setSelectedTab(int index) {
        if (index == 0) {
            checkPersonInfo();
        } else if (index == 1) {
            checkProfessionInfo();
        } else if (index == 2) {
            checkSocialRelations();
        }
    }

    private void checkPersonInfo() {
        clearColor();
        v.verifyIdentity2_personinfo.setTextColor(Color.parseColor("#FF623E"));
        v.verifyIdentity2_personinfo.setBackgroundResource(R.drawable.hollow_round_corner_app_left);
        v.viewpager.setCurrentItem(0);
    }

    private void checkProfessionInfo() {
        clearColor();
        v.verifyIdentity2_professioninfo.setTextColor(Color.parseColor("#FF623E"));
        v.verifyIdentity2_professioninfo.setBackgroundResource(R.drawable.hollow_app);
        v.viewpager.setCurrentItem(1);
    }

    private void checkSocialRelations() {
        clearColor();
        v.verifyIdentity2_socialRelations.setTextColor(Color.parseColor("#FF623E"));
        v.verifyIdentity2_socialRelations.setBackgroundResource(R.drawable.hollow_round_corner_app_right);
        v.viewpager.setCurrentItem(2);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
