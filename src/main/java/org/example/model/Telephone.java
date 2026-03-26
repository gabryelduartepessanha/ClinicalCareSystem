package org.example.model;

public class Telephone {
    private Integer id;
    private String phone_number;

    public Telephone(String phone_number){
        this.phone_number = phone_number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public String toString(){
        return "Telefone{" +
                "id= " + id +
                ", numero= " + phone_number + '\'' +
                '}';
    }
}
