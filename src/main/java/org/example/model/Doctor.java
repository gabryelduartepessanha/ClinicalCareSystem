package org.example.model;

import javax.print.Doc;
import java.util.ArrayList;

public class Doctor {
    private Integer id;
    private String name;
    private String crm;
    private ArrayList<Service> services;

    public Doctor(){
        this.id = 0;
        this.name = "";
        this.crm = "";
    }

    public Doctor(String name, String crm) {
        this.id = 0;
        this.name = name;
        this.crm = crm;
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

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public ArrayList<Service> getServices() {
        return services;
    }
    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }

    public void addService(int doctor_id, int patient_id, String date){
        Service service = new Service(doctor_id, patient_id, date);
        services.add(service);
    }
}
