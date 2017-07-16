package com.hernanbosqued.olx;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.hernanbosqued.olx.R;

public abstract class BaseFragmentActivity<F extends Fragment> extends AppCompatActivity {

    private static final String FRAGMENT_TAG = "tag";
    private static final String STACK_KEY = "stack";

    protected abstract F getFragment();

    protected abstract String getActionBarTitle();

    private void setToolbarTitle() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getActionBarTitle());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        if (savedInstanceState == null) {
            setToolbarTitle();
            initFragment();
        }
    }

    private void initFragment() {
        replaceContent(getFragment(), false, FRAGMENT_TAG);
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getCurrentFragment();
        if (fragment instanceof BackPressedCallbacks) {
            if (((BackPressedCallbacks) fragment).onBackPressedCallback()) {
                return;
            }
        }

        super.onBackPressed();
    }

    @SuppressWarnings("unchecked")
    F getCurrentFragment() {
        F fragment;
        try {
            fragment = (F) getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        } catch (ClassCastException e) {
            fragment = null;
        }
        return fragment;
    }

    private void replaceContent(F fragment, boolean addToBackStack, String tag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentContainer, fragment, tag);
        if (addToBackStack) {
            ft.addToBackStack(STACK_KEY);
        }
        ft.commit();
    }

    public interface BackPressedCallbacks {
        boolean onBackPressedCallback();
    }
}