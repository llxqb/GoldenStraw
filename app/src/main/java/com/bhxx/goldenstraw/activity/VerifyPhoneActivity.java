package com.bhxx.goldenstraw.activity;
/**
 * 手机认证
 */

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.pc.ioc.event.EventBus;
import com.android.pc.ioc.inject.InjectAll;
import com.android.pc.ioc.inject.InjectBinder;
import com.android.pc.ioc.inject.InjectLayer;
import com.android.pc.ioc.view.listener.OnClick;
import com.bhxx.goldenstraw.R;
import com.bhxx.goldenstraw.utils.ActivityCollector;
import com.bhxx.goldenstraw.utils.IntentUtil;
import com.bhxx.goldenstraw.utils.LogUtils;

@InjectLayer(R.layout.activity_verify_phone)
public class VerifyPhoneActivity extends BasicActivity {

    @Override
    protected void init() {
        ActivityCollector.addActivity(this);
        v.titleTitle.setText("手机认证");
    }

    @Override
    protected void click(View view) {
        switch (view.getId()) {
            case R.id.titleBack:
                finish();
                break;
            case R.id.verifyPhone_forgetPwd://忘记服务密码
                IntentUtil.setIntent(VerifyPhoneActivity.this, ResetServerPwdActivity.class);
                break;
            case R.id.verifyPhone_nextStep://下一步
                IntentUtil.setIntent(VerifyPhoneActivity.this, VerifyIdentityActivity.class);
                break;
            case R.id.verifyPhone_xieyi://用户协议
                break;
        }
    }

    @InjectAll
    private Views v;

    private class Views {
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        ImageView titleBack;
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        TextView titleTitle, verifyPhone_phone, verifyPhone_operator, verifyPhone_forgetPwd,
                verifyPhone_nextStep, verifyPhone_xieyi;
        EditText verifyPhone_pwd;
    }


}
