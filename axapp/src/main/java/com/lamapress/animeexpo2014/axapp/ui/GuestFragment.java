package com.lamapress.animeexpo2014.axapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.lamapress.animeexpo2014.axapp.R;
import com.lamapress.animeexpo2014.axapp.core.Guest;
import com.lamapress.animeexpo2014.axapp.core.Panel;
import com.lamapress.animeexpo2014.axapp.sqlite_helper.DatabaseHelper;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.view.CardListView;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Anthony on 6/26/2014.
 */
public class GuestFragment extends Fragment {

    private static final String ARG_SECTION = "section_number";
    DatabaseHelper dbHelper = null;
    List<Guest> m_guestList = new ArrayList<Guest>();

    public static GuestFragment newInstance(int sectionNumber){
        GuestFragment fragment = new GuestFragment();

        Bundle args = new Bundle();
        args.putInt(ARG_SECTION,sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public void onDestroy(){
        super.onDestroy();
        if(dbHelper != null){
            OpenHelperManager.releaseHelper();
            dbHelper = null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){

        View rootView = inflater.inflate(R.layout.fragment_guest,container,false);

        try{
            initCard();
        }
        catch(NullPointerException npe){
            RestAdapter rest = new RestAdapter.Builder()
                    .setEndpoint(getActivity().getString(R.string.deployd_server_ip) + ":" +
                                 getActivity().getString(R.string.deployd_server_port))
                    .build();

            Guest.GuestService guestService = rest.create(Guest.GuestService.class);
            guestService.listItems(
                    new Callback<List<Guest>>() {

                        @Override
                        public void success(List<Guest> guests,Response response){
                            try{
                                Dao<Guest,String> guestStringDao = getHelper().getGuestDao();
                                for(int i = 0;i<guests.size();i++){
                                    guestStringDao.createOrUpdate(guests.get(i));
                                }
                                initCard();
                            }
                            catch(SQLException se){

                            }
                        }

                        @Override
                        public void failure(RetrofitError error){
                            Log.v("FYI","Guest inflation error @ server" + error.getUrl() );
                            Log.v("FYI","Error: " + error.toString());

                        }
                    }
            );
        }

        return rootView;
    }

    private void initCard(){
        ArrayList<Card> cards = new ArrayList<Card>();

        try{
            Dao<Guest,String> guestDao = getHelper().getGuestDao();
            m_guestList = guestDao.queryForAll();
        }
        catch(SQLException se){

        }

        for(int i = 0; i < m_guestList.size();i++){
            CardBody card = new CardBody(getActivity(),m_guestList.get(i));

            cards.add(card);
        }

        if(getActivity() != null) {
            CardArrayAdapter adapter = new CardArrayAdapter(getActivity(), cards);
            CardListView cardList = (CardListView) getActivity().findViewById(R.id.guest_list);
            cardList.setAdapter(adapter);
        }
    }

    class CardBody extends Card{
        TextView tvGuestName;
        TextView tvGuestAbout;
        TextView tvGuestURL;
        ImageView ivGuestAvatar;

        Guest guest;

        public CardBody(Context context,Guest guest){
            super(context,R.layout.card_guest_inner);
            this.guest = guest;
        }

        @Override
        public void setupInnerViewElements(ViewGroup vg,View v){
            tvGuestName = (TextView) v.findViewById(R.id.text_guest_name);
            tvGuestAbout =(TextView) v.findViewById(R.id.text_guest_about);
            tvGuestURL = (TextView) v.findViewById(R.id.text_guest_url);
            ivGuestAvatar = (ImageView) v.findViewById(R.id.image_guest_avatar);

            tvGuestName.setText(guest.getM_sName());
            tvGuestAbout.setText(guest.getM_sAbout());
            tvGuestURL.setText(guest.getM_websiteURL());

            Picasso.with(getActivity()).load(guest.getM_sImageBitmap())
                   .into(ivGuestAvatar);
        }
    }

    public DatabaseHelper getHelper(){
        if(dbHelper == null){
            dbHelper = OpenHelperManager.getHelper(getActivity(),DatabaseHelper.class);
        }
        return dbHelper;
    }

}
