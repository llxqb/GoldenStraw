package com.bhxx.goldenstraw.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.pc.ioc.inject.InjectInit;
import com.bhxx.goldenstraw.R;
import com.bhxx.goldenstraw.views.CustomProgressDialog;

public abstract class BaseFragment extends Fragment {
    private Toast toast;
    public CustomProgressDialog progressDialog = null;

    @InjectInit
    protected abstract void init();

    protected abstract void click(View view);

    protected void showToast(int resId) {
        if (toast == null) {
            toast = Toast.makeText(getActivity(), resId, Toast.LENGTH_SHORT);
        } else {
            toast.setText(resId);
        }
        toast.show();
    }

    protected void showToast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    /**
     * 方法说明 对话框显示
     *
     * @param
     * @return
     * @author
     */
    protected void showProgressDialog(Context context) {
        if (null == progressDialog) {
            progressDialog = CustomProgressDialog.createDialog(context);
        }
        progressDialog.show();
    }

    protected void showProgressDialog(Context context, String messager) {
        if (null == progressDialog) {
            progressDialog = CustomProgressDialog.createDialog(context);
        }
        progressDialog.setMessage(messager);
        progressDialog.show();
    }

    /**
     * 方法说明 对话框关闭
     * @param
     * @return
     * @author
     */
    protected void dismissProgressDialog() {
        if (null != progressDialog && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }


}
