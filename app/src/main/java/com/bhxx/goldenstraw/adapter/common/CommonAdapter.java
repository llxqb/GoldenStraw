package com.bhxx.goldenstraw.adapter.common;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

/**
 * 通用适配器
 */
public abstract class CommonAdapter<T> extends BaseObjectAdapter<T>{

	private MultiTypeSupport<T> multiTypeSupport;
	private int layoutId;
	protected Toast toast;
	public CommonAdapter(List<T> dataList, Context context, MultiTypeSupport<T> multiTypeSupport) {
		super(dataList,context);
		this.context = context;
		this.multiTypeSupport = multiTypeSupport;
	}
	
	public CommonAdapter(List<T> dataList, Context context, int layoutId) {
		super(dataList,context);
		this.context = context;
		this.layoutId = layoutId;
	}
	protected void showToast(int resId) {
		if (toast == null) {
			toast = Toast.makeText(context, resId, Toast.LENGTH_SHORT);
		} else {
			toast.setText(resId);
		}
		toast.show();
	}

	protected void showToast(String msg) {
		if (toast == null) {
			toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
		} else {
			toast.setText(msg);
		}
		toast.show();
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder;
		if(multiTypeSupport != null){
				
			int itemViewType = getItemViewType(position);
			
			int layoutId = multiTypeSupport.getLayoutId(itemViewType);
			
			holder = ViewHolder.getViewHolder(context,convertView,layoutId,parent,position);
		}else{
			
			holder = ViewHolder.getViewHolder(context, convertView, layoutId, parent,position);
		}
		
		convert(holder, getDataAt(position));
		return holder.getConvertView();
	}
	
	public abstract void convert(ViewHolder holder,T data);
	
	@Override
	public int getItemViewType(int position) {
		
		if(multiTypeSupport != null){
			
			return multiTypeSupport.getItemViewType(position, getDataAt(position));
		}
		return super.getItemViewType(position);
	}
	
	@Override
	public int getViewTypeCount() {
		
		if(multiTypeSupport != null){
			
			return multiTypeSupport.getViewTypeCount();
		}
		return super.getViewTypeCount();
	}
	
	
}
