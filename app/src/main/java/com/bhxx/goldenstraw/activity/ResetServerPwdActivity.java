package com.bhxx.goldenstraw.activity;
/**
 * 重置服务密码
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.bhxx.goldenstraw.utils.Code;
import com.bhxx.goldenstraw.utils.IntentUtil;

@InjectLayer(R.layout.activity_reset_server_pwd)
public class ResetServerPwdActivity extends BasicActivity {

    @InjectAll
    private Views v;
    private String realCode;//产生的验证码

    private class Views {
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        ImageView titleBack;
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        TextView titleTitle, resetServerPwd_changeVerify, resetServerPwd_nextStep;
        EditText resetServerPwd_verifyEt;
        ImageView resetServerPwd_verifyImg;//验证码图片
    }

    @Override
    protected void init() {
        ActivityCollector.addActivity(this);
        v.titleTitle.setText("重置服务密码");
        v.resetServerPwd_verifyImg.setImageBitmap(Code.getInstance().createBitmap());
        realCode = Code.getInstance().getCode().toLowerCase();
    }

    @Override
    protected void click(View view) {
        switch (view.getId()) {
            case R.id.titleBack:
                finish();
                break;
            case R.id.resetServerPwd_changeVerify://换一张
                v.resetServerPwd_verifyImg.setImageBitmap(Code.getInstance().createBitmap());
                realCode = Code.getInstance().getCode().toLowerCase();
                break;
            case R.id.resetServerPwd_nextStep://下一步
                if (!v.resetServerPwd_verifyEt.getText().toString().equals(realCode)) {
                    showToast("验证码有误");
                    return;
                }
                IntentUtil.setIntent(ResetServerPwdActivity.this, ResetServerPwd2Activity.class);
                finish();
                break;
        }
    }



}
