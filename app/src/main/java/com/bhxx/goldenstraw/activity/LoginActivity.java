package com.bhxx.goldenstraw.activity;

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
import com.bhxx.goldenstraw.utils.CheckUtils;
import com.bhxx.goldenstraw.utils.IntentUtil;
import com.bhxx.goldenstraw.views.TimeButton;

@InjectLayer(R.layout.activity_login)
public class LoginActivity extends BasicActivity {
    @InjectAll
    private Views v;

    private class Views {
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        ImageView titleBack;
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        TextView titleTitle, LoginTv;
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        TimeButton code_bt;
        EditText login_phone;
    }

    @Override
    protected void init() {
        ActivityCollector.addActivity(this);
        v.titleTitle.setText("用户登录");
    }

    @Override
    protected void click(View view) {
        switch (view.getId()) {
            case R.id.titleBack:
                finish();
                break;
            case R.id.LoginTv://登录
                IntentUtil.setIntent(LoginActivity.this, MainActivity.class);
                finish();
                break;
            case R.id.code_bt:
                if (TextUtils.isEmpty(v.login_phone.getText().toString())) {
                    showToast("请填写手机号");
                    return;
                }
                if (!CheckUtils.checkMobile(v.login_phone.getText().toString())) {
                    showToast("手机格式不正确");
                    return;
                }
                v.code_bt.setRun(true);
                break;
        }
    }
}
