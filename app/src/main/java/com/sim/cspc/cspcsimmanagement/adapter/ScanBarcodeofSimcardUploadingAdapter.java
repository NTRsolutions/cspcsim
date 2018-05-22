package com.sim.cspc.cspcsimmanagement.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sim.cspc.cspcsimmanagement.fragements.ScanBarCodeContactDetailFragment;
import com.sim.cspc.cspcsimmanagement.fragements.ScanBarcodeofSimcardUploadingDocFragment;
import com.sim.cspc.cspcsimmanagement.fragements.SignupAddBankDetailsFragment;
import com.sim.cspc.cspcsimmanagement.fragements.SignupBusinessDetailsFragment;
import com.sim.cspc.cspcsimmanagement.fragements.SignupContactDetailFragment;
import com.sim.cspc.cspcsimmanagement.fragements.SignupGeneralInformationFragment;

public class ScanBarcodeofSimcardUploadingAdapter extends FragmentStatePagerAdapter {
    int mNumOfPage = 2;

    public ScanBarcodeofSimcardUploadingAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ScanBarcodeofSimcardUploadingDocFragment tab1 = ScanBarcodeofSimcardUploadingDocFragment.newInstance("", "");
                return tab1;
            case 1:
                ScanBarCodeContactDetailFragment tab2 = ScanBarCodeContactDetailFragment.newInstance("", "");
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfPage;
    }
}

