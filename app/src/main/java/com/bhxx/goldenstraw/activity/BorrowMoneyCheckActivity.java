package com.bhxx.goldenstraw.activity;
/**
 * 审核第一步
 */

import android.graphics.Color;
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
import com.bhxx.goldenstraw.utils.ActivityCollector;
import com.bhxx.goldenstraw.utils.IntentUtil;
import com.bhxx.goldenstraw.utils.LogUtils;

@InjectLayer(R.layout.activity_verifyidentity_check)
public class BorrowMoneyCheckActivity extends BasicActivity {
    @InjectAll
    private Views v;
    private String CheckStep;

    private class Views {
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        ImageView titleBack, titleRightImg, icon;
        TextView titleTitle;
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        TextView Checking, Check_confirmCard, Check_IDcard, Check_sign, Check_Pay, Check_tishi, Check_nextStep;
    }

    @Override
    protected void init() {
        ActivityCollector.finishAll();
        ActivityCollector.addActivity(this);
        EventBus.getDefault().register(this);
        initView();
    }

    private void initView() {
        v.titleTitle.setText("金稻草");
        v.titleBack.setImageResource(R.mipmap.icon_user);
        v.titleRightImg.setVisibility(View.VISIBLE);
        v.titleRightImg.setImageResource(R.mipmap.icon_message);
        CheckStep = App.app.getData("CheckStep");
        switch (CheckStep) {
            case "2":
                two();
                break;
            case "3":
                three();
                break;
            case "4":
                four();
                break;
            case "5":
                five();
                break;
            default:
                one();
                break;
        }
    }

    @Override
    protected void click(View view) {
        switch (view.getId()) {
            case R.id.titleBack://跳转到个人中心
                IntentUtil.setIntent(BorrowMoneyCheckActivity.this, PersonalCenterActivity.class);
                break;
            case R.id.titleRightImg:
                showToast("暂无消息");
                break;
            case R.id.Check_tishi:
                IntentUtil.setIntent(BorrowMoneyCheckActivity.this, TiShiActivity.class);
                break;
            case R.id.Check_nextStep:
                LogUtils.i("CheckStep=" + CheckStep);
                switch (CheckStep) {
                    case "2"://下一步 -> 身份认证
                        showToast("身份认证");
                        IntentUtil.setIntent(this, VerifyIdentity3Activity.class);
                        break;
                    case "3"://下一步 -> 签约
                        IntentUtil.setIntent(this, VerifySignActivity.class);
                        break;
                    case "4"://下一步 -> 放款
                        showToast("放款成功");
                        App.app.setData("CheckStep", "");
                        five();
                        break;
                    case "5"://放款成功
                        break;
                    default://下一步 -> 确认银行卡
                        IntentUtil.setIntent(this, VerifyCardActivity.class);
                        break;
                }
                break;
        }
    }


    protected void onEventMainThread(CheckStepEntity checkStepEntity) {
        switch (checkStepEntity.getStep()) {
            case CheckStepEntity.ONE://审核中
                one();
                break;
            case CheckStepEntity.TWO://确认银行卡
                App.app.setData("CheckStep", "2");
                two();
                break;
            case CheckStepEntity.THREE://身份认证
                App.app.setData("CheckStep", "3");
                three();
                break;
            case CheckStepEntity.FOUR://签约
                App.app.setData("CheckStep", "4");
                four();
                break;
            case CheckStepEntity.FIVE://放款
                App.app.setData("CheckStep", "5");
                five();
                break;
        }
        CheckStep = App.app.getData("CheckStep");
    }

    private void one() {
        v.icon.setImageResource(R.mipmap.icon_1s1);
        v.Checking.setTextColor(Color.parseColor("#FF623E"));
    }

    private void two() {
        v.icon.setImageResource(R.mipmap.icon_1s2);
        v.Check_confirmCard.setTextColor(Color.parseColor("#FF623E"));
    }

    private void three() {
        v.icon.setImageResource(R.mipmap.icon_1s3);
        v.Check_confirmCard.setTextColor(Color.parseColor("#FF623E"));
        v.Check_IDcard.setTextColor(Color.parseColor("#FF623E"));
    }

    private void four() {
        v.icon.setImageResource(R.mipmap.icon_1s4);
        v.Check_confirmCard.setTextColor(Color.parseColor("#FF623E"));
        v.Check_IDcard.setTextColor(Color.parseColor("#FF623E"));
        v.Check_sign.setTextColor(Color.parseColor("#FF623E"));
    }

    private void five() {
        v.icon.setImageResource(R.mipmap.icon_1s5);
        v.Check_confirmCard.setTextColor(Color.parseColor("#FF623E"));
        v.Check_IDcard.setTextColor(Color.parseColor("#FF623E"));
        v.Check_sign.setTextColor(Color.parseColor("#FF623E"));
        v.Check_Pay.setTextColor(Color.parseColor("#FF623E"));

        v.Check_nextStep.setText("放款成功");

    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
