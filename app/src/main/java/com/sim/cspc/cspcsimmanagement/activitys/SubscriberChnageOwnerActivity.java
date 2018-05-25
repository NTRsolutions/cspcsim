package com.sim.cspc.cspcsimmanagement.activitys;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
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

public class SubscriberChnageOwnerActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner networkSpineer, existingSpineer, registerwithSpineer, idificationspinner, nationalityspinner, newidificationspinner, newnationalityspinner,
            proofAdressspinner, countrySpinner, postalSpinner;

    private EditText cellNumber, fourdigitSIM, idNumber, newidNumber, passportexpiryView, fullName, sureName, Country_Code, areaCode, dilangNumber,
            Adress , Adress1 , Adress3, City_Town, suburb;
    private Button Submit, cancle;
    TextView passportexpDate, asylumexpDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscriber_chnageowner);
        chechPortaitAndLandSacpe();//chech Portait And LandSacpe Orientation
        initView();
    }

    //chech Portait And LandSacpe Orientation
    public void chechPortaitAndLandSacpe() {
        if (CompatibilityUtility.isTablet(SubscriberChnageOwnerActivity.this)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    private void initView() {

        this.cellNumber = this.findViewById(R.id.cellNumber);
        this.fourdigitSIM = this.findViewById(R.id.fourdigitSIM);
        this.idNumber = this.findViewById(R.id.idNumber);
        this.newidNumber = this.findViewById(R.id.newidNumber);
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


        this.networkSpineer = this.findViewById(R.id.networkSpineer);
        this.existingSpineer = this.findViewById(R.id.existingSpineer);
        this.registerwithSpineer = this.findViewById(R.id.registerwithSpineer);
        this.idificationspinner = this.findViewById(R.id.idificationspinner);
        this.nationalityspinner = this.findViewById(R.id.nationalityspinner);
        this.newidificationspinner = this.findViewById(R.id.newidificationspinner);
        this.newnationalityspinner = this.findViewById(R.id.newnationalityspinner);
        this.proofAdressspinner = this.findViewById(R.id.proofAdressspinner);
        this.countrySpinner = this.findViewById(R.id.countrySpinner);
        this.postalSpinner = this.findViewById(R.id.postalSpinner);
        setSpinerValue();
    }

    private void setSpinerValue() {

        String networkSpineer_array[] = {"Please Select", "Zoner", "Sub wholeseller", "Shop"};
        String existingSpineer_array[] = {"New", "Existing"};
        String registerwithSpineer_array[] = {"Please Select", "Cell Phone Number", "Starter Pack", "SIM"};
        String idificationspinner_array[] = {"Please Select", "Business Registration Number", "Id Number", "Passport"};
        String newidificationspinner_array[] = {"Please Select", "Business Registration Number", "Id Number", "Passport"};
        String nationalityspinner_array[] = {"RSA", "Non RSA"};
        String newnationalityspinner_array[] = {"RSA", "Non RSA"};
        String proofAdressspinner_array[] = {"Passport", "Asylum", "Workpermit"};
        String postal_address_array[] = {"Yes", "No"};
        final String countrySpinner_array[] = {"Johannesburg", "Capetown", "Durban"};


        ArrayAdapter<String> networkspinnerdata = new ArrayAdapter<String>(this, R.layout.spinner_row, networkSpineer_array);
        networkSpineer.setAdapter(networkspinnerdata);
        networkSpineer.setOnItemSelectedListener(new MyOnItemSelectedListener());

        ArrayAdapter<String> existingSpineer_arraydata = new ArrayAdapter<String>(this, R.layout.spinner_row, existingSpineer_array);
        existingSpineer.setAdapter(existingSpineer_arraydata);
        existingSpineer.setOnItemSelectedListener(new MyOnItemSelectedListener());

        ArrayAdapter<String> registerwithspinnerdata = new ArrayAdapter<String>(this, R.layout.spinner_row, registerwithSpineer_array);
        registerwithSpineer.setAdapter(registerwithspinnerdata);
        registerwithSpineer.setOnItemSelectedListener(new MyOnItemSelectedListener());


        ArrayAdapter<String> idificationspinnerrdata = new ArrayAdapter<String>(this, R.layout.spinner_row, idificationspinner_array);
        idificationspinner.setAdapter(idificationspinnerrdata);
        idificationspinner.setOnItemSelectedListener(new MyOnItemSelectedListener());

        ArrayAdapter<String> newidificationspinnerrdata = new ArrayAdapter<String>(this, R.layout.spinner_row, newidificationspinner_array);
        idificationspinner.setAdapter(newidificationspinnerrdata);
        idificationspinner.setOnItemSelectedListener(new MyOnItemSelectedListener());


        ArrayAdapter<String> nationalityspinneradapter = new ArrayAdapter<String>(this, R.layout.spinner_row, nationalityspinner_array);
        nationalityspinner.setAdapter(nationalityspinneradapter);
        nationalityspinner.setOnItemSelectedListener(new MyOnItemSelectedListener());

        ArrayAdapter<String> newnationalityspinneradapter = new ArrayAdapter<String>(this, R.layout.spinner_row, newnationalityspinner_array);
        nationalityspinner.setAdapter(newnationalityspinneradapter);
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
                Intent intent = new Intent(SubscriberChnageOwnerActivity.this, DashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }


}
