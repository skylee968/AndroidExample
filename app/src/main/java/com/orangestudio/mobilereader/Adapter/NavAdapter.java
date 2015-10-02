package com.orangestudio.mobilereader.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.orangestudio.mobilereader.NavigationServe.EntryItem;
import com.orangestudio.mobilereader.NavigationServe.Item;
import com.orangestudio.mobilereader.NavigationServe.SectionItem;
import com.orangestudio.mobilereader.R;

import java.util.ArrayList;


@SuppressLint("NewApi")
public class NavAdapter extends ArrayAdapter<Item> {

    private Context context;
    private ArrayList<Item> items;
    private LayoutInflater vi;

    public NavAdapter(Context context, ArrayList<Item> items) {
        super(context, 0, items);
        this.context = context;
        this.items = items;
        vi = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        final Item i = items.get(position);
        if (i != null) {
           if (i.itemType() == Item.ItemType.SECTION) {
                SectionItem si = (SectionItem) i;
                v = vi.inflate(R.layout.mr__menu__item_section, null);

                v.setOnClickListener(null);
                v.setOnLongClickListener(null);
                v.setLongClickable(false);


            } else if (i.itemType() == Item.ItemType.ENTRY) {

                EntryItem ei = (EntryItem) i;
                v = vi.inflate(R.layout.mr__menu__item_entry, null);
                final TextView title = (TextView) v.findViewById(R.id.mr__mn_item_entry_title);
                final ImageView img = (ImageView) v.findViewById(R.id.mr__mn_item_entry_drawable);

                if (title != null && img != null) {
                    title.setText(ei.getTitle());
                }

               if(img != null){

                   if(ei.getResource() != null){
                       try {
                           img.setBackground(ei.getResource());
                       } catch (Exception e) {
                           img.setBackgroundDrawable(ei.getResource());
                       }
                   }else{
                       img.setVisibility(View.GONE);
                   }

               }
            }
        }
        return v;
    }

}
