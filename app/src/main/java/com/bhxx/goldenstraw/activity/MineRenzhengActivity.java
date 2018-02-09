package com.bhxx.goldenstraw.activity;
/**
 * 我的认证
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.pc.ioc.inject.InjectAll;
import com.android.pc.ioc.inject.InjectBinder;
import com.android.pc.ioc.inject.InjectLayer;
import com.android.pc.ioc.view.listener.OnClick;
import com.bhxx.goldenstraw.R;
import com.bhxx.goldenstraw.utils.ActivityCollector;
import com.bhxx.goldenstraw.utils.IntentUtil;

@InjectLayer(R.layout.activity_mine_renzheng)
public class MineRenzhengActivity extends BasicActivity {

    @InjectAll
    private Views v;

    private class Views {
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        ImageView titleBack;
        TextView titleTitle;
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        LinearLayout mine_phoneVerify, mine_weixinVerify, mine_zhifubaoVerify,mine_qqVerify;
    }

    @Override
    protected void init() {
        ActivityCollector.addActivity(this);
        v.titleTitle.setText("我的认证");
    }

    @Override
    protected void click(View view) {
        switch (view.getId()) {
            case R.id.titleBack:
                finish();
                break;
            case R.id.mine_weixinVerify://微信认证
                IntentUtil.setIntent(MineRenzhengActivity.this, WeiXinVerifyActivity.class);
                break;
            case R.id.mine_zhifubaoVerify://支付宝认证
                IntentUtil.setIntent(MineRenzhengActivity.this, ZhifubaoVerifyActivity.class);
                break;
            case R.id.mine_qqVerify://QQ认证
                IntentUtil.setIntent(MineRenzhengActivity.this, QQVerifyActivity.class);
                break;
        }
    }

}
