package com.svnit.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText item;
    Button add;
    RecyclerView recyclerView;
    ArrayList<String> listview = new ArrayList<>();
    ArrayList<Boolean> tickview = new ArrayList<>();
    RecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        item = findViewById(R.id.task);
        add = findViewById(R.id.button);
        recyclerView = findViewById(R.id.view);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        listview = Filehelper.readText(this);
        tickview = Filehelper.readCheck(this);
        adapter = new RecyclerAdapter(listview,tickview,MainActivity.this);
        recyclerView.setAdapter(adapter);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemName = item.getText().toString();
                if (itemName.equals("")) {
                    Toast.makeText(MainActivity.this, "Enter a item", Toast.LENGTH_SHORT).show();
                } else {
                    listview.add(itemName);
                    tickview.add(false);
                    item.setText("");
                    Filehelper.writeData(listview, tickview, getApplicationContext());
                    adapter.notifyDataSetChanged();
                    InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(view.getApplicationWindowToken(),0);
                }
            }

        });
    }
}