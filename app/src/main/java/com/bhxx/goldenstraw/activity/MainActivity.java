package com.bhxx.goldenstraw.activity;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.pc.ioc.inject.InjectAll;
import com.android.pc.ioc.inject.InjectBinder;
import com.android.pc.ioc.inject.InjectLayer;
import com.android.pc.ioc.view.listener.OnClick;
import com.android.pc.util.MD5;
import com.bhxx.goldenstraw.R;
import com.bhxx.goldenstraw.utils.ActivityCollector;
import com.bhxx.goldenstraw.utils.IntentUtil;
import com.bhxx.goldenstraw.utils.LogUtils;

@InjectLayer(R.layout.activity_main)
public class MainActivity extends BasicActivity {

    @Override
    protected void init() {
        ActivityCollector.addActivity(this);
        initView();
    }

    @InjectAll
    private Views v;
    private int TotalpayMoneyNum;//总还款金额
    private int couponsPayNum = 0;//优惠券金额
    private int moneyType = 0;//借款金额状态 0：500，1：1000
    private int dayType = 0;//借款天数状态 0：7，1：14

    private class Views {
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        ImageView titleBack, titleRightImg, AddCoupons;
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        TextView titleTitle, pay_500, pay_1000, pay_7day, pay_14day,
                financePay, interestPay, managerPay, couponsPay,//4种费用 ：信贷费 、息费、账户管理费、优惠券
                TotalpayMoney, startBorrowMoney;

    }

    private void initView() {
        v.titleTitle.setText("首页");
        v.titleBack.setImageResource(R.mipmap.icon_user);
        v.titleRightImg.setVisibility(View.VISIBLE);
        v.titleRightImg.setImageResource(R.mipmap.icon_message);
    }

    @Override
    protected void click(View view) {
        switch (view.getId()) {
            case R.id.titleBack://跳转到个人中心
                IntentUtil.setIntent(MainActivity.this, PersonalCenterActivity.class);
                break;
            case R.id.titleRightImg:
                IntentUtil.setIntent(MainActivity.this, MessageActivity.class);
                break;
            case R.id.pay_500:
                v.pay_500.setBackgroundResource(R.drawable.round_corner_app);
                v.pay_500.setTextColor(Color.parseColor("#ffffff"));
                v.pay_1000.setBackgroundResource(R.drawable.hollow_round_corner_app);
                v.pay_1000.setTextColor(Color.parseColor("#FF623E"));
                moneyType = 0;
                switch (dayType) {
                    case 0:
                        getPayNum(500, 7);
                        break;
                    case 1:
                        getPayNum(500, 14);
                        break;
                }
                break;
            case R.id.pay_1000:
                v.pay_1000.setBackgroundResource(R.drawable.round_corner_app);
                v.pay_1000.setTextColor(Color.parseColor("#ffffff"));
                v.pay_500.setBackgroundResource(R.drawable.hollow_round_corner_app);
                v.pay_500.setTextColor(Color.parseColor("#FF623E"));
                moneyType = 1;
                switch (dayType) {
                    case 0:
                        getPayNum(1000, 7);
                        break;
                    case 1:
                        getPayNum(1000, 14);
                        break;
                }
                break;
            case R.id.pay_7day:
                v.pay_7day.setBackgroundResource(R.drawable.round_corner_app);
                v.pay_7day.setTextColor(Color.parseColor("#ffffff"));
                v.pay_14day.setBackgroundResource(R.drawable.hollow_round_corner_app);
                v.pay_14day.setTextColor(Color.parseColor("#FF623E"));
                dayType = 0;
                switch (moneyType) {
                    case 0:
                        getPayNum(500, 7);
                        break;
                    case 1:
                        getPayNum(1000, 7);
                        break;
                }
                break;
            case R.id.pay_14day:
                v.pay_14day.setBackgroundResource(R.drawable.round_corner_app);
                v.pay_14day.setTextColor(Color.parseColor("#ffffff"));
                v.pay_7day.setBackgroundResource(R.drawable.hollow_round_corner_app);
                v.pay_7day.setTextColor(Color.parseColor("#FF623E"));
                dayType = 1;
                switch (moneyType) {
                    case 0:
                        getPayNum(500, 14);
                        break;
                    case 1:
                        getPayNum(1000, 14);
                        break;
                }
                break;
            case R.id.startBorrowMoney://开始借款
                IntentUtil.setIntent(MainActivity.this, VerifyPhoneActivity.class);
//                IntentUtil.setIntent(MainActivity.this, PayActivity.class);
                break;
            case R.id.AddCoupons://优惠券
                IntentUtil.setIntent(MainActivity.this, YouHuiQuanActivity.class);
                break;
        }
    }

    private void getPayNum(int MoneyNum, int DayNum) {
        //4种借款方式 0：500*7 ，1：500*14，2：1000*7，3：1000*14
        int totalNum = MoneyNum + DayNum;
        switch (totalNum) {
            case 507:
                //方式1
                v.financePay.setText("18元");
                v.interestPay.setText("1元");
                v.managerPay.setText("6元");
                TotalpayMoneyNum = 500 + 25 - couponsPayNum;
                v.TotalpayMoney.setText(TotalpayMoneyNum + "元");
                break;
            case 514:
                //方式2
                v.financePay.setText("36元");
                v.interestPay.setText("2元");
                v.managerPay.setText("12元");
                TotalpayMoneyNum = 500 + 50 - couponsPayNum;
                v.TotalpayMoney.setText(TotalpayMoneyNum + "元");
                break;
            case 1007:
                //方式3
                v.financePay.setText("36元");
                v.interestPay.setText("2元");
                v.managerPay.setText("12元");
                TotalpayMoneyNum = 1000 + 50 - couponsPayNum;
                v.TotalpayMoney.setText(TotalpayMoneyNum + "元");
                break;
            case 1014:
                //方式4
                v.financePay.setText("72元");
                v.interestPay.setText("4元");
                v.managerPay.setText("24元");
                TotalpayMoneyNum = 1000 + 100 - couponsPayNum;
                v.TotalpayMoney.setText(TotalpayMoneyNum + "元");
                break;
        }
    }

    /**
     * dialog样式1：图片  文字  底部按钮
     * dialog样式2：文字  图片  文字  底部按钮
     * dialog样式3：顶部文字  集合 + 底部按钮（或有或无）
     * dialog样式4：标题  文字  底部按钮
     */
//    private void showDialog1() {
//        // 获取Dialog布局
//        View view = LayoutInflater.from(this).inflate(R.layout.dialog_style1, null);
//
//        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
//        Display display = windowManager.getDefaultDisplay();
//        // 设置Dialog最小宽度为屏幕宽度
//        view.setMinimumWidth(4 * display.getWidth() / 5);
//
//        // 获取自定义Dialog布局中的控件
////        ListView selectcar_dialog_listview = (ListView) view.findViewById(R.id.selectcar_dialog_listview);
//
//        // 定义Dialog布局和参数
//        final Dialog dialog = new Dialog(MainActivity.this, R.style.ActionSheetDialogStyle);
//        dialog.setContentView(view);
//        dialog.setCancelable(true);
//        dialog.setCanceledOnTouchOutside(true);
//        Window dialogWindow = dialog.getWindow();
//        dialogWindow.setGravity(Gravity.CENTER);
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        lp.x = 0;
//        lp.y = 0;
//        dialogWindow.setAttributes(lp);
//        dialog.show();
//    }
//
//    private void showDialog2() {
//        // 获取Dialog布局
//        View view = LayoutInflater.from(this).inflate(R.layout.dialog_style2, null);
//        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
//        Display display = windowManager.getDefaultDisplay();
//        // 设置Dialog最小宽度为屏幕宽度
//        view.setMinimumWidth(4 * display.getWidth() / 5);
//
//        // 获取自定义Dialog布局中的控件
////        ListView selectcar_dialog_listview = (ListView) view.findViewById(R.id.selectcar_dialog_listview);
//
//        // 定义Dialog布局和参数
//        final Dialog dialog = new Dialog(MainActivity.this, R.style.ActionSheetDialogStyle);
//        dialog.setContentView(view);
//        dialog.setCancelable(true);
//        dialog.setCanceledOnTouchOutside(true);
//        Window dialogWindow = dialog.getWindow();
//        dialogWindow.setGravity(Gravity.CENTER);
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        lp.x = 0;
//        lp.y = 0;
//        dialogWindow.setAttributes(lp);
//        dialog.show();
//    }
//
//    private void showDialog3() {
//        // 获取Dialog布局
//        View view = LayoutInflater.from(this).inflate(R.layout.dialog_style3, null);
//        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
//        Display display = windowManager.getDefaultDisplay();
//        // 设置Dialog最小宽度为屏幕宽度
//        view.setMinimumWidth(4 * display.getWidth() / 5);
//
//        // 获取自定义Dialog布局中的控件
//        ListView dialog_style3_listview = (ListView) view.findViewById(R.id.dialog_style3_listview);
//
//        String[] attr = {"程序猿", "设计师", "教师", "服务员", "司机", "厨师"};
//
//        dialog_style3_listview.setAdapter(new ArrayAdapter<String>(MainActivity.this, R.layout.arrayadapter_item_text, attr));
//
//        // 定义Dialog布局和参数
//        final Dialog dialog = new Dialog(MainActivity.this, R.style.ActionSheetDialogStyle);
//        dialog.setContentView(view);
//        dialog.setCancelable(true);
//        dialog.setCanceledOnTouchOutside(true);
//        Window dialogWindow = dialog.getWindow();
//        dialogWindow.setGravity(Gravity.CENTER);
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        lp.x = 0;
//        lp.y = 0;
//        dialogWindow.setAttributes(lp);
//        dialog.show();
//    }
//
//    private void showDialog4() {
//        // 获取Dialog布局
//        View view = LayoutInflater.from(this).inflate(R.layout.dialog_style4, null);
//        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
//        Display display = windowManager.getDefaultDisplay();
//        // 设置Dialog最小宽度为屏幕宽度
//        view.setMinimumWidth(4 * display.getWidth() / 5);
//
//        // 定义Dialog布局和参数
//        final Dialog dialog = new Dialog(MainActivity.this, R.style.ActionSheetDialogStyle);
//        dialog.setContentView(view);
//        dialog.setCancelable(true);
//        dialog.setCanceledOnTouchOutside(true);
//        Window dialogWindow = dialog.getWindow();
//        dialogWindow.setGravity(Gravity.CENTER);
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        lp.x = 0;
//        lp.y = 0;
//        dialogWindow.setAttributes(lp);
//        dialog.show();
//    }
}
