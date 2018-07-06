package com.sim.cspc.cspcsimmanagement.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sim.cspc.cspcsimmanagement.R;
import com.sim.cspc.cspcsimmanagement.activitys.DashboardActivity;
import com.sim.cspc.cspcsimmanagement.activitys.OtpVerificationActivity;
import com.sim.cspc.cspcsimmanagement.activitys.RegistrationActivity;
import com.sim.cspc.cspcsimmanagement.activitys.ScanBarcodeofSimcardUploadingDocActivity;
import com.sim.cspc.cspcsimmanagement.activitys.SigninActivity;
import com.sim.cspc.cspcsimmanagement.fragements.AdminApproveRejectUserFragment;
import com.sim.cspc.cspcsimmanagement.fragements.AirtimeRechargeFragment;
import com.sim.cspc.cspcsimmanagement.fragements.ReportsDashBoardsFragment;
import com.sim.cspc.cspcsimmanagement.fragements.RicaFragment;
import com.sim.cspc.cspcsimmanagement.fragements.ViewAllocatedStockFragment;
import com.sim.cspc.cspcsimmanagement.utilities.FontManager;

import java.util.HashSet;
import java.util.List;


/**
 * Created by Neeraj on 4/6/2017.
 */
public class NavigationMenuAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    private List<String> menuList = null;
    private List<String> iconList = null;
    HashSet<Integer> selectedPosition = new HashSet<>();
    Typeface materialdesignicons_font, roboto_regular, gomedii;

    public NavigationMenuAdapter(Context context,
                                 List<String> menuList, List<String> iconList) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
        this.menuList = menuList;
        this.iconList = iconList;
        this.materialdesignicons_font = FontManager.getFontTypefaceMaterialDesignIcons(mContext, "fonts/materialdesignicons-webfont.otf");
        this.roboto_regular = FontManager.getFontTypeface(mContext, "fonts/roboto.regular.ttf");
        this.gomedii = FontManager.getFontTypefaceMaterialDesignIcons(mContext, "fonts/gomedii.ttf");
    }

    @Override
    public int getCount() {
        if (menuList != null) {
            return menuList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.menu_item, null);
            holder.menuTitel = (TextView) convertView.findViewById(R.id.menuTitel);
            holder.menuIcon = (TextView) convertView.findViewById(R.id.menuIcon);
            holder.menuItemLayout = (LinearLayout) convertView.findViewById(R.id.menuItemLayout);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.menuIcon.setTypeface(materialdesignicons_font);
        holder.menuTitel.setTag(position);
        holder.menuTitel.setText(menuList.get(position).toString());
        holder.menuIcon.setText(Html.fromHtml("&#x" + iconList.get(position).toString() + ";"));

//----------fill selected value------
        if (selectedPosition.contains(position)) {
            holder.menuItemLayout.setBackgroundColor(Color.parseColor("#007FD2"));
            holder.menuTitel.setTextColor(Color.WHITE);
            holder.menuIcon.setTextColor(Color.parseColor("#ffffff"));
        } else {
            holder.menuItemLayout.setBackgroundColor(Color.WHITE);
            holder.menuIcon.setTextColor(Color.parseColor("#007FD2"));
            holder.menuTitel.setTextColor(Color.parseColor("#212121"));
        }
        holder.menuTitel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menuList.get(position).equals("CSPC Admin to Approve/Reject User")) {
                    Fragment fragment = AdminApproveRejectUserFragment.newInstance("", "");
                    navigateToFragment(fragment);
                } else if (menuList.get(position).equals("View Allocated Stock")) {
                    Fragment fragment = ViewAllocatedStockFragment.newInstance("", "");
                    navigateToFragment(fragment);
                } else if (menuList.get(position).equals("Scan Barcode of Simcard")) {
                    Intent intent = new Intent(mContext, ScanBarcodeofSimcardUploadingDocActivity.class);
                    mContext.startActivity(intent);
                } else if (menuList.get(position).equals("Reports/Dash Boards")) {
                    ReportsDashBoardsFragment fragment = new ReportsDashBoardsFragment();
                    navigateToFragment(fragment);
                } else if (menuList.get(position).equals("Sim Sales")) {
                    Fragment fragment = ViewAllocatedStockFragment.newInstance("", "");
                    navigateToFragment(fragment);
                } else if (menuList.get(position).equals("Rica")) {
                    Fragment fragment = new RicaFragment();
                    navigateToFragment(fragment);
                }else if (menuList.get(position).equals("Airtime")) {
                    Fragment fragment = new AirtimeRechargeFragment();
                    navigateToFragment(fragment);
                }
                else if (menuList.get(position).equals("Reports")) {
                    Fragment fragment = new ReportsDashBoardsFragment();
                    navigateToFragment(fragment);
                } else if (menuList.get(position).equals("Log Off")) {
                    Intent intent = new Intent(mContext, SigninActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }else if (menuList.get(position).equals("Home")) {
                    Intent intent = new Intent(mContext, DashboardActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
                int pos = (int) v.getTag();
                if (selectedPosition.contains(pos)) {
                    //selectedPosition.remove(pos);
                    //selectedPosition.clear();
                    notifyDataSetChanged();
                } else {
                    selectedPosition.clear();
                    selectedPosition.add(pos);
                    notifyDataSetChanged();
                }

                DashboardActivity rootActivity = (DashboardActivity) mContext;
               // rootActivity.hidMenuIcon();
                rootActivity.closeDrawer();
            }
        });

        return convertView;
    }


    public void navigateToFragment(Fragment fragment) {
        android.support.v4.app.FragmentManager fragmentManager = ((FragmentActivity) mContext).getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        //transaction.setCustomAnimations(R.anim.in_from_right, R.anim.out_to_right);
        transaction.replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }


    public class ViewHolder {
        public TextView menuTitel;
        public TextView menuIcon;
        public LinearLayout menuItemLayout;
    }

}
