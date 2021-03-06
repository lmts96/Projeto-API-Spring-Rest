package br.radixeng.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Distancia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private Integer distance;
    private String path;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public Integer getDistance() {return distance;}
    public void setDistance(Integer distance) {this.distance = distance;}
    public String getPath() {return path; }
    public void setPath(String path) { this.path = path;}

}