package com.example.ruan.ceep.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ruan.ceep.R;
import com.example.ruan.ceep.model.Nota;


import static com.example.ruan.ceep.ui.activity.NotaActivityConstantes.CHAVE_NOTA;
import static com.example.ruan.ceep.ui.activity.NotaActivityConstantes.CHAVE_POSICAO;
import static com.example.ruan.ceep.ui.activity.NotaActivityConstantes.POSICAO_INVALIDA;

public class FormularioNotaActivity extends AppCompatActivity {

    private int posicaoRecebida = POSICAO_INVALIDA;

    private TextView titulo, descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_nota);

        inicializaCampos();

        Intent dadosRecebidos = getIntent();
        if ( dadosRecebidos.hasExtra(CHAVE_NOTA) ){
            Nota notaRecebida = (Nota)dadosRecebidos.getSerializableExtra(CHAVE_NOTA);
            posicaoRecebida = dadosRecebidos.getIntExtra(CHAVE_POSICAO, POSICAO_INVALIDA);

            preencheCampos(notaRecebida);
        }
    }

    private void preencheCampos(Nota notaRecebida) {
        titulo.setText(notaRecebida.getTitulo());
        descricao.setText(notaRecebida.getDescricao());
    }

    private void inicializaCampos() {
        titulo = findViewById(R.id.formulario_nota_titulo);
        descricao = findViewById(R.id.formulario_nota_descricao);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario_nota_salva, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.menu_formulario_nota_salva:
                Nota nota = criaNota();
                retornaNota(nota);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void retornaNota(Nota nota) {
        // estamos enviando o resultado para a activity que chamou este formulário
        Intent resultadoInsercao = new Intent();
        resultadoInsercao.putExtra(CHAVE_NOTA, nota);
        if (posicaoRecebida != POSICAO_INVALIDA){
            resultadoInsercao.putExtra(CHAVE_POSICAO, posicaoRecebida);
        }
        setResult(Activity.RESULT_OK, resultadoInsercao);
    }

    @NonNull
    private Nota criaNota() {

// // passamos a responsabilidade de inserir a nota para a ListaNotasActivity, já que passamos a nota
// // como parâmetro para a activity que nos chamou
//                NotaDAO notaDAO = new NotaDAO();
//                notaDAO.insere(nota);
        return new Nota(titulo.getText().toString(), descricao.getText().toString());
    }
}
