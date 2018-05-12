package com.sim.cspc.cspcsimmanagement.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.google.gson.Gson;
import com.sim.cspc.cspcsimmanagement.fragements.SignupAddBankDetailsFragment;
import com.sim.cspc.cspcsimmanagement.fragements.SignupBusinessDetailsFragment;
import com.sim.cspc.cspcsimmanagement.fragements.SignupContactDetailFragment;
import com.sim.cspc.cspcsimmanagement.fragements.SignupGeneralInformationFragment;

import java.util.ArrayList;
import java.util.List;

public class RegistrationPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfPage = 4;

    public RegistrationPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                SignupGeneralInformationFragment tab1 = SignupGeneralInformationFragment.newInstance("", "");
                return tab1;
            case 1:
                SignupContactDetailFragment tab2 = SignupContactDetailFragment.newInstance("", "");
                return tab2;
            case 2:
                SignupBusinessDetailsFragment tab3 = SignupBusinessDetailsFragment.newInstance("", "");
                return tab3;
            case 3:
                SignupAddBankDetailsFragment tab4 = SignupAddBankDetailsFragment.newInstance("", "");
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfPage;
    }
}
