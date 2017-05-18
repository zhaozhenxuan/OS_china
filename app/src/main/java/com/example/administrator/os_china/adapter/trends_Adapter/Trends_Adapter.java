package com.example.administrator.os_china.adapter.trends_Adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.app.FragmentActivity;
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
import com.example.administrator.os_china.adapter.synthetical_Adapter.BlogsAdapter;
import com.example.administrator.os_china.model.entity.trends_beans.Trends_Beans;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18 0018.
 */

public class Trends_Adapter extends BaseAdapter {

    private Context context;
    private List<Trends_Beans.TweetBean> list;

    public Trends_Adapter(Context context, List<Trends_Beans.TweetBean> list) {

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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_trends_newsdt , null);
            holder.tv_trends_a = (TextView) convertView.findViewById(R.id.tv_trends_a);
            holder.tv_trends_b = (TextView) convertView.findViewById(R.id.tv_trends_b);
            holder.trends_iv = (ImageView) convertView.findViewById(R.id.trends_iv);
            holder.trends_image = (ImageView) convertView.findViewById(R.id.trends_image);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Trends_Beans.TweetBean tweetBean = list.get(position);

        String str = tweetBean.getImgSmall();
        holder.tv_trends_a.setText(tweetBean.getAuthor());
        holder.tv_trends_b.setText(tweetBean.getBody());

        Glide.with(context).load(tweetBean.getPortrait()).transform(new GlideCircleTransform(context)).into(holder.trends_iv);
        if(str != null)
            Glide.with(context).load(str).into(holder.trends_image);


        return convertView;
    }

    class ViewHolder{
        private ImageView trends_iv;
        private ImageView trends_image;
        private TextView tv_trends_a;
        private TextView tv_trends_b;
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
