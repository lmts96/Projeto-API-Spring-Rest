package br.radixeng.model;
import javax.persistence.*;

@Entity
@Table(name = "grafo")
public class Grafo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1)
    private String source;

    @Column(nullable = false, length = 1)
    private String target;

    @Column(nullable = false)
    private int distance;

    @Column(name = "id_group", nullable = false)
    private int idGroup;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getIdGroup() { return idGroup; }

    public void setIdGroup(int idGroup) { this.idGroup = idGroup; }

}