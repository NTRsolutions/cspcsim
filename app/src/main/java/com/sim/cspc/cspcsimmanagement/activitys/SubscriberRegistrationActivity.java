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
import android.widget.Spinner;
import android.widget.TextView;

import com.sim.cspc.cspcsimmanagement.R;
import com.sim.cspc.cspcsimmanagement.utilities.CompatibilityUtility;

public class SubscriberRegistrationActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner networkspinner, subscriberSpinner, registerwithspinner, idificationspinner, nationalityspinner,
            proofAdressspinner, countrySpinner, postalSpinner;

    private TextInputLayout input_layout_cellNo, input_layout_simdigit, input_layout_idNo, input_layout_passportexpiry, input_layout_fullName,
            input_layout_surName, input_layout_countryCode, input_layout_areaCode, input_layout_dilingNo, input_layout_adress,
             input_layout_adress1, input_layout_adress3,
            input_layout_cityTown, input_layout_suburb;

    private EditText cellphoneName, simDigitNo, idNoView, passportexpiryView, fullName, sureName, Country_Code, areaCode, dilangNumber,
            Adress, Adress1, Adress3, City_Town, suburb;
    private Button Submit, cancle;
    TextView passportexpDate, asylumexpDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscriber_registration);
        chechPortaitAndLandSacpe();//chech Portait And LandSacpe Orientation
        initView();
    }

    //chech Portait And LandSacpe Orientation
    public void chechPortaitAndLandSacpe() {
        if (CompatibilityUtility.isTablet(SubscriberRegistrationActivity.this)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    private void initView() {

        this.input_layout_cellNo = this.findViewById(R.id.input_layout_cellNo);
        this.input_layout_simdigit = this.findViewById(R.id.input_layout_simdigit);
        this.input_layout_idNo = this.findViewById(R.id.input_layout_idNo);
        this.input_layout_passportexpiry = this.findViewById(R.id.input_layout_passportexpiry);
        this.input_layout_fullName = this.findViewById(R.id.input_layout_fullName);
        this.input_layout_surName = this.findViewById(R.id.input_layout_surName);
        this.input_layout_countryCode = this.findViewById(R.id.input_layout_countryCode);
        this.input_layout_areaCode = this.findViewById(R.id.input_layout_areaCode);
        this.input_layout_dilingNo = this.findViewById(R.id.input_layout_dilingNo);
        this.input_layout_adress = this.findViewById(R.id.input_layout_adress);
        this.input_layout_adress1 = this.findViewById(R.id.input_layout_adress1);
        this.input_layout_adress3 = this.findViewById(R.id.input_layout_adress3);
        this.input_layout_cityTown = this.findViewById(R.id.input_layout_cityTown);
        this.input_layout_suburb = this.findViewById(R.id.input_layout_suburb);


        this.cellphoneName = this.findViewById(R.id.cellphoneName);
        this.simDigitNo = this.findViewById(R.id.simDigitNo);
        this.idNoView = this.findViewById(R.id.idNoView);
        this.passportexpiryView = this.findViewById(R.id.passportexpiryView);
        this.fullName = this.findViewById(R.id.fullName);
        this.sureName = this.findViewById(R.id.sureName);
        this.Country_Code = this.findViewById(R.id.Country_Code);
        this.areaCode = this.findViewById(R.id.areaCode);
        this.dilangNumber = this.findViewById(R.id.dilangNumber);
        this.Adress = this.findViewById(R.id.Adress);
        this.Adress1 = this.findViewById(R.id.Adress1);
        this.Adress3 = this.findViewById(R.id.Adress3);
        this.City_Town = this.findViewById(R.id.City_Town);
        this.suburb = this.findViewById(R.id.suburb);
        this.Submit = this.findViewById(R.id.Submit);
        this.cancle = this.findViewById(R.id.cancle);

        this.networkspinner = this.findViewById(R.id.networkspinner);
        this.subscriberSpinner = this.findViewById(R.id.subscriberSpinner);
        this.registerwithspinner = this.findViewById(R.id.registerwithspinner);
        this.idificationspinner = this.findViewById(R.id.idificationspinner);
        this.nationalityspinner = this.findViewById(R.id.nationalityspinner);
        this.proofAdressspinner = this.findViewById(R.id.proofAdressspinner);
        this.countrySpinner = this.findViewById(R.id.countrySpinner);
        this.postalSpinner = this.findViewById(R.id.postalSpinner);
        setSpinerValue();
    }

    private void setSpinerValue() {

        String networkspinner_array[] = {"Please Select", "MTN", "Vodacom", "CellC","Telkom","Virgin"};
        String subscriberSpinnerdata_array[] = {"New", "Existing"};
        String registerwithspinner_array[] = {"Please Select", "Cell Phone Number", "Starter Pack", "SIM"};
        String idificationspinner_array[] = {"Please Select", "Business Registration Number", "Id Number", "Passport"};
        String nation_array[] = {"RSA", "Non RSA"};
        String proofAdressspinner_array[] = {"No","Passport", "Asylum", "Workpermit"};
        String postal_address_array[] = {"Please Select","Yes", "No"};
        final String countrySpinner_array[] = {"South Africa","Johannesburg", "Capetown", "Durban"};


        ArrayAdapter<String> networkspinnerdata = new ArrayAdapter<String>(this, R.layout.spinner_row, networkspinner_array);
        networkspinner.setAdapter(networkspinnerdata);
        networkspinner.setOnItemSelectedListener(new MyOnItemSelectedListener());

        ArrayAdapter<String> subscriberSpinnerdata = new ArrayAdapter<String>(this, R.layout.spinner_row, subscriberSpinnerdata_array);
        subscriberSpinner.setAdapter(subscriberSpinnerdata);
        subscriberSpinner.setOnItemSelectedListener(new MyOnItemSelectedListener());

        ArrayAdapter<String> registerwithspinnerdata = new ArrayAdapter<String>(this, R.layout.spinner_row, registerwithspinner_array);
        registerwithspinner.setAdapter(registerwithspinnerdata);
        registerwithspinner.setOnItemSelectedListener(new MyOnItemSelectedListener());


        ArrayAdapter<String> idificationspinnerrdata = new ArrayAdapter<String>(this, R.layout.spinner_row, idificationspinner_array);
        idificationspinner.setAdapter(idificationspinnerrdata);
        idificationspinner.setOnItemSelectedListener(new MyOnItemSelectedListener());

        ArrayAdapter<String> nationalityspinneradapter = new ArrayAdapter<String>(this, R.layout.spinner_row, nation_array);
        nationalityspinner.setAdapter(nationalityspinneradapter);
        nationalityspinner.setOnItemSelectedListener(new MyOnItemSelectedListener());


        ArrayAdapter<String> proofAdressspinnerspinneradapter = new ArrayAdapter<String>(this, R.layout.spinner_row, proofAdressspinner_array);
        proofAdressspinner.setAdapter(proofAdressspinnerspinneradapter);
        proofAdressspinner.setOnItemSelectedListener(new MyOnItemSelectedListener());


        ArrayAdapter<String> countrySpinnerAdapter = new ArrayAdapter<String>(this, R.layout.spinner_row, countrySpinner_array);
        countrySpinner.setAdapter(countrySpinnerAdapter);
        countrySpinner.setOnItemSelectedListener(new MyOnItemSelectedListener());

        ArrayAdapter<String> postalAdapter = new ArrayAdapter<String>(this, R.layout.spinner_row, postal_address_array);
        postalSpinner.setAdapter(postalAdapter);
        postalSpinner.setOnItemSelectedListener(new MyOnItemSelectedListener());


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
                Intent intent = new Intent(SubscriberRegistrationActivity.this, DashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }


}
