package com.example.ibane.bannertest2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jesllagr on 9/14/15.
 */
public class MainMenu extends Activity {
    private GridLayoutManager gridLayoutManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        List<MenuItem> menuItemList = getAllItemList();
        gridLayoutManager = new GridLayoutManager(MainMenu.this, 2);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.menu_recycler_view);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(gridLayoutManager);

        MenuViewAdapter rcAdapter = new MenuViewAdapter(MainMenu.this, menuItemList);
        recyclerView.setAdapter(rcAdapter);
    }
    private List<MenuItem> getAllItemList(){

        List<MenuItem> allItems = new ArrayList<MenuItem>();
        allItems.add(new MenuItem("Schedule", R.drawable.schedule));
        allItems.add(new MenuItem("Transcript", R.drawable.transcript));
        allItems.add(new MenuItem("Student Information", R.drawable.student));
        allItems.add(new MenuItem("Class Registration", R.drawable.registration));
        allItems.add(new MenuItem("Email", R.drawable.email));
        allItems.add(new MenuItem("Campus Map", R.drawable.map));
        allItems.add(new MenuItem("Bill Pay", R.drawable.pay));
        allItems.add(new MenuItem("Blackboard Learn", R.drawable.blackboard));
        return allItems;
    }
}
