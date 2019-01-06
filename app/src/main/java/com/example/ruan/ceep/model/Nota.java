package com.example.ruan.ceep.model;

import java.io.Serializable;

/*
* o [ Parcelable ] é uma outra abordagem de serialização que visa uma melhor
* performance em relação ao [ Serializable ].
*   url para o Parcelable: https://developer.android.com/reference/android/os/Parcelable
*
* Considerando apenas a documentação, veja que a implementação possui algumas peculiaridades.
* Caso tenha interesse e queira informações de uma maneira mais prática, dê uma olhada neste
* artigo. => https://medium.com/@lucas_marciano/por-que-usar-o-parcelable-ao-inv%C3%A9s-do-serializable-5f7543a9c7f3
*
* */

public class Nota implements Serializable {

    private final String titulo;
    private final String descricao;

    public Nota(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

}