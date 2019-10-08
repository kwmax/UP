package com.kwmax.up.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kwmax.up.R;


/**
 * Created by kwmax on 2019/10/7.
 */
public class LoginActivity extends Activity implements View.OnClickListener {

    private EditText et_account;
    private EditText et_password;
    private Button loginBtn;
    private TextView tv_forgetPwd;
    private TextView tv_registerAcc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        et_account = findViewById(R.id.login_tv_account);
        et_password = findViewById(R.id.login_tv_pwd);
        loginBtn = findViewById(R.id.login_btn);
        tv_forgetPwd = findViewById(R.id.login_forget_password);
        tv_registerAcc = findViewById(R.id.login_register_account);

        loginBtn.setOnClickListener(this);
        tv_forgetPwd.setOnClickListener(this);
        tv_registerAcc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.login_btn:
                break;
            case R.id.login_forget_password:
                break;
            case R.id.login_register_account:
                break;
            default:
                break;
        }
    }
}
