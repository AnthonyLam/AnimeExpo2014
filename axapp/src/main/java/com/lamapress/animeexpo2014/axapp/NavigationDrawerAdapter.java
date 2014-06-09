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
        m_sMehData.add(new Meta(R.integer.event,"Events"));
        m_sMehData.add(new Meta(R.integer.event,"More Events"));
        m_sMehData.add(new Meta(R.integer.panel,"Guest of Honor"));
        m_sMehData.add(new Meta(R.integer.panel,"Workshop"));
        m_sMehData.add(new Meta(R.integer.panel,"Panel"));
        m_sMehData.add(new Meta(R.integer.misc,"Misc"));
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
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder)convertView.getTag();
        }

        holder.text.setText(m_sMehData.get(position).fragmentType);

        return convertView;
    }

    @Override
    public View getHeaderView(int position,View convertView,ViewGroup parent){
        HeaderViewHolder holder;

        if(convertView == null){
            holder = new HeaderViewHolder();
            convertView = inflater.inflate(R.layout.list_item_header,parent,false);
            holder.text = (TextView)convertView.findViewById(R.id.header_text);
            convertView.setTag(holder);
        }
        else{
            holder = (HeaderViewHolder)convertView.getTag();
        }

        /*
         * TODO: WARNING: Hardcoded data here
         */
        String headerText = "UhOh";
        switch(m_sMehData.get(position).typeId){
            case R.integer.event:
                headerText = "Event";
                break;
            case R.integer.info:
                headerText = "Info";
                break;
            case R.integer.panel:
                headerText = "Panel";
                break;
            case R.integer.misc:
                headerText = "Misc";
                break;
        }

        holder.text.setText(headerText);
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

    /*
     * TODO: Move to separate core class?
     */
    class Meta{
        int typeId;
        String fragmentType;

        public Meta(int typeId,String fragmentType){
            this.typeId = typeId;
            this.fragmentType = fragmentType;
        }
    }
}
