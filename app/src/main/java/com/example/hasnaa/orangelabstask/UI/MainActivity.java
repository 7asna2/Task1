package com.example.hasnaa.orangelabstask.UI;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.hasnaa.orangelabstask.R;
import com.example.hasnaa.orangelabstask.UI.GroupsUI.GroupsFragment;
import com.example.hasnaa.orangelabstask.UI.PhotosUI.PhotosFragment;

import net.grandcentrix.thirtyinch.TiActivity;

public class MainActivity extends TiActivity<MainPresenter,MainView> implements MainView {

    private final String LOG_TAG = this.getClass().getSimpleName();

    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private SearchView searchView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        searchView = (SearchView) findViewById(R.id.search1);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Searchable fragment=(Searchable)mSectionsPagerAdapter.getItem(mViewPager.getCurrentItem());
                fragment.search(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the two
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tab, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @NonNull
    @Override
    public MainPresenter providePresenter() {
        return new MainPresenter();
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private PhotosFragment photosFragment;
        private GroupsFragment groupsFragment;
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {        //open photos fragment
                if(photosFragment ==null) {
                    photosFragment=PhotosFragment.newInstance();
                    return photosFragment;
                }
                else
                    return photosFragment;
            }
            else
            if (position == 1) {        //open groups fragment
                if(groupsFragment==null) {
                    groupsFragment = GroupsFragment.newInstance();
                    return groupsFragment;
                }
                else
                    return groupsFragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.photos);
                case 1:
                    return getString(R.string.groups);
            }
            return null;
        }
    }
}
