package com.example.finalhavenus.registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.finalhavenus.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import community.Module3Fragment;
import emergency.Module1Fragment;
import empowerment.Module4Fragment;
import safety.Module2Fragment;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    String name, email, birthday, number;
    Button button, bm1, bm2, bm3, bm4, edit;
    TextView textEmail, textName, textBirthday, textNumber;
    FirebaseUser user;
    FirebaseDatabase db;
    DatabaseReference ref;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bm1 = findViewById(R.id.buttonModule1);
        bm2 = findViewById(R.id.buttonModule2);
        bm3 = findViewById(R.id.buttonModule3);
        bm4 = findViewById(R.id.buttonModule4);



        auth = FirebaseAuth.getInstance();
        button = findViewById(R.id.logout);
        textEmail = findViewById(R.id.user_email);
        textName = findViewById(R.id.user_name);
        user = auth.getCurrentUser();
        String userId = user.getUid();
        ref = FirebaseDatabase.getInstance().getReference().child("users").child(userId);
        if(user == null){
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
        else{
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Users user = snapshot.getValue(Users.class);
                    name = user.getName();
                    email = user.getEmail();

                    textEmail.setText(email);
                    textName.setText(name);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

        bm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Module1Fragment fragment = new Module1Fragment();
                fragmentTransaction.replace(R.id.fragment_container, fragment); // Use your container ID
                fragmentTransaction.addToBackStack(null); // Add this line if you want to enable back navigation
                fragmentTransaction.commit();
            }
        });

        bm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Module2Fragment fragment = new Module2Fragment();
                fragmentTransaction.replace(R.id.fragment_container, fragment); // Use your container ID
                fragmentTransaction.addToBackStack(null); // Add this line if you want to enable back navigation
                fragmentTransaction.commit();
            }
        });

        bm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Module3Fragment fragment = new Module3Fragment();
                fragmentTransaction.replace(R.id.fragment_container, fragment); // Use your container ID
                fragmentTransaction.addToBackStack(null); // Add this line if you want to enable back navigation
                fragmentTransaction.commit();
            }
        });

        bm4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Module4Fragment fragment = new Module4Fragment();
                fragmentTransaction.replace(R.id.fragment_container, fragment); // Use your container ID
                fragmentTransaction.addToBackStack(null); // Add this line if you want to enable back navigation
                fragmentTransaction.commit();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), editActivity.class);
                startActivity(intent);
            }
        });
    }
}