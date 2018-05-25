package com.sim.cspc.cspcsimmanagement.activitys;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sim.cspc.cspcsimmanagement.R;
import com.sim.cspc.cspcsimmanagement.utilities.CompatibilityUtility;

public class SubscriberQueryActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout input_layout_cellNo;

    private EditText cellphoneName;
    private Button Submit, cancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscriber_query);
        chechPortaitAndLandSacpe();//chech Portait And LandSacpe Orientation
        initView();
    }

    //chech Portait And LandSacpe Orientation
    public void chechPortaitAndLandSacpe() {
        if (CompatibilityUtility.isTablet(SubscriberQueryActivity.this)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    private void initView() {

        this.input_layout_cellNo = this.findViewById(R.id.input_layout_cellNo);
        this.cellphoneName = this.findViewById(R.id.cellphoneName);
        this.Submit = this.findViewById(R.id.Submit);
        this.cancle = this.findViewById(R.id.cancle);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.Submit:
                Intent intent = new Intent(SubscriberQueryActivity.this, DashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }


}
