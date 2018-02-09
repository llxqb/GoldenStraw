package com.bhxx.goldenstraw.activity;
/**
 * 还款 --银行卡方式还款
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
import com.bhxx.goldenstraw.utils.CheckUtils;
import com.bhxx.goldenstraw.utils.IntentUtil;
import com.bhxx.goldenstraw.views.TimeButton;

import java.util.LinkedHashMap;

import okhttp3.Call;

@InjectLayer(R.layout.activity_pay_way_bank)
public class PayWayBankActivity extends BasicActivity {

    @InjectAll
    private Views v;

    private class Views {
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        ImageView titleBack;
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        TextView titleTitle, payWay_surePay;
        EditText pay_bank_phone, pay_bank_verifyEt;
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        TimeButton code_bt;
    }


    @Override
    protected void init() {
        ActivityCollector.addActivity(this);
        v.titleTitle.setText("还款");
    }

    @Override
    protected void click(View view) {
        switch (view.getId()) {
            case R.id.titleBack:
                finish();
                break;
            case R.id.code_bt:
                if (TextUtils.isEmpty(v.pay_bank_phone.getText().toString())) {
                    showToast("请填写手机号");
                    return;
                }
                if (!CheckUtils.checkMobile(v.pay_bank_phone.getText().toString())) {
                    showToast("手机格式不正确");
                    return;
                }
                v.code_bt.setRun(true);
//                getCode(v.find_pwd_phone.getText().toString());
                break;
            case R.id.payWay_surePay:
                showToast("确认支付");
                IntentUtil.setIntent(PayWayBankActivity.this, PayFinishActivity.class);
                break;
        }
    }

    /**
     * 获取验证码
     *
     * @param phoneNum
     */
}
