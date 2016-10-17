package com.sxhxjy.roadmonitor.ui.main;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.loopj.android.http.RequestParams;
import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.base.BaseActivity;
import com.sxhxjy.roadmonitor.util.Utils;

import org.json.JSONObject;

/**
 * register activity
 *
 * @author Michael Zhao
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private CountDownTimer countDownTimer;
    private TextView textView;
    private boolean isGetPsw = false;
    public static String GET_PSW = "psw";
    private Button mSignUp;
    private EditText mUser, mPassword, mCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isGetPsw = getIntent().getBooleanExtra(GET_PSW, true);
        setContentView(R.layout.register_activity);
        initToolBar("修改密码", true);

        mSignUp = (Button) findViewById(R.id.sign_up);
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getMessage(getHttpService().changePassword());
            }
        });


        /*countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText(millisUntilFinished / 1000 + "秒后重新获取");
                textView.setEnabled(false);
            }

            @Override
            public void onFinish() {
                textView.setTextColor(getResources().getColor(R.color.white));
                textView.setEnabled(true);
                textView.setText("获取验证码");
            }
        };*/
    }

    private boolean isPhoneValid() {
        if (Utils.checkPhone(mUser.getText().toString()))
            return true;
        else {
            mUser.setError("请输入正确的手机号");
            mUser.requestFocus();
            return false;
        }
    }



    @Override
    public void onClick(View v) {
        if (isValid());
    }


    private boolean isValid() {
        if (Utils.checkPhone(mUser.getText().toString()))
            if (Utils.checkPwd(mPassword.getText().toString()))
                if (!mCode.getText().toString().isEmpty())
                    return true;
                else {
                    mCode.setError("请输入验证码");
                    mCode.requestFocus();
                    return false;
                }
            else {
                mPassword.setError("密码不合法");
                mPassword.requestFocus();
                return false;
            }
        else {
            mUser.setError("请输入正确的手机号");
            mUser.requestFocus();
            return false;
        }
    }
}
