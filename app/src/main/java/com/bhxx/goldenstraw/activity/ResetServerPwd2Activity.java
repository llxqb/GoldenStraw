package com.bhxx.goldenstraw.activity;
/**
 * 重置服务密码2
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.pc.ioc.inject.InjectAll;
import com.android.pc.ioc.inject.InjectBinder;
import com.android.pc.ioc.inject.InjectLayer;
import com.android.pc.ioc.view.listener.OnClick;
import com.bhxx.goldenstraw.R;
import com.bhxx.goldenstraw.utils.ActivityCollector;
import com.bhxx.goldenstraw.utils.IntentUtil;

@InjectLayer(R.layout.activity_reset_server_pwd2)
public class ResetServerPwd2Activity extends BasicActivity {

    @InjectAll
    private Views v;

    @Override
    protected void init() {
        ActivityCollector.addActivity(this);
        v.titleTitle.setText("重置服务密码");
    }

    @Override
    protected void click(View view) {
        switch (view.getId()) {
            case R.id.titleBack:
                finish();
                break;
            case R.id.resetServerPwd2_verify://获取验证码
                v.resetServerPwd2_verifyEt.setText("1234");
                break;
            case R.id.resetServerPwd2_nextStep://下一步
                if (TextUtils.isEmpty(v.resetServerPwd2_verifyEt.getText().toString())) {
                    showToast("请获取验证码");
                    return;
                }
                IntentUtil.setIntent(ResetServerPwd2Activity.this, ResetServerPwd3Activity.class);
                finish();
                break;
        }
    }

    private class Views {
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        ImageView titleBack;
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        TextView titleTitle, resetServerPwd2_verify, resetServerPwd2_nextStep;
        EditText resetServerPwd2_verifyEt;
    }
}
