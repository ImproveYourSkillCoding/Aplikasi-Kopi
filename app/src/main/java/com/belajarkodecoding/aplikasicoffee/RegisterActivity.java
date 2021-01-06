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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    // deklarasi tipe data dan autentikasi firebase
    private FirebaseAuth mAuth;
    private EditText etEmail, etUsername, etPassword;
    FloatingActionButton fabRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // mengambil dan memasukkan autentikasi
        mAuth = FirebaseAuth.getInstance();

        // deklarasi id untuk regis
        etEmail = findViewById(R.id.et_email);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        fabRegister = findViewById(R.id.fab_register);

        // mengecek user dan langsung pindah ke main activity
        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        // event klik register
        fabRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        // memanggil username dan password
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();


        if (TextUtils.isEmpty(email)) {
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


            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // jika regis berhasil, maka langsung pindah ke main activity
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(RegisterActivity.this, "User Created success.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();

                            } else {
                                // jika regis gagal maka akan keluar notif
                                Toast.makeText(RegisterActivity.this, "Authentication failed." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

        // event klik back
    @Override
    public void onBackPressed() {
        Intent goLogin = new Intent(RegisterActivity.this, SelamatDatangActivity.class);
        startActivity(goLogin);
        finish();
    }

    // event start, mengecek user telah login/regis atau setidaknya tidak null
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
}
