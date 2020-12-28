package com.belajarkodecoding.aplikasicoffee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class editProfilActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etNama, etAlamat, etPhone;
    private Button btn_simpan;
    private DatabaseReference getReference;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_edit_profil);

        etNama = (EditText) findViewById(R.id.et_nama_edit);
        etAlamat = (EditText) findViewById(R.id.et_alamat_edit);
        etPhone = (EditText) findViewById(R.id.et_phone_edit);
        btn_simpan = (Button) findViewById(R.id.btn_simpan_edit);

        btn_simpan.setOnClickListener(this);




    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_simpan_edit:
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                FirebaseDatabase database = FirebaseDatabase.getInstance();

                String nama = etNama.getText().toString();
                String alamat =  etAlamat.getText().toString();
                String phone = etPhone.getText().toString();

                getReference = database.getReference();

                if(nama == null && alamat  == null && etPhone == null){
                    Toast.makeText(editProfilActivity.this, "Data tidak boleh kosong",Toast.LENGTH_SHORT).show();
                } else {
                    getReference.child("user").child(user.getUid()).child("profil").push()
                            .setValue(new user(nama,alamat,phone)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(editProfilActivity.this, "Data Tersimpan",Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            case R.id.back_editprofil:
                Intent intent =  new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

        }
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void setEditValue(String nama, String alamat, String no_telp){

    }
}
