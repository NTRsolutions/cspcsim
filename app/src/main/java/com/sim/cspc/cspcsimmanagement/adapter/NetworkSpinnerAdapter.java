package com.sim.cspc.cspcsimmanagement.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
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
import java.util.List;

public class NetworkSpinnerAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    ArrayList<Integer> ImagesArray;
    HashSet<Integer> selectedPosition = new HashSet<>();
    Typeface materialdesignicons_font;

    public NetworkSpinnerAdapter(Context context,
                                 ArrayList<Integer> ImagesArray) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
        this.ImagesArray = ImagesArray;
        this.materialdesignicons_font = FontManager.getFontTypefaceMaterialDesignIcons(mContext, "fonts/materialdesignicons-webfont.otf");

    }

    @Override
    public int getCount() {
        if (ImagesArray != null) {
            return ImagesArray.size();
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
            convertView = inflater.inflate(R.layout.network_pinner_row, null);
            //holder.menuTitel = (TextView) convertView.findViewById(R.id.menuTitel);
            holder.logoimage = (ImageView) convertView.findViewById(R.id.logoimage);
            holder.radio = (RadioButton) convertView.findViewById(R.id.radio);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.logoimage.setImageResource(ImagesArray.get(position));
       /* holder.menuTitel.setTag(position);
        holder.menuTitel.setText(menuList.get(position).toString());
        holder.menuIcon.setText(Html.fromHtml("&#x" + iconList.get(position).toString() + ";"));*/


        return convertView;
    }

    public class ViewHolder {
        public TextView menuTitel;
        public TextView menuIcon;
        public LinearLayout menuItemLayout;
        public ImageView logoimage;
        public RadioButton radio;
    }

}
