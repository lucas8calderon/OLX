package com.lucascalderon1.olx.autenticacao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lucascalderon1.olx.R;
import com.lucascalderon1.olx.helper.FirebaseHelper;

public class RecuperarSenhaActivity extends AppCompatActivity {

    private EditText edt_email;
    private ProgressBar progressBar;
    private ImageButton ib_voltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);

        iniciaComponentes();
        configCliques();
    }

    private void configCliques() {
        ib_voltar.setOnClickListener(view -> finish());
    }


    public void validarDados(View view) {
        String email = edt_email.getText().toString();

        if (!email.isEmpty()) {

            progressBar.setVisibility(View.VISIBLE);

            enviarEmail(email);


        } else  {
            edt_email.requestFocus();
            edt_email.setError("Preencha seu email.");

        }

    }

    private void enviarEmail(String email) {
        FirebaseHelper.getAuth().sendPasswordResetEmail(email)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "E-mail enviado com sucesso!", Toast.LENGTH_SHORT).show();
                    } else {
                        String erro = FirebaseHelper.validaErros(task.getException().getMessage());
                        Toast.makeText(this, erro, Toast.LENGTH_SHORT).show();

                    }

                    progressBar.setVisibility(View.GONE);
                });

    }


    private void iniciaComponentes() {

        TextView text_toolbar = findViewById(R.id.text_toolbar);
        text_toolbar.setText("Recuperar senha");

        edt_email = findViewById(R.id.edt_email);
        progressBar = findViewById(R.id.progressBar);
        ib_voltar = findViewById(R.id.ib_voltar);


    }
}