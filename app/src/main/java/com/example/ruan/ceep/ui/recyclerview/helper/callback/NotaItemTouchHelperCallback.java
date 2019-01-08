package com.example.ruan.ceep.ui.recyclerview.helper.callback;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.ruan.ceep.dao.NotaDAO;
import com.example.ruan.ceep.ui.recyclerview.adapter.ListaNotasRecyclerViewAdapter;

/*
* O callback ItemTouchHelper.Callback é uma implementação primária para criar um callback para o
* ItemTouchHelper, mas existem outras classes que possibilitam a implementação do callback também.
* É o caso da ItemTouchHelper.SimpleCallback, que é uma subclasse do ItemTouchHelper.Callback e tem
* o objetivo de evitar a implementação do método getMovementFlags().
*
* Esta é uma implementação no estilo de classe anônima assim como fazemos com as interfaces.
*
* */
public class NotaItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private final ListaNotasRecyclerViewAdapter adapter;
    private final NotaDAO dao;

    public NotaItemTouchHelperCallback(ListaNotasRecyclerViewAdapter adapter) {
        this.adapter = adapter;
        dao = new NotaDAO();
    }

    // a ideia deste método é fazer a definição do que a gente vai permitir de animação. Ex. Permitir
    // o deslizamento pela esquerda, para a direita, etc.
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int marcacoesDeDeslize = ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
        int marcacoesDeArrastar = ItemTouchHelper.DOWN | ItemTouchHelper.UP
                | ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
        // cria o movimento que esperamos a partir das marcações. Assim enviamos a marcação para que
        // os elementos tenham os comportamentos desejados
        // dragFlags = arrasta os elementos
        // swipeFlags = desliza os elementos
        return makeMovementFlags(marcacoesDeArrastar, marcacoesDeDeslize);
    }

    // este método será uma chamada para quando um elemento for arrastado dentro no nosso RecyclerView.
    // Quando um elemento for arrastado, este método será chamado
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        int posicaoInicial = viewHolder.getAdapterPosition();
        int posicaoFinal = target.getAdapterPosition();
        trocaNotas(posicaoInicial, posicaoFinal);
        return true; // se o movimento ocorrer ele deverá devolver true
    }

    private void trocaNotas(int posicaoInicial, int posicaoFinal) {
        dao.troca(posicaoInicial, posicaoFinal);
        adapter.troca(posicaoInicial, posicaoFinal);
    }

    // este método será chamado quando um elemento for deslizado dentro do RecycleView
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int posicaoDaNotaDeslizada = viewHolder.getAdapterPosition();
        removeNota(posicaoDaNotaDeslizada);
    }

    private void removeNota(int posicao) {
        dao.remove(posicao);
        adapter.remove(posicao);
    }
}
