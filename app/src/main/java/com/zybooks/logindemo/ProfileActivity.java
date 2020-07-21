package com.zybooks.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private ImageView profilePic;
    //important that variables names are distinguishable from other name/age/email vars
    private TextView  profileName, profileAge, profileEmail;
    private Button profileUpdate;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profilePic = findViewById(R.id.ivProfilePic);
        profileName = findViewById(R.id.tvProfileName);
        profileAge = findViewById(R.id.tvProfileAge);
        profileEmail = findViewById(R.id.tvProfileEmail);
        profileUpdate = findViewById(R.id.btnProfileUpdate);

//      firebaseAuth is object created, FirebaseAuth is the class
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

//      reference of user in database using Uid
        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
//      Access the database and retrieve the data
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //retreive values from db and asign them to instance of UserProfile class
                UserProfile userProfile = snapshot.getValue(UserProfile.class);
                profileName.setText("Name: " + userProfile.getUserName());
                profileAge.setText("Age: " + userProfile.getUserAge());
                profileEmail.setText("Email: " + userProfile.getUserEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this, error.getCode(),Toast.LENGTH_SHORT).show();

            }
        });


    }
}