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

        List<Nota> todasNotas = notasDeExemplo();

        configuraRecyclerView(todasNotas);
    }

    private List<Nota> notasDeExemplo() {

        NotaDAO dao = new NotaDAO();

        dao.insere(
                new Nota("Primeira nota", "Descrição pequena"),
                new Nota("Segunda nota", "Esta segunda descrição é bem maior que a da primeira nota")
        );

        return dao.todos();
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
    }
}
