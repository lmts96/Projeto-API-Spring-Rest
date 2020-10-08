package br.radixeng.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Rota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String route;
    private Integer stops;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getRoute() {return route;}
    public void setRoute(String route) {this.route = route;}
    public Integer getStops() {return stops;}
    public void setStops(Integer stops) {this.stops = stops;}

}