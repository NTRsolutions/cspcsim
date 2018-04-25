package com.sim.cspc.cspcsimmanagement.activitys;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.sim.cspc.cspcsimmanagement.R;
import com.sim.cspc.cspcsimmanagement.utilities.CompatibilityUtility;
import com.sim.cspc.cspcsimmanagement.utilities.FontManager;

public class SigninActivity extends AppCompatActivity implements View.OnClickListener{


    private TextInputLayout email_layout, password_layout;
    private EditText user, password;
    private String login_email, login_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        chechPortaitAndLandSacpe();//chech Portait And LandSacpe Orientation
        initView();
    }
    //chech Portait And LandSacpe Orientation
    public void chechPortaitAndLandSacpe() {
        if (CompatibilityUtility.isTablet(SigninActivity.this)) {

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

    }
    private void initView(){
        Typeface materialdesignicons_font = FontManager.getFontTypefaceMaterialDesignIcons(this, "fonts/materialdesignicons-webfont.otf");
        //userIcon = (TextView) findViewById(R.id.userIcon);
        //userIcon.setTypeface(materialdesignicons_font);
       // userIcon.setText(Html.fromHtml("&#xf1f0;"));
        email_layout = (TextInputLayout) findViewById(R.id.email_layout);
        user = (EditText) findViewById(R.id.user);
        password_layout = (TextInputLayout) findViewById(R.id.password_layout);
        password = (EditText) findViewById(R.id.password);
        TextView loginText = (TextView) findViewById(R.id.loginText);
        loginText.setOnClickListener(this);
        TextView signupText = (TextView) findViewById(R.id.signupText);
        signupText.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginText:
                if (isValidate()) {
                    Intent intent = new Intent(SigninActivity.this, DashboardActivity.class);
                     intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                break;
            case R.id.signupText:
                Intent intent = new Intent(SigninActivity.this, SignUpActivity.class);
               // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }


    // ----validation -----
    private boolean isValidate() {
        String emailRegex = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
        login_email = user.getText().toString();
        login_password = password.getText().toString();

        if (login_email.length() == 0) {
            email_layout.setError("Please Enter Phone Number");
            requestFocus(user);
            return false;
        }  else if (login_email.contains(" ")) {
            email_layout.setError("No Spaces Allowed");
            email_layout.setErrorEnabled(false);
            requestFocus(user);
            return false;
        } else {
            email_layout.setErrorEnabled(false);
            //password_layout.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
    //for hid keyboard when tab outside edittext box
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }
}
