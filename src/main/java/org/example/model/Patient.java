package org.example.model;

import java.util.ArrayList;

public class Paciente {
    private Integer id;
    private String nome;
    private String cpf;
    private ArrayList<Telephone> telephones;
    private Address endereco;
    private ArrayList<Service> services;

    public Paciente(String nome, String cpf) {
        this.id = 0;
        this.nome = nome;
        this.cpf = cpf;
        this.telephones = new ArrayList<>();
        this.endereco = new Address();
        this.services = new ArrayList<>();
    }

    public Paciente(String nome, String cpf, String rua, String bairro, int numero) {
        this.id = 0;
        this.nome = nome;
        this.cpf = cpf;
        this.telephones = new ArrayList<>();
        this.endereco = new Address(rua, bairro, numero);
        this.services = new ArrayList<>();
    }

    public Paciente(String nome, String cpf, String rua, String bairro, int numero, ArrayList<Telephone> telephones) {
        this.id = 0;
        this.nome = nome;
        this.cpf = cpf;
        this.telephones = new ArrayList<>();
        this.endereco = new Address(rua, bairro, numero);
        this.services = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public ArrayList<Telephone> getTelefones() {
        return telephones;
    }

    public void setTelefones(ArrayList<Telephone> telephones) {
        this.telephones = telephones;
    }

    public Address getEndereco() {
        return endereco;
    }

    public void setEndereco(Address endereco) {
        this.endereco = endereco;
    }

    public ArrayList<Service> getAtendimentos() {
        return services;
    }

    public void setAtendimentos(ArrayList<Service> services) {
        this.services = services;
    }

    public void addTelefone(String telefone){
        Telephone t = new Telephone(telefone);
        telephones.add(t);
    }

    public void addEndereco(String rua, String bairro, int numero){
        this.endereco = new Address(rua, bairro, numero);
    }

    public void addAtendimento(int medico_id, int paciente_id, String data){
        Service service = new Service(medico_id, paciente_id, data);
        services.add(service);
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telefones=" + telephones +
                ", endereco=" + endereco +
                ", atendimentos=" + services +
                '}';
    }
}
