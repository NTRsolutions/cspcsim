package com.sim.cspc.cspcsimmanagement.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.sim.cspc.cspcsimmanagement.R;
import com.sim.cspc.cspcsimmanagement.utilities.FontManager;

import java.util.ArrayList;
import java.util.HashSet;

public class SpinnerWithRadioButtonAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    ArrayList<String> Arraydata;
    HashSet<Integer> selectedPosition = new HashSet<>();
    Typeface materialdesignicons_font;

    public SpinnerWithRadioButtonAdapter(Context context,
                                         ArrayList<String> Arraydata) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
        this.Arraydata = Arraydata;
        this.materialdesignicons_font = FontManager.getFontTypefaceMaterialDesignIcons(mContext, "fonts/materialdesignicons-webfont.otf");

    }

    @Override
    public int getCount() {
        if (Arraydata != null) {
            return Arraydata.size();
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
            convertView = inflater.inflate(R.layout.spinner_radio_row, null);
            holder.text_view = (TextView) convertView.findViewById(R.id.text_view);
             holder.radio = (RadioButton) convertView.findViewById(R.id.radio);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

       holder.text_view.setText(Arraydata.get(position).toString());
       /* holder.menuTitel.setTag(position);
        holder.menuTitel.setText(menuList.get(position).toString());
        holder.menuIcon.setText(Html.fromHtml("&#x" + iconList.get(position).toString() + ";"));*/


        return convertView;
    }

    public class ViewHolder {
        public TextView text_view;
        public LinearLayout menuItemLayout;
        public RadioButton radio;
    }

}
