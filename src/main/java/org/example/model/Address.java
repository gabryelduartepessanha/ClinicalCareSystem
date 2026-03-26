package org.example.model;

public class Address {
    private Integer id;
    private String road;
    private String neighborhood;
    private Integer number;

    public Address() {
        this.id = 0;
        this.road = "";
        this.neighborhood = "";
        this.number = 0;
    }

    public Address(String road, String neighborhood, Integer number) {
        this.id = 0;
        this.road = road;
        this.neighborhood = neighborhood;
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "id=" + id +
                ", rua='" + road + '\'' +
                ", bairro='" + neighborhood + '\'' +
                ", numero=" + number +
                '}';
    }
}
