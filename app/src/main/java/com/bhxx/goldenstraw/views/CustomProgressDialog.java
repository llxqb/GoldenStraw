package com.bhxx.goldenstraw.views;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bhxx.goldenstraw.R;


/**
 * 自定义的 ProgressDialog
 *
 * @since 2014-12-04
 */
public class CustomProgressDialog extends Dialog {

    private Context context = null;
    private static CustomProgressDialog customProgressDialog = null;

    public CustomProgressDialog(Context context) {
        super(context);
        this.context = context;
    }

    public CustomProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    public static CustomProgressDialog createDialog(Context context) {
        customProgressDialog = new CustomProgressDialog(context, R.style.CustomDialog);
        customProgressDialog.setContentView(R.layout.loading_dialog_view);
        customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
//        customProgressDialog.setMessage("正在加载中...");// 默认
        customProgressDialog.setCanceledOnTouchOutside(false);
        return customProgressDialog;
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        if (customProgressDialog == null) {
            return;
        }
        ProgressBar progressBar = (ProgressBar) customProgressDialog.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * setTitile 标题
     *
     * @param strTitle
     * @return
     */
    public CustomProgressDialog setTitile(String strTitle) {
        return customProgressDialog;
    }

    /**
     * setMessage 提示内容
     *
     * @param strMessage
     * @return
     */
    public CustomProgressDialog setMessage(String strMessage) {
        TextView tvMsg = (TextView) customProgressDialog.findViewById(R.id.tv_loadingmsg);

        if (tvMsg != null) {
            tvMsg.setText(strMessage);
        }

        return customProgressDialog;
    }
}