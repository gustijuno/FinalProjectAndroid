package org.aplas.frozenfood;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

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

        //pembuatan arraylist
        modelList = new ArrayList<>();
        modelList.add(new Model("Sosis Frozen",getString(R.string.sosis),R.drawable.sosis));
        modelList.add(new Model("Kentang Frozen",getString(R.string.kentang),R.drawable.kentang));
        modelList.add(new Model("Bakso Frozen",getString(R.string.bakso),R.drawable.bakso));
        modelList.add(new Model("Nugget Frozen",getString(R.string.nugget),R.drawable.nugget));
        modelList.add(new Model("Tempura Frozen",getString(R.string.tempura),R.drawable.tempura));
        modelList.add(new Model("Kacang Polong Frozen",getString(R.string.kacangPolong),R.drawable.polong));
        modelList.add(new Model("Daging Slice Frozen",getString(R.string.dagingSlice),R.drawable.daging));
        modelList.add(new Model("Otak-otak Frozen",getString(R.string.otakOtak),R.drawable.otak));
        modelList.add(new Model("Siomay Frozen",getString(R.string.siomay),R.drawable.siomay));

        //recycleView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));
        //adapter
        mAdapter = new OrderAdapter(this,modelList);
        recyclerView.setAdapter(mAdapter);
    }
}