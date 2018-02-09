package com.bhxx.goldenstraw.activity;
/**
 * 身份认证
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

@InjectLayer(R.layout.activity_verify_identity)
public class VerifyIdentityActivity extends BasicActivity {

    @Override
    protected void init() {
        ActivityCollector.addActivity(this);
        v.titleTitle.setText("身份认证");
    }

    @Override
    protected void click(View view) {

        switch (view.getId()) {
            case R.id.titleBack:
                finish();
                break;
            case R.id.verifyIdentity_nextStep://下一步
                if (TextUtils.isEmpty(v.verifyIdentity_username.getText().toString())) {
                    showToast("姓名不能为空");
                    return;
                }
                IntentUtil.setIntent(VerifyIdentityActivity.this, VerifyIdentity2Activity.class);
                break;
        }
    }

    @InjectAll
    private Views v;

    private class Views {
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        ImageView titleBack;
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        TextView titleTitle, verifyIdentity_nextStep;
        EditText verifyIdentity_username, verifyIdentity_identity;
    }
}
