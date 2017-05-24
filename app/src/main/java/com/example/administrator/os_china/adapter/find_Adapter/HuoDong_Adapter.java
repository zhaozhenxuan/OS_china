package com.example.administrator.os_china.adapter.find_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.os_china.R;
import com.example.administrator.os_china.model.entity.find_Beans.HuoDong_Bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/24 0024.
 */

public class HuoDong_Adapter extends BaseAdapter {
    private Context context;
    private List<HuoDong_Bean.EventBean> list;

    public HuoDong_Adapter(Context context, List<HuoDong_Bean.EventBean> list) {
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

        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.huodong_item , null);
            holder.huodong_tv = (TextView) convertView.findViewById(R.id.huodong_tv);
            holder.huodong_time_tv = (TextView) convertView.findViewById(R.id.huodong_time_tv);
            holder.huodong_img = (ImageView) convertView.findViewById(R.id.huodong_img);
            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        HuoDong_Bean.EventBean eventBean = list.get(position);

        String startTime = eventBean.getCreateTime();

        holder.huodong_time_tv.setText(startTime);
        holder.huodong_tv.setText(eventBean.getTitle());

        Glide.with(context).load(eventBean.getCover()).into(holder.huodong_img);

        return convertView;
    }

    class ViewHolder{

        private ImageView huodong_img;
        private TextView huodong_tv;
        private TextView huodong_time_tv;


    }
}
