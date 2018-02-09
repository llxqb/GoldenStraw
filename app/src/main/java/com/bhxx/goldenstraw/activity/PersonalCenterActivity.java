package com.bhxx.goldenstraw.activity;
/**
 * 个人中心
 */

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.pc.ioc.inject.InjectAll;
import com.android.pc.ioc.inject.InjectBinder;
import com.android.pc.ioc.inject.InjectLayer;
import com.android.pc.ioc.view.listener.OnClick;
import com.bhxx.goldenstraw.R;
import com.bhxx.goldenstraw.utils.ActivityCollector;
import com.bhxx.goldenstraw.utils.IntentUtil;

@InjectLayer(R.layout.activity_personal_center)
public class PersonalCenterActivity extends BasicActivity {

    @InjectAll
    private Views v;

    private class Views {
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        ImageView titleBack, titleRightImg;
        TextView titleTitle;
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        LinearLayout personalCenter_sqjk, personalCenter_xhjk, personalCenter_yhq;
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        RelativeLayout personalCenter_renzheng_layout, personalCenter_changePhone_layout,
                personalCenter_help_layout, personalCenter_action_layout, personalCenter_share_layout, personalCenter_fankui_layout,
                personalCenter_exit_layout;
    }

    @Override
    protected void init() {
        ActivityCollector.addActivity(this);
        v.titleTitle.setText("个人中心");
        v.titleRightImg.setVisibility(View.VISIBLE);
        v.titleRightImg.setImageResource(R.mipmap.icon_message);
    }

    @Override
    protected void click(View view) {
        switch (view.getId()) {
            case R.id.titleBack:
                finish();
                break;
            case R.id.titleRightImg://个人消息
                IntentUtil.setIntent(PersonalCenterActivity.this, MessageActivity.class);
                break;
            case R.id.personalCenter_sqjk:
                showToast("申请借款");
                break;
            case R.id.personalCenter_xhjk:
                showToast("需还借款");
                IntentUtil.setIntent(PersonalCenterActivity.this, PayActivity.class);
                break;
            case R.id.personalCenter_yhq:
                IntentUtil.setIntent(PersonalCenterActivity.this, YouHuiQuanActivity.class);
                showToast("优惠券");
                break;
            case R.id.personalCenter_renzheng_layout:
                showToast("我的认证");
                IntentUtil.setIntent(PersonalCenterActivity.this, MineRenzhengActivity.class);
                break;
            case R.id.personalCenter_changePhone_layout:
                showToast("更换手机号");
                IntentUtil.setIntent(PersonalCenterActivity.this, ChangePhoneActivity.class);
                break;
            case R.id.personalCenter_help_layout:
                showToast("帮助");
                IntentUtil.setIntent(PersonalCenterActivity.this, HelpActivity.class);
                break;
            case R.id.personalCenter_action_layout:
                showToast("活动中心");
                break;
            case R.id.personalCenter_share_layout:
                showToast("邀请好友");
                break;
            case R.id.personalCenter_fankui_layout:
                showToast("意见反馈");
                IntentUtil.setIntent(PersonalCenterActivity.this, SuggestActivity.class);
                break;
            case R.id.personalCenter_exit_layout:
                showToast("退出登录");
                IntentUtil.setIntent(PersonalCenterActivity.this, LoginActivity.class);
                finish();
                break;
        }
    }


}
