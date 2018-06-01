package com.sim.cspc.cspcsimmanagement.activitys;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
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
import com.sim.cspc.cspcsimmanagement.utilities.FontManager;

import java.util.ArrayList;

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
    private LinearLayout passportLayout;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscriber_registration);
        chechPortaitAndLandSacpe();//chech Portait And LandSacpe Orientation
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
        Typeface materialdesignicons_font = FontManager.getFontTypefaceMaterialDesignIcons(this, "fonts/materialdesignicons-webfont.otf");
        TextView back = (TextView) toolbar.findViewById(R.id.back);
        back.setTypeface(materialdesignicons_font);
        back.setText(Html.fromHtml("&#xf30d;"));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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

        passportLayout = this.findViewById(R.id.passportLayout);
        setSpinerValue();
    }

    private void setSpinerValue() {
        ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
        Integer[] IMAGES = {R.drawable.mtn, R.drawable.vodalogo, R.drawable.celllogo, R.drawable.talllogo, R.drawable.verlogo};
        for (int i = 0; i < IMAGES.length; i++) {
            ImagesArray.add(IMAGES[i]);
        }
       // String networkspinner_array[] = {"Please Select", "MTN", "Vodacom", "CellC", "Telkom", "Virgin"};
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


        String nation_array[] = {"RSA", "Non RSA"};
        String proofAdressspinner_array[] = {"No", "Passport", "Asylum", "Workpermit"};
        String postal_address_array[] = {"Please Select", "Yes", "No"};
        final String countrySpinner_array[] = {"South Africa", "Johannesburg", "Capetown", "Durban"};


        // ArrayAdapter<String> networkspinnerdata = new ArrayAdapter<String>(this, R.layout.spinner_row, networkspinner_array);
        NetworkSpinnerAdapter networkspinnerdata = new NetworkSpinnerAdapter(this, ImagesArray);
        networkspinner.setAdapter(networkspinnerdata);
        networkspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        //ArrayAdapter<String> subscriberSpinnerdata = new ArrayAdapter<String>(this, R.layout.spinner_radio_row, subscriberSpinnerdata_array);
        SpinnerWithRadioButtonAdapter subscriberSpinnerdata = new SpinnerWithRadioButtonAdapter(this, subscriberSpinnerdata_array);
        subscriberSpinner.setAdapter(subscriberSpinnerdata);
        subscriberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        registerwithspinner.setAdapter(registerwithspinnerdata);
        registerwithspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
