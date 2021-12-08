package org.aplas.frozenfood;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    List<Model> modelList;
    RecyclerView recyclerView;
    OrderAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //header
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_bg));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //logo
        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.logo,null);
        actionBar.setCustomView(view);

        //pembuatan arraylist
        modelList = new ArrayList<>();
        modelList.add(new Model("Sosis",getString(R.string.sosis),R.drawable.sosis));
        modelList.add(new Model("Kentang",getString(R.string.kentang),R.drawable.kentang));
        modelList.add(new Model("Bakso",getString(R.string.bakso),R.drawable.bakso));
        modelList.add(new Model("Paket Udang",getString(R.string.siomay),R.drawable.udang));
        modelList.add(new Model("Tempura",getString(R.string.tempura),R.drawable.tempura));
        modelList.add(new Model("Paket Ayam",getString(R.string.ayam),R.drawable.ayam));
        modelList.add(new Model("Paket Daging",getString(R.string.dagingSlice),R.drawable.daging));
        modelList.add(new Model("Otak-otak",getString(R.string.otakOtak),R.drawable.otak));
        modelList.add(new Model("Siomay",getString(R.string.siomay),R.drawable.siomay));

        //recycleView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));
        //adapter
        mAdapter = new OrderAdapter(this,modelList);
        recyclerView.setAdapter(mAdapter);
    }
}