package com.example.administrator.os_china.adapter.find_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.os_china.R;
import com.example.administrator.os_china.base.BaseFragment;
import com.example.administrator.os_china.model.entity.find_Beans.Fenlei_Beans;

import java.util.List;



/**
 * Created by Administrator on 2017/5/24 0024.
 */

public class Fenlei_Adapter extends BaseAdapter {

    private Context context;
    private List<Fenlei_Beans.SoftwareTypeBean> list;

    public Fenlei_Adapter(Context context, List<Fenlei_Beans.SoftwareTypeBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_fenlei , null);
            holder.fenlei_tv = (TextView) convertView.findViewById(R.id.fenlei_tv);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Fenlei_Beans.SoftwareTypeBean softwareTypeBean = list.get(position);
        holder.fenlei_tv.setText(softwareTypeBean.getName());

        return convertView;
    }

    private class ViewHolder{
        private TextView fenlei_tv;
    }
}
