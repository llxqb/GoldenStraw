package com.bhxx.goldenstraw.activity;
/**
 * 签约
 */

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.pc.ioc.event.EventBus;
import com.android.pc.ioc.inject.InjectAll;
import com.android.pc.ioc.inject.InjectBinder;
import com.android.pc.ioc.inject.InjectLayer;
import com.android.pc.ioc.view.listener.OnClick;
import com.bhxx.goldenstraw.R;
import com.bhxx.goldenstraw.entity.CheckStepEntity;
import com.bhxx.goldenstraw.utils.ActivityCollector;
import com.bhxx.goldenstraw.utils.IntentUtil;

@InjectLayer(R.layout.activity_verify_sign)
public class VerifySignActivity extends BasicActivity {

    @InjectAll
    private Views v;

    private class Views {
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        ImageView titleBack, titleRightImg, verifySign_detail1, verifySign_detail2;
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        TextView titleTitle, verifySign_nextStep, VerifySign_xieyi;
    }

    @Override
    protected void init() {
        ActivityCollector.addActivity(this);
        v.titleRightImg.setVisibility(View.VISIBLE);
        v.titleRightImg.setImageResource(R.mipmap.icon_meanu);
        v.titleTitle.setText("签约");
    }

    @Override
    protected void click(View view) {
        switch (view.getId()) {
            case R.id.titleBack:
                finish();
                break;
            case R.id.verifySign_nextStep://放款
                EventBus.getDefault().post(new CheckStepEntity(CheckStepEntity.FOUR));
                finish();
                break;
            case R.id.VerifySign_xieyi://协议
                IntentUtil.setIntent(this, TiShi2Activity.class);
                break;
            case R.id.verifySign_detail1://综合费用详情
                showDialog5();
                break;
            case R.id.verifySign_detail2://逾期政策详情
                showDialog6();
                break;
        }
    }

    private void showDialog5() {
        // 获取Dialog布局
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_style5, null);
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        // 设置Dialog最小宽度为屏幕宽度
        view.setMinimumWidth(4 * display.getWidth() / 5);

        // 定义Dialog布局和参数
        final Dialog dialog = new Dialog(VerifySignActivity.this, R.style.ActionSheetDialogStyle);
        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        dialogWindow.setAttributes(lp);
        dialog.show();

        // 获取自定义Dialog布局中的控件
        TextView dialogStyle5_isIKnow = (TextView) view.findViewById(R.id.dialogStyle5_isIKnow);

        dialogStyle5_isIKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void showDialog6() {
        // 获取Dialog布局
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_style5_1, null);
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        // 设置Dialog最小宽度为屏幕宽度
        view.setMinimumWidth(4 * display.getWidth() / 5);

        // 定义Dialog布局和参数
        final Dialog dialog = new Dialog(VerifySignActivity.this, R.style.ActionSheetDialogStyle);
        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        dialogWindow.setAttributes(lp);
        dialog.show();

        // 获取自定义Dialog布局中的控件
        TextView dialogStyle5_1_isIKnow = (TextView) view.findViewById(R.id.dialogStyle5_1_isIKnow);

        dialogStyle5_1_isIKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


}
