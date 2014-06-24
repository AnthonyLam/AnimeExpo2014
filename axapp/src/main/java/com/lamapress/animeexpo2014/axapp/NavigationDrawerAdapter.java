package com.lamapress.animeexpo2014.axapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * @author Anthony Lam
 * @version 1.0
 */
public class NavigationDrawerAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    private ArrayList<Meta> m_sMehData = new ArrayList<Meta>();
    private LayoutInflater inflater;



    public NavigationDrawerAdapter(Context context){
         /*
         * TODO: WARNING: Hardcoded data here
         */
        m_sMehData.add(new Meta(R.integer.section1,"Events",R.drawable.ic_panel));
        m_sMehData.add(new Meta(R.integer.section2,"More Events",R.drawable.ic_panel));
        m_sMehData.add(new Meta(R.integer.section2,"Guest of Honor",R.drawable.ic_guesthonor));
        m_sMehData.add(new Meta(R.integer.section2,"Workshop",R.drawable.ic_workshop));
        m_sMehData.add(new Meta(R.integer.section2,"Panel",R.drawable.ic_panel));
        m_sMehData.add(new Meta(R.integer.section3,"Maps",R.drawable.ic_map));
        m_sMehData.add(new Meta(R.integer.section3,"Misc",R.drawable.ic_video));
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount(){
        return m_sMehData.size();
    }

    @Override
    public Object getItem(int position){
        return m_sMehData.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position,View convertView, ViewGroup parent){

        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item_navigation_drawer,parent,false);
            holder.text = (TextView)convertView.findViewById(R.id.navigation_text);

            holder.image = (ImageView)convertView.findViewById(R.id.navigation_image);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder)convertView.getTag();
        }

        holder.text.setText(m_sMehData.get(position).fragmentType);
        holder.image.setImageResource(m_sMehData.get(position).imageId);

        return convertView;
    }

    @Override
    public View getHeaderView(int position,View convertView,ViewGroup parent){
        HeaderViewHolder holder;

        if(convertView == null){
            holder = new HeaderViewHolder();
            convertView = inflater.inflate(R.layout.list_item_header,parent,false);
            convertView.setTag(holder);
        }
        else{
            holder = (HeaderViewHolder)convertView.getTag();
        }

        return convertView;
    }

    @Override
    public long getHeaderId(int position){
        return m_sMehData.get(position).typeId;
    }

    class HeaderViewHolder{
        TextView text;
    }

    class ViewHolder{
        TextView text;
        ImageView image;
    }

    class Meta{
        long typeId;
        String fragmentType;
        public int imageId;

        public Meta(long sl,String fragmentType,int imageId){
            this.typeId = sl;
            this.fragmentType = fragmentType;
            this.imageId = imageId;
        }
    }
}
