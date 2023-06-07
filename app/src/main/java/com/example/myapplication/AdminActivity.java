package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.Classes.Desease;
import com.example.Classes.MyAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class AdminActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private Button AddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dref = database.getReference("deseases");
        recyclerView = findViewById(R.id.recyclerView);
        AddButton = findViewById(R.id.add_button);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Desease> itemList = new ArrayList<>();
                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                    String name = itemSnapshot.child("name").getValue(String.class);
                    String symptomes = itemSnapshot.child("symptomes").getValue(String.class);
                    if (name != null && symptomes != null) {
                        Desease item = new Desease(name, symptomes);
                        itemList.add(item);
                    }
                }

                adapter = new MyAdapter(itemList);
                recyclerView.setAdapter(adapter);            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("firebase_error", "Firebase database error: " + databaseError.getMessage());


            }


        });

        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, AddDeseaseActivity.class));
            }
        });
    }

}