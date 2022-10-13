package com.lucascalderon1.olx.autenticacao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lucascalderon1.olx.R;
import com.lucascalderon1.olx.activitys.MainActivity;
import com.lucascalderon1.olx.helper.FirebaseHelper;
import com.lucascalderon1.olx.model.Usuario;

public class CriarContaActivity extends AppCompatActivity {

    private EditText edt_nome, edt_email, edt_telefone, edt_senha;
    private ProgressBar progressBar;
    private ImageButton ib_voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);

        iniciaComponentes();
        configCliques();

    }

    private void configCliques() {
        ib_voltar.setOnClickListener(view -> finish());


    }

    public void validaDados(View view) {

        String nome = edt_nome.getText().toString();
        String email = edt_email.getText().toString();
        String telefone = edt_telefone.getText().toString();
        String senha = edt_senha.getText().toString();

        if (!nome.isEmpty()) {
            if (!email.isEmpty()) {
                if (!telefone.isEmpty()) {
                    if (!senha.isEmpty()) {

                        progressBar.setVisibility(View.VISIBLE);


                        Usuario usuario = new Usuario();
                        usuario.setNome(nome);
                        usuario.setEmail(email);
                        usuario.setTelefone(telefone);
                        usuario.setSenha(senha);

                        cadastrarusuario(usuario);

                        





                    } else {
                        edt_senha.requestFocus();
                        edt_senha.setError("Preencha sua senha.");

                    }
                } else {
                    edt_telefone.requestFocus();
                    edt_telefone.setError("Preencha seu telfone.");

                }
            } else {
                edt_email.requestFocus();
                edt_email.setError("Preencha seu email.");

            }
        } else {
            edt_nome.requestFocus();
            edt_nome.setError("Preencha seu nome.");

        }
    }

    private void cadastrarusuario(Usuario usuario) {
        FirebaseHelper.getAuth().createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(task -> {

            if (task.isSuccessful()) {

                String id = task.getResult().getUser().getUid();

                usuario.setId(id);
                usuario.salvar(progressBar, getBaseContext());

               startActivity(new Intent(this, MainActivity.class));
               finish();

            } else {

                String erro = FirebaseHelper.validaErros(task.getException().getMessage());
                Toast.makeText(this, erro, Toast.LENGTH_SHORT).show();



                progressBar.setVisibility(View.GONE);

            }

        });
    }


    private void iniciaComponentes() {

        TextView text_toolbar = findViewById(R.id.text_toolbar);
        text_toolbar.setText("Criar conta");

        edt_nome = findViewById(R.id.edt_nome);
        edt_email = findViewById(R.id.edt_email);
        edt_telefone = findViewById(R.id.edt_telefone);
        edt_senha = findViewById(R.id.edt_senha);
        progressBar = findViewById(R.id.progressBar);
        ib_voltar = findViewById(R.id.ib_voltar);




    }

}