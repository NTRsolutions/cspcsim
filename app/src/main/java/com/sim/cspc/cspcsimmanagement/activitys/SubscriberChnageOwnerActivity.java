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
import android.widget.TextView;

import com.sim.cspc.cspcsimmanagement.R;
import com.sim.cspc.cspcsimmanagement.adapter.NetworkSpinnerAdapter;
import com.sim.cspc.cspcsimmanagement.adapter.SpinnerWithRadioButtonAdapter;
import com.sim.cspc.cspcsimmanagement.utilities.CompatibilityUtility;

import java.util.ArrayList;

public class SubscriberChnageOwnerActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner networkSpineer, existingSpineer, registerwithSpineer, idificationspinner, nationalityspinner, newidificationspinner, newnationalityspinner,
            proofAdressspinner, countrySpinner, postalSpinner;

    private EditText cellNumber, fourdigitSIM, passportexpiryView, fullName, sureName, Country_Code, areaCode, dilangNumber,
            Adress, Adress1, Adress3, City_Town, suburb, newidNoView, idNoView;
    private Button Submit, cancle;
    TextView passportexpDate, asylumexpDate;
    private LinearLayout passportLayout, newpassportLayout;
    private TextInputLayout input_layout_idNo, newinput_layout_idNo;

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
        passportLayout = this.findViewById(R.id.passportLayout);
        newpassportLayout = this.findViewById(R.id.newpassportLayout);
        this.input_layout_idNo = this.findViewById(R.id.input_layout_idNo);
        this.newinput_layout_idNo = this.findViewById(R.id.newinput_layout_idNo);
        this.idNoView = this.findViewById(R.id.idNoView);
        this.newidNoView = this.findViewById(R.id.newidNoView);

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

        //String networkspinner_array[] = {"Please Select", "MTN", "Vodacom", "CellC", "Telkom", "Virgin"};
        ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
        Integer[] IMAGES = {R.drawable.mtn, R.drawable.vodalogo, R.drawable.celllogo, R.drawable.talllogo, R.drawable.verlogo};
        for (int i = 0; i < IMAGES.length; i++) {
            ImagesArray.add(IMAGES[i]);
        }
        ArrayList<String> subscriberSpinnerdata_array = new ArrayList<String>();
        subscriberSpinnerdata_array.add("New");
        subscriberSpinnerdata_array.add("Existing");

        ArrayList<String> registerwithspinner_array = new ArrayList<String>();
        registerwithspinner_array.add("SIM");
        registerwithspinner_array.add("Cell Phone Number");
        registerwithspinner_array.add("Starter Pack");


        final ArrayList<String> idificationspinner_array = new ArrayList<String>();
        idificationspinner_array.add("Please Select");
        idificationspinner_array.add("Business Registration Number");
        idificationspinner_array.add("Id Number");
        idificationspinner_array.add("Passport");

        String nationalityspinner_array[] = {"RSA", "Non RSA"};
        String newnationalityspinner_array[] = {"RSA", "Non RSA"};
        String proofAdressspinner_array[] = {"Passport", "Asylum", "Workpermit"};
        String postal_address_array[] = {"Yes", "No"};
        final String countrySpinner_array[] = {"Johannesburg", "Capetown", "Durban"};


        NetworkSpinnerAdapter networkspinnerdata = new NetworkSpinnerAdapter(this, ImagesArray);
        networkSpineer.setAdapter(networkspinnerdata);
        networkSpineer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //networkspinner.setSelection(position);
                RadioButton radio = (RadioButton) view.findViewById(R.id.radio);
                if (radio != null) {
                    radio.setChecked(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SpinnerWithRadioButtonAdapter subscriberSpinnerdata = new SpinnerWithRadioButtonAdapter(this, subscriberSpinnerdata_array);
        existingSpineer.setAdapter(subscriberSpinnerdata);
        existingSpineer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                RadioButton radio = (RadioButton) view.findViewById(R.id.radio);
                if (radio != null) {
                    radio.setChecked(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SpinnerWithRadioButtonAdapter registerwithspinnerdata = new SpinnerWithRadioButtonAdapter(this, registerwithspinner_array);
        registerwithSpineer.setAdapter(registerwithspinnerdata);
        registerwithSpineer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                RadioButton radio = (RadioButton) view.findViewById(R.id.radio);
                if (radio != null) {
                    radio.setChecked(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        SpinnerWithRadioButtonAdapter idificationspinnerrdata = new SpinnerWithRadioButtonAdapter(this, idificationspinner_array);
        idificationspinner.setAdapter(idificationspinnerrdata);
        idificationspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                RadioButton radio = (RadioButton) view.findViewById(R.id.radio);
                if (radio != null) {
                    radio.setChecked(true);
                }
                String str = idificationspinner_array.get(position).toString();
                if (str.equals("Business Registration Number")) {
                    passportLayout.setVisibility(View.GONE);
                    input_layout_idNo.setVisibility(View.VISIBLE);
                    idNoView.setHint("Business Registration Number*");
                } else if (str.equals("Id Number")) {
                    passportLayout.setVisibility(View.GONE);
                    input_layout_idNo.setVisibility(View.VISIBLE);
                    idNoView.setHint("ID Number*");
                } else if (str.equals("Passport")) {
                    passportLayout.setVisibility(View.VISIBLE);
                    input_layout_idNo.setVisibility(View.GONE);
                } else {
                    passportLayout.setVisibility(View.GONE);
                    input_layout_idNo.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SpinnerWithRadioButtonAdapter newidificationspinnerrdata = new SpinnerWithRadioButtonAdapter(this, idificationspinner_array);
        newidificationspinner.setAdapter(newidificationspinnerrdata);
        newidificationspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                RadioButton radio = (RadioButton) view.findViewById(R.id.radio);
                if (radio != null) {
                    radio.setChecked(true);
                }
                String str = idificationspinner_array.get(position).toString();
                if (str.equals("Business Registration Number")) {
                    newpassportLayout.setVisibility(View.GONE);
                    newinput_layout_idNo.setVisibility(View.VISIBLE);
                    newidNoView.setHint("Business Registration Number*");
                } else if (str.equals("Id Number")) {
                    newpassportLayout.setVisibility(View.GONE);
                    newinput_layout_idNo.setVisibility(View.VISIBLE);
                    newidNoView.setHint("ID Number*");
                } else if (str.equals("Passport")) {
                    newpassportLayout.setVisibility(View.VISIBLE);
                    newinput_layout_idNo.setVisibility(View.GONE);
                } else {
                    newpassportLayout.setVisibility(View.GONE);
                    newinput_layout_idNo.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


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
