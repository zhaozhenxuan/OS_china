package com.example.administrator.os_china.adapter.find_Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.os_china.R;
import com.example.administrator.os_china.model.entity.find_Beans.Fenlei_Beans;
import com.example.administrator.os_china.model.entity.find_Beans.Fenlei_three_Beans;

import java.util.List;

/**
 * Created by Administrator on 2017/5/25 0025.
 */

public class Fenlei_three_Adapter extends BaseAdapter {

    private Context context;
    private List<Fenlei_three_Beans.SoftwareBean> list;

    public Fenlei_three_Adapter(Context context, List<Fenlei_three_Beans.SoftwareBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder ;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_fenlei_three , null);
            holder.fenlei_three_tv_a = (TextView) convertView.findViewById(R.id.fenlei_three_tv_a);
            holder.fenlei_three_tv_b = (TextView) convertView.findViewById(R.id.fenlei_three_tv_b);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Fenlei_three_Beans.SoftwareBean softwareBean = list.get(position);

        holder.fenlei_three_tv_a.setText(softwareBean.getName());
        holder.fenlei_three_tv_b.setText(softwareBean.getDescription());

        return convertView;
    }

    private class ViewHolder{
        private TextView fenlei_three_tv_a;
        private TextView fenlei_three_tv_b;
    }

}
