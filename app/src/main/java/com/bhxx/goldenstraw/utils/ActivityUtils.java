package com.bhxx.goldenstraw.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class ActivityUtils {

	/**
	 * 跳转到裁剪页面
	 * @param activity
	 * @param
	 */
	public static void startImageCropActivity(Activity activity, int requestCode, Uri sourceUri, Uri ouputUri){
		
		Intent intent = new Intent("com.android.camera.action.CROP");
		   intent.setDataAndType(sourceUri, "image/*");  
		   intent.putExtra("crop", "true");//可裁剪  
		   intent.putExtra("aspectX", 1);  
		   intent.putExtra("aspectY", 1);  
		   intent.putExtra("outputX", 150);  
		   intent.putExtra("outputY", 150);  
		   intent.putExtra("scale", true);  
		   intent.putExtra(MediaStore.EXTRA_OUTPUT, ouputUri);
		   intent.putExtra("return-data", false);//若为false则表示不返回数据  
		   intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		   intent.putExtra("noFaceDetection", true);   
		   activity.startActivityForResult(intent, requestCode); 
	}
	
	/**
	 * 安装应用
	 * @param context
	 * @param uri
	 */
	public static void installApk(Context context, Uri uri){
		
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(Intent.ACTION_VIEW);

		/* 调用getMIMEType()来取得MimeType */
		String type = "application/vnd.android.package-archive";

		/* 设置intent的file与MimeType */
		intent.setDataAndType(uri, type);
		/*
		* 经过实验，发现无论是否成功安装，该Intent都返回result为0 具体结果如下： type requestCode
		* resultCode data 取消安装 10086 0 null 覆盖安装 10086 0 null 全新安装 10086 0
		* null
		*/
		context.startActivity(intent); 
	}

	/**
	 * 获得屏幕高度
	 *
	 * @param context
	 * @return
	 */
	public static int getScreenWidth(Context context)
	{
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.widthPixels;
	}

	/**
	 * 获得屏幕宽度
	 *
	 * @param context
	 * @return
	 */
	public static int getScreenHeight(Context context)
	{
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.heightPixels;
	}

	/**
	 * 获取当前屏幕截图，包含状态栏
	 *
	 * @param activity
	 * @return
	 */
	public static Bitmap snapShotWithStatusBar(Activity activity)
	{
		View view = activity.getWindow().getDecorView();
		view.setDrawingCacheEnabled(true);
		view.buildDrawingCache();
		Bitmap bmp = view.getDrawingCache();
		int width = getScreenWidth(activity);
		int height = getScreenHeight(activity);
		Bitmap bp = null;
		bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
		view.destroyDrawingCache();
		return bp;

	}

	/**
	 * 获取当前屏幕截图，不包含状态栏
	 *
	 * @param activity
	 * @return
	 */
	public static Bitmap snapShotWithoutStatusBar(Activity activity)
	{
		View view = activity.getWindow().getDecorView();
		view.setDrawingCacheEnabled(true);
		view.buildDrawingCache();
		Bitmap bmp = view.getDrawingCache();
		Rect frame = new Rect();
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		int statusBarHeight = frame.top;

		int width = getScreenWidth(activity);
		int height = getScreenHeight(activity);
		Bitmap bp = null;
		bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height
				- statusBarHeight);
		view.destroyDrawingCache();
		return bp;
	}


	/**
	 * 打卡软键盘
	 *
	 * @param mEditText
	 *            输入框
	 * @param mContext
	 *            上下文
	 */
	public static void openKeybord(EditText mEditText, Context mContext)
	{
		InputMethodManager imm = (InputMethodManager) mContext
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
				InputMethodManager.HIDE_IMPLICIT_ONLY);
	}

	/**
	 * 关闭软键盘
	 *
	 * @param mEditText
	 *            输入框
	 * @param mContext
	 *            上下文
	 * 在manifest中设置 android:windowSoftInputMode="adjustPan|stateHidden"也能默认不打开软键盘
	 */
	public static void closeKeybord(EditText mEditText, Context mContext)
	{
		InputMethodManager imm = (InputMethodManager) mContext
				.getSystemService(Context.INPUT_METHOD_SERVICE);

		imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
	}


}
