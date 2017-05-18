package com.example.administrator.os_china.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.example.administrator.os_china.R;
import com.example.administrator.os_china.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/5/18 0018.
 */

public class DT_Activity extends BaseActivity {

    @BindView(R.id.img_btn_DT)
    ImageView imgBtnDT;
    @BindView(R.id.DT_xiangqing_tv)
    TextView DTXiangqingTv;
    @BindView(R.id.DT_image)
    ImageView DTImage;
    @BindView(R.id.DT_tv_a)
    TextView DTTvA;
    @BindView(R.id.DT_tv_b)
    TextView DTTvB;
    private String url;
    private String content;
    private String name;

    @Override
    protected int getLayoutId() {
        return R.layout.dt_xiangqing_item;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        content = intent.getStringExtra("content");
        name = intent.getStringExtra("name");
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loderData() {
        DTTvA.setText(name);
        DTTvB.setText(content);
        Glide.with(this).load(url).transform(new GlideCircleTransform(this)).into(DTImage);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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
