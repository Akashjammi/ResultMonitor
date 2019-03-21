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
import android.widget.ProgressBar;
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
TextView internet;
ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);
        results = (TextView) findViewById(R.id.results);
        Winner = findViewById(R.id.winner);
        internet = findViewById(R.id.internet);
        mProgressBar=findViewById(R.id.progressBar2);

        final Intent intent =getIntent();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("votes");
        final DatabaseReference uRef = database.getReference("Users");

        if(intent.getBooleanExtra("livedata",false)== true) {

            myRef.child("ITA").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
//                 mobileArray.add(snapshot.getValue().toString());
                    mProgressBar.setVisibility(View.INVISIBLE);
                    internet.setVisibility(View.INVISIBLE);
                    results.setText(snapshot.getValue().toString());
                    String votes = snapshot.getValue().toString();
                    String texti="";
                    int a=1;
//                    int i=0;
                    int large=0;
                    for( int i=3;i<votes.length()-1;i++)
//                    while(i<=votes.length())
                       {
                        if(votes.charAt(i)=='[' || votes.charAt(i)==']'|| votes.charAt(i)=='{'|| votes.charAt(i)==']')
                        {
                            i++;
                        }
                        if(votes.charAt(i)==',')
                        {
                            texti +="\n" + "Nominee " +"\t ";
//                            + a + "\t has got";
                            a++;
//                            i++;

                        }
                        else{
//                            if(votes.charAt(i)!='=' && votes.charAt(i)!=a)
                                texti+= votes.charAt(i) ;
//                            else

                            if(large < Character.getNumericValue(votes.charAt(i)))
                            {
//                                large =Character.getNumericValue(votes.charAt(i));
//                                Winner.setText("Nominee " +String.valueOf(a-1)+ "\t has Won");
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
        if(intent.getBooleanExtra("voters",true)==true) {
            uRef.child("DoneUsers").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
//                    mobileArray.add(snapshot.getValue().toString());
//                    adapter.notifyDataSetChanged();
                    mProgressBar.setVisibility(View.INVISIBLE);
                    internet.setVisibility(View.INVISIBLE);
                    String users = snapshot.getValue().toString();
                    String newva = "";
                    for (int i = 0; i < users.length()-1; i++) {
                        if (users.charAt(i) == '{')
                            i++;
                        if (users.charAt(i) == '=') {
                            i = i + 11;
                            newva+='\n';
                        } else {
                            newva += users.charAt(i);
                        }
//                    }
//                        results.setText(snapshot.getValue().toString());
                        results.setText(newva);

                    }
                    Log.i("check",newva);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }}}