package com.zybooks.logindemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.R.layout;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    private TextView ItemName;
    private EditText Quantity;
    private Switch textNotifications;
    private FloatingActionButton addItem;
    private DatabaseReference firebaseDatabaseItems;
    private FirebaseAuth mAuth;
    private String userID;
    private ListView mListView;
    private String itemName;
    private String itemQuantity;
    //sms permission
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    String phoneNo;
    String message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        firebaseDatabaseItems = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        ItemName = (EditText) findViewById(R.id.etItemName);
        Quantity = (EditText) findViewById(R.id.etItemQuantity);
        textNotifications = (Switch) findViewById(R.id.switchTextNotifications);
        addItem = (FloatingActionButton) findViewById(R.id.faBtnAddItem);
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();
        mListView = (ListView) findViewById(R.id.mListView);
//on click listener for add button for adding items from input
        addItem.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                itemName = ItemName.getText().toString().trim();
                itemQuantity = Quantity.getText().toString().trim();
                if (itemName != null && itemQuantity != null) {
                    newItem(itemName, itemQuantity);
                } else {
                    toastMessage("Enter in the name and quantity!");
                }
            }
        });
//functions used for taking "snapshots" of the Firebase db, used to update the list as it changes
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ArrayList<Object> array = new ArrayList<>();
                array.add(snapshot.child("Items").getValue());
                ArrayAdapter adapter = new ArrayAdapter(SecondActivity.this, layout.simple_list_item_1, array);
                mListView.setAdapter(adapter);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        firebaseDatabaseItems.addChildEventListener(childEventListener);
    }

    //function for adding user entered items to the firebase db
    private void newItem(String itemName, String itemQuantity) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        Item item = new Item(itemName, itemQuantity);
        firebaseDatabaseItems.child((mAuth.getUid())).child("Items").push().setValue(item).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                toastMessage("Item Added");
            }
        });
    }

    //function for custom toast messages
    public void toastMessage(String message) {
        Toast.makeText(SecondActivity.this, message, Toast.LENGTH_SHORT).show();
    }


}