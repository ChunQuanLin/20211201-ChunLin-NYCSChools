package com.example.a20211201_chunlin_nycschools;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.a20211201_chunlin_nycschools.click_listeners.EmailClickListener;
import com.example.a20211201_chunlin_nycschools.click_listeners.LocationClickListener;
import com.example.a20211201_chunlin_nycschools.click_listeners.PhoneClickListener;
import com.example.a20211201_chunlin_nycschools.click_listeners.SiteClickListener;
import com.example.a20211201_chunlin_nycschools.databinding.MainActivityLayoutBinding;
import com.example.a20211201_chunlin_nycschools.model.NYCSchoolModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private boolean isSchoolCallGoingOn = false;
    private boolean isSATCallGoingOn = false;
    private SwipeRefreshLayout refreshLayout;
    NYCViewModel model;
    private RecyclerView recyclerView;
    private NYCSchoolsRecyclerViewAdapter nycSchoolsRecyclerViewAdapter;
    MainActivityLayoutBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainActivityLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setUpVariables();
        refreshLayout.setOnRefreshListener(() -> model.refreshData());
        setSupportActionBar(toolbar);

        setUpObservers();
    }

    private void setUpObservers() {
        Observer<Boolean> observerOfSchoolCall = aBoolean -> {
            isSchoolCallGoingOn = aBoolean;
            refreshScreen();
        };
        Observer<Boolean> observerOfSATCall = aBoolean -> {
            isSATCallGoingOn = aBoolean;
            refreshScreen();
        };
        model.getIsSchoolCallStarted().observe(this, observerOfSchoolCall);
        model.getIsSATCallStarted().observe(this, observerOfSATCall);
    }

    private void setUpVariables() {
        model = new ViewModelProvider(this).get(NYCViewModel.class);
        toolbar = binding.toolbar;
        refreshLayout = binding.swipeLayout;
        recyclerView = binding.itemList;
    }

    private void refreshScreen() {
        if (isSATCallGoingOn && isSchoolCallGoingOn)
            refreshLayout.setRefreshing(true);
        if(!isSchoolCallGoingOn && !isSATCallGoingOn) {
            refreshLayout.setRefreshing(false);
            refreshRecycler();
        }
    }
    private void refreshRecycler() {
        nycSchoolsRecyclerViewAdapter = new NYCSchoolsRecyclerViewAdapter(model.getListOfNYCSchools(),
                new EmailClickListener(this),
                new LocationClickListener(this),
                new PhoneClickListener(this),
                new SiteClickListener(this));
        recyclerView.setAdapter(nycSchoolsRecyclerViewAdapter);
        recyclerView.invalidate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem search = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) search.getActionView();
        search(searchView);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_refresh) {
            model.refreshData();
            return true;
        }
        else if (id == R.id.action_search){
            SearchView searchView = (SearchView) item.getActionView();
            search(searchView);
        }

        return super.onOptionsItemSelected(item);
    }

    private void search(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();
                ArrayList<NYCSchoolModel> newList = new ArrayList<>();
                for (NYCSchoolModel tempSchool : model.getListOfNYCSchools()){
                    String entireStringForSchool = String.format("%s \n%s \n%s \n%s \n%s \n%s \n%s \n%s \n%s",
                            tempSchool.getSchoolName().toLowerCase(),
                            tempSchool.getPhoneNumber().toLowerCase(),
                            tempSchool.getSchoolEmail().toLowerCase(),
                            tempSchool.getWebsite().toLowerCase(),
                            tempSchool.getFormattedLocation().toLowerCase(),
                            tempSchool.getOverviewParagraph().toLowerCase(),
                            tempSchool.getSatAvgReadScore().toLowerCase(),
                            tempSchool.getSatAvgMathScore().toLowerCase(),
                            tempSchool.getSatAvgWriteScore().toLowerCase());

                    if (entireStringForSchool.contains(newText)){
                        newList.add(tempSchool);
                    }
                }
                nycSchoolsRecyclerViewAdapter.setFilter(newList);
                return true;
            }
        });
    }
}