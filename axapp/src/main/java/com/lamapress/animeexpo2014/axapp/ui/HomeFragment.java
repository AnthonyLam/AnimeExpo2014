package com.lamapress.animeexpo2014.axapp.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lamapress.animeexpo2014.axapp.R;
import com.lamapress.animeexpo2014.axapp.rss.RssFeed;
import com.lamapress.animeexpo2014.axapp.rss.RssItem;
import com.lamapress.animeexpo2014.axapp.rss.RssReader;
import com.squareup.picasso.Picasso;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardExpand;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.ViewToClickToExpand;
import it.gmariotti.cardslib.library.view.CardListView;

/**
 * @author Anthony Lame
 * @version 1.0
 */
public class HomeFragment extends Fragment {


    ProgressDialog m_Dialog ;

    public static HomeFragment newInstance(int sectionNumber){
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public void onDestroy(){
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        View mainView = inflater.inflate(R.layout.fragment_home_activity,container,false);

        m_Dialog = new ProgressDialog(getActivity());
        initCard(mainView);


        return mainView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onPause(){
        super.onPause();
        if(m_Dialog.isShowing() ){
            m_Dialog.dismiss();
        }
    }

    public void initCard(View v){
        final ArrayList<Card> cards = new ArrayList<Card>();


        class RssGrab extends AsyncTask<URL,Void,List<RssItem>>{

            ArrayList<RssItem> item = new ArrayList<RssItem>();

            @Override
            protected void onPreExecute(){
                super.onPreExecute();

                m_Dialog.setTitle("Loading...");
                m_Dialog.setMessage("Please while loading news...");
                m_Dialog.setIndeterminate(true);
                m_Dialog.show();
            }


            @Override
            public List<RssItem> doInBackground(URL... urls){
                try {
                    RssFeed feed = RssReader.read(urls[0]);
                    ArrayList<RssItem> rssItems = feed.getRssItems();
                    return rssItems;
                }
                catch(SAXException sax){
                    return item;
                }
                catch(IOException io){
                    return item;
                }
            }

            @Override
            public void onPostExecute(List<RssItem> rssList){

                for(int i = 0; i < rssList.size(); i++){
                    CardTitle card = new CardTitle(getActivity(),rssList.get(i));
                    CardBody body = new CardBody(getActivity(),rssList.get(i));
                    card.addCardExpand(body);

                    ViewToClickToExpand viewToClickToExpand =
                            ViewToClickToExpand.builder()
                            .highlightView(false)
                            .setupCardElement(ViewToClickToExpand.CardElementUI.CARD);

                    card.setViewToClickToExpand(viewToClickToExpand);
                    cards.add(card);
                }

                try {
                    CardArrayAdapter m_CardArrayAdapter = new CardArrayAdapter(getActivity(), cards);
                    CardListView listView = (CardListView) getActivity().findViewById(R.id.cards_newslist);
                    listView.setAdapter(m_CardArrayAdapter);
                }
                catch(NullPointerException npe){
                }
                if(m_Dialog.isShowing()) {
                    m_Dialog.dismiss();
                }
            }
        }

        try {
            URL url = new URL("http://fulltextrssfeed.com/anime-expo.org");
            new RssGrab().execute(url);
        }
        catch(MalformedURLException me){
        }
    }

    class CardBody extends CardExpand{
        RssItem item;
        public CardBody(Context context,RssItem item){
            super(context,R.layout.card_news_inner);
            this.item = item;
        }

        @Override
        public void setupInnerViewElements(ViewGroup vg, View v){
            TextView text = (TextView)v.findViewById(R.id.card_main_inner_simple_title);

            if(item!= null) {
                text.setText(
                        SimpleDateFormat.getDateInstance().format(item.getPubDate())
                        + " - " + Html.fromHtml(item.getDescription()).toString().replace((char)65532,(char)32));
            }
        }
    }

    class CardTitle extends Card{
        ImageView image;
        String imageURL;

        RssItem item;
        public CardTitle(Context context,RssItem item){
            super(context,R.layout.card_news_header);
            this.item = item;
        }

        @Override
        public void setupInnerViewElements(ViewGroup vg,View v){
            image = (ImageView)v.findViewById(R.id.card_header_image);
            image.setVisibility(View.INVISIBLE);
            TextView text = (TextView)v.findViewById(R.id.card_header_inner_simple_title);

            if(item != null){
                Html.fromHtml(item.getDescription(), new Html.ImageGetter() {
                    @Override
                    public Drawable getDrawable(String s) {
                        if (imageURL == null ) {
                            imageURL = s;
                        }
                        return null;
                    }
                }, null);

                text.setText(item.getTitle());
                if(imageURL.contains("anime")) {
                    Picasso.with(getActivity()).load(imageURL).resize(200, 300).centerInside().into(image);
                    image.setVisibility(View.VISIBLE);
                }
            }
        }
    }



}
