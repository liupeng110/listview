package com.andlp.listview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Adapter extends BaseAdapter
{
	private Context context;
	public Adapter(Context context)
	{
		this.context = context;
	}
	@Override public int getCount()
	{
		return 40;
	}

	@Override public Object getItem(int position)
	{
		return null;
	}

	@Override public long getItemId(int position)
	{
		return 0;
	}

	@SuppressLint("SetTextI18n")
	@Override public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView == null) {
			holder = new ViewHolder();
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.item, null);
			holder.tv =  convertView.findViewById(R.id.tv);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder)convertView.getTag();
		}
		holder.tv.setText((position + 1) + ". " + "hello friends.");
		return convertView;
	}

	class ViewHolder { TextView tv; }
}
