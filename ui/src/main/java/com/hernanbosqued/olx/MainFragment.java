package com.hernanbosqued.olx;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hernanbosqued.olx.domain.TwitterModel;

import java.util.List;

public class MainFragment extends BaseFragment<MainFragment.Callbacks> implements BaseFragmentActivity.BackPressedCallbacks, MainContract.View {

    private ItemsAdapter adapter;
    private Dialog dialog;
    private MainPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        presenter = new MainPresenter();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.bindView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unbindView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prepareRecyclerView(view);
        prepareDialog();
    }

    private void prepareRecyclerView(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext()));
        adapter = new ItemsAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void prepareDialog() {
        dialog = new Dialog(getActivity(), R.style.TransparentDialog);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.progress_dialog);
    }

    public void performSearch(String query) {
        presenter.processQuery(query);
    }

    @Override
    public void showItems(List<TwitterModel.StatusModel> items) {
        adapter.setData(items);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        dialog.show();
    }

    @Override
    public void hideProgress() {
        dialog.dismiss();
    }

    @Override
    public boolean onBackPressedCallback() {
        return false;
    }

    @Override
    protected Callbacks getDummyCallbacks() {
        return new Callbacks() {
        };
    }

    interface Callbacks {
    }
}
