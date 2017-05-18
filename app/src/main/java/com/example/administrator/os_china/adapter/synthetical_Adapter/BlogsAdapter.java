package com.example.administrator.os_china.adapter.synthetical_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.os_china.R;
import com.example.administrator.os_china.model.entity.synthetical_beans.Blogs_beans;

import java.util.List;

/**
 * Created by Administrator on 2017/5/15 0015.
 */

public class BlogsAdapter extends BaseAdapter {

    private Context context;
    private List<Blogs_beans.BlogBean> list;

    public BlogsAdapter(Context context, List<Blogs_beans.BlogBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_blogs , null);
            holder.tv_blogs_a = (TextView) convertView.findViewById(R.id.tv_blogs_a);
            holder.tv_blogs_b = (TextView) convertView.findViewById(R.id.tv_blogs_b);
            holder.tv_blogs_c = (TextView) convertView.findViewById(R.id.tv_blogs_c);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Blogs_beans.BlogBean blogBean = list.get(position);

        holder.tv_blogs_a.setText(blogBean.getTitle());
        holder.tv_blogs_b.setText(blogBean.getBody());
        holder.tv_blogs_c.setText("@"+blogBean.getAuthorname());

        return convertView;
    }

    class ViewHolder{
        private TextView tv_blogs_a;
        private TextView tv_blogs_b;
        private TextView tv_blogs_c;
    }
}
