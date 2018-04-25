package com.sim.cspc.cspcsimmanagement.activitys;

import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sim.cspc.cspcsimmanagement.R;
import com.sim.cspc.cspcsimmanagement.utilities.CompatibilityUtility;
import com.sim.cspc.cspcsimmanagement.utilities.FontManager;

public class OtpVerificationActivity extends AppCompatActivity implements View.OnClickListener{
    private TextInputLayout email_layout, password_layout;
    private EditText user, password;
    private String login_email, login_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        chechPortaitAndLandSacpe();//chech Portait And LandSacpe Orientation
        initView();
    }
    //chech Portait And LandSacpe Orientation
    public void chechPortaitAndLandSacpe() {
        if (CompatibilityUtility.isTablet(OtpVerificationActivity.this)) {

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
    public void onClick(View v) {

    }
}
