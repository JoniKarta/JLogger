package com.example.jloggerlib;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jloggerlib.adapters.LoggerRecyclerAdapter;
import com.example.jloggerlib.spinner.LogLevelAdapter;
import com.example.jloggerlib.spinner.LogLevelManager;
import com.example.jloggerlib.utility.Direction;
import com.example.jloggerlib.viewmodel.LoggerViewModel;


public class LoggerUI extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private RecyclerView recyclerView;
    private LoggerRecyclerAdapter loggerRecyclerAdapter;
    public LoggerViewModel loggerViewModel;
    private EditText search;
    private Switch aSwitch;
    private Spinner spinner;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lib);
        setupViews();
        loggerViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(LoggerViewModel.class);

        loggerViewModel.getByFilters().observe(this, loggerList -> loggerRecyclerAdapter.submitList(loggerList));

        // Handle text event change listener
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void afterTextChanged(Editable s) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loggerViewModel.setLogSearchValue(s.toString());
            }
        });


        // Handle remove on logger item
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                loggerViewModel.delete(loggerRecyclerAdapter.getLoggerPositionAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);

        // Handle change state of the switch
        aSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> loggerViewModel.setSortingValue(isChecked ? Direction.ASC.name() : Direction.DESC.name()));


    }

    public LoggerViewModel getLoggerViewModel() {
        return loggerViewModel;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        loggerViewModel.setLogTypeValue(parent.getSelectedItem().toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void setupViews() {
        setupSpinner();
        setupRecyclerView();
        setupSearch();
        setupSwitch();
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.logger_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        loggerRecyclerAdapter = new LoggerRecyclerAdapter();
        recyclerView.setAdapter(loggerRecyclerAdapter);
    }

    private void setupSwitch() {
        aSwitch = findViewById(R.id.activity_main_switch_direction);
        aSwitch.setChecked(true);

    }

    private void setupSpinner() {
        spinner = findViewById(R.id.activity_main_spinner);
        spinner.setAdapter(new LogLevelAdapter(this, LogLevelManager.getInstance().getLogLevelItemList()));
        spinner.setOnItemSelectedListener(this);
    }

    private void setupSearch() {
        search = findViewById(R.id.activity_main_logger_search);
    }

}