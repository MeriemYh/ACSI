package com.example.myapplication;
import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Classes.Desease;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class AddDeseaseActivity extends AppCompatActivity {

    private EditText deseaseName, deseaseSymptomes;
    private Button submitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_desease);
        deseaseName = findViewById(R.id.desease_name);
        deseaseSymptomes = findViewById(R.id.symptomes);
        submitButton = findViewById(R.id.desease_button);



        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = deseaseName.getText().toString().trim();
                String symptomes = deseaseSymptomes.getText().toString().trim();
                if (name.isEmpty()) {
                    deseaseName.setError("name cannot be empty!");
                }
                if (symptomes.isEmpty()) {
                    deseaseSymptomes.setError("symtomes cannot  be empty!");
                } else {

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference dref = database.getReference("deseases");
                    String ID = dref.push().getKey(); // Generate a unique key for the user
                    Desease deceaseObject = new Desease(name, symptomes);
                    dref.child(ID).setValue(deceaseObject)

                            .addOnSuccessListener(aVoid -> {
                                Toast.makeText(AddDeseaseActivity.this, "Add desease is Successful", Toast.LENGTH_SHORT).show();
                                // Object inserted successfully
                            })
                            .addOnFailureListener(e -> {
                                // Failed to insert object
                                Log.d("AddDeseaseActivity",e.getMessage());
                            });

                }

            }

        });
       
    }
}