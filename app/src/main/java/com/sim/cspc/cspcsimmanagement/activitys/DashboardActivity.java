package com.sim.cspc.cspcsimmanagement.activitys;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.sim.cspc.cspcsimmanagement.R;
import com.sim.cspc.cspcsimmanagement.adapter.NavigationMenuAdapter;
import com.sim.cspc.cspcsimmanagement.fragements.AdminApproveRejectUserFragment;
import com.sim.cspc.cspcsimmanagement.fragements.HomeFragment;
import com.sim.cspc.cspcsimmanagement.fragements.ReportsDashBoardsFragment;
import com.sim.cspc.cspcsimmanagement.fragements.ScanBarcodeofSimcardUploadingDocFragment;
import com.sim.cspc.cspcsimmanagement.fragements.ViewAllocatedStockFragment;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = (TextView) toolbar.findViewById(R.id.title);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setUpDashboardFragment();
        setMenuLayout();
    }
    //set slider item value
    public void setMenuLayout() {

        ListView menuList = (ListView) findViewById(R.id.lst_menu_items);
        menuList.setDivider(null);
        List<String> itemList = new ArrayList<String>();
        itemList.add("CSPC Admin to Approve/Reject User");
        itemList.add("View Allocated Stock");
        itemList.add("Scan Barcode of Simcard");
        itemList.add("Reports/Dash Boards");
        itemList.add("Login");
        itemList.add("SignUp");

        List<String> menuIconList = new ArrayList<String>();
        menuIconList.add("f515");
        menuIconList.add("f2dc");
        menuIconList.add("f072");
        menuIconList.add("f219");
        menuIconList.add("f342");
        menuIconList.add("f004");

        NavigationMenuAdapter navigationMenuAdapter = new NavigationMenuAdapter(DashboardActivity.this, itemList, menuIconList);
        menuList.setAdapter(navigationMenuAdapter);
    }
    //close drawer after item select
    public void closeDrawer() {
        drawer.closeDrawer(GravityCompat.START);
        //return true;
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //hide navigation view
    public void NavHide() {
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    //open default fragment
    private void setUpDashboardFragment() {
        Fragment fragment = HomeFragment.newInstance("", "");
        moveFragment(fragment);
    }

    private void moveFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                //.addToBackStack(null)
                .commit();
    }

    public void setTitle(String titleStr) {
        title.setText(titleStr);
    }

    //for hid keyboard when tab outside edittext box
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

      /*  if (id == R.id.nav_gallery) {
            Fragment fragment = AdminApproveRejectUserFragment.newInstance("", "");
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .addToBackStack(null)
                    .commit();
        } else if (id == R.id.nav_Login) {
            Intent intent = new Intent(DashboardActivity.this, SigninActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_SignUp) {
            Intent intent = new Intent(DashboardActivity.this, RegistrationActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_stock) {
            Fragment fragment = ViewAllocatedStockFragment.newInstance("", "");
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .addToBackStack(null)
                    .commit();
        } else if (id == R.id.upload_document) {
            Intent intent = new Intent(DashboardActivity.this, ScanBarcodeofSimcardUploadingDocActivity.class);
            startActivity(intent);
        } else if (id == R.id.reports_DashBoards) {
            ReportsDashBoardsFragment fragment = new ReportsDashBoardsFragment();
            naviGateFragment(fragment, null);
        }*//*else if (id == R.id.subscriber_register) {
            Intent intent = new Intent(DashboardActivity.this, SubscriberRegistrationActivity.class);
           // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (id == R.id.subscriber_deregister) {
            Intent intent = new Intent(DashboardActivity.this, SubscriberDeRegistrationActivity.class);
            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (id == R.id.subscriber_query) {
            Intent intent = new Intent(DashboardActivity.this, SubscriberQueryActivity.class);
           // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (id == R.id.subscriber_chnageowner) {
            Intent intent = new Intent(DashboardActivity.this, SubscriberChnageOwnerActivity.class);
            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }*//*

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);*/
        return true;
    }

    public void naviGateFragment(Fragment fragment, Bundle bundle) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }

}
