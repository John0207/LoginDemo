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
import android.widget.Button;
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
//Firebase stuff
    private final static  String TAG = "ViewDatabase";
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference firebaseDatabaseItems;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userID;
    private ListView mListView;
//Item stuff
    private String itemName;
    private String itemQuantity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseDatabaseItems = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
//placeholders
        ItemName = (EditText) findViewById(R.id.etItemName);
        Quantity = (EditText) findViewById(R.id.etItemQuantity);
//        Add = (Button) findViewById(R.id.btnAdd);
//        Subtract = (Button) findViewById(R.id.btnSub);
//        Delete = (Button) findViewById(R.id.btnDel);
//        itemName2 = (TextView) findViewById(R.id.tvItemName2);
//        Quantity2 = (TextView) findViewById(R.id.tvQuantity2);
//        Add2 = (Button) findViewById(R.id.btnAdd2);
//        Subtract2 = (Button) findViewById(R.id.btnSub2);
//        Delete2 = (Button) findViewById(R.id.btnDel2);
//buttons
        textNotifications = (Switch) findViewById(R.id.switchTextNotifications);
        addItem = (FloatingActionButton) findViewById(R.id.faBtnAddItem);
//auth and userID
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();
//list
        mListView = (ListView) findViewById(R.id.mListView);
        addItem.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                itemName = ItemName.getText().toString().trim();
                itemQuantity = Quantity.getText().toString().trim();
                if(itemName != null && itemQuantity != null){
                    newItem(itemName, itemQuantity);
                }
                else{
                    toastMessage("Enter in the name and quantity!");
                }
            }
        });
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ArrayList<Object> array = new ArrayList<>();
                array.add(snapshot.child("Items").getValue());
                ArrayAdapter adapter = new ArrayAdapter(SecondActivity.this,layout.simple_list_item_1,array);
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



//                addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                //showData(snapshot);
//                Item item = new Item();
//                ArrayList<String> array = new ArrayList<>();
//                array.add(snapshot.getChildren().toString());
//
//                ArrayAdapter adapter = new ArrayAdapter(SecondActivity.this,layout.simple_list_item_1,array);
//                mListView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }

//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
////    private void showData(DataSnapshot snapshot) {
////        for (DataSnapshot ds: snapshot.getChildren()){
////            Item item = new Item();
////            item.setItemName(ds.child(Objects.requireNonNull(mAuth.getUid())).getValue(Item.class).getItemName());
////            item.setItemQuantity(ds.child(mAuth.getUid()).getValue(Item.class).getItemName());
////
////            Log.d(TAG, "showData: name: " + item.getItemName());
////            Log.d(TAG, "showData: Quan: " + item.getItemQuantity());
////
////            ArrayList<String> array = new ArrayList<>();
////            array.add(item.getItemName());
////            array.add(item.getItemQuantity());
////            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
////            mListView.setAdapter(adapter);
////        }
////    }



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


    public void toastMessage(String message){
        Toast.makeText(SecondActivity.this, message, Toast.LENGTH_SHORT).show();
    }



}