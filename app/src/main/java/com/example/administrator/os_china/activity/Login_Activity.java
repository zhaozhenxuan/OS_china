package com.example.administrator.os_china.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.os_china.App;
import com.example.administrator.os_china.R;
import com.example.administrator.os_china.base.BaseActivity;
import com.example.administrator.os_china.model.entity.my_beans.Login_Beans;
import com.example.administrator.os_china.model.http.biz.message.Main.IMain;
import com.example.administrator.os_china.model.http.biz.message.Main.MainImpl;
import com.example.administrator.os_china.model.http.biz.message.news.INewsModel;
import com.example.administrator.os_china.model.http.biz.message.news.NewsMineImpl;
import com.example.administrator.os_china.model.http.callback.MyCallBack;
import com.thoughtworks.xstream.XStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/25 0025.
 */

public class Login_Activity extends BaseActivity {


    @BindView(R.id.login_img_bt)
    ImageView loginImgBt;
    @BindView(R.id.wangji)
    TextView wangji;
    @BindView(R.id.login)
    RelativeLayout login;
    @BindView(R.id.zuche)
    RelativeLayout zuche;
    @BindView(R.id.ed_login)
    EditText edLogin;
    @BindView(R.id.ed_zuche)
    EditText edZuche;

    private String username;
    private String password;

    private IMain iMain;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected int getLayoutId() {
        return R.layout.login_activity_item;
    }

    @Override
    protected void initData() {

        iMain = new MainImpl();

        preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loderData() {

    }

    @Override
    protected void initListener() {
        loginImgBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.login_img_bt, R.id.wangji, R.id.login, R.id.zuche})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_img_bt:

                onBackPressed();

                break;
            case R.id.wangji:

                Toast.makeText(this, "忘了就忘了把，找什么找，再去注册一个", Toast.LENGTH_SHORT).show();
                break;
            case R.id.login:
                username = edLogin.getText().toString().trim();
                password = edZuche.getText().toString().trim();

                if (username.equals("")) {
                    Toast.makeText(this, "用户名或者密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.equals("")) {
                        Toast.makeText(this, "用户名或者密码不能为空", Toast.LENGTH_SHORT).show();
                    } else {
                        iMain.login(username, password, new MyCallBack() {
                            @Override
                            public void onSuccess(String result) {
                                Log.w("LOGIN", "打印：" + result);

                                XStream xStream = new XStream();
                                xStream.alias("oschina", Login_Beans.class);

                                Login_Beans beans = (Login_Beans) xStream.fromXML(result);

                                String errorCode = beans.getResult().getErrorCode();
                                if ("1".equals(errorCode)) {
                                    Toast.makeText(Login_Activity.this, "登陆成功", Toast.LENGTH_SHORT).show();

                                    String uid = beans.getUser().getUid();
                                    String name = beans.getUser().getName();
                                    String portrait = beans.getUser().getPortrait();

                                    editor.putString("uid", uid);
                                    editor.putString("name", name);
                                    editor.putString("img", portrait);

                                    editor.commit();

                                    finish();
                                } else {
                                    Toast.makeText(Login_Activity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                                }





                            }

                            @Override
                            public void onError(String MsgError) {

                            }
                        });
                    }
                }

                break;
            case R.id.zuche:

                Toast.makeText(this, "狗跟你不得注册", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
