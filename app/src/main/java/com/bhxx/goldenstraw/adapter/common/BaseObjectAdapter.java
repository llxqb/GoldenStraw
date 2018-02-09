package com.bhxx.goldenstraw.adapter.common;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseObjectAdapter<T> extends BaseAdapter {

	private List<T> dataList;
	protected Context context;
	
	public BaseObjectAdapter(List<T> dataList, Context context) {
		super();
		this.dataList = (dataList == null ? new ArrayList<T>() : dataList);
		this.context = context;
	}

	@Override
	public int getCount() {
		return this.dataList.size();
	}

	@Override
	public Object getItem(int position) {
		return this.dataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	
	/**
	 * 在指定位置添加数�?
	 * @param data
	 * @param position
	 */
	public void addDataAtIndex(T data,int position){
		
		this.dataList.add(position, data);
		notifyDataSetChanged();
	}
	
	/**
	 * 在指定位置插入数据列�?
	 * @param dataList
	 * @param position
	 */
	public void addDataLsitAtIndex(List<T> dataList, int position){
		
		this.dataList.addAll(position, dataList);
		notifyDataSetChanged();
	}
	
	/**
	 * 在末尾添加数�?
	 * @param data
	 */
	public void addDataAtLast(T data){
		
		addDataAtIndex(data, this.dataList.size());
	}
	
	/**
	 * 在末尾插入数据列�?
	 * @param dataList
	 */
	public void addDataListAtLast(List<T> dataList){
		
		addDataLsitAtIndex(dataList, this.dataList.size());
	}
	
	/**
	 * 在开始位置添加数�?
	 * @param data
	 */
	public void addDataAtFirst(T data){
		
		addDataAtIndex(data, 0);
	}
	
	/**
	 * 在开始位置添加数据列�?
	 * @param dataList
	 */
	public void addDataListAtFirst(List<T> dataList){
		
		addDataLsitAtIndex(dataList, 0);
	}
	
	/**
	 * 移除指定位置的数�?
	 * @param position
	 */
	public void removeDataAtIndex(int position){
		
		this.dataList.remove(position);
		notifyDataSetChanged();
	}
	
	/**
	 * 移除�?个数�?
	 * @param data
	 */
	public void removeData(T data){
		
		int count = getCount();
		for (int i = 0; i < count; i++) {
			
			if(this.dataList.get(i).equals(data)){
				
				removeDataAtIndex(i);
				break;
			}
		}
	}
	
	public void removeAll(){
		
		this.dataList.clear();
		notifyDataSetChanged();
	}
	
	/**
	 * 设置数据
	 * @param dataList
	 */
	public void setDataList(List<T> dataList){
		
		if(this.dataList == null){
			this.dataList = new ArrayList<T>();
		}
		
		this.dataList.clear();
		this.dataList.addAll(dataList);
		notifyDataSetChanged();
	}
	
	/**
	 * 获取指定位置的数�?
	 * @param position
	 */
	public T getDataAt(int position){
		
		return this.dataList.get(position);
	}
	/**
	 * 获取指定位置的数�?
	 * @param position
	 */
	public int getDataIndex(T t){
		
		return this.dataList.indexOf(t);
	}
	
	/**
	 * 获取数据列表
	 * @return
	 */
	public List<T> getDataList(){
		
		return this.dataList;
	}

}
