package org.example;

import org.example.controller.Database;
import org.example.model.Address;
import org.example.model.Doctor;
import org.example.model.Patient;
import org.example.model.Telephone;

import javax.print.Doc;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Database b = new Database();
        Connection connection = b.connect();
        Scanner sc = new Scanner(System.in);

        Patient p = b.searchPatient("56", connection);

        System.out.println(p.toString());

        b.disconnect(connection);
    }
}