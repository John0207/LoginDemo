package com.zybooks.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    private EditText userName, userPassword, userEmail, userAge;
    private Button regButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;
    private ImageView userProfilePic;
    String name, email, age, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //once validation of credentials is complete
                if(validate()){
                    //upload data to the database
                    String user_email = userEmail.getText().toString().trim();
                    String user_password = userPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                //sendEmailVerification();
                                sendUserData();
                                firebaseAuth.signOut();
                                Toast.makeText(RegistrationActivity.this, "Successfully Registered, Upload complete", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                            }else{
                                Toast.makeText(RegistrationActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }

            }
        });
        //direct user to login activity
        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            }
        });

    }
    //function to assign variables with ids in xml layout
    private void setupUIViews(){
        userName = (EditText)findViewById(R.id.etUserName);
        userPassword = (EditText)findViewById(R.id.etUserPassword);
        userEmail = (EditText)findViewById(R.id.etUserEmail);
        regButton = (Button)findViewById(R.id.btnRegister);
        userLogin = (TextView)findViewById(R.id.tvUserLogin);
        userAge = (EditText)findViewById(R.id.etAge);
        userProfilePic = (ImageView)findViewById(R.id.ivProfile);

    }
    //validate f the user has entered all the needed details
    private Boolean validate(){
        Boolean result = false;

        name = userName.getText().toString();
        password = userPassword.getText().toString();
        email = userEmail.getText().toString();
        age = userAge.getText().toString();

        if(name.isEmpty() || password.isEmpty() || email.isEmpty()|| age.isEmpty()) {
            Toast.makeText(this, "Please Enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            result = true;

        }

        return result;
    }

    //email verification
    private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser != null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        sendUserData();
                        Toast.makeText(RegistrationActivity.this, "Succesfully Registered, Verification Email has been sent", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                    }else{
                        Toast.makeText(RegistrationActivity.this, "Verification Email has not been sent", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    private void sendUserData(){
        //creates reference of userProfile class using Uid of user
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        UserProfile  userProfile = new UserProfile(name, age, email);
        myRef.setValue(userProfile);

    }


}