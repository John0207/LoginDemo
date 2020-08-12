package com.zybooks.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ValueEventListener;


public class SecondActivity extends AppCompatActivity {
    private TextView Inventory;

    private TextView ItemName;
    private EditText Quantity;
    private Button Add;
    private Button Subtract;
    private Button Delete;
    private TextView itemName2;
    private TextView Quantity2;
    private Button Add2;
    private Button Subtract2;
    private Button Delete2;
    private Switch textNotifications;
    private FloatingActionButton addItem;

    private DatabaseReference firebaseDatabaseItems;
    private FirebaseAuth firebaseAuth;


    private String itemName;
    private String itemQuantity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseDatabaseItems = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        ItemName = (EditText) findViewById(R.id.etItemName);
        Quantity = (EditText) findViewById(R.id.etQuantity);
        Add = (Button) findViewById(R.id.btnAdd);
        Subtract = (Button) findViewById(R.id.btnSub);
        Delete = (Button) findViewById(R.id.btnDel);

        itemName2 = (TextView) findViewById(R.id.tvItemName2);
        Quantity2 = (TextView) findViewById(R.id.tvQuantity2);
        Add2 = (Button) findViewById(R.id.btnAdd2);
        Subtract2 = (Button) findViewById(R.id.btnSub2);
        Delete2 = (Button) findViewById(R.id.btnDel2);

        textNotifications = (Switch) findViewById(R.id.switchTextNotifications);
        addItem = (FloatingActionButton) findViewById(R.id.faBtnAddItem);


        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemName = ItemName.getText().toString().trim();
                itemQuantity = Quantity.getText().toString().trim();
                newItem(itemName, itemQuantity);
            }
        });
    }

//    ValueEventListener itemsListener = new ValueEventListener() {
//        @Override
//        public void onDataChange(DataSnapshot dataSnapshot) {
//            // Get Post object and use the values to update the UI
//            Post post = dataSnapshot.getValue(Post.class);
//            // ...
//        }
//    };
//        firebaseDatabaseItems.child(firebaseAuth.getUid()).child("Items").getValue().
//
//    addValueEventListener(itemsListener) {
//
//    }

    private void newItem(String itemName, String itemQuantity) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        Item item = new Item(itemName, itemQuantity);
        firebaseDatabaseItems.child(firebaseAuth.getUid()).child("Items").push().setValue(item).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(SecondActivity.this, "Item Added to Database!", Toast.LENGTH_SHORT).show();
            }
        });


    }


}