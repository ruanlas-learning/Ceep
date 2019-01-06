package com.example.ruan.ceep.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.ruan.ceep.R;
import com.example.ruan.ceep.dao.NotaDAO;
import com.example.ruan.ceep.model.Nota;

public class FormularioNotaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_nota);
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
                EditText titulo = findViewById(R.id.formulario_nota_titulo);
                EditText descricao = findViewById(R.id.formulario_nota_descricao);
                Nota nota = new Nota(titulo.getText().toString(), descricao.getText().toString());
                NotaDAO notaDAO = new NotaDAO();
                notaDAO.insere(nota);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
