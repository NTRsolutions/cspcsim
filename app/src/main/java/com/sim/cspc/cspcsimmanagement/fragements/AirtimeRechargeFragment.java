package com.sim.cspc.cspcsimmanagement.fragements;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sim.cspc.cspcsimmanagement.R;
import com.sim.cspc.cspcsimmanagement.activitys.DashboardActivity;
import com.sim.cspc.cspcsimmanagement.adapter.AirtimeRechargeAdapter;
import com.sim.cspc.cspcsimmanagement.adapter.PartyServiceAdapter;
import com.sim.cspc.cspcsimmanagement.models.SalModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AirtimeRechargeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AirtimeRechargeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public AirtimeRechargeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AirtimeRechargeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AirtimeRechargeFragment newInstance(String param1, String param2) {
        AirtimeRechargeFragment fragment = new AirtimeRechargeFragment();
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

    private View view;
private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context=getActivity();
        view = inflater.inflate(R.layout.fragment_airtime_recharge, container, false);
        initView();
        return view;
    }

    private void initView() {
        DashboardActivity rootActivity = (DashboardActivity) getActivity();
        rootActivity.showBackButtonOrHamburger(true);
        RecyclerView recyclerView = (view.findViewById(R.id.recycler_view));

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ArrayList<SalModel> list=new ArrayList<SalModel>();
        String stock_array[] = {"Recharge", "Bulk Recharge", "Schedule Recharge","Balance Query","Funds Transfer","Change PIN","Current Discount"};
        for(int i=0;i<stock_array.length;i++){
            SalModel model=new SalModel();
            model.setTitle(stock_array[i].toString());
            list.add(model);
        }
        AirtimeRechargeAdapter mAdapter = new AirtimeRechargeAdapter(context,list);
        recyclerView.setAdapter(mAdapter);
    }
}
