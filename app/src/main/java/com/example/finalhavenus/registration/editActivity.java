package com.example.finalhavenus.registration;

import android.app.DatePickerDialog;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalhavenus.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class editActivity extends AppCompatActivity {

    Button save;
    DatabaseReference ref, refP;
    FirebaseAuth mAuth;
    FirebaseDatabase db;
    Uri imageUri;
    String myUri;
    TextInputEditText editTextBirthday, editTextName, editTextNumber;
    String name, birthday, number, email, password;
    Calendar calendar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        save = findViewById(R.id.save);

        editTextName = findViewById(R.id.name);
        editTextNumber = findViewById(R.id.number);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = String.valueOf(editTextName.getText());
                number = String.valueOf(editTextNumber.getText());
                birthday = String.valueOf(editTextBirthday.getText());

                if(TextUtils.isEmpty(name)){
                    Toast.makeText(editActivity.this, "Enter name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(number)){
                    Toast.makeText(editActivity.this, "Enter phone number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(birthday)){
                    Toast.makeText(editActivity.this, "Enter birthday", Toast.LENGTH_SHORT).show();
                    return;
                }


                FirebaseUser currentUser = mAuth.getCurrentUser();
                String userId = currentUser.getUid();
                ref = db.getReference().child("users").child(userId);
                refP = db.getReference().child("profile").child(userId);
                Profile profile = new Profile(birthday.toString(), number.toString());
                refP.setValue(profile);

                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Users user = snapshot.getValue(Users.class);
                        email = user.getEmail();
                        password = user.getPassword();
                        Users user1 = new Users(name, email, password);
                        ref.setValue(user1);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                refP.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Profile profile = snapshot.getValue(Profile.class);
                        Profile profile1 = new Profile(birthday, number);
                        refP.setValue(profile1);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                Toast.makeText(editActivity.this, "Profile updated.", Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void showDatePickerDialog(View v) {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // Handle the selected date
                // Update the EditText with the selected date
                editTextBirthday = findViewById(R.id.birthday);
                editTextBirthday.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        };

        // Get the current date
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create and show the DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, day);
        datePickerDialog.show();
    }
}
