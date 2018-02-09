package com.bhxx.goldenstraw.fragment;
/**
 * 身份验证--社会关系
 */

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.pc.ioc.inject.InjectAll;
import com.android.pc.ioc.inject.InjectBinder;
import com.android.pc.ioc.view.listener.OnClick;
import com.android.pc.util.Handler_Inject;
import com.bhxx.goldenstraw.R;
import com.bhxx.goldenstraw.activity.BorrowMoneyCheckActivity;
import com.bhxx.goldenstraw.application.App;
import com.bhxx.goldenstraw.utils.IntentUtil;

public class VerifyidentitySocialRelationsFragment extends BaseFragment {
    @InjectAll
    private Views v;

    private class Views {
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        TextView socialRelations_qinshu_Tv, socialRelations_shehui_Tv, socialRelations_submit;
        EditText socialRelations_qinshuPhone_Et, socialRelations_shehuiPhone_Et;
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        ImageView socialRelations_qinshuPhone_nextRightImg, socialRelations_shehuiPhone_nextRightImg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_verifyidentity_social_relations, container, false);
        Handler_Inject.injectFragment(this, rootView);
        return rootView;
    }

    @Override
    protected void init() {
        //启动计时器 判断下一步是否为可点击状态
        handler.postDelayed(runnable, 1000); //每隔1s执行
    }

    @Override
    protected void click(View view) {
        switch (view.getId()) {
            case R.id.socialRelations_qinshu_Tv://亲属关系
                showDialog3_qinshu();
                break;
            case R.id.socialRelations_shehui_Tv://社会关系
                showDialog3_shehui();
                break;
            case R.id.socialRelations_qinshuPhone_nextRightImg://获取手机通讯录
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, 100);
                break;
            case R.id.socialRelations_shehuiPhone_nextRightImg://获取手机通讯录
                Intent intent2 = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent2, 101);
                break;
            case R.id.socialRelations_submit://提交
//                if (TextUtils.isEmpty(App.app.getData("VerifyIdentityPersonInfo"))) {
//                    showToast("请先认证个人信息");
//                    return;
//                }
//                if (TextUtils.isEmpty(App.app.getData("VerifyIdentityProfessionInfo"))) {
//                    showToast("请先认证职业信息");
//                    return;
//                }
//
//                if (!App.app.getData("VerifyIdentityState").equals("3")) {
//                    showToast("请填写完整信息");
//                    return;
//                }
                IntentUtil.setIntent(getActivity(), BorrowMoneyCheckActivity.class);
                break;
        }
    }

    private void showDialog3_qinshu() {
        // 获取Dialog布局
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_style3, null);
        WindowManager windowManager = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        // 设置Dialog最小宽度为屏幕宽度
        view.setMinimumWidth(4 * display.getWidth() / 5);

        // 定义Dialog布局和参数
        final Dialog dialog = new Dialog(getActivity(), R.style.ActionSheetDialogStyle);
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
        TextView dialog_style3_title = (TextView) view.findViewById(R.id.dialog_style3_title);
        dialog_style3_title.setText("亲属关系");

        final String[] attr = {"父母", "配偶", "兄弟姐妹"};

        dialog_style3_listview.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.arrayadapter_item_text, attr));

        dialog_style3_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                v.socialRelations_qinshu_Tv.setText(attr[position]);
                dialog.dismiss();
            }
        });


    }

    private void showDialog3_shehui() {
        // 获取Dialog布局
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_style3, null);
        WindowManager windowManager = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        // 设置Dialog最小宽度为屏幕宽度
        view.setMinimumWidth(4 * display.getWidth() / 5);

        // 定义Dialog布局和参数
        final Dialog dialog = new Dialog(getActivity(), R.style.ActionSheetDialogStyle);
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
        TextView dialog_style3_title = (TextView) view.findViewById(R.id.dialog_style3_title);
        dialog_style3_title.setText("社会关系");

        final String[] attr = {"同学", "同事", "朋友"};

        dialog_style3_listview.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.arrayadapter_item_text, attr));

        dialog_style3_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                v.socialRelations_shehui_Tv.setText(attr[position]);
                dialog.dismiss();
            }
        });


    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {

        @Override
        public void run() {
            // handler自带方法实现定时器
            try {
                handler.postDelayed(this, 1000);
                if (!TextUtils.isEmpty(v.socialRelations_qinshu_Tv.getText().toString()) && !TextUtils.isEmpty(v.socialRelations_qinshuPhone_Et.getText().toString()) &&
                        !TextUtils.isEmpty(v.socialRelations_shehui_Tv.getText().toString()) && !TextUtils.isEmpty(v.socialRelations_shehuiPhone_Et.getText().toString())
                        ) {
                    if (TextUtils.isEmpty(App.app.getData("VerifyIdentityPersonInfo"))) {
                        return;
                    }
                    if (!App.app.getData("VerifyIdentityState").equals("2") && !App.app.getData("VerifyIdentityState").equals("3")) {
                        return;
                    }
                    v.socialRelations_submit.setBackgroundResource(R.drawable.round_corner_app);
                    //记录身份认证状态 0 个人信息 ， 1 职业信息 ，2 社会关系
                    App.app.setData("VerifyIdentityState", "3");
                    //社会关系 已认证   0未认证 1已认证
                    App.app.setData("VerifyIdentitySocialRelations", "1");
                    handler.removeCallbacks(runnable);// 关闭定时器处理
                    handler = null;
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    };


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 100:
                if (resultCode == Activity.RESULT_OK) {
                    //  获取返回的联系人的Uri信息
                    Uri contactDataUri = data.getData();
                    Cursor cursor = getActivity().getContentResolver().query(contactDataUri, null, null, null, null);
                    if (cursor.moveToFirst()) {
                        //   获得联系人记录的ID
                        String contactId = cursor.getString(cursor.getColumnIndex(
                                ContactsContract.Contacts._ID));
                        //  获得联系人的名字
                        String name = cursor.getString(cursor.getColumnIndex(
                                ContactsContract.Contacts.DISPLAY_NAME));
                        String phoneNumber = "未找到联系人号码";
                        Cursor phoneCursor = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.
                                Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.
                                Phone.CONTACT_ID + "=" + "?", new String[]{contactId}, null);
                        if (phoneCursor.moveToFirst()) {
                            phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(
                                    ContactsContract.CommonDataKinds.Phone.NUMBER));
                        }
                        //  关闭查询手机号码的cursor
                        phoneCursor.close();
                        v.socialRelations_qinshuPhone_Et.setText(phoneNumber);
                    }
                    //  关闭查询联系人信息的cursor
                    cursor.close();
                }
                break;
            case 101:
                if (resultCode == Activity.RESULT_OK) {
                    //  获取返回的联系人的Uri信息
                    Uri contactDataUri = data.getData();
                    Cursor cursor = getActivity().getContentResolver().query(contactDataUri, null, null, null, null);
                    if (cursor.moveToFirst()) {
                        //   获得联系人记录的ID
                        String contactId = cursor.getString(cursor.getColumnIndex(
                                ContactsContract.Contacts._ID));
                        //  获得联系人的名字
                        String name = cursor.getString(cursor.getColumnIndex(
                                ContactsContract.Contacts.DISPLAY_NAME));
                        String phoneNumber = "未找到联系人号码";
                        Cursor phoneCursor = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.
                                Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.
                                Phone.CONTACT_ID + "=" + "?", new String[]{contactId}, null);
                        if (phoneCursor.moveToFirst()) {
                            phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(
                                    ContactsContract.CommonDataKinds.Phone.NUMBER));
                        }
                        //  关闭查询手机号码的cursor
                        phoneCursor.close();
                        v.socialRelations_shehuiPhone_Et.setText(phoneNumber);
                    }
                    //  关闭查询联系人信息的cursor
                    cursor.close();
                }
                break;
        }
    }

    private void checkPhone(int resultCode, Intent data) {

    }

    @Override
    public void onDestroy() {
        if (handler != null) {
            handler.removeCallbacks(runnable);// 关闭定时器处理
        }
        super.onDestroy();
    }


}
