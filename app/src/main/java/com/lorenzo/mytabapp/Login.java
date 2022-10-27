package com.lorenzo.mytabapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class Login extends AppCompatActivity {

    EditText m_email, m_password;
    Button m_accediBtn;
    TextView m_iscrivitiBtn;
    CheckBox ricordami;
    ProgressBar barraProgresso;
    FirebaseAuth fAuth;
    String email, password;
    Boolean checked;
    SharedPreferences sharedPreferences;

    private static final String PREF_NAME = "Preferenze";
    private static final String KEY_REMEMBER = "Ricordati";
    private static final String KEY_EMAIL = "Email";
    private static final String KEY_PASSWORD = "Password";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        m_email = findViewById(R.id.edit_email); // edit email
        m_password = findViewById(R.id.edit_password); //edit password

        m_accediBtn = findViewById(R.id.btn_accedi); // bottone accedi
        m_iscrivitiBtn = findViewById(R.id.text_iscriviti); // textview iscriviti

        barraProgresso = findViewById(R.id.progressBar); //progressbar
        barraProgresso.setVisibility(View.INVISIBLE);

        fAuth = FirebaseAuth.getInstance(); //firebase

        ricordami = findViewById(R.id.check_ricordami); //checkbox

        // email e password memorizzate da edit a string
         email = m_email.getText().toString().trim();
         password = m_password.getText().toString().trim();

        //ricorda dati d'accesso
        sharedPreferences = getApplicationContext().getSharedPreferences(PREF_NAME, 0); // zero per private mode
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // parte del check
        checked = sharedPreferences.getBoolean(KEY_REMEMBER, false);// ricorda di controllare la if

        //prendo email e password
        email = sharedPreferences.getString(KEY_EMAIL, "");
        password = sharedPreferences.getString(KEY_PASSWORD, "");
        Toast.makeText( Login.this, "prendo "+email, Toast.LENGTH_SHORT ).show();


      /*  if (sharedPreferences.getBoolean(KEY_REMEMBER,false)){
            ricordami.setChecked(true);
        } else {
            Toast.makeText(this, "Perfavore iscriviti", Toast.LENGTH_SHORT).show();
            ricordami.setChecked(false);
        } */


        // scrivo email e password
        m_email.setText(email);
        m_password.setText(password);
        ricordami.setChecked(checked);
        Toast.makeText( Login.this, " password="+password, Toast.LENGTH_SHORT ).show();


      m_accediBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              email = m_email.getText().toString().trim();
              password = m_password.getText().toString().trim();

              if (TextUtils.isEmpty(email)) {
                  m_email.setError("Email necessaria");
                  return;
              }

              if (TextUtils.isEmpty(password)) {
                  m_password.setError("Password necessaria");
                  return;
              }

              if (password.length() < 6) {
                  m_password.setError("La Password non può essere più corta di 6 caratteri");
                  return;
              }

              barraProgresso.setVisibility(View.VISIBLE);

              // accesso utente

              fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {

                      if (task.isSuccessful()) {
                          Toast.makeText(Login.this, "Accesso in corso", Toast.LENGTH_SHORT).show();
                          ricorda_dati_utente();
                          startActivity(new Intent(getApplicationContext(), MainActivity.class));
                      } else {
                          Toast.makeText(Login.this, "Errore in fase di accesso" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                          barraProgresso.setVisibility(View.GONE);
                      }

                  }

              });

          }
      });



        m_iscrivitiBtn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Register.class)));


        ricordami.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                email = m_email.getText().toString().trim();
                password = m_password.getText().toString().trim();

                if(ricordami.isChecked()){
                    editor.putString(KEY_EMAIL, email);
                    editor.putString(KEY_PASSWORD, password);
                    editor.putBoolean(KEY_REMEMBER, true);
                    editor.apply();
                    Toast.makeText(Login.this, "Dati salvati", Toast.LENGTH_SHORT).show();

                    //  myEdit.commit();
                } else {
                    editor.putBoolean(KEY_REMEMBER, false);
                    editor.clear();
                    editor.apply();
                    Toast.makeText(Login.this, "Dati non salvati", Toast.LENGTH_SHORT).show();
                }
            }
        });







    }

    private void ricorda_dati_utente(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        email = m_email.getText().toString().trim();
        password = m_password.getText().toString().trim();

        if(ricordami.isChecked()){
            editor.putString(KEY_EMAIL, email);
            editor.putString(KEY_PASSWORD, password);
            editor.putBoolean(KEY_REMEMBER, true);
            editor.apply();
            Toast.makeText(Login.this, "Dati salvati", Toast.LENGTH_SHORT).show();

            //  myEdit.commit();
        } else {
            editor.putBoolean(KEY_REMEMBER, false);
            editor.clear();
            editor.apply();
            Toast.makeText(Login.this, "Dati non salvati", Toast.LENGTH_SHORT).show();
        }
    }


}
