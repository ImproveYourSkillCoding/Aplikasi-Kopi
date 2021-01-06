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

    //Deklarasi Variabel Edit Text, Button, dan alamat Reference untuk database
    private EditText etNama, etAlamat, etPhone;
    private Button btn_simpan;
    private DatabaseReference getReference;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_edit_profil);
        //Menyambungkan Edit Text, Button ke activity
        etNama = (EditText) findViewById(R.id.et_nama_edit);
        etAlamat = (EditText) findViewById(R.id.et_alamat_edit);
        etPhone = (EditText) findViewById(R.id.et_phone_edit);
        btn_simpan = (Button) findViewById(R.id.btn_simpan_edit);

        //Set button listener
        btn_simpan.setOnClickListener(this);




    }


    @Override
    public void onClick(View v) {
        //switch untuk tiap button
        switch (v.getId()){
            case R.id.btn_simpan_edit:
                //Inisiasi untuk mengambil status user dan database
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                //Mengambil text yang ada didalam Edit Text
                String nama = etNama.getText().toString();
                String alamat =  etAlamat.getText().toString();
                String phone = etPhone.getText().toString();
                //Membuat variabel pengambil alamat database
                getReference = database.getReference();

                //If else Cek Edit Text
                if(nama == null && alamat  == null && phone == null){
                    Toast.makeText(editProfilActivity.this, "Data tidak boleh kosong",Toast.LENGTH_SHORT).show();
                } else {
                    //Memasukan Nilai kedalam database
                    getReference.child("user").child(user.getUid()).child("profil").push()
                            .setValue(new user(nama,alamat,phone)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(editProfilActivity.this, "Data Tersimpan",Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            case R.id.back_editprofil:
                //event untuk mengembalikan activity ke Main
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
