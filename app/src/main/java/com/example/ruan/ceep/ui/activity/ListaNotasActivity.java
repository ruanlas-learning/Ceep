package com.example.ruan.ceep.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ruan.ceep.R;
import com.example.ruan.ceep.dao.NotaDAO;
import com.example.ruan.ceep.model.Nota;
import com.example.ruan.ceep.ui.recyclerview.adapter.ListaNotasRecyclerViewAdapter;

import java.util.List;

public class ListaNotasActivity extends AppCompatActivity {

    private NotaDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);

        dao = new NotaDAO();
        notasDeExemplo();
    }

    @Override
    protected void onStart() {
        super.onStart();

        List<Nota> todasNotas = dao.todos();
        configuraRecyclerView(todasNotas);
    }

    private void notasDeExemplo() {
        dao.insere(
                new Nota("Primeira nota", "Descrição pequena"),
                new Nota("Segunda nota", "Esta segunda descrição é bem maior que a da primeira nota")
        );
    }

    private void configuraRecyclerView(List<Nota> todasNotas) {

        RecyclerView listaNotas = findViewById(R.id.lista_notas_recyclerview);
//        ListaNotasAdapter adapter = new ListaNotasAdapter(this, todasNotas);

        ListaNotasRecyclerViewAdapter recyclerViewAdapter =
                new ListaNotasRecyclerViewAdapter(todasNotas, this);

        listaNotas.setAdapter(recyclerViewAdapter);

//        // é necessário indicar em qual tipo de layout (Gerenciador de Layout) queremos
//        // apresentar os itens do RecyclerView. Por padrão, o RecyclerView já implementa
//        // alguns layouts managers, como o LinearLayoutManager, o GridLayoutManager
//        // e o StaggeredGridLayoutManager
//        // OBS: (Podemos fazer essa indicação via código ou via XML)
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        listaNotas.setLayoutManager(linearLayoutManager);

        TextView campoInsereNotas = findViewById(R.id.lista_notas_insere_nota);
        campoInsereNotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =
                        new Intent(ListaNotasActivity.this, FormularioNotaActivity.class);
                startActivity(intent);
            }
        });
    }
}
