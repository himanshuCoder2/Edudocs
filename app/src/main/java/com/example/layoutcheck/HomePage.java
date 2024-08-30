package com.example.layoutcheck;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UtilityAdapter utilityAdapter;
    private List<UtilityItem> utilityItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        recyclerView = findViewById(R.id.recyclerViewUtilities);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3)); // 3 columns

        utilityItemList = new ArrayList<>();
        utilityItemList.add(new UtilityItem(R.drawable.home, "True ID - vCard"));
        utilityItemList.add(new UtilityItem(R.drawable.menu, "Scan QR"));
        utilityItemList.add(new UtilityItem(R.drawable.doc, "Drive"));
        utilityItemList.add(new UtilityItem(R.drawable.notes, "My Consent"));
        utilityItemList.add(new UtilityItem(R.drawable.profile, "Nominee"));
        utilityItemList.add(new UtilityItem(R.drawable.home, "My Activity"));

        utilityAdapter = new UtilityAdapter(utilityItemList);
        recyclerView.setAdapter(utilityAdapter);
    }
}