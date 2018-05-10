package com.gmail.placement_cell;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FrontPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);
    }
    public void A(View view) {
        Intent intent = new Intent(FrontPage.this, LoginActivity.class);
        startActivity(intent);
    }
}