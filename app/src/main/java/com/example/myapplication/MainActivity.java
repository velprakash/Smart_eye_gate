package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;



public class MainActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button login,b;
    String s1,s2,id,password1;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    DocumentReference rf=db.document("login/credential");

    private TextView loginLockedTV;
    private TextView attemptsLeftTV;
    private TextView numberOfRemainingLoginAttemptsTV;
    int numberOfRemainingLoginAttempts = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         setupVariables();
    }

    private void setupVariables() {
    }

    public void authenticateLogin(View view) {
        rf.get().addOnSuccessListener((OnSuccessListener) (DocumentSnapshot)); {
                s1 = DocumentSnapshot.getString("id");
        s2 = DocumentSnapshot.getString("password");

        id = username.getText().toString();
        password1 = password.getText().toString();
        if (s1.equals(id) && s2.equals(password1)) {
            Intent d1 = new Intent(MainActivity.this, drawer.class);
            startActivity(d1);
        } else {
            Toast.makeText(getApplicationContext(), "Seems like you 're not admin!",
                    Toast.LENGTH_SHORT).show();
            numberOfRemainingLoginAttempts--;
            attemptsLeftTV.setVisibility(View.VISIBLE);
            numberOfRemainingLoginAttemptsTV.setVisibility(View.VISIBLE);
            numberOfRemainingLoginAttemptsTV.setText(Integer.toString(numberOfRemainingLoginAttempts));

            if (numberOfRemainingLoginAttempts == 0) {
                login.setEnabled(false);
                loginLockedTV.setVisibility(View.VISIBLE);
                loginLockedTV.setBackgroundColor(Color.RED);
                loginLockedTV.setText("LOGIN LOCKED!!!");
            }
        }
         }
    }
    };

    private void setupVariables() {
        username = (EditText) findViewById(R.id.usernameET);
        password = (EditText) findViewById(R.id.passwordET);
        login = (Button) findViewById(R.id.loginBtn);
        loginLockedTV = (TextView) findViewById(R.id.textView1);
        attemptsLeftTV = (TextView) findViewById(R.id.attemptsLeftTV);
        numberOfRemainingLoginAttemptsTV = (TextView) findViewById(R.id.numberOfRemainingLoginAttemptsTV);
        numberOfRemainingLoginAttemptsTV.setText(Integer.toString(numberOfRemainingLoginAttempts));
        b = (Button) findViewById(R.id.button2);

        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, newuser.class);
                startActivity(i);
            }


        }
    }
}
