package com.example.ruan.ceep.ui.recyclerview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruan.ceep.R;
import com.example.ruan.ceep.model.Nota;
import com.example.ruan.ceep.ui.recyclerview.adapter.listener.OnItemClickListener;

import java.util.Collections;
import java.util.List;

//public class ListaNotasRecyclerViewAdapter extends RecyclerView.Adapter {
public class ListaNotasRecyclerViewAdapter extends RecyclerView.Adapter<ListaNotasRecyclerViewAdapter.NotaViewHolder> {

    private final List<Nota> notaList;
    private final Context context;
    private OnItemClickListener onItemClickListener;

    public ListaNotasRecyclerViewAdapter(List<Nota> notaList, Context context) {
        this.notaList = notaList;
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
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

    public void altera(int posicao, Nota nota) {
        notaList.set(posicao, nota);
        notifyDataSetChanged();
//        notifyItemChanged(posicao);
    }

    public void remove(int posicao) {
        notaList.remove(posicao);
//        notifyDataSetChanged();
        notifyItemRemoved(posicao);
    }

    public void troca(int posicaoInicial, int posicaoFinal) {
        // este método swap faz a troca de posição dos elementos na Collection
        Collections.swap(notaList, posicaoInicial, posicaoFinal);
//        notifyDataSetChanged();
        notifyItemMoved(posicaoInicial, posicaoFinal);
    }

    class NotaViewHolder extends RecyclerView.ViewHolder{

        private final TextView titulo, descricao;
        private Nota nota;

        public NotaViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.item_nota_titulo);
            descricao = itemView.findViewById(R.id.item_nota_descricao);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(nota, getAdapterPosition());
                }
            });
        }

        public void vincula(Nota nota){
            this.nota = nota;
            preencheCampo(nota);
        }

        private void preencheCampo(Nota nota) {
            titulo.setText(nota.getTitulo());
            descricao.setText(nota.getDescricao());
        }
    }
}
/*
*
* Além das animações para o Adapter que comentei, temos outras animações para ações que envolve um
* range da lista. Por exemplo: suponhamos que você queira filtrar elementos. Para isso, primeiro
* removeria todos os elementos e apresentaria apenas os desejados.
*
* Para manter uma animação fluída no momento que todos os elementos são removidos, podemos usar a
* notificação notifyItemRangeRemoved(), enviando a posição inicial e final do range. Da mesma maneira,
* para exibir todos novamente, existe o notifyItemRangeInserted()...
*
* Perceba que temos diversas possibilidades dentro do Adapter do RecyclerView, caso tenha interesse
* em aprender mais técnicas, recomendo que dê uma olhada na documentação e explore as demais
* possibilidades.
* Documentação: https://developer.android.com/reference/android/support/v7/widget/RecyclerView.Adapter
*
*
* */