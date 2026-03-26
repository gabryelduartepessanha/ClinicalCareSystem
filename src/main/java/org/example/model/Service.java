package org.example.model;

public class Service {
    private Integer id;
    private Integer doctor_id;
    private Integer patient_id;
    private String date;

    public Service(Integer doctor_id, Integer patient_id, String date) {
        this.id = 0;
        this.doctor_id = doctor_id;
        this.patient_id = patient_id;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Integer doctor_id) {
        this.doctor_id = doctor_id;
    }

    public Integer getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Atendimento{" +
                "id=" + id +
                ", medico_id=" + doctor_id +
                ", paciente_id=" + patient_id +
                ", data='" + date + '\'' +
                '}';
    }
}
