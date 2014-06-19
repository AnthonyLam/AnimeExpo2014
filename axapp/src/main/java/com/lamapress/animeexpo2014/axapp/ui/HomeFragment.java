package com.lamapress.animeexpo2014.axapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cengalabs.flatui.FlatUI;
import com.lamapress.animeexpo2014.axapp.R;
import com.lamapress.animeexpo2014.axapp.core.News;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardExpand;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.ViewToClickToExpand;
import it.gmariotti.cardslib.library.view.CardListView;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * @author Anthony Lame
 * @version 1.0
 */
public class HomeFragment extends Fragment {


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

        FlatUI.initDefaultValues(getActivity());
        FlatUI.setDefaultTheme(FlatUI.CANDY);

        View mainView = inflater.inflate(R.layout.fragment_home_activity,container,false);

        initCard();

        return mainView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    public void initCard(){
        final ArrayList<News> newsList = new ArrayList<News>();
        final ArrayList<Card> cards = new ArrayList<Card>();

        RestAdapter rest = new RestAdapter.Builder()
                    .setEndpoint("http://162.243.153.109:5000")
                    .build();

            News.NewsServices newsServices = rest.create(News.NewsServices.class);
            newsServices.listItems(
                    new Callback<List<News>>() {
                        @Override
                        public void success(List<News> news, Response response) {
                            newsList.addAll(news);


                            for(int i=0;i< newsList.size();i++){
                                CardInside card = new CardInside(getActivity());

                                CardTitle header = new CardTitle(getActivity(),newsList.get(i));

                                //Card title
                                card.addCardHeader(header);

                                //Card body
                                CardBody body = new CardBody(getActivity(),newsList.get(i));
                                card.addCardExpand(body);

                                ViewToClickToExpand viewToClickToExpand =
                                        ViewToClickToExpand.builder()
                                        .highlightView(false)
                                        .setupCardElement(ViewToClickToExpand.CardElementUI.CARD);

                                card.setViewToClickToExpand(viewToClickToExpand);

                                cards.add(card);
                            }

                            CardArrayAdapter m_CardArrayAdapter = new CardArrayAdapter(getActivity(),cards);
                            CardListView listView = (CardListView)getActivity().findViewById(R.id.cards_newslist);

                            listView.setAdapter(m_CardArrayAdapter);
                        }
                        @Override
                        public void failure(RetrofitError error){
                            Log.v("FYI","News grab failed");
                        }
                    }
            );


    }

    public class CardInside extends Card{
        public CardInside(Context context){
            super(context);
        }
    }

    class CardTitle extends CardHeader{
        News news;
        public CardTitle(Context context,News news){
            super(context,R.layout.card_news_header);
            this.news = news;
        }

        @Override
        public void setupInnerViewElements(ViewGroup vg, View v){
            TextView text = (TextView)v.findViewById(R.id.text_card_news_header);

            if(news!= null){
                text.setText(news.getM_sNewsTitle().toUpperCase());
            }
        }
    }


    class CardBody extends CardExpand{
        News news;

        public CardBody(Context context,News news){
            super(context,R.layout.card_news_inner);
            this.news = news;
        }

        @Override
        public void setupInnerViewElements(ViewGroup parent,View view) {
            TextView text = (TextView) view.findViewById(R.id.text_card_news_body);

            if(news != null){
                text.setText(news.getM_sNewsDescription() + "\n\n" +
                        news.convertTime().MONTH + " " +
                        news.convertTime().DATE + " " +
                        news.convertTime().YEAR);
            }
        }
    }

}
