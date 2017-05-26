package com.example.administrator.os_china.adapter.synthetical_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.administrator.os_china.R;
import com.example.administrator.os_china.model.entity.synthetical_beans.Beans;
import com.example.administrator.os_china.utils.Dates;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/10 0010.
 */

public class MyMessageAdapter extends BaseAdapter {

    private Context context;
    private List<Beans.NewsBean> list;

    public MyMessageAdapter(Context context, List<Beans.NewsBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_message , null);
            holder.tv_message_a = (TextView) convertView.findViewById(R.id.tv_message_a);
            holder.tv_message_b = (TextView) convertView.findViewById(R.id.tv_message_b);
            holder.tv_message_c = (TextView) convertView.findViewById(R.id.tv_message_c);
            holder.message_time = (TextView) convertView.findViewById(R.id.message_time);
            holder.message_number = (TextView) convertView.findViewById(R.id.message_number);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Beans.NewsBean beans = list.get(position);
        String attachment = beans.getCommentCount();
        String date = Dates.getDate(beans.getPubDate());

        holder.tv_message_a.setText(beans.getTitle());
        holder.tv_message_b.setText(beans.getBody());
        holder.tv_message_c.setText("@"+beans.getAuthor());
        holder.message_number.setText(attachment);
        holder.message_time.setText(date);

        return convertView;
    }

    class ViewHolder{
        private TextView tv_message_a;
        private TextView tv_message_b;
        private TextView tv_message_c;
        private TextView message_time;
        private TextView message_number;
    }
}
