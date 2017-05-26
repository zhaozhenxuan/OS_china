package com.example.administrator.os_china.adapter.synthetical_Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.example.administrator.os_china.R;
import com.example.administrator.os_china.model.entity.synthetical_beans.Answers_beans;
import com.example.administrator.os_china.utils.Dates;

import java.util.List;

/**
 * Created by Administrator on 2017/5/15 0015.
 */

public class AnswersAdapter extends BaseAdapter {

    private Context context;
    private List<Answers_beans.PostBean> list;

    public AnswersAdapter(Context context, List<Answers_beans.PostBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_inquire , null);
            holder.tv_inquire_a = (TextView) convertView.findViewById(R.id.tv_inquire_a);
            holder.tv_inquire_b = (TextView) convertView.findViewById(R.id.tv_inquire_b);
            holder.tv_inquire_c = (TextView) convertView.findViewById(R.id.tv_inquire_c);
            holder.inquire_iv = (ImageView) convertView.findViewById(R.id.inquire_iv);
            holder.inquire_number = (TextView) convertView.findViewById(R.id.inquire_number);
            holder.inquire_time = (TextView) convertView.findViewById(R.id.inquire_time);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Answers_beans.PostBean postBean = list.get(position);

        holder.tv_inquire_a.setText(postBean.getTitle());
        holder.tv_inquire_b.setText(postBean.getBody());
        holder.tv_inquire_c.setText("@"+postBean.getAuthor());
        holder.inquire_number.setText(postBean.getAnswerCount());
        holder.inquire_time.setText(Dates.getDate(postBean.getPubDate()));

        Glide.with(context).load(postBean.getPortrait()).transform(new GlideCircleTransform(context)).into(holder.inquire_iv);

        return convertView;
    }

    class ViewHolder{
        private ImageView inquire_iv;
        private TextView tv_inquire_a;
        private TextView tv_inquire_b;
        private TextView tv_inquire_c;
        private TextView inquire_number;
        private TextView inquire_time;
    }

    class GlideCircleTransform extends BitmapTransformation {
        public GlideCircleTransform(Context context) {
            super(context);
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return circleCrop(pool, toTransform);
        }

        private  Bitmap circleCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;

            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            // TODO this could be acquired from the pool too
            Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

            Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);
            return result;
        }

        @Override
        public String getId() {
            return getClass().getName();
        }
    }
}
