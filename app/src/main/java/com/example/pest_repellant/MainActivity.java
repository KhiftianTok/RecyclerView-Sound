package com.example.pest_repellant;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.example.pest_repellant.Helper.RecyclerViewClickListener;
import com.example.pest_repellant.Helper.RecyclerViewTouchListener;
import com.example.pest_repellant.adapter.RecyclerAdapter;
import com.example.pest_repellant.model.Book;
import com.example.pest_repellant.viewModel.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private MainActivityViewModel mMainActivityViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mMainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mMainActivityViewModel.init();

        mMainActivityViewModel.getBooks().observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(@Nullable List<Book> books) {
                mAdapter.notifyDataSetChanged();
            }
        });

        mRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(),
                mRecyclerView, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, PestRepellant.class);
                String message = String.valueOf(position);
                intent.putExtra("Id", message);
                startActivity(intent);
            }

        }));

        initRecyclerView();

    }

    private void initRecyclerView(){
        mAdapter = new RecyclerAdapter(this, mMainActivityViewModel.getBooks().getValue());
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
