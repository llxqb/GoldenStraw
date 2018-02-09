package com.bhxx.goldenstraw.views;
/**
 * 获取手机验证码60S倒计时
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class TimeButton extends Button implements View.OnClickListener {
	private final String CTIME = "ctime";
	private final String TIME = "time";
	private int afterBg;
	private int beforBg;

	@SuppressLint({ "HandlerLeak" })
	Handler han = new Handler() {
		public void handleMessage(Message paramMessage) {
			TimeButton.this.setText(TimeButton.this.time / 1000L + TimeButton.this.textafter);
			TimeButton.this.setBackgroundResource(TimeButton.this.afterBg);
			TimeButton localTimeButton = TimeButton.this;
			localTimeButton.time -= 1000L;
			if (TimeButton.this.time < 0L) {
				TimeButton.this.setEnabled(true);
				TimeButton.this.setBackgroundResource(TimeButton.this.beforBg);
				TimeButton.this.setText(TimeButton.this.textbefore);
				TimeButton.this.clearTimer();
			}
		}
	};
	private boolean isRun;
	private long lenght = 60000L;
	private OnClickListener mOnclickListener;
	Map<String, Long> map = new HashMap();
	private Timer t;
	private String textafter = "s重新获取";
	private String textbefore = "获取验证码";
	private long time;
	private TimerTask tt;

	public TimeButton(Context paramContext) {
		super(paramContext);
		setOnClickListener(this);
	}

	public TimeButton(Context paramContext, AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
		setOnClickListener(this);
	}

	private void clearTimer() {
		if (this.tt != null) {
			this.tt.cancel();
			this.tt = null;
		}
		if (this.t != null)
			this.t.cancel();
		this.t = null;
	}

	private void initTimer() {
		this.time = this.lenght;
		this.t = new Timer();
		this.tt = new TimerTask() {
			public void run() {
				TimeButton.this.han.sendEmptyMessage(1);
			}
		};
	}

	public boolean isRun() {
		return this.isRun;
	}

	public void onClick(View paramView) {
		if (this.mOnclickListener != null)
			this.mOnclickListener.onClick(paramView);
		if (this.isRun) {
			initTimer();
			setText(this.time / 1000L + this.textafter);
			setEnabled(false);
			this.t.schedule(this.tt, 0L, 1000L);
		}
	}

	public void onCreate(Bundle paramBundle) {
		if (this.time > 0L)
			return;
		initTimer();
		this.time = Math.abs(this.time);
		this.t.schedule(this.tt, 0L, 1000L);
		setText(this.time + this.textafter);
		setEnabled(false);
	}

	public void onDestroy() {
		clearTimer();
	}

	public void setAfterBg(int paramInt) {
		this.afterBg = paramInt;
	}

	public void setBeforBg(int paramInt) {
		this.beforBg = paramInt;
	}

	public TimeButton setLenght(long paramLong) {
		this.lenght = paramLong;
		return this;
	}

	public void setOnClickListener(OnClickListener paramOnClickListener) {
		if ((paramOnClickListener instanceof TimeButton)) {
			super.setOnClickListener(paramOnClickListener);
			return;
		}
		this.mOnclickListener = paramOnClickListener;
	}

	public void setRun(boolean paramBoolean) {
		this.isRun = paramBoolean;
	}

	public TimeButton setTextAfter(String paramString) {
		this.textafter = paramString;
		return this;
	}

	public TimeButton setTextBefore(String paramString) {
		this.textbefore = paramString;
		setText(this.textbefore);
		return this;
	}
}

/*
 * Location: C:\Users\Administrator\Desktop\classes_dex2jar.jar Qualified Name:
 * com.android.pc.ioc.view.TimeButton JD-Core Version: 0.6.0
 */