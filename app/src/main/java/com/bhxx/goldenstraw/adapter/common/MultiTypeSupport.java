package com.bhxx.goldenstraw.adapter.common;

/**
 * 适配器中加载多布局使用
 * @类名称：MultiTypeSupport
 * @类描述：根据列表的内容不同定义
 */
public interface MultiTypeSupport<T>{
	
	int getViewTypeCount();
	
	int getItemViewType(int position, T data);
	
	int getLayoutId(int viewType);
}
