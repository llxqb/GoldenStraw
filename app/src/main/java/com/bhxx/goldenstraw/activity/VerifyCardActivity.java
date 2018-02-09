package com.bhxx.goldenstraw.activity;
/**
 * 验证银行卡
 */

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.pc.ioc.event.EventBus;
import com.android.pc.ioc.inject.InjectAll;
import com.android.pc.ioc.inject.InjectBinder;
import com.android.pc.ioc.inject.InjectLayer;
import com.android.pc.ioc.view.listener.OnClick;
import com.bhxx.goldenstraw.R;
import com.bhxx.goldenstraw.entity.CheckStepEntity;
import com.bhxx.goldenstraw.utils.ActivityCollector;

@InjectLayer(R.layout.activity_verify_card)
public class VerifyCardActivity extends BasicActivity {
    @InjectAll
    private Views v;

    private class Views {
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        ImageView titleBack, titleRightImg;
        TextView titleTitle;
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        TextView verifyCardBank, verifyCardSheng, verifyCardPhone, verifyCard_nextStep;
        EditText verifyCardCardNumEt;
    }

    @Override
    protected void init() {
        ActivityCollector.addActivity(this);
        v.titleRightImg.setVisibility(View.VISIBLE);
        v.titleRightImg.setImageResource(R.mipmap.icon_meanu);
        v.titleTitle.setText("确认银行卡");
    }

    @Override
    protected void click(View view) {
        switch (view.getId()) {
            case R.id.titleBack:
                finish();
                break;
            case R.id.verifyCardBank://开户行
                showDialog3();
                break;
            case R.id.verifyCardSheng://开户省市
                showToast("开户省市");
                break;
            case R.id.verifyCard_nextStep://提交
                showDialog1();
                break;
        }
    }

    /**
     * 样式1  图片 文字 底部按钮
     */
    private void showDialog1() {
        // 获取Dialog布局
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_style1, null);

        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        // 设置Dialog最小宽度为屏幕宽度
        view.setMinimumWidth(4 * display.getWidth() / 5);

        // 定义Dialog布局和参数
        final Dialog dialog = new Dialog(VerifyCardActivity.this, R.style.ActionSheetDialogStyle);
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
        TextView dialog_style1_buttonTv = (TextView) view.findViewById(R.id.dialog_style1_buttonTv);
        TextView dialog_style1_centerTv = (TextView) view.findViewById(R.id.dialog_style1_centerTv);

        dialog_style1_buttonTv.setText("我知道了");
        dialog_style1_centerTv.setText("银行卡信息提交成功");

        dialog_style1_buttonTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                EventBus.getDefault().post(new CheckStepEntity(CheckStepEntity.TWO));
                finish();
            }
        });


    }

    /**
     * dialog样式3：顶部文字  集合 + 底部按钮（或有或无）
     */
    private void showDialog3() {
        // 获取Dialog布局
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_style3, null);
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        // 设置Dialog最小宽度为屏幕宽度
        view.setMinimumWidth(4 * display.getWidth() / 5);
        // 定义Dialog布局和参数
        final Dialog dialog = new Dialog(VerifyCardActivity.this, R.style.ActionSheetDialogStyle);
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
        ListView dialog_style3_listview = (ListView) view.findViewById(R.id.dialog_style3_listview);

        final String[] attr = {"工商银行", "建设银行", "农业银行", "中国银行", "招商银行", "广发银行"};

        dialog_style3_listview.setAdapter(new ArrayAdapter<String>(VerifyCardActivity.this, R.layout.arrayadapter_item_text, attr));

        dialog_style3_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showToast(attr[position]);
                v.verifyCardBank.setText(attr[position]);
                dialog.dismiss();
            }
        });
    }
}
