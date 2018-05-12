package com.sim.cspc.cspcsimmanagement.fragements;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.sim.cspc.cspcsimmanagement.R;
import com.sim.cspc.cspcsimmanagement.activitys.DashboardActivity;
import com.sim.cspc.cspcsimmanagement.activitys.SignUpActivity;
import com.sim.cspc.cspcsimmanagement.utilities.FontManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignupAddBankDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignupAddBankDetailsFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public SignupAddBankDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignupAddBankDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignupAddBankDetailsFragment newInstance(String param1, String param2) {
        SignupAddBankDetailsFragment fragment = new SignupAddBankDetailsFragment();
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

    private Spinner banknamespinner, accounttypespinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity();
        view = inflater.inflate(R.layout.fragment_signup_addbankdetails, container, false);
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

        String account_type_array[] = {"Savings Account", "Current Account", "Money Market Investment Accounts"};
        String bank_name_array[] = {"African Bank", "Bidvest Bank", "Capitec Bank", "Discovery Bank", "First National Bank", "Grindrod Bank", "Imperial Bank South Africa"};
        banknamespinner = (Spinner) view.findViewById(R.id.banknamespinner);
        accounttypespinner = (Spinner) view.findViewById(R.id.accounttypespinner);
        ArrayAdapter<String> accountAdapter = new ArrayAdapter<String>(context, R.layout.spinner_row, account_type_array);
        accounttypespinner.setAdapter(accountAdapter);
        ArrayAdapter<String> bankAdapter = new ArrayAdapter<String>(context, R.layout.spinner_row, bank_name_array);
        banknamespinner.setAdapter(bankAdapter);
        accounttypespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView textview = view.findViewById(R.id.cust_view);
                if (textview.getText().toString().equals("Savings Account")) {

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
                Intent intent = new Intent(context, DashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
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
