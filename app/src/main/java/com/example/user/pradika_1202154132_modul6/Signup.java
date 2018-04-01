package com.example.user.pradika_1202154132_modul6;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signup extends AppCompatActivity {

    EditText user; EditText pass; ProgressDialog dlg;
    FirebaseAuth auth; FirebaseAuth.AuthStateListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Inisialisasi semua objek yang digunakan pada class ini
        user = findViewById(R.id.etusr); pass = findViewById(R.id.etpass);
        dlg = new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();
        listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = auth.getCurrentUser();
                if(user!=null){
                    Intent movehome = new Intent(Signup.this, Home.class);
                    movehome.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                    startActivity(movehome);
                }
            }
        };

    }

    //Method ketika activity dimulai
    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(listener);
    }

    //Method ketika activity berakhir
    @Override
    protected void onStop() {
        super.onStop();
        auth.removeAuthStateListener(listener);
    }


    public void daftar(View view) {
        //Menampilkan dialog
        dlg.setMessage("Creating account. . .");
        dlg.show();

        //Membaca input user
        String inuser = user.getText().toString().trim();
        String inpass = pass.getText().toString().trim();

        //Apakah input user kosong?

        //Jika tidak :
        if(!TextUtils.isEmpty(inuser)||!TextUtils.isEmpty(inpass)){
            //Membuat user baru berdasarkan input user
            auth.createUserWithEmailAndPassword(inuser, inpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    //Operasi ketika pembuatan user baru berhasil
                    if(task.isSuccessful()){
                        Toast.makeText(Signup.this, "Account created!", Toast.LENGTH_SHORT).show();
                        Intent movehome = new Intent(Signup.this, MainActivity.class);
                        movehome.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                        finish();

                        //Operasi ketika pembuatan user baru gagal
                    }else{
                        Log.w("Firebase", task.getException());
                        Toast.makeText(Signup.this, "Account creation failed!", Toast.LENGTH_SHORT).show();
                        user.setText(null); pass.setText(null);
                    }
                    //Tutup dialog ketika berhasil atau pun tidak
                    dlg.dismiss();
                }
            });

            //Ketika input user kosong
        }else{
            Toast.makeText(this, "The field is empty", Toast.LENGTH_SHORT).show();
            user.setText(null); pass.setText(null);
        }

    }
}