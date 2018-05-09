package com.sim.cspc.cspcsimmanagement.fragements;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.sim.cspc.cspcsimmanagement.R;
import com.sim.cspc.cspcsimmanagement.activitys.DashboardActivity;
import com.sim.cspc.cspcsimmanagement.activitys.SignUpActivity;
import com.sim.cspc.cspcsimmanagement.adapter.PartyServiceAdapter;
import com.sim.cspc.cspcsimmanagement.models.SalModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link AdminApproveRejectUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminApproveRejectUserFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public AdminApproveRejectUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminApproveRejectUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminApproveRejectUserFragment newInstance(String param1, String param2) {
        AdminApproveRejectUserFragment fragment = new AdminApproveRejectUserFragment();
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
    private View view;
    private Spinner userspinner;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity();
        view = inflater.inflate(R.layout.fragment_admin_approve_reject_user, container, false);
        init();
        return view;
    }

    private void init() {
        DashboardActivity rootActivity = (DashboardActivity) getActivity();
        rootActivity.setTitle("Admin Approve Reject User");

        userspinner = (Spinner) view.findViewById(R.id.userspinner);
        String postal_address_array[] = {"Pending Approvals","Approved Users","Rejected Users"};
        ArrayAdapter<String> postalAdapter = new ArrayAdapter<String>(context, R.layout.spinner_row, postal_address_array);
        userspinner.setAdapter(postalAdapter);
        userspinner.setOnItemSelectedListener(new MyOnItemSelectedListener());

        ArrayList<SalModel> list=new ArrayList<SalModel>();
        SalModel salModel=new SalModel();
        salModel.setTitle("SMITH");
        list.add(salModel);

        SalModel salModel1=new SalModel();
        salModel1.setTitle("JOHNSON");
        list.add(salModel1);

        SalModel salModel2=new SalModel();
        salModel2.setTitle("WILLIAMS");
        list.add(salModel2);

        SalModel salModel3=new SalModel();
        salModel3.setTitle("BROWN");
        list.add(salModel3);

        SalModel salModel4=new SalModel();
        salModel4.setTitle("JONES");
        list.add(salModel3);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.party_recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        PartyServiceAdapter mAdapter = new PartyServiceAdapter(context,list);
        recyclerView.setAdapter(mAdapter);
    }
    public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent,
                                   View view, int pos, long id) {
             Toast.makeText(parent.getContext(), "The planet is " +parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
        }

        public void onNothingSelected(AdapterView parent) {
            // Do nothing.
        }
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
