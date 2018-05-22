package com.sim.cspc.cspcsimmanagement.fragements;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.sim.cspc.cspcsimmanagement.R;
import com.sim.cspc.cspcsimmanagement.activitys.DashboardActivity;
import com.sim.cspc.cspcsimmanagement.utilities.FontManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ReportsDashBoardsFragment extends Fragment implements View.OnClickListener {

    View  view;
    private Spinner selectReportSpinner, selecttypeSpinner;
    private Context context;
    private TextView dateIcon, datetext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        view = inflater.inflate(R.layout.fragment_report_dasboard, container, false);
        initView(view);
        return view;
    }

    private void initView(View v) {
        DashboardActivity rootActivity = (DashboardActivity) getActivity();
        rootActivity.setTitle("Report DashBoard");
        this.selectReportSpinner = v.findViewById(R.id.selectReportSpinner);
        this.selecttypeSpinner = v.findViewById(R.id.selecttypeSpinner);

        this.setSpinerValue();
    }

    private void setSpinerValue() {
        String select_report[] = {"ABC001", "ABC002", "ABC0003", "ABC0004"};
        String select_report_type[] = {"Sales per region", "Commission received", "Sim cards allocated", "No. of Activations", "No. of Sim Swoops"};


        ArrayAdapter<String> select_reportAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_row, select_report);
        selectReportSpinner.setAdapter(select_reportAdapter);
        selectReportSpinner.setOnItemSelectedListener(new MyOnItemSelectedListener());

        ArrayAdapter<String> roladapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_row, select_report_type);
        selecttypeSpinner.setAdapter(roladapter);
        selecttypeSpinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
        dateIcon = (TextView) view.findViewById(R.id.dateIcon);
        Typeface materialdesignicons_font = FontManager.getFontTypefaceMaterialDesignIcons(context, "fonts/materialdesignicons-webfont.otf");
        dateIcon.setTypeface(materialdesignicons_font);
        dateIcon.setText(Html.fromHtml("&#xf0ed;"));

        LinearLayout stockDateLayout = (LinearLayout) view.findViewById(R.id.stockDateLayout);
        datetext = (TextView) view.findViewById(R.id.datetext);
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        final SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener asylumdate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                datetext.setText(sdf.format(myCalendar.getTime()));
            }
        };
        stockDateLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(context, asylumdate, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent,
                                   View view, int pos, long id) {
        }

        public void onNothingSelected(AdapterView parent) {
            // Do nothing.
        }

    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onClick(View view) {
        switch (this.view.getId()) {


        }


    }

    public boolean isValidateUser() {
        return true;

    }
}
