package com.example.ruan.ceep.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ruan.ceep.R;
import com.example.ruan.ceep.dao.NotaDAO;
import com.example.ruan.ceep.model.Nota;
import com.example.ruan.ceep.ui.recyclerview.adapter.ListaNotasRecyclerViewAdapter;

import java.util.List;

public class ListaNotasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);

        RecyclerView listaNotas = findViewById(R.id.lista_notas_recyclerview);

        NotaDAO dao = new NotaDAO();
        for (int i = 1; i <= 10000; i++){
            dao.insere(
                    new Nota("Título "+ i, "Descrição " + i)
            );
        }

        List<Nota> todasNotas = dao.todos();

//        ListaNotasAdapter adapter = new ListaNotasAdapter(this, todasNotas);

        ListaNotasRecyclerViewAdapter recyclerViewAdapter =
                new ListaNotasRecyclerViewAdapter(todasNotas, this);

        listaNotas.setAdapter(recyclerViewAdapter);
        // é necessário indicar em qual tipo de layout (Gerenciador de Layout) queremos
        // apresentar os itens do RecyclerView. Por padrão, o RecyclerView já implementa
        // alguns layouts managers, como o LinearLayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listaNotas.setLayoutManager(linearLayoutManager);
    }
}
