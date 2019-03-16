package com.jammi.akash.facultyce;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

public class homeActivity extends AppCompatActivity {
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("votes");
    }
public  void bulkadd(View view)
{
    Intent intent = new Intent(homeActivity.this,MainActivity.class);
    startActivity(intent);
}

public void result(View view)
{
    Intent intent = new Intent(homeActivity.this,vote.class);

    intent.putExtra("livedata",true);
    intent.putExtra("voters",false);
    startActivity(intent);
    }


public void total(View view){

    Intent intent = new Intent(homeActivity.this,vote.class);

    intent.putExtra("livedata",false);
    intent.putExtra("voters",true);
    startActivity(intent);


}}