package com.lamapress.animeexpo2014.axapp;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.Button;
import android.widget.TextView;

import com.cengalabs.flatui.FlatUI;
import com.cengalabs.flatui.views.FlatButton;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.lamapress.animeexpo2014.axapp.core.Convention;
import com.lamapress.animeexpo2014.axapp.sqlite_helper.DatabaseHelper;

import java.sql.SQLException;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class HomeScreen extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.home_screen, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     *
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        TextView view;
        Convention con;
        private DatabaseHelper dbHelper = null;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public void onDestroy(){
            super.onDestroy();
            if(dbHelper != null){
                OpenHelperManager.releaseHelper();
                dbHelper = null;
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            FlatUI.initDefaultValues(getActivity());
            FlatUI.setDefaultTheme(FlatUI.BLOOD);

            View rootView = inflater.inflate(R.layout.fragment_home_screen, container, false);


            view = (TextView)rootView.findViewById(R.id.test_text_view);
            FlatButton btn = (FlatButton)rootView.findViewById(R.id.test_button);
            FlatButton show = (FlatButton)rootView.findViewById(R.id.show_button);

            btn.setOnClickListener(
                    new View.OnClickListener(){
                        @Override
                        public void onClick(View v){
                            try{
                                Dao<Convention,String> conDao = getHelper().getConventionDao();
                                con = conDao.queryForAll().get(0);
                                view.setText(con.m_sConventionCenter);
                            }
                            catch(SQLException e){

                            }
                        }
                    }
            );

            show.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    RestAdapter rest = new RestAdapter.Builder()
                            .setEndpoint(getString(R.string.deployd_server_ip) + ":" +
                                         getString(R.string.deployd_server_port))
                            .build();

                    Convention.ConventionService conService = rest.create(Convention.ConventionService
                            .class);
                    conService.listItems(
                            new Callback<List<Convention>>() {
                                public void failure(RetrofitError e) {
                                    Log.v("FYI", "Callback failed " + e.toString() + " @URL " + e.getUrl());
                                }

                                public void success(List<Convention> con, Response response) {
                                    Log.v("FYI", "Callback success with response: " + con.toString());
                                    try {
                                        Dao<Convention, String> conDao = getHelper().getConventionDao();
                                        view.setText(con.get(0).m_sConventionName);
                                        conDao.create(con.get(0));
                                    } catch (SQLException e) {

                                    }

                                }
                            }
                    );
                }
            });



            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((HomeScreen) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }

        private DatabaseHelper getHelper(){
            if(dbHelper == null){
                dbHelper = OpenHelperManager.getHelper(getActivity(),DatabaseHelper.class);
            }
            return dbHelper;
        }
    }

}
