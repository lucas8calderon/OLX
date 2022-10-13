package com.lucascalderon1.olx.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.lucascalderon1.olx.R;
import com.lucascalderon1.olx.adapter.EstadoAdapter;
import com.lucascalderon1.olx.helper.EstadosList;
import com.lucascalderon1.olx.helper.SPFiltro;
import com.lucascalderon1.olx.model.Estado;

public class EstadosActivity extends AppCompatActivity implements EstadoAdapter.OnClickListener {

    private RecyclerView rv_estados;
    private Boolean acesso = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estados);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            acesso = bundle.getBoolean("filtros");
        }

        iniciaComponentes();

        configRv();

        configCliques();
    }

    private void configCliques(){
        findViewById(R.id.ib_voltar).setOnClickListener(v -> finish());
    }

    private void configRv(){
        rv_estados.setLayoutManager(new LinearLayoutManager(this));
        rv_estados.setHasFixedSize(true);
        EstadoAdapter estadoAdapter = new EstadoAdapter(EstadosList.getList(), this);
        rv_estados.setAdapter(estadoAdapter);
    }

    private void iniciaComponentes(){
        TextView text_toolbar = findViewById(R.id.text_toolbar);
        text_toolbar.setText("Estados");

        rv_estados = findViewById(R.id.rv_estados);
    }

    @Override
    public void OnClick(Estado estado) {
        if(!estado.getNome().equals("Brasil")){
            SPFiltro.setFiltro(this, "ufEstado", estado.getUf());
            SPFiltro.setFiltro(this, "nomeEstado", estado.getNome());

            if(acesso){
                finish();
            }else {
                startActivity(new Intent(this, RegioesActivity.class));
            }
        }else {
            SPFiltro.setFiltro(this, "ufEstado", "");
            SPFiltro.setFiltro(this, "nomeEstado", "");

            finish();
        }
    }
}