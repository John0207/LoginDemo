package com.zybooks.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    private TextView userRegistration;
    private TextView forgotPassword;

    //used to import firebase authentication libraries
    private FirebaseAuth firebaseAuth;

    //progress of login
    private ProgressDialog progressDialog;


    //@SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText) findViewById(R.id.etName);
        Password = (EditText) findViewById(R.id.etPassword);
        Info = (TextView) findViewById(R.id.tvInfo);
        Login = (Button) findViewById(R.id.btnLogin);
        userRegistration = (TextView) findViewById(R.id.tvRegister);

        Info.setText("Number of attempts remaining: 5");

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        forgotPassword = (TextView) findViewById(R.id.tvForgotPassword);

        FirebaseUser user = firebaseAuth.getCurrentUser();
        //brings user to next activity
        if (user != null) {
            finish();
            startActivity(new Intent(MainActivity.this, SecondActivity.class));
        }


        Login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });

        userRegistration.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //directs user to registration activity when they clock on the new user button
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });

        forgotPassword.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PasswordActivity.class));
            }
        });
    }

    //function which validates entered user creditials with database and directs them to the second activity
    private void validate(String userName, String userPassword) {

        progressDialog.setMessage("Login loading...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    //Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    checkEmailVerification();
                } else {
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    counter--;
                    Info.setText("Number of attempts remaining: " + counter);
                    progressDialog.dismiss();
                    if (counter == 0) {
                        Login.setEnabled(false);
                    }
                }
            }
        });
    }

    private void checkEmailVerification() {
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();

        startActivity(new Intent(MainActivity.this, SecondActivity.class));
//       uncomment to enable email verification
//        if(emailflag){
//            finish();
//            startActivity(new Intent(MainActivity.this, SecondActivity.class));
//
//        }else{
//            Toast.makeText(this, "Verify your Email", Toast.LENGTH_SHORT).show();
//            firebaseAuth.signOut();
//        }
//    }
    }
}

