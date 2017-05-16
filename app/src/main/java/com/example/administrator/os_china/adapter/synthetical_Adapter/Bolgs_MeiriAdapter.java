package com.example.administrator.os_china.adapter.synthetical_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.os_china.R;
import com.example.administrator.os_china.model.entity.Blogs_beans;
import com.example.administrator.os_china.model.entity.Bolgs_Zuixin_Beans;

import java.util.List;

/**
 * Created by Administrator on 2017/5/15 0015.
 */

public class Bolgs_MeiriAdapter extends BaseAdapter {

    private Context context;
    private List<Bolgs_Zuixin_Beans.BlogBean> list;

    public Bolgs_MeiriAdapter(Context context, List<Bolgs_Zuixin_Beans.BlogBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_exercise , null);
            holder.tv_exercise_a = (TextView) convertView.findViewById(R.id.tv_exercise_a);
            holder.tv_exercise_b = (TextView) convertView.findViewById(R.id.tv_exercise_b);
            holder.tv_exercise_c = (TextView) convertView.findViewById(R.id.tv_exercise_c);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Bolgs_Zuixin_Beans.BlogBean beans = list.get(position);

        holder.tv_exercise_a.setText(beans.getTitle());
        holder.tv_exercise_b.setText(beans.getBody());
        holder.tv_exercise_c.setText("@"+beans.getAuthorname());

        return convertView;
    }

    class ViewHolder{
        private TextView tv_exercise_a;
        private TextView tv_exercise_b;
        private TextView tv_exercise_c;
    }
}
