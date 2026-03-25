package org.example.controller;

import org.example.model.*;

import java.sql.Connection;

public class Banco {
    // usuario, senha, url=localhost:3306/vidanova

    private static String url;
    private static String usuario;
    private static String senha;

    public Banco(){
        //conexao banco de dados;
    }

    public static Connection conexao(){
        usuario="root";
        senha="root";
        url="jdbc:mysql://localhost:3306/vidanova";

        Connection connection = null;
    }

    public void add(Medico medico){
        // codigo mysql atraves do java
    }

    public void add(Paciente paciente){
        // codigo mysql atraves do java
    }

    public void add(Endereco endereco){
        // codigo mysql atraves do java
    }

    public void add(Telefone telefone){
        // codigo mysql atraves do java
    }

    public void add(Atendimento atendimento){
        // codigo mysql atraves do java
    }
}
