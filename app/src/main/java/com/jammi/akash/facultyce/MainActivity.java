package com.jammi.akash.facultyce;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import javax.xml.transform.sax.SAXResult;

public class MainActivity extends AppCompatActivity {
EditText From;
EditText To;
String  from;
String too;
    private FirebaseAuth mAuth;
    ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        From = findViewById(R.id.from);
        To = findViewById(R.id.to);
        mAuth = FirebaseAuth.getInstance();
        mProgressBar=(ProgressBar)findViewById(R.id.progressBar);
        }

        void register(View view)
        {
            mProgressBar.setVisibility(View.VISIBLE);
            from = From.getText().toString();
            too = To.getText().toString();
                attemptRegistration();
        }

    private void attemptRegistration() {
        String password = Integer.toString(123456);
        boolean cancel = false;
        View focusView = null;
            createFirebaseUser();
    }

    // TODO: Create a Firebase user
    private void createFirebaseUser() {

        for(Integer i=Integer.parseInt(from);i<=Integer.parseInt(too) ;i++ )
        {
        mAuth.createUserWithEmailAndPassword(Integer.toString(i)+"@svce.com", "123456").addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("FacultyCE", "createUser onComplete: " + task.isSuccessful());

                        if(!task.isSuccessful()){
                            Log.d("FacultuCE", "user creation failed");
                            showErrorDialog("Registration attempt failed");
                        } else {
                            Intent intent = new Intent(MainActivity.this,homeActivity.class);
                            finish();
                            startActivity(intent);
                        }
                    }
                });
    }}


    // TODO: Create an alert dialog to show in case registration failed
    private void showErrorDialog(String message){

        new AlertDialog.Builder(this)
                .setTitle("Oops")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }





}

