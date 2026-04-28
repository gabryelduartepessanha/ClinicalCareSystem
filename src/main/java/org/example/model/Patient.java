package org.example.model;

import java.util.ArrayList;

public class Patient {
    private Integer id;
    private String name;
    private String cpf;
    private ArrayList<Telephone> telephones;
    private Address address;
    private ArrayList<Service> services;

    public Patient(){
        this.id = 0;
        this.name = name;
        this.cpf = cpf;
        this.telephones = new ArrayList<>();
        this.address = new Address();
    }

    public Patient(String name, String cpf) {
        this.id = 0;
        this.name = name;
        this.cpf = cpf;
        this.telephones = new ArrayList<>();
        this.address = new Address();
        this.services = new ArrayList<>();
    }

    public Patient(String name, String cpf, String rua, String bairro, int numero) {
        this.id = 0;
        this.name = name;
        this.cpf = cpf;
        this.telephones = new ArrayList<>();
        this.address = new Address(rua, bairro, numero);
        this.services = new ArrayList<>();
    }

    public Patient(String name, String cpf, String rua, String bairro, int numero, ArrayList<Telephone> telephones) {
        this.id = 0;
        this.name = name;
        this.cpf = cpf;
        this.telephones = new ArrayList<>();
        this.address = new Address(rua, bairro, numero);
        this.services = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public ArrayList<Telephone> getTelephones() {
        return telephones;
    }

    public void setTelephones(ArrayList<Telephone> telephones) {
        this.telephones = telephones;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }

    public void addTelephone(String telephone){
        Telephone t = new Telephone(telephone);
        telephones.add(t);
    }

    public void addAdress(String road, String neighborhood, int number){
        this.address = new Address(road, neighborhood, number);
    }

    public void addServices(int doctor_id, int patient_id, String date){
        Service service = new Service(doctor_id, patient_id, date);
        services.add(service);
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nome='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telefones=" + telephones +
                ", endereco=" + address +
                ", atendimentos=" + services +
                '}';
    }
}
