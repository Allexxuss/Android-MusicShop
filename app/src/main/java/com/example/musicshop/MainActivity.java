package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int Quantity = 0;
    Spinner spiner;
    ArrayList spinerArray;
    ArrayAdapter spinerAdapter;
    HashMap goodsMap;
    String goodsName;
    double price;
    EditText usernameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.editTextPersonName);
        createSpinner();
        createMap();
    }
    void createSpinner()
    {
        spiner = findViewById(R.id.spinner);
        spiner.setOnItemSelectedListener(this);
        spinerArray = new ArrayList();
        spinerArray.add("guitar");
        spinerArray.add("drums");
        spinerArray.add("keyboard");
        spinerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinerArray);
        spinerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner.setAdapter(spinerAdapter);
    }

    void createMap()
    {
        goodsMap = new HashMap();
        goodsMap.put("guitar", 500.0);
        goodsMap.put("drums", 1500.0);
        goodsMap.put("keyboard", 2500.0);
    }

    public void increaseQuantity(View view) {
        TextView quantityText = findViewById(R.id.quantityText);
        quantityText.setText("" + ++Quantity);
        TextView priceTextView = findViewById(R.id.price);
        priceTextView.setText("" + Quantity * price + " $ ");
    }
    public void decreaseQuantity(View view) {
        TextView quantityText = findViewById(R.id.quantityText);
        if (Quantity>0)
        quantityText.setText("" + --Quantity);
        TextView priceTextView = findViewById(R.id.price);
        priceTextView.setText("" + Quantity * price + " $ ");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodsName = spiner.getSelectedItem().toString();
        price = (double)goodsMap.get(goodsName);
        TextView priceTextView = findViewById(R.id.price);
        priceTextView.setText("" + Quantity * price + " $ ");
        ImageView goodsImageView = findViewById(R.id.goodsImageView);

        switch (goodsName)
        {
            case "guitar":
                goodsImageView.setImageResource(R.drawable.muzyka_elektrogitary_magazin);
                break;
            case "drums":
                goodsImageView.setImageResource(R.drawable.drums);
                break;
            case "keyboard":
                goodsImageView.setImageResource(R.drawable.keyboard);
                break;
            default:
                goodsImageView.setImageResource(R.drawable.muzyka_elektrogitary_magazin);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addToCart(View view) {
        Order order = new Order();
        order.userName = usernameEditText.getText().toString();
        order.goodsName = goodsName;
        order.quantity = Quantity;
        order.price = price;
        order.orderPrice = Quantity * price;
        Log.d("printUserName", order.userName);
        Intent orderIntent = new Intent(MainActivity.this,OrderActivity.class);
        orderIntent.putExtra("userNameForIntent", order.userName);
        orderIntent.putExtra("goodsName", order.goodsName);
        orderIntent.putExtra("quantity", order.quantity);
        orderIntent.putExtra("orderPrice", order.orderPrice);
        orderIntent.putExtra("Price", order.price);
        startActivity(orderIntent);


    }
}