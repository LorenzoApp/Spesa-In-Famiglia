package com.lorenzo.mytabapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Register extends AppCompatActivity {

    EditText m_nome, m_email, m_password, m_confermaPassword;
    Button m_registratiBtn;
    TextView m_giaRegistratoBtn;
    ProgressBar barraProgresso;
    FirebaseAuth fAuth;
    String uId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        m_nome = findViewById(R.id.edit_nome);
        m_email = findViewById(R.id.edit_email);
        m_password = findViewById(R.id.edit_password);
        m_registratiBtn = findViewById(R.id.btn_registrati);
        m_confermaPassword = findViewById(R.id.edit_conferma_password);
        m_giaRegistratoBtn = findViewById(R.id.text_gia_registrato);

        barraProgresso = findViewById(R.id.progressBar);
        barraProgresso.setVisibility(View.INVISIBLE);
        fAuth = FirebaseAuth.getInstance();


       if (fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        m_registratiBtn.setOnClickListener(v -> {
            String nome = m_nome.getText().toString().trim();
            String email = m_email.getText().toString().trim();
            String password = m_password.getText().toString().trim();
            String confermaPassword = m_confermaPassword.getText().toString().trim();

            if(TextUtils.isEmpty(nome)){
                m_email.setError("Nome necessario");
                return;
            }

            if(TextUtils.isEmpty(email)){
                m_email.setError("Email necessaria");
                return;
            }

            if(TextUtils.isEmpty(password)){
                m_password.setError("Password necessaria");
                return;
            }

            if(TextUtils.isEmpty(confermaPassword)){
                m_password.setError("Password di conferma necessaria");
                return;
            }

            if(password.length() < 6){
                m_password.setError("La Password non può essere più corta di 6 caratteri");
                return;
            }

            if (!confermaPassword.equals(password)){
                m_confermaPassword.setError("Le password non corrispondono");
                return;
            }

            barraProgresso.setVisibility(View.VISIBLE);

            //regisrazione utente su firebase

            fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        uId = FirebaseAuth.getInstance().getUid();
                        Utente utenteP = new Utente(nome, email, uId, password);
                        CreateNewUserDB(uId,utenteP);

                       // myRef.push();
                        Toast.makeText(Register.this, "Utente creato", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                    else {
                        Toast.makeText(Register.this, "Errore in fase di creazione" + Objects.requireNonNull(task.getException()).getMessage() , Toast.LENGTH_SHORT).show();
                        barraProgresso.setVisibility(View.GONE);
                    }
                }

            });

        });


       m_giaRegistratoBtn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Login.class)));




    }

    private void CreateNewUserDB (String Uidx,Utente utente){

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = mDatabase.getReference("Users");
        myRef.child(Uidx).setValue(utente);
    }
}