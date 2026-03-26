package org.example;

import org.example.controller.Database;
import org.example.model.Doctor;

import javax.print.Doc;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Database b = new Database();
        Connection connection = b.connect();

//        Doctor doctor1 = new Doctor("Gabryel Duarte", "0610");
//        Doctor doctor2 = new Doctor("Ana Beatriz", "061020");
//        b.add(doctor1, connection);
//        b.add(doctor2, connection);
//
//        Doctor doctor1 = new Doctor("Gabryel Duarte", "1530");
//        doctor1.setId(1);
//        b.update(doctor1, connection);

        List<Doctor> doctors = b.searchAllDoctors(connection);

        for(int i = 0; i<doctors.size(); i++){
            Doctor m = doctors.get(i);

            System.out.println(m.getId() + ", " + m.getName() + ", " + m.getCrm());
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Informe o ID que deseja excluir: ");
        int id = sc.nextInt();
        b.deleteDoctor(id, connection);

        b.disconnect(connection);
    }
}