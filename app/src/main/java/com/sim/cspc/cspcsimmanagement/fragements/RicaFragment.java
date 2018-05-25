package com.sim.cspc.cspcsimmanagement.fragements;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.sim.cspc.cspcsimmanagement.R;
import com.sim.cspc.cspcsimmanagement.activitys.DashboardActivity;
import com.sim.cspc.cspcsimmanagement.activitys.SubscriberChnageOwnerActivity;
import com.sim.cspc.cspcsimmanagement.activitys.SubscriberDeRegistrationActivity;
import com.sim.cspc.cspcsimmanagement.activitys.SubscriberQueryActivity;
import com.sim.cspc.cspcsimmanagement.activitys.SubscriberRegistrationActivity;
import com.sim.cspc.cspcsimmanagement.adapter.SlidingImage_Adapter_For_ItemDetails;
import com.sim.cspc.cspcsimmanagement.utilities.FontManager;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RicaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RicaFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public RicaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RicaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RicaFragment newInstance(String param1, String param2) {
        RicaFragment fragment = new RicaFragment();
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
    LinearLayout subregister, querylayout, changeLayout, deregisterLayout;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES = {R.drawable.mtn, R.drawable.voda, R.drawable.cell, R.drawable.ver, R.drawable.tall};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity();
        view = inflater.inflate(R.layout.fragment_rica, container, false);
        init();
        return view;
    }

    private void init() {
        DashboardActivity rootActivity = (DashboardActivity) getActivity();
        // rootActivity.setTitle("Dash Board");
        Typeface fontawesome_font = FontManager.getFontTypefaceMaterialDesignIcons(getActivity(), "fonts/fontawesome-webfont.ttf");
        Typeface materialdesignicons_font = FontManager.getFontTypefaceMaterialDesignIcons(getActivity(), "fonts/materialdesignicons-webfont.otf");
      /*  TextView previous = (TextView) view.findViewById(R.id.previous);
        previous.setTypeface(materialdesignicons_font);
        previous.setText(Html.fromHtml("&#xf141;"));
        previous.setOnClickListener(this);
        nextLayout = (LinearLayout) view.findViewById(R.id.nextLayout);
        nextLayout.setOnClickListener(this);
        TextView nextIcon = (TextView) view.findViewById(R.id.nextIcon);
        nextIcon.setTypeface(materialdesignicons_font);
        nextIcon.setText(Html.fromHtml("&#xf142;"));*/
        subregister = (LinearLayout) view.findViewById(R.id.subregister);
        querylayout = (LinearLayout) view.findViewById(R.id.querylayout);
        changeLayout = (LinearLayout) view.findViewById(R.id.changeLayout);
        deregisterLayout = (LinearLayout) view.findViewById(R.id.deregisterLayout);
        subregister.setOnClickListener(this);
        querylayout.setOnClickListener(this);
        changeLayout.setOnClickListener(this);
        deregisterLayout.setOnClickListener(this);
        slideimage();
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {

            case R.id.subregister:
                intent = new Intent(context, SubscriberRegistrationActivity.class);
                // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.querylayout:
                intent = new Intent(context, SubscriberQueryActivity.class);
                // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.changeLayout:
                intent = new Intent(context, SubscriberChnageOwnerActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.deregisterLayout:
                intent = new Intent(context, SubscriberDeRegistrationActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }

    private void slideimage() {
        for (int i = 0; i < IMAGES.length; i++)
            ImagesArray.add(IMAGES[i]);

        mPager = (ViewPager) view.findViewById(R.id.pager);


        mPager.setAdapter(new SlidingImage_Adapter_For_ItemDetails(context, ImagesArray));


       /* CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        final float density = getResources().getDisplayMetrics().density;
        //Set circle indicator radius
        indicator.setRadius(5 * density);*/

        NUM_PAGES = IMAGES.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
      /*  indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
*/
    }
}
