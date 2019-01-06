package com.example.ruan.ceep.ui.recyclerview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ruan.ceep.R;
import com.example.ruan.ceep.model.Nota;

import java.util.List;

//public class ListaNotasRecyclerViewAdapter extends RecyclerView.Adapter {
public class ListaNotasRecyclerViewAdapter extends RecyclerView.Adapter<ListaNotasRecyclerViewAdapter.NotaViewHolder> {

    private final List<Nota> notaList;
    private final Context context;

    public ListaNotasRecyclerViewAdapter(List<Nota> notaList, Context context) {
        this.notaList = notaList;
        this.context = context;
    }

    @NonNull
    @Override
    public ListaNotasRecyclerViewAdapter.NotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Este método tem a ideia de basicamente criar as views dos itens da lista (ViewHolder)
        // Ele cria inicialmente todas as views necessárias para fazer a reciclagem delas depois

        View viewCriada = LayoutInflater.from(context)
                .inflate(R.layout.item_nota, parent, false);

        return new NotaViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaNotasRecyclerViewAdapter.NotaViewHolder viewHolder, int position) {
        // Este método tem a ideia de popular as views com os dados do objeto (popular o ViewHolder)
        // Depois de criado todas as ViewsHolders, este método irá populá-las à medida que ele os recebe

        Nota nota = notaList.get(position);

//        NotaViewHolder notaViewHolder = (NotaViewHolder) viewHolder;
//        notaViewHolder.vincula(nota);
        viewHolder.vincula(nota);
    }

    @Override
    public int getItemCount() {
        return notaList.size();
    }

    public void adiciona(Nota nota){
        notaList.add(nota);
        this.notifyDataSetChanged();
    }

    class NotaViewHolder extends RecyclerView.ViewHolder{

        private final TextView titulo, descricao;

        public NotaViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.item_nota_titulo);
            descricao = itemView.findViewById(R.id.item_nota_descricao);
        }

        public void vincula(Nota nota){
            titulo.setText(nota.getTitulo());
            descricao.setText(nota.getDescricao());
        }
    }
}
