package com.zybooks.logindemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.jar.Attributes;

public class SecondActivity extends AppCompatActivity {
    private TextView Inventory;

    private TextView itemName;
    private TextView Quantity;
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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        itemName = (TextView)findViewById(R.id.tvItemName);
        Quantity = (TextView)findViewById(R.id.tvQuantity);
        Add = (Button)findViewById(R.id.btnAdd);
        Subtract = (Button)findViewById(R.id.btnSub);
        Delete = (Button)findViewById(R.id.btnDel);

        itemName2 = (TextView)findViewById(R.id.tvItemName2);
        Quantity2 = (TextView)findViewById(R.id.tvQuantity2);
        Add2 = (Button)findViewById(R.id.btnAdd2);
        Subtract2 = (Button)findViewById(R.id.btnSub2);
        Delete2 = (Button)findViewById(R.id.btnDel2);

        textNotifications = (Switch)findViewById(R.id.switchTextNotifications);
        addItem = (FloatingActionButton)findViewById(R.id.faBtnAddItem);



    }
}