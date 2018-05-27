package com.sim.cspc.cspcsimmanagement.activitys;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.sim.cspc.cspcsimmanagement.R;
import com.sim.cspc.cspcsimmanagement.adapter.SpinnerWithRadioButtonAdapter;
import com.sim.cspc.cspcsimmanagement.utilities.CompatibilityUtility;

import java.util.ArrayList;

public class SubscriberDeRegistrationActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner identificationTypeSpineer, countryCodespinner, ReasonSpinner;
    private TextInputLayout input_layout_cellNo, input_layout_idNumber;
    private EditText cellphoneName, idNumber;
    private Button Submit, cancle;
    private LinearLayout passportLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscriber_deregistration);
        chechPortaitAndLandSacpe();//chech Portait And LandSacpe Orientation
        initView();
    }

    //chech Portait And LandSacpe Orientation
    public void chechPortaitAndLandSacpe() {
        if (CompatibilityUtility.isTablet(SubscriberDeRegistrationActivity.this)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    private void initView() {

        this.input_layout_cellNo = this.findViewById(R.id.input_layout_cellNo);
        this.input_layout_idNumber = this.findViewById(R.id.input_layout_idNumber);
        this.cellphoneName = this.findViewById(R.id.cellphoneName);
        this.idNumber = this.findViewById(R.id.idNumber);
        this.Submit = this.findViewById(R.id.Submit);
        this.cancle = this.findViewById(R.id.cancle);
        passportLayout = this.findViewById(R.id.passportLayout);

        this.identificationTypeSpineer = this.findViewById(R.id.identificationTypeSpineer);
        this.countryCodespinner = this.findViewById(R.id.countryCodespinner);
        this.ReasonSpinner = this.findViewById(R.id.ReasonSpinner);
        setSpinerValue();
    }

    private void setSpinerValue() {

        String countryCodespinner_array[] = {"South Africa", "India"};
        String resaonspinner_array[] = {"Please Select", "Error", "Lost", "Stolen", "immigration", "Deceased", "other"};

        final ArrayList<String> idificationspinner_array = new ArrayList<String>();
        idificationspinner_array.add("Please Select");
        idificationspinner_array.add("Business Registration Number");
        idificationspinner_array.add("Id Number");
        idificationspinner_array.add("Passport");

        SpinnerWithRadioButtonAdapter idificationspinnerrdata = new SpinnerWithRadioButtonAdapter(this, idificationspinner_array);
        identificationTypeSpineer.setAdapter(idificationspinnerrdata);
        identificationTypeSpineer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                RadioButton radio = (RadioButton) view.findViewById(R.id.radio);
                if (radio != null) {
                    radio.setChecked(true);
                }
                String str = idificationspinner_array.get(position).toString();
                if (str.equals("Business Registration Number")) {
                    passportLayout.setVisibility(View.GONE);
                    input_layout_idNumber.setVisibility(View.VISIBLE);
                    idNumber.setHint("Business Registration Number*");
                } else if (str.equals("Id Number")) {
                    passportLayout.setVisibility(View.GONE);
                    input_layout_idNumber.setVisibility(View.VISIBLE);
                    idNumber.setHint("ID Number*");
                } else if (str.equals("Passport")) {
                    passportLayout.setVisibility(View.VISIBLE);
                    input_layout_idNumber.setVisibility(View.GONE);
                } else {
                    passportLayout.setVisibility(View.GONE);
                    input_layout_idNumber.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> subscriberSpinnerdata = new ArrayAdapter<String>(this, R.layout.spinner_row, countryCodespinner_array);
        countryCodespinner.setAdapter(subscriberSpinnerdata);
        countryCodespinner.setOnItemSelectedListener(new MyOnItemSelectedListener());

        ArrayAdapter<String> ReasonSpinnerdata = new ArrayAdapter<String>(this, R.layout.spinner_row, resaonspinner_array);
        ReasonSpinner.setAdapter(ReasonSpinnerdata);
        ReasonSpinner.setOnItemSelectedListener(new MyOnItemSelectedListener());


    }

    public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        }

        public void onNothingSelected(AdapterView parent) {
            // Do nothing.
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.Submit:
                Intent intent = new Intent(SubscriberDeRegistrationActivity.this, DashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }


}
