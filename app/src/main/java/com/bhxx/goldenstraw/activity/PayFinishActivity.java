package com.bhxx.goldenstraw.activity;
/**
 * 支付完成
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.pc.ioc.inject.InjectAll;
import com.android.pc.ioc.inject.InjectBinder;
import com.android.pc.ioc.inject.InjectLayer;
import com.android.pc.ioc.view.listener.OnClick;
import com.bhxx.goldenstraw.R;
import com.bhxx.goldenstraw.utils.ActivityCollector;
import com.bhxx.goldenstraw.utils.IntentUtil;

@InjectLayer(R.layout.activity_pay_finish)
public class PayFinishActivity extends BasicActivity {

    @InjectAll
    private Views v;

    private class Views {
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        ImageView titleBack;
        TextView titleTitle;
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        TextView payFinish_bianhao, payFinish_startDate, payFinish_time, payFinish_endDate, payFinish_totalMoney, payFinish_next;
    }

    @Override
    protected void init() {
        ActivityCollector.addActivity(this);
        v.titleTitle.setText("还款成功");
    }

    @Override
    protected void click(View view) {
        switch (view.getId()) {
            case R.id.titleBack:
                finish();
                break;
            case R.id.payFinish_next://跳转到个人中心查看我的借款
                IntentUtil.setIntent(PayFinishActivity.this, PersonalCenterActivity.class);
                break;
        }
    }

}
