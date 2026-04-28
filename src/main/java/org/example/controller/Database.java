package org.example.controller;

import org.example.model.*;

import javax.print.Doc;
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

    public void addDoctor(Doctor doctor, Connection connection) throws SQLException {
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

    public void updateDoctor(Doctor doctor, Connection connection){
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

    public Doctor searchForCrm(String crm, Connection connection){
        String sql = "select id, nome, crm from doctor where crm = ?";
        Doctor doctor = new Doctor();

        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, crm);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String crm2 = rs.getString("crm");
                doctor.setId(id);
                doctor.setName(name);
                doctor.setCrm(crm2);
            }

            rs.close();
            stmt.close();
        }catch (SQLException e){
            System.out.println("Erro ao pesquisar médico pelo CRM: " + e.getMessage());
        }

        return doctor;
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

    public void addPatient(Patient patient, Address address, ArrayList<Telephone> telephones, Connection connection){
        String sql = "insert into patient(name, cpf) values (?, ?)";

        try{
            //cadastro do paciente
            PreparedStatement stmtPatient = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmtPatient.setString(1, patient.getName());
            stmtPatient.setString(2, patient.getCpf());

            stmtPatient.executeUpdate();

            ResultSet rsPatient = stmtPatient.getGeneratedKeys();
            int patientId = 0;

            if(rsPatient.next()){
                patientId = rsPatient.getInt(1);
            }

            rsPatient.close();

            //cadastrando o endereço usando a chave primaria
            String sqlAddress = "insert into address(patient_id, number, neighborhood, road) values (?, ?, ?, ?)";

            PreparedStatement stmtAddress = connection.prepareStatement(sqlAddress);
            stmtAddress.setInt(1, patientId);
            stmtAddress.setString(4, address.getRoad());
            stmtAddress.setString(3, address.getNeighborhood());
            stmtAddress.setInt(2, address.getNumber());

            stmtAddress.executeUpdate();
            stmtAddress.close();

            //cadastrando os telefones usando chave estrangeira de paciente
            String sqlTelephone = "insert into telephone(patient_id, phone_number) values (?, ?)";


            for (int i = 0; i < telephones.size(); i++) {
                Telephone telephone = telephones.get(i);

                PreparedStatement stmtTelephone = connection.prepareStatement(sqlTelephone);
                stmtTelephone.setInt(1, patientId);
                stmtTelephone.setString(2, telephone.getPhone_number());

                stmtTelephone.executeUpdate();
                stmtTelephone.close();
            }
            connection.close();

            System.out.println("Paciente foi cadastrado com sucesso!");
            }catch (SQLException e){
            System.out.println("Aconteceu um erro ao tentar cadastrar um paciente no banco de dados. - " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Patient searchPatient(String cpf, Connection connection){
        String sql = "select p.id, p.name, p.cpf, e.road, e.neighborhood, e.number, t.phone_number from patient p join address e on p.id = e.patient_id join telephone t on p.id = t.patient_id where cpf = ?";
        Patient p = new Patient();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();


            Address e = new Address();
            Telephone t;
            ArrayList<Telephone> telephones = new ArrayList<>();

            while (rs.next()){
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setCpf(rs.getString("cpf"));

                e.setRoad(rs.getString("road"));
                e.setNeighborhood(rs.getString("neighborhood"));
                e.setNumber(rs.getInt("number"));

                t = new Telephone(rs.getString("phone_number"));
                t.setId(p.getId());
                telephones.add(t);
            }
            rs.close();
            stmt.close();

            p.setId(p.getId());
            p.setTelephones(telephones);
            p.setAddress(e);


        }catch (SQLException e){
            System.out.println("Erro ao pesquisar paciente pelo CPF!");
            e.printStackTrace();
        }
        return p;
    }

    public void addDoctor(Telephone telephone, Connection connection){
        // codigo mysql atraves do java
    }

    public void addDoctor(Service service, Connection connection){
        // codigo mysql atraves do java
    }
}
