package com.hernanbosqued.olx;


import android.app.SearchManager;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;

import com.hernanbosqued.olx.domain.Utils;

public class MainActivity extends BaseFragmentActivity<MainFragment> implements MainFragment.Callbacks, SearchView.OnQueryTextListener {

    private SearchView searchView;
    private String query;
    private final String QUERY_KEY = "query";

    @Override
    protected MainFragment getFragment() {
        return MainFragment.newInstance();
    }

    @Override
    protected String getActionBarTitle() {
        return getString(R.string.app_name);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(QUERY_KEY, searchView.getQuery().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        query = savedInstanceState.getString(QUERY_KEY);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        if (!Utils.isNullOrEmpty(query)) {
            searchView.setIconified(false);
            searchView.setQuery(query, false);
            searchView.clearFocus();
        }
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        getCurrentFragment().performSearch(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        //do nothing
        return false;
    }
}