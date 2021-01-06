package com.belajarkodecoding.aplikasicoffee;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    // deklarasi tipe data dan autentikasi firebase
    private FirebaseAuth mAuth;
    private EditText etUsername, etPassword;
    FloatingActionButton fabLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // deklarasi id dan juga memasukkan autentikasi
        mAuth = FirebaseAuth.getInstance();
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        fabLogin = findViewById(R.id.fab_login);

        // membuat event klik
        fabLogin.setOnClickListener(this);
    }

    // event klik, ketika memasukkan email dan password
    @Override
    public void onClick(View view) {

        // memanggil username dan password
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if (TextUtils.isEmpty(username)) {
            etUsername.setError("Email is Required");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Password is Required");
            return;
        }
        if (password.length() < 6) {
            etPassword.setError("Password Must Be >= 6 Characters");
            return;
        }

            mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // jika login berhasil
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(LoginActivity.this, "Authentication success.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();


                            } else {
                                // jika tidak berhasil
                                Toast.makeText(LoginActivity.this, "Authentication failed."+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }
    // mengambil status identitas user
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
}