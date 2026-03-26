package org.example.controller;

import org.example.model.*;

import javax.print.Doc;
import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    // usuario, senha, url=localhost:3306/vidanova

    private String url;
    private String user;
    private String password;

    public Database(){

    }

    public Connection connect(){
        user ="root";
        password ="root";
        url="jdbc:mysql://localhost:3306/vidanova";

        Connection connected = null;

        try{
            connected = DriverManager.getConnection(url, user, password);
            System.out.println("Banco de dados conectado com sucesso!");
        }catch (SQLException e){
            System.out.println("Não foi possível conectar o banco de dados com essas informações.");
        }
        return connected;
    }

    public void disconnect(Connection connection) throws SQLException {
        connection.close();
    }

    public void add(Doctor doctor, Connection connection) throws SQLException {
        String sql = "insert into doctor(name, crm) values(?,?)";
        try{
            //prepara a string da variavel "sql" em um codigo sql;
            PreparedStatement stmt = connection.prepareStatement(sql);

            //informar para as interrogações da string que tipo de dado será preenchido;
            stmt.setString(1, doctor.getName());
            stmt.setString(2, doctor.getCrm());

            //vai fazer o ato de enviar as informações e o if vai informar o sucesso, caso contrário, cai no catch;
            int updateInf = stmt.executeUpdate();

            if(updateInf > 0){
                System.out.println("Médico foi cadastrado com sucesso!");
            }

            //obrigatório para evitar consumo desnecessário;
            stmt.close();
            connection.close();
        }catch (SQLException e){
            System.out.println("Aconteceu um erro ao tentar cadastrar um médico no banco de dados.");
        }
    }

    public void update(Doctor doctor, Connection connection){
        String sql = "update doctor set name = ?, crm = ? where id = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, doctor.getName());
            stmt.setString(2, doctor.getCrm());
            stmt.setInt(3, doctor.getId());

            int updateInf = stmt.executeUpdate();
            if(updateInf > 0){
                System.out.println("Atualização executada com sucesso!");
            }else{
                System.out.println("Nenhum médico foi encontrado com o ID informado!");
            }
        }catch (SQLException e){
            System.out.println("Aconteceu um erro ao tentar atualizar um médico no banco de dados.");
        }
    }

    public List<Doctor> searchAllDoctors(Connection connection){
        List<Doctor> doctors = new ArrayList<>();
        String sql = "select id, name, crm from doctor";

        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            //resultado da pesquisa no BD armazenadas nesa variavel;
            ResultSet rs = stmt.executeQuery();


            while(rs.next()){
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("id"));
                doctor.setName(rs.getString("name"));
                doctor.setCrm(rs.getString("crm"));

                doctors.add(doctor);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar os cadastros dos médicos.");
        }
        return doctors;
    }

    public void deleteDoctor(int id, Connection connection){
        String sql = "delete from doctor where id = ?";

        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);

            int affectedLine = stmt.executeUpdate();

            if(affectedLine > 0){
                System.out.println("O médico foi deletado com sucesso!");
            }else{
                System.out.println("Nenhum médico foi identificado com o ID informado.");
            }
            stmt.close();
        }catch (SQLException e){
            System.out.println("Não foi possível deletar o médico pelo ID.");
            e.printStackTrace();
        }

    }

    public void add(Patient patient, Connection connection){
        // codigo mysql atraves do java
    }

    public void add(Address adress, Connection connection){
        // codigo mysql atraves do java
    }

    public void add(Telephone telephone, Connection connection){
        // codigo mysql atraves do java
    }

    public void add(Service service, Connection connection){
        // codigo mysql atraves do java
    }
}
