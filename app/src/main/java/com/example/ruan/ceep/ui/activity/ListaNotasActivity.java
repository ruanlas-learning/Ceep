package com.example.ruan.ceep.ui.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ruan.ceep.R;
import com.example.ruan.ceep.dao.NotaDAO;
import com.example.ruan.ceep.model.Nota;
import com.example.ruan.ceep.ui.recyclerview.adapter.ListaNotasRecyclerViewAdapter;

import java.util.List;

import static com.example.ruan.ceep.ui.activity.NotaActivityConstantes.CHAVE_NOTA;
import static com.example.ruan.ceep.ui.activity.NotaActivityConstantes.CODIGO_REQUISICAO_INSERE_NOTA;
import static com.example.ruan.ceep.ui.activity.NotaActivityConstantes.CODIGO_RESULTADO_NOTA_CRIADA;

public class ListaNotasActivity extends AppCompatActivity {

    private NotaDAO dao;
//    private List<Nota> todasNotas;
    private ListaNotasRecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);

        List<Nota> todasNotas = pegaTodasAsNotas();

        configuraRecyclerView(todasNotas);
        configuraCampoInsereNotas();
    }

    private List<Nota> pegaTodasAsNotas() {
        dao = new NotaDAO();
        return dao.todos();
    }

    @Override
    protected void onStart() {
        super.onStart();
//
//  Como implementamos o onActivityResult(), este trecho abaixo que atualiza
//  a lista do adapter não é mais necessário

//        // limpa a lista
//        todasNotas.clear();
//        // Adiciona todos do banco novamente
//        todasNotas.addAll( dao.todos() );
//        // atualiza o adapter com as informações atualizadas
//        recyclerViewAdapter.notifyDataSetChanged();
    }

    private List<Nota> notasDeExemplo() {
        dao.insere(
                new Nota("Primeira nota", "Descrição pequena"),
                new Nota("Segunda nota", "Esta segunda descrição é bem maior que a da primeira nota")
        );
        return dao.todos();
    }

    private void configuraRecyclerView(List<Nota> todasNotas) {

        RecyclerView listaNotas = findViewById(R.id.lista_notas_recyclerview);
//        ListaNotasAdapter adapter = new ListaNotasAdapter(this, todasNotas);

//        ListaNotasRecyclerViewAdapter recyclerViewAdapter =
        recyclerViewAdapter =
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

    private void configuraCampoInsereNotas() {
        TextView campoInsereNotas = findViewById(R.id.lista_notas_insere_nota);
        campoInsereNotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =
                        new Intent(ListaNotasActivity.this, FormularioNotaActivity.class);
//                startActivity(intent);
                // estamos chamando o formulário de notas e especificando que iremos esperar um
                // resultado deste formulário, e estamos identificando esta requisição da chamada do
                // formulario com o requestCode de valor 1. Ou seja, quando o requestCode for igual
                // a 1, significa que foi daqui que a activity foi chamada
                startActivityForResult(intent, CODIGO_REQUISICAO_INSERE_NOTA);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // quando chamamos uma activity esperando um resultado, este método que é chamado
        // quando a activity finaliza, sendo passado o resultado que esperamos

        // reqestCode = Código de quem chamou a activity (quem fez a requisição)
        // resultCode = Código do resultado da activity que finalizou
        // data = dados que a activity que foi finalizada enviou

        if ( ehResultadoComNota(requestCode, resultCode, data) ) {
            Nota notaRecebida = (Nota) data.getSerializableExtra(CHAVE_NOTA);
            adicionaNota(notaRecebida);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void adicionaNota(Nota notaRecebida) {
        dao.insere(notaRecebida);
        recyclerViewAdapter.adiciona(notaRecebida);
    }

    private boolean ehResultadoComNota(int requestCode, int resultCode, @Nullable Intent data) {
        return ehCodigoRequisicaoInsereNota(requestCode) &&
                ehCodigoResultadoNotaCriada(resultCode) &&
                temNota(data);
    }

    private boolean temNota(@Nullable Intent data) {
        return data.hasExtra(CHAVE_NOTA);
    }

    private boolean ehCodigoResultadoNotaCriada(int resultCode) {
        return resultCode == CODIGO_RESULTADO_NOTA_CRIADA;
    }

    private boolean ehCodigoRequisicaoInsereNota(int requestCode) {
        return requestCode == CODIGO_REQUISICAO_INSERE_NOTA;
    }
}
