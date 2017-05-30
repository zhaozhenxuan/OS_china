package com.example.administrator.os_china.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.os_china.App;
import com.example.administrator.os_china.R;
import com.example.administrator.os_china.base.BaseActivity;
import com.example.administrator.os_china.model.http.biz.message.Main.IMain;
import com.example.administrator.os_china.model.http.biz.message.Main.MainImpl;
import com.example.administrator.os_china.model.http.callback.MyCallBack;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/26 0026.
 */

public class Add_Activity extends BaseActivity {
    @BindView(R.id.img_btn_add)
    ImageView imgBtnAdd;
    @BindView(R.id.add_tv)
    TextView addTv;
    @BindView(R.id.add)
    TextView add;
    @BindView(R.id.add_content)
    EditText addContent;

    private IMain iMain;

    @Override
    protected int getLayoutId() {
        return R.layout.add_fragment_item;
    }

    @Override
    protected void initData() {
        iMain = new MainImpl();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loderData() {

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

    @OnClick({R.id.img_btn_add, R.id.add, R.id.add_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_btn_add:
                onBackPressed();
                break;
            case R.id.add:
                SharedPreferences preferences =  getSharedPreferences("user", Context.MODE_PRIVATE);
                String uid = preferences.getString("uid",null);
                String s = addContent.getText().toString();

                if (s != null){
                    iMain.TYT(uid, s, new MyCallBack() {
                        @Override
                        public void onSuccess(String result) {
                            Log.e("TYT","返回的数据："+result);
                            Toast.makeText(Add_Activity.this, "发表成功", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(String MsgError) {

                        }
                    });
                }
                addContent.setText("");




                break;
            case R.id.add_content:

                break;
        }
    }
}
