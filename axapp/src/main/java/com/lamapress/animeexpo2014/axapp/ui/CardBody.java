package com.lamapress.animeexpo2014.axapp.ui;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.lamapress.animeexpo2014.axapp.R;
import com.lamapress.animeexpo2014.axapp.core.Panel;

import java.util.GregorianCalendar;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Card type for panels
 */
public class CardBody extends Card {
        ImageView m_Rectangle;
        TextView m_PanelName;
        TextView m_PanelDescription;
        TextView m_PanelTime;
        TextView m_PanelLocation;
        CheckBox m_Favorited;

        Panel panel;
        GregorianCalendar date;

        public CardBody(Context context, Panel panel){
            super(context, R.layout.card_panel_inner);
            this.panel = panel;
        }

        @Override
        public void setupInnerViewElements(ViewGroup vg, View v){
            m_Rectangle = (ImageView) v.findViewById(R.id.colorBorder);
            m_PanelName = (TextView) v.findViewById(R.id.card_panel_name);
            m_PanelDescription = (TextView) v.findViewById(R.id.card_main_inner_simple_title);
            m_PanelTime = (TextView) v.findViewById(R.id.card_time);
            m_PanelLocation = (TextView) v.findViewById(R.id.card_panel_location);
            m_Favorited = (CheckBox) v.findViewById(R.id.favorited);

            m_Rectangle.setBackgroundColor(Color.rgb(255, 0, 0));

            // Fill view with data
            m_PanelName.setText(panel.getM_sPanelName());
            m_PanelDescription.setText(panel.getM_sPanelDescription());

            date = new GregorianCalendar();
            date.setTimeInMillis(panel.m_PanelTime);

            m_PanelTime.setText(date.getTime().toString());
            m_PanelLocation.setText(panel.getM_sPanelRoom());
            m_Favorited.setChecked(panel.getFavorited());

        }

}
