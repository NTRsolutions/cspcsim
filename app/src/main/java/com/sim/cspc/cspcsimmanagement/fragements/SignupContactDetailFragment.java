package com.sim.cspc.cspcsimmanagement.fragements;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.sim.cspc.cspcsimmanagement.R;
import com.sim.cspc.cspcsimmanagement.utilities.FontManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignupContactDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignupContactDetailFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public SignupContactDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignupContactDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignupContactDetailFragment newInstance(String param1, String param2) {
        SignupContactDetailFragment fragment = new SignupContactDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private Context context;
    View view;
    LinearLayout nextLayout;
    private TextInputLayout input_layout_phone, input_layout_email, input_layout_physicaladdress, input_layout_postaladdress, input_layout_mobile, input_layout_secondayemail, input_layout_postalsuburb, input_layout_postalcity, input_layout_postalpostalcode;
    private EditText enterphone, enteremail, enterphysicaladdress, enterpostaladdress, entermobile, entersecondayemail, enterpostalsuburb, enterpostalcity, enterpostalpostalcode;
    private Spinner postalspinner;
    private EditText enterphysicalsuburb, enterphysicalcity, enterphysicalpostalcode;
    private TextInputLayout input_layout_physicalsuburb, input_layout_physicalcity, input_layout_physicalpostalcodel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity();
        view = inflater.inflate(R.layout.fragment_signup_contact_detail, container, false);
        init();
        return view;
    }

    private void init() {
        Typeface fontawesome_font = FontManager.getFontTypefaceMaterialDesignIcons(getActivity(), "fonts/fontawesome-webfont.ttf");
        Typeface materialdesignicons_font = FontManager.getFontTypefaceMaterialDesignIcons(getActivity(), "fonts/materialdesignicons-webfont.otf");
        TextView previous = (TextView) view.findViewById(R.id.previous);
        previous.setTypeface(materialdesignicons_font);
        previous.setText(Html.fromHtml("&#xf141;"));
        previous.setOnClickListener(this);
        nextLayout = (LinearLayout) view.findViewById(R.id.nextLayout);
        nextLayout.setOnClickListener(this);
        TextView nextIcon = (TextView) view.findViewById(R.id.nextIcon);
        nextIcon.setTypeface(materialdesignicons_font);
        nextIcon.setText(Html.fromHtml("&#xf142;"));

        input_layout_phone = (TextInputLayout) view.findViewById(R.id.input_layout_phone);
        input_layout_secondayemail = (TextInputLayout) view.findViewById(R.id.input_layout_secondayemail);
        input_layout_phone = (TextInputLayout) view.findViewById(R.id.input_layout_phone);
        input_layout_physicaladdress = (TextInputLayout) view.findViewById(R.id.input_layout_physicaladdress);
        input_layout_postaladdress = (TextInputLayout) view.findViewById(R.id.input_layout_postaladdress);
        input_layout_physicalsuburb = (TextInputLayout) view.findViewById(R.id.input_layout_physicalsuburb);
        input_layout_physicalcity = (TextInputLayout) view.findViewById(R.id.input_layout_physicalcity);
        input_layout_physicalpostalcodel = (TextInputLayout) view.findViewById(R.id.input_layout_physicalpostalcode);

        input_layout_postalsuburb = (TextInputLayout) view.findViewById(R.id.input_layout_postalsuburb);
        input_layout_postalcity = (TextInputLayout) view.findViewById(R.id.input_layout_postalcity);
        input_layout_postalpostalcode = (TextInputLayout) view.findViewById(R.id.input_layout_postalpostalcode);

        enterphone = (EditText) view.findViewById(R.id.enterphone);
        enteremail = (EditText) view.findViewById(R.id.enteremail);
        enterphysicaladdress = (EditText) view.findViewById(R.id.enterphysicaladdress);
        enterphysicalsuburb = (EditText) view.findViewById(R.id.enterphysicalsuburb);
        enterphysicalcity = (EditText) view.findViewById(R.id.enterphysicalcity);
        enterphysicalpostalcode = (EditText) view.findViewById(R.id.enterphysicalpostalcode);

        enterpostaladdress = (EditText) view.findViewById(R.id.enterpostaladdress);
        entermobile = (EditText) view.findViewById(R.id.entermobile);
        entersecondayemail = (EditText) view.findViewById(R.id.entersecondayemail);
        enterpostalsuburb = (EditText) view.findViewById(R.id.enterpostalsuburb);
        enterpostalcity = (EditText) view.findViewById(R.id.enterpostalcity);
        enterpostalpostalcode = (EditText) view.findViewById(R.id.enterpostalpostalcode);

        String postal_address_array[] = {"Yes", "No"};
        postalspinner = (Spinner) view.findViewById(R.id.postalspinner);
        ArrayAdapter<String> postalAdapter = new ArrayAdapter<String>(context, R.layout.spinner_row, postal_address_array);
        postalspinner.setAdapter(postalAdapter);
        postalspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView textview = view.findViewById(R.id.cust_view);
                if (textview.getText().toString().equals("Yes")) {
                    String pAddressStr = enterphysicaladdress.getText().toString();
                    enterpostaladdress.setText(pAddressStr);
                    String psuburbStr = enterphysicalsuburb.getText().toString();
                    enterpostalsuburb.setText(psuburbStr);
                    String pcityStr = enterphysicalcity.getText().toString();
                    enterpostalcity.setText(pcityStr);
                    String pcodeStr = enterphysicalpostalcode.getText().toString();
                    enterpostalpostalcode.setText(pcodeStr);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nextLayout:
                saveScreenData(true, false);
                break;
            case R.id.previous:
                saveScreenData(false, false);
                break;

        }
    }

    private void saveScreenData(boolean NextPreviousFlag, boolean DoneFlag) {
        Intent intent = new Intent("ViewPageChange");
        intent.putExtra("NextPreviousFlag", NextPreviousFlag);
        intent.putExtra("DoneFlag", DoneFlag);
        getActivity().sendBroadcast(intent);
    }

}
