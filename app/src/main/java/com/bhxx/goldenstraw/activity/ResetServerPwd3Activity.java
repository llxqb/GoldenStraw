package com.bhxx.goldenstraw.activity;
/**
 * 重置服务密码3
 */

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

@InjectLayer(R.layout.activity_reset_server_pwd3)
public class ResetServerPwd3Activity extends BasicActivity {

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
            case R.id.resetServerPwd3_nextStep://重置密码
                if (TextUtils.isEmpty(v.resetServerPwd3_newPwd.getText().toString())) {
                    showToast("请输入新密码");
                    return;
                }
                showDialogStyle4();
                break;
        }
    }

    @InjectAll
    private Views v;

    private class Views {
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        ImageView titleBack;
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        TextView titleTitle, resetServerPwd3_nextStep;
        EditText resetServerPwd3_newPwd;
    }


    /**
     * dialog样式4：标题  文字  底部按钮
     */
    private void showDialogStyle4() {
        // 获取Dialog布局
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_style4, null);
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        // 设置Dialog最小宽度为屏幕宽度
        view.setMinimumWidth(4 * display.getWidth() / 5);

        // 定义Dialog布局和参数
        final Dialog dialog = new Dialog(ResetServerPwd3Activity.this, R.style.ActionSheetDialogStyle);
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

        TextView dialog_style4_title = (TextView) view.findViewById(R.id.dialog_style4_title);
        TextView dialog_style4_content = (TextView) view.findViewById(R.id.dialog_style4_content);
        TextView dialog_style4_buttonTv = (TextView) view.findViewById(R.id.dialog_style4_buttonTv);
        dialog_style4_title.setText("验证提示");
        dialog_style4_content.setText(Html.fromHtml("验证手机时，您将收到短信提醒，我们承诺仅针对您的运营商单做信用评估分析" +
                "，绝不会泄露给他人或第三方机构（发生借款逾期除外）。\n " + "详情参考" + "<font color='red'>" + "《借款服务于隐私条例》" + "</font>"));
        dialog_style4_buttonTv.setText("我知道了");

        dialog_style4_buttonTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                showDialogStyle2();
            }
        });
    }

    /**
     * dialog样式2：文字  图片  文字  底部按钮
     */
    private void showDialogStyle2() {
        // 获取Dialog布局
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_style2, null);
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        // 设置Dialog最小宽度为屏幕宽度
        view.setMinimumWidth(4 * display.getWidth() / 5);

        // 定义Dialog布局和参数
        final Dialog dialog = new Dialog(ResetServerPwd3Activity.this, R.style.ActionSheetDialogStyle);
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

        TextView dialog_style2_title = (TextView) view.findViewById(R.id.dialog_style2_title);
        ImageView dialog_style2_img = (ImageView) view.findViewById(R.id.dialog_style2_img);
        TextView dialog_style2_buttonTv = (TextView) view.findViewById(R.id.dialog_style2_buttonTv);//确认

        dialog_style2_buttonTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //返回到手机认证界面
                finish();
            }
        });


    }
}
