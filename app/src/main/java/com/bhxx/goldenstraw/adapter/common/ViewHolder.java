package com.bhxx.goldenstraw.adapter.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.method.MovementMethod;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

public class ViewHolder {

	private SparseArray<View> views;
	private View mConvertView;
	private int layoutId;
	private int position;
	private Context context;

	public ViewHolder(Context context, int layoutId, ViewGroup root,
					  int position) {
		super();
		this.context = context;
		this.layoutId = layoutId;
		this.position = position;
		this.views = new SparseArray<View>();
		this.mConvertView = LayoutInflater.from(context).inflate(layoutId,
				root, false);
		this.mConvertView.setTag(this);
	}

	public static <T> ViewHolder getViewHolder(Context context,
											   View convertView, int layoutId, ViewGroup root, int position) {

		if (convertView == null) {

			return new ViewHolder(context, layoutId, root, position);
		}
		return (ViewHolder) convertView.getTag();
	}

	public ViewHolder setText(int viewId, CharSequence charSequence) {

		TextView textView = getView(viewId);
		textView.setText(charSequence);
		return this;
	}
	public String getText(int viewId) {

		TextView textView = getView(viewId);
		return textView.getText().toString();
	}

	public ViewHolder setImageResource(int viewId, int imageResource) {

		ImageView imageView = getView(viewId);
		imageView.setImageResource(imageResource);
		return this;
	}

	public View getConvertView() {
		return mConvertView;
	}

	public ViewHolder setMidLine(int viewId) {
		TextView tv = getView(viewId);
		tv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		return this;
	}

	@SuppressWarnings("unchecked")
	public <T extends View> T getView(int viewId) {

		View view = views.get(viewId);

		if (view == null) {

			view = mConvertView.findViewById(viewId);
			if (view != null) {

				views.put(viewId, view);
			}
		}

		return (T) view;
	}

	public int getLayoutId() {
		return layoutId;
	}

	public void setLayoutId(int layoutId) {
		this.layoutId = layoutId;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public ViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
		ImageView view = getView(viewId);
		view.setImageBitmap(bitmap);
		return this;
	}

	public ViewHolder setImageDrawable(int viewId, Drawable drawable) {
		ImageView view = getView(viewId);
		view.setImageDrawable(drawable);
		return this;
	}

	public ViewHolder setBackgroundColor(int viewId, int color) {
		View view = getView(viewId);
		view.setBackgroundColor(color);
		return this;
	}

	public ViewHolder setBackgroundRes(int viewId, int backgroundRes) {
		View view = getView(viewId);
		view.setBackgroundResource(backgroundRes);
		return this;
	}

	public ViewHolder setTextColor(int viewId, int textColor) {
		TextView view = getView(viewId);
		view.setTextColor(textColor);
		return this;
	}

	public ViewHolder setTextColorRes(int viewId, int textColorRes) {
		TextView view = getView(viewId);
		view.setTextColor(context.getResources().getColor(textColorRes));
		return this;
	}

	@SuppressLint("NewApi")
	public ViewHolder setAlpha(int viewId, float value) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getView(viewId).setAlpha(value);
		} else {
			// Pre-honeycomb hack to set Alpha value
			AlphaAnimation alpha = new AlphaAnimation(value, value);
			alpha.setDuration(0);
			alpha.setFillAfter(true);
			getView(viewId).startAnimation(alpha);
		}
		return this;
	}

	public ViewHolder setVisible(int viewId, boolean visible) {
		View view = getView(viewId);
		view.setVisibility(visible ? View.VISIBLE : View.GONE);
		return this;
	}

	public ViewHolder setVisibility(int viewId, int visible) {
		View view = getView(viewId);
		view.setVisibility(visible);
		return this;
	}

	public ViewHolder linkify(int viewId) {
		TextView view = getView(viewId);
		Linkify.addLinks(view, Linkify.ALL);
		return this;
	}

	public ViewHolder setTypeface(Typeface typeface, int... viewIds) {
		for (int viewId : viewIds) {
			TextView view = getView(viewId);
			view.setTypeface(typeface);
			view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
		}
		return this;
	}

	public ViewHolder setProgress(int viewId, int progress) {
		ProgressBar view = getView(viewId);
		view.setProgress(progress);
		return this;
	}

	public ViewHolder setProgress(int viewId, int progress, int max) {
		ProgressBar view = getView(viewId);
		view.setMax(max);
		view.setProgress(progress);
		return this;
	}

	public ViewHolder setMax(int viewId, int max) {
		ProgressBar view = getView(viewId);
		view.setMax(max);
		return this;
	}

	public ViewHolder setRating(int viewId, float rating) {
		RatingBar view = getView(viewId);
		view.setRating(rating);
		return this;
	}

	public ViewHolder setRating(int viewId, float rating, int max) {
		RatingBar view = getView(viewId);
		view.setMax(max);
		view.setRating(rating);
		return this;
	}

	public ViewHolder setTag(int viewId, Object tag) {
		View view = getView(viewId);
		view.setTag(tag);
		return this;
	}

	public ViewHolder setTag(int viewId, int key, Object tag) {
		View view = getView(viewId);
		view.setTag(key, tag);
		return this;
	}

	public ViewHolder setChecked(int viewId, boolean checked) {
		Checkable view = (Checkable) getView(viewId);
		view.setChecked(checked);
		return this;
	}

	/**
	 * 关于事件�?
	 */
	public ViewHolder setOnClickListener(int viewId,
			View.OnClickListener listener) {
		View view = getView(viewId);
		view.setOnClickListener(listener);
		return this;
	}

	public ViewHolder setOnTouchListener(int viewId,
			View.OnTouchListener listener) {
		View view = getView(viewId);
		view.setOnTouchListener(listener);
		return this;
	}

	public ViewHolder setOnLongClickListener(int viewId,
			View.OnLongClickListener listener) {
		View view = getView(viewId);
		view.setOnLongClickListener(listener);
		return this;
	}

	public ViewHolder setAdapter(int viewId, Adapter adapter) {

		AdapterView<Adapter> adapterView = getView(viewId);
		adapterView.setAdapter(adapter);
		return this;
	}

	public ViewHolder setOnItemClickListener(int viewId,
			OnItemClickListener itemClickListener) {

		AdapterView<Adapter> adapterView = getView(viewId);
		adapterView.setOnItemClickListener(itemClickListener);
		return this;
	}

	public ViewHolder setOnItemLongClickListener(int viewId,
			OnItemLongClickListener listener) {

		AdapterView<Adapter> adapterView = getView(viewId);
		adapterView.setOnItemLongClickListener(listener);
		return this;
	}

	public ViewHolder setCompoundDrawablePadding(int viewId, int pad) {

		TextView textView = getView(viewId);
		textView.setCompoundDrawablePadding(pad);
		return this;
	}

	public ViewHolder setCompoundDrawablesWithIntrinsicBounds(int viewId,
															  Drawable left, Drawable top, Drawable right, Drawable bottom) {

		TextView textView = getView(viewId);
		textView.setCompoundDrawablesWithIntrinsicBounds(left, top, right,
				bottom);
		return this;
	}

	public ViewHolder setLayouParams(int viewId, LayoutParams layoutParams) {

		getView(viewId).setLayoutParams(layoutParams);
		return this;
	}

	public int getVisiblity(int viewId) {

		return getView(viewId).getVisibility();
	}

	public ViewHolder setMovementMethod(int viewId,
			MovementMethod movementMethod) {

		TextView textView = getView(viewId);
		textView.setMovementMethod(movementMethod);
		return this;
	}

	public ViewHolder setOnCheckedChangeListener(int viewId,
			OnCheckedChangeListener onCheckedChangeListener) {

		CheckBox checkBox = getView(viewId);
		checkBox.setOnCheckedChangeListener(onCheckedChangeListener);
		return this;
	}
}
