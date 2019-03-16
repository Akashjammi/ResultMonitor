package com.jammi.akash.facultyce;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class vote extends AppCompatActivity {
//    ListView listView ;
TextView results;
TextView Winner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);
        results = (TextView) findViewById(R.id.results);
        Winner = findViewById(R.id.winner);
//        ListView listView = (ListView) findViewById(R.id.list);
//
//        final ArrayList<String> mobileArray = new ArrayList<String>(asList(""));
//
//        final ArrayAdapter adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, mobileArray);
//
//
//        listView.setAdapter(adapter);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                Toast.makeText(getApplicationContext(), "Hello " , Toast.LENGTH_LONG).show();
//
//            }
//        });
//
        Intent intent =getIntent();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("votes");
        final DatabaseReference uRef = database.getReference("Users");

        if(intent.getBooleanExtra("livedata",false)== true) {

            myRef.child("ITA").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
//                 mobileArray.add(snapshot.getValue().toString());
                    results.setText(snapshot.getValue().toString());
                    String votes = snapshot.getValue().toString();
                    String texti="";
                    int a=1;

                    int large=0;
                    for(int i=0;i<votes.length();i++)
                    {
                        if(votes.charAt(i)=='[' || votes.charAt(i)==']')
                        {

                        }
                        if(votes.charAt(i)==',')
                        {
                            texti +="\n" + "Nominie " +"\t "+ a + "\t Has got";
                            a++;

                        }
                        else{
                            texti+= votes.charAt(i);
                            if(large < Character.getNumericValue(votes.charAt(i)))
                            {
                                large =Character.getNumericValue(votes.charAt(i));
                                Winner.setText("Nominee " +String.valueOf(a-1)+ "\t has Won");
                            }

                        }
                    }
                    results.setText(texti);
                    Log.i("texti",texti);
//                    results.setText(snapshot.getValue().toString());




//                Integer[] array = new Integer[vo]
//                for(int i=0;i<votes.length();i++)
//                {       if(votes.charAt(i)!=',' || votes.charAt(i)=='[' || votes.charAt(i)==']' ) {
//                    Log.i("val", String.valueOf(votes.charAt(i)));
//                } else
//
//
//                }
                    Log.i("Val", snapshot.getValue().toString());
//                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        if(intent.getBooleanExtra("voters",true)==true)
        {
            uRef.child("DoneUsers").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
//                    mobileArray.add(snapshot.getValue().toString());
//                    adapter.notifyDataSetChanged();
                    results.setText(snapshot.getValue().toString());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }






        }
    }