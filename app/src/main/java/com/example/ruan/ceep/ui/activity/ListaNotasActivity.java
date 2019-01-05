package com.example.ruan.ceep.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.ruan.ceep.R;
import com.example.ruan.ceep.dao.NotaDAO;
import com.example.ruan.ceep.model.Nota;
import com.example.ruan.ceep.ui.adapter.ListaNotasAdapter;

import java.util.List;

public class ListaNotasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);

        ListView listaNotas = findViewById(R.id.listView);

        NotaDAO dao = new NotaDAO();
        for (int i = 1; i <= 10000; i++){
            dao.insere(
                    new Nota("Título "+ i, "Descrição " + i)
            );
        }

        List<Nota> todasNotas = dao.todos();

        ListaNotasAdapter adapter = new ListaNotasAdapter(this, todasNotas);
        listaNotas.setAdapter(adapter);
    }
}
